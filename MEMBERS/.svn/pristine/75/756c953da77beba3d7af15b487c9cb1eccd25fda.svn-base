package com.daboo.finance.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.ConfigProperty;
import com.daboo.core.util.MD5Encoder;
import com.daboo.core.util.SerialNoUtil;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.enums.DrawStatus;
import com.daboo.finance.mapper.MDrawMapper;
import com.daboo.finance.model.MDrawModel;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.system.service.SysDictService;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.model.MUserInfoModel;
import com.danga.MemCached.MemCachedClient;
import com.schooner.MemCached.MemcachedItem;

/**
 * 
  * @ClassName: MDrawServiceImpl
  * @Description: 提现
  * @author 马正正
  * @date 2015年10月21日
 */
@Service
public class MDrawServiceImpl implements MDrawService {
	private Log log=LogFactory.getLog(getClass());
	@Autowired
	private MDrawMapper mDrawMapper;
	@Autowired
	private MemCachedClient memCachedClient;
	@Autowired
	private MUserInfoMapper mUserInfoMapper;
	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private MemberRewardService memberRewardService;
	
	/**
	 * 
	  * @Description: 提现申请
	  * @param userId
	  * @param amount
	  * @param cardNo
	  * @param pwd
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public void apply(int userId,int amount,String cardNo,String pwd,String operatorNo) throws BusinessException {
		try{
			log.info("申请提现：userId="+userId+",amount="+amount+",cardNo="+cardNo);
			
			Calendar cal=Calendar.getInstance();
			if(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY){
				 throw new BusinessException("仅支持周一提现（节假日顺延）");
			}
			
			if(StringUtils.isBlank(cardNo)){
				throw new BusinessException("银行卡号为空");
			}
			
			String ckpwd=checkPayPwd(userId, pwd);
			if(!"ok".equals(ckpwd)){//支付密码校验
				throw new BusinessException(ckpwd);
			}
			
			String minthreshold=sysDictService.getDetailValue("提现阀值", "最小阀值");	//来源于数据字典
			String maxthreshold=sysDictService.getDetailValue("提现阀值", "最大阀值");
			String auditthreshold=sysDictService.getDetailValue("提现阀值", "审核阀值");
			if(amount>=Integer.parseInt(minthreshold) && amount<=Integer.parseInt(maxthreshold)){
				MUserInfoModel user=mUserInfoMapper.getObjectById(userId);
				if(StringUtils.isBlank(user.getCardNo()) || StringUtils.isBlank(user.getCardName())||StringUtils.isBlank(user.getSubbranchBank())){
					throw new BusinessException("请先完善银行卡信息");
				}else if(!user.getCardNo().equals(cardNo)){
					throw new BusinessException("银行卡不一致");
				}
				
				String key="memberfx_draw_user_"+userId;
				String drawNum=ConfigProperty.getProperty("draw.day.num");
				MemcachedItem item=memCachedClient.gets(key);
				
				long mils=cal.getTimeInMillis();
				cal.add(Calendar.DATE, 1);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				if(item==null){
					memCachedClient.add(key, 0, new Date(cal.getTimeInMillis()));
					item=memCachedClient.gets(key);
				}
				
				if((Integer)item.getValue()<Integer.parseInt(drawNum)){
					MDrawModel draw=new MDrawModel();
					draw.setOperator(operatorNo);
					draw.setVersion(0);
					draw.setSerialNo(SerialNoUtil.getSerialNo("draw","D"));
					draw.setUserId(userId);
					draw.setMemberNo(user.getAccountNo());
					draw.setCardOwnerName(user.getCardName());
					draw.setBankName(user.getBankName());
					draw.setOpenBankCity(user.getSubbranchBank());
					draw.setCardNo(cardNo);
					Map<String, Integer> cf=calFee(amount);
					draw.setAmount(cf.get("amt"));
					draw.setFee(cf.get("fee"));
					draw.setArrivalAmount(cf.get("ari"));
					draw.setJf(cf.get("fen"));
					draw.setStatus(DrawStatus.AUDIT_WAIT.getKey());
					if(draw.getAmount()<Integer.parseInt(auditthreshold)){
						draw.setStatus(DrawStatus.AUDIT_SUCC.getKey());
					}
					draw.setCreateTime(new Date());
					draw.setUpdateTime(draw.getCreateTime());
					
					memberRewardService.drawCash(draw.getUserId(), new Long(draw.getAmount()), draw.getSerialNo(), 1,operatorNo);
					
					mDrawMapper.insertModel(draw);
					
					if(!memCachedClient.cas(key, (Integer)item.getValue()+1,new Date(cal.getTimeInMillis()), item.getCasUnique())){
						throw new BusinessException("更新提现限制错误");
					}
				}else{
					throw new BusinessException("每天仅限提现"+Integer.parseInt(drawNum)+"次");
				}
			}else{
				throw new BusinessException("提现金额范围为"+Integer.parseInt(minthreshold)+"~"+Integer.parseInt(maxthreshold));
			}
		} catch(Exception e){
			log.error("申请提现异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("申请提现异常");
			}
		}
	}
	
	/**
	 * 
	  * @Description: 提现状态更改
	  * @param drawId
	  * @param drawStatus
	  * @param drawMemo
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public void changeStatus(int drawId,int drawStatus,String drawMemo,String operatorNo) throws BusinessException {
		try{
			if((drawStatus==1 || drawStatus==4) && (drawMemo==null || "".equals(drawMemo.trim()))){
				throw new BusinessException("审核失败或提现失败时备注不能为空");
			}
			
			MDrawModel draw=mDrawMapper.getObjectById(drawId);
			int status=draw.getStatus();
			if(drawStatus==0 
					|| ((drawStatus==1 || drawStatus==2) && status!=0) 
					|| (drawStatus==3 && status!=2 && status!=0) 
					|| ((drawStatus==4 || drawStatus==5) && status!=3 && status!=2 && status!=0) 
					|| (drawStatus==6 && status!=3)){
				throw new BusinessException("提现单状态变更不合法");
			}
			
			MDrawModel tmp=new MDrawModel();
			tmp.setOperator(operatorNo);
			tmp.setId(drawId);
			tmp.setVersion(draw.getVersion());
			tmp.setStatus(drawStatus);
			tmp.setMemo(drawMemo);
			tmp.setUpdateTime(new Date());
			
			if(mDrawMapper.updateModelById(tmp)==0){
				throw new BusinessException("乐观锁版本号不一致");
			}
			
			if(drawStatus==1 || drawStatus==4){
				memberRewardService.drawCash(draw.getUserId(), new Long(draw.getAmount()), draw.getSerialNo(), 2,operatorNo);
			}else if(drawStatus==5){
				if(draw.getJf()>0){
					memberRewardService.drawCash(draw.getUserId(), new Long(draw.getJf()), draw.getSerialNo(), 3,operatorNo);
				}
			}
		} catch(Exception e){
			log.error("更改提现单状态异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("更改提现单状态异常");
			}
		}
	}
	
	/**
	 * 
	  * @Description: 提现查询
	  * @param po
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public GridDataModel query(PageObject po) throws BusinessException {
		try{
			RowBounds rowBounds=new RowBounds(po.getOffset(), po.getPageSize());
			
			List<Map<String, Object>> rows=mDrawMapper.findByCons(po.getCondition(), rowBounds);
			int total=mDrawMapper.findByConsCount(po.getCondition());
			
			return new GridDataModel(rows, total);
		} catch(Exception e){
			log.error("查询提现单异常",e);
			throw new BusinessException("查询提现单异常");
		}
	}
	
	/**
	 * 
	  * @Description: 支付密码校验
	  * @param userId
	  * @param pwd
	  * @return
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public String checkPayPwd(int userId,String pwd){
		MUserInfoModel user=mUserInfoMapper.getObjectById(userId);
		if(user==null){
			return "用户不存在";
		}else if(user.getPayPassword()==null){
			return "支付密码为空";
		}else if(!user.getPayPassword().equals(MD5Encoder.encode(pwd))){
			return "支付密码错误";
		}else{
			return "ok";
		}
	}
	
	/**
	 * 
	  * @Description: 计算提现手续费
	  * @param amount
	  * @return
	  * @author 马正正
	  * @date 2015年11月2日
	 */
	public Map<String, Integer> calFee(int amount) throws BusinessException {
		try{
			String[] rates=ConfigProperty.getProperty("draw.fee").split(";");
			for(String s:rates){
				String[] kv=s.split("=");		//key,value
				int kdouIdx=kv[0].indexOf(",");
				int klen=kv[0].length();
				
				boolean flagl=false;
				String kl=kv[0].substring(1, kdouIdx);
				if("".equals(kl)){
					flagl=true;
				}else{
					if(amount>Integer.parseInt(kl)){
						flagl=true;
					}else if("[".equals(kv[0].substring(0, 1)) && amount==Integer.parseInt(kl)){
						flagl=true;
					}
				}
				
				boolean flagr=false;
				String kr=kv[0].substring(kdouIdx+1, klen-1);
				if("".equals(kr)){
					flagr=true;
				}else{
					if(amount<Integer.parseInt(kr)){
						flagr=true;
					}else if("]".equals(kv[0].substring(klen-1, klen)) && amount==Integer.parseInt(kr)){
						flagr=true;
					}
				}
				
				if(flagl && flagr){
					int vlen=kv[1].length();
					int vdouIdx=kv[1].indexOf(",");
					String vl=kv[1].substring(1,vdouIdx);
					String vr=kv[1].substring(vdouIdx+1,vlen-1);
					
					BigDecimal bd=new BigDecimal(amount);
					BigDecimal bdfen=null;
					BigDecimal bdfee=null;
					BigDecimal bdari=null;
					if(vl.startsWith("r")){
						bdfee=bd.multiply(new BigDecimal(vl.substring(1)));
					}else if(vl.startsWith("f")){
						bdfee=new BigDecimal(vl.substring(1));
					}else{
						throw new BusinessException("提现手续费配置不完整");
					}
					if(vr.startsWith("r")){
						bdfen=bd.multiply(new BigDecimal(vr.substring(1)));
					}else if(vr.startsWith("f")){
						bdfen=new BigDecimal(vr.substring(1));
					}else{
						throw new BusinessException("提现手续费配置不完整");
					}
					
					bdfee=new BigDecimal(bdfee.intValue());
					bdfen=new BigDecimal(bdfen.divide(new BigDecimal(100)).intValue());
					bdari=bd.subtract(bdfee).subtract(bdfen.multiply(new BigDecimal(100)));
					Map<String, Integer> res=new HashMap<String, Integer>();
					res.put("fee", bdfee.intValue());
					res.put("fen", bdfen.intValue());
					res.put("ari", bdari.intValue());
					res.put("amt", bd.intValue());
					
					return res;
				}
			}
			
			throw new BusinessException("提现手续费配置不存在");
		} catch(Exception e){
			log.error("计算提现手续费异常", e);
			throw new BusinessException("计算提现手续费异常");
		}
	}
}
