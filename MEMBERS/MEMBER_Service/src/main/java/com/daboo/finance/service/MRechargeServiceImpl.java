package com.daboo.finance.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.SerialNoUtil;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.enums.RechargeForward;
import com.daboo.finance.enums.RechargeStatus;
import com.daboo.finance.enums.RechargeWay;
import com.daboo.finance.mapper.MRechargeMapper;
import com.daboo.finance.model.AlipayRequest;
import com.daboo.finance.model.AlipayResponse;
import com.daboo.finance.model.MRechargeModel;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.user.service.MUserInfoService;


/**
 * 
  * @ClassName: MRechargeServiceImpl
  * @Description: 充值
  * @author 马正正
  * @date 2015年10月21日
 */
@Service
public class MRechargeServiceImpl implements MRechargeService {
	private Log log=LogFactory.getLog(getClass());
	@Autowired
	private MRechargeMapper mRechargeMapper;
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private MUserInfoService mUserInfoService;
	@Autowired
	private MemberRewardService memberRewardService;
	
	/**
	 * 
	  * @Description: 后台充值
	  * @param recharge
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public void background(MRechargeModel recharge) throws BusinessException {
		try{
			MRechargeModel rc=rechargeReq(recharge);
			
			rechargeRes(rc.getSerialNo(), RechargeStatus.SUCC, null, null);
		} catch(Exception e){
			log.error("后台充值异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("后台充值异常");
			}
		}
	}
	
	/**
	 * 
	  * @Description: 支付宝充值请求
	  * @param recharge
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public String aliReq(MRechargeModel recharge) throws BusinessException {
		String html=null;
		
		try{
			MRechargeModel rc=rechargeReq(recharge);
			
			AlipayRequest request=new AlipayRequest();
			request.setOutTradeNo(rc.getSerialNo());
			request.setSubject("充值");
			request.setBody("充值");
			if(rc.getAccountType()==1){
				request.setTotalFee(rc.getAmount()/100.0+"");
			}else{
				request.setTotalFee(rc.getAmount()+"");
			}
			
			html=alipayService.request(request);
		} catch(Exception e){
			log.error("支付宝充值请求异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("支付宝充值请求异常");
			}
		}
		
		return html;
	}
	
	/**
	 * 
	  * @Description: 支付宝充值响应
	  * @param requestParams
	  * @param syn
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public void aliRes(Map requestParams,boolean syn) throws BusinessException {
		try{
			AlipayResponse response=null;
			if(syn){
				response=alipayService.synnotify(requestParams);
			}else{
				response=alipayService.asynotify(requestParams);
			}
			if(response!=null && response.isSucc()){
				rechargeRes(response.getOutTradeNo(), RechargeStatus.SUCC, 
						response.getBuyerId()+"/"+response.getBuyerEmail(), null);
			}else{
				throw new BusinessException("支付宝充值失败");
			}
		} catch(Exception e){
			log.error("支付宝充值响应异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("支付宝充值响应异常");
			}
		}
	}
	
	/**
	 * 
	  * @Description: 充值请求
	  * @param recharge
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public MRechargeModel rechargeReq(MRechargeModel recharge) throws BusinessException {
		try{
			if(recharge.getAmount()<=0){
				throw new BusinessException("金额不合法");
			}
			if(RechargeWay.get(recharge.getWay())==null){
				throw new BusinessException("充值方式不合法");
			}
			if(RechargeForward.get(recharge.getForward())==null){
				throw new BusinessException("金额方向不合法");
			}
			if(RechargeForward.SUB==RechargeForward.get(recharge.getForward())){
				if(recharge.getMemo()==null || "".equals(recharge.getMemo().trim())){
					throw new BusinessException("金额减少必须填写备注");
				}
			}
			
			recharge.setSerialNo(SerialNoUtil.getSerialNo("recharge","R"));
			recharge.setCreateTime(new Date());
			recharge.setUpdateTime(recharge.getCreateTime());
			recharge.setVersion(0);
			recharge.setStatus(0);
			
			mRechargeMapper.insertModel(recharge);
			
			return recharge;
		} catch(Exception e){
			log.error("充值请求异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("充值请求异常");
			}
		}
	}
	
	/**
	 * 
	  * @Description: 充值响应
	  * @param serialNo		流水号
	  * @param status		充值状态
	  * @param outAccount	外部账户
	  * @param memo
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public void rechargeRes(String serialNo,RechargeStatus status,String outAccount,
			String memo) throws BusinessException {
		try{
			if(status!=RechargeStatus.FAIL && status!=RechargeStatus.SUCC){
				throw new BusinessException("参数状态不合法"); 
			}
			
			MRechargeModel recharge=new MRechargeModel();
			recharge.setSerialNo(serialNo);
			recharge=mRechargeMapper.getObjectByModel(recharge);
			
			if(RechargeStatus.SUCC==RechargeStatus.get(recharge.getStatus())){
				return;
			}else if(RechargeStatus.ING!=RechargeStatus.get(recharge.getStatus())){
				throw new BusinessException("状态变更不合法"); 
			}
			
			MRechargeModel tmp=new MRechargeModel();
			tmp.setId(recharge.getId());
			tmp.setVersion(recharge.getVersion());
			tmp.setStatus(status.getKey());
			tmp.setUpdateTime(new Date());
			tmp.setOutAccount(outAccount);
			if(recharge.getMemo()!=null && !"".equals(recharge.getMemo().trim())){
				tmp.setMemo(memo);
			}
			
			int num=mRechargeMapper.updateModelById(tmp);
			if(num==0){
				throw new BusinessException("乐观锁版本号不一致");
			}
			
			if(RechargeStatus.SUCC==status){
				if(RechargeForward.ADD==RechargeForward.get(recharge.getForward())){
					memberRewardService.userDeposit(recharge.getUserId(), new Long(recharge.getAmount()), recharge.getSerialNo(), recharge.getAccountType(),recharge.getOperator());
				}else if(RechargeForward.SUB==RechargeForward.get(recharge.getForward())){
					memberRewardService.userDeposit(recharge.getUserId(), new Long(0-recharge.getAmount()), recharge.getSerialNo(), recharge.getAccountType(),recharge.getOperator());
				}
			}
		} catch(Exception e){
			log.error("充值回调异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("充值回调异常");
			}
		}
	}
	
	/**
	 * 
	  * @Description: 充值查询
	  * @param po
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public GridDataModel query(PageObject po) throws BusinessException {
		try{
			RowBounds rowBounds=new RowBounds(po.getOffset(), po.getPageSize());
			
			List<Map<String, Object>> rows=mRechargeMapper.findByCons(po.getCondition(), rowBounds);
			int total=mRechargeMapper.findByConsCount(po.getCondition());
			
			return new GridDataModel(rows, total);
		} catch(Exception e){
			log.error("查询充值记录异常",e);
			throw new BusinessException("查询充值记录异常");
		}
	}
}
