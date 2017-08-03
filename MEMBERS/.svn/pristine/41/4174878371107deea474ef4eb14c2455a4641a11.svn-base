package com.daboo.finance.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.MD5Encoder;
import com.daboo.core.util.SerialNoUtil;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.mapper.MCurrencyConvertMapper;
import com.daboo.finance.model.MCurrencyConvertModel;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.model.MUserInfoModel;

@Service
public class MCurrencyConvertServiceImpl implements MCurrencyConvertService{
	
	private Logger log=LoggerFactory.getLogger(MCurrencyConvertServiceImpl.class);
	
	@Autowired
	private MCurrencyConvertMapper convertMapper;
	@Autowired
	private MUserInfoMapper mUserInfoMapper;
	@Autowired
	private MemberRewardService memberRewardService;
	
	/**
	 * 分页查询货币转换记录
	 * @FunName findListByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月2日
	 */
	public GridDataModel findListByConditions(PageObject po) {

		RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
		List<MCurrencyConvertModel> rows=convertMapper.findModelsByCondition(po.getCondition(),rb);
		int count=convertMapper.countByCondition(po.getCondition());
		
		return new GridDataModel(rows, count);
	}
	
	/**
	 * 货币转换[博豆转积分]
	 * @FunName doConvert
	 * @param userId 用户ID
	 * @param accountNo 用户账号
	 * @param convertType 转换类型
	 * @param amount 转换数值
	 * @param password 密码
	 * @return boolean
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年11月3日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public boolean doConvert(Integer userId, String accountNo,String convertType, int amount,
			String password,String operatorNo) throws BusinessException {
		
		String ckpwd=checkPayPwd(userId, password);
		if(!"ok".equals(ckpwd)){//支付密码校验
			throw new BusinessException(ckpwd);
		}
		
		final String tradeNo=SerialNoUtil.getSerialNo("convert","CC");
		
		MCurrencyConvertModel cc=new MCurrencyConvertModel();
		cc.setSerialNo(tradeNo);
		cc.setUserId(userId);
		cc.setAccountNo(accountNo);
		cc.setConvertType(convertType);
		cc.setConvertAmount(amount);
		cc.setGetAmount(amount);
		cc.setFee("1");
		cc.setState("1");
		cc.setCreateTime(new Date());
		
		try {
			//调用账号转换接口
			if(convertType.equals("1")){//博豆转积分
				memberRewardService.userAccountTransfers(userId, (long) amount, tradeNo , 2,operatorNo);
			}else if(convertType.equals("2")){//现金转博豆
				memberRewardService.userAccountTransfers(userId, (long) amount*100, tradeNo , 3,operatorNo);
			}
			
			//插入转换记录
			convertMapper.insertModel(cc);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("货币转换异常",e);
			if(e instanceof BusinessException){
				throw new BusinessException(e.getMessage());
			}else{
				throw new BusinessException("货币转换异常");
			}
		}
	}

	/**
	 * 检查用户密码正确性
	 * @FunName checkPayPwd
	 * @param userId
	 * @param pwd
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月3日
	 */
	public String checkPayPwd(int userId,String pwd){
		MUserInfoModel user=mUserInfoMapper.getObjectById(userId);
		if(user==null){
			return "用户不存在";
		}else if(user.getPayPassword()==null){
			return "密码为空";
		}else if(!user.getPayPassword().equals(MD5Encoder.encode(pwd))){
			return "密码错误";
		}else{
			return "ok";
		}
	}
	
}
