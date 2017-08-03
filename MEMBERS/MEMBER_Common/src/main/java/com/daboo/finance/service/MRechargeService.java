package com.daboo.finance.service;

import java.util.Map;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.enums.RechargeStatus;
import com.daboo.finance.model.AlipayResponse;
import com.daboo.finance.model.MRechargeModel;

/**
 * 
  * @ClassName: MRechargeService
  * @Description: 充值
  * @author 马正正
  * @date 2015年10月21日
 */
@RemoteService
public interface MRechargeService {
	/**
	 * 
	  * @Description: 后台充值
	  * @param recharge
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public void background(MRechargeModel recharge) throws BusinessException;
	
	/**
	 * 
	  * @Description: 支付宝充值请求
	  * @param recharge
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public String aliReq(MRechargeModel recharge) throws BusinessException;
	
	/**
	 * 
	  * @Description: 支付宝充值响应
	  * @param requestParams
	  * @param syn
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public void aliRes(Map requestParams,boolean syn) throws BusinessException;
	
	/**
	 * 
	  * @Description: 充值请求
	  * @param recharge
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public MRechargeModel rechargeReq(MRechargeModel recharge) throws BusinessException;
	
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
	public void rechargeRes(String serialNo,RechargeStatus status,String outAccount,
			String memo) throws BusinessException;
	
	/**
	 * 
	  * @Description: 充值查询
	  * @param po
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public GridDataModel query(PageObject po) throws BusinessException;
}
