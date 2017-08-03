package com.daboo.finance.service;

import java.util.Map;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.finance.model.AlipayRequest;
import com.daboo.finance.model.AlipayResponse;

/**
 * 
  * @ClassName: AlipayService
  * @Description: 支付宝支付
  * @author 马正正
  * @date 2015年10月26日
 */
@RemoteService
public interface AlipayService {
	/**
	 * 
	  * @Description: 发起支付请求
	  * @param request
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public String request(AlipayRequest request) throws BusinessException;
	
	/**
	 * 
	  * @Description: 异步回调通知
	  * @param requestParams
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public AlipayResponse asynotify(Map requestParams) throws BusinessException;
	
	/**
	 * 
	  * @Description: 同步回调通知
	  * @param requestParams
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public AlipayResponse synnotify(Map requestParams) throws BusinessException;
}
