package com.daboo.finance.model;

/**
 * 
  * @ClassName: AlipayResponse
  * @Description: 支付宝响应
  * @author 马正正
  * @date 2015年10月26日
 */
public class AlipayResponse {
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
	/**
	 * 支付宝交易号
	 */
	private String tradeNo;
	/**
	 * 交易状态
	 */
	private String tradeStatus;
	/**
	 * 买家支付宝账号
	 */
	private String buyerEmail;
	/**
	 * 买家支付宝账户号
	 */
	private String buyerId;
	/**
	 * 回执
	 */
	private String answer="fail";
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getAnswer() {
		return answer;
	}
	
	public void setSucc(){
		answer="success";
	}
	
	public boolean isSucc(){
		return "success".equals(answer);
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
}
