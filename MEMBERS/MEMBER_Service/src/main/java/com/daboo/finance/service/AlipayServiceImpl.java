package com.daboo.finance.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.daboo.core.exception.BusinessException;
import com.daboo.finance.model.AlipayRequest;
import com.daboo.finance.model.AlipayResponse;

/**
 * 
  * @ClassName: AlipayServiceImpl
  * @Description: 支付宝支付
  * @author 马正正
  * @date 2015年10月23日
 */
@Service
public class AlipayServiceImpl implements AlipayService {
	private Log log=LogFactory.getLog(getClass());
	
	/**
	 * 
	  * @Description: 发起支付请求
	  * @param request
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public String request(AlipayRequest request) throws BusinessException {
		String sHtmlText = null;
		
		try{
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", AlipayConfig.service);
	        sParaTemp.put("partner", AlipayConfig.partner);
	        sParaTemp.put("seller_email", AlipayConfig.seller_email);
	        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", request.getPaymentType());
			sParaTemp.put("notify_url", AlipayConfig.notify_url);
			sParaTemp.put("return_url", AlipayConfig.return_url);
			sParaTemp.put("out_trade_no", request.getOutTradeNo());
			sParaTemp.put("subject", request.getSubject());
			sParaTemp.put("total_fee", request.getTotalFee());
			sParaTemp.put("body", request.getBody());
			sParaTemp.put("show_url", AlipayConfig.show_url);
			sParaTemp.put("anti_phishing_key", request.getAntiPhishingKey());
			sParaTemp.put("exter_invoke_ip", request.getExterInvokeIp());
			
			//建立请求
			sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		} catch(Exception e){
			log.error("支付宝请求异常",e);
			throw new BusinessException("支付宝请求异常");
		}
		
		return sHtmlText;
	}
	
	/**
	 * 
	  * @Description: 异步回调通知
	  * @param requestParams
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public AlipayResponse asynotify(Map requestParams) throws BusinessException {
		AlipayResponse response=new AlipayResponse();
		
		try{
			Map<String,String> params = new HashMap<String,String>();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			
			response.setOutTradeNo(params.get("out_trade_no"));
			response.setTradeNo(params.get("trade_no"));
			response.setTradeStatus(params.get("trade_status"));
			response.setBuyerEmail(params.get("buyer_email"));
			response.setBuyerId(params.get("buyer_id"));
			
			if(AlipayNotify.verify(params)){
				if(response.getTradeStatus().equals("TRADE_FINISHED") || 
						response.getTradeStatus().equals("TRADE_SUCCESS")){
					response.setSucc();
				}
			}
		} catch(Exception e){
			log.error("支付宝异步通知异常",e);
			throw new BusinessException("支付宝异步通知异常");
		}
		
		return response;
	}
	
	/**
	 * 
	  * @Description: 同步回调通知
	  * @param requestParams
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public AlipayResponse synnotify(Map requestParams) throws BusinessException {
		AlipayResponse response=new AlipayResponse();
		
		try{
			Map<String,String> params = new HashMap<String,String>();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
//				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			response.setOutTradeNo(params.get("out_trade_no"));
			response.setTradeNo(params.get("trade_no"));
			response.setTradeStatus(params.get("trade_status"));
			response.setBuyerEmail(params.get("buyer_email"));
			response.setBuyerId(params.get("buyer_id"));
			
			log.info("1========="+response.getTradeStatus()+"=========");
			if(AlipayNotify.verify(params)){//验证成功
				log.info("2========="+response.getTradeStatus()+"=========");
				if(response.getTradeStatus().equals("TRADE_FINISHED") || 
						response.getTradeStatus().equals("TRADE_SUCCESS")){
					response.setSucc();
				}
			}
		} catch(Exception e){
			log.error("支付宝同步通知异常",e);
			throw new BusinessException("支付宝同步通知异常");
		}
		
		return response;
	}
}
