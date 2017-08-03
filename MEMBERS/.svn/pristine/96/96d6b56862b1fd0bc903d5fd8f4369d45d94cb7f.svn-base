package com.daboo.finance.service;

import java.util.Map;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;

/**
 * 
  * @ClassName: MDrawService
  * @Description: 提现
  * @author 马正正
  * @date 2015年10月21日
 */
@RemoteService
public interface MDrawService {
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
	public void apply(int userId,int amount,String cardNo,String pwd,String operatorNo) throws BusinessException;
	
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
	public void changeStatus(int drawId,int drawStatus,String drawMemo,String operatorNo) throws BusinessException;
	
	/**
	 * 
	  * @Description: 提现查询
	  * @param po
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public GridDataModel query(PageObject po) throws BusinessException;
	
	/**
	 * 
	  * @Description: 计算提现手续费
	  * @param amount
	  * @return
	  * @author 马正正
	  * @date 2015年11月2日
	 */
	public Map<String, Integer> calFee(int amount) throws BusinessException;
}
