package com.daboo.finance.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
  * @ClassName: AlipayRequest
  * @Description: 支付宝请求
  * @author 马正正
  * @date 2015年10月26日
 */
public class AlipayRequest {
	private static Pattern patNum=Pattern.compile("(([1-9]\\d{0,7})(\\.\\d{1,2})?)|(0\\.[1-9])|(0\\.0[1-9])|(0\\.[1-9]\\d)|(100000000(\\.0{1,2})?)");
	private static Pattern patInt=Pattern.compile("[1-9]\\d*");
	
	/**
	 * 商户网站唯一订单号
	 */
	private String outTradeNo;
	/**
	 * 商品名称
	 */
	private String subject;
	/**
	 * 商品描述
	 */
	private String body;
	/**
	 * 商品单价
	 */
	private String price;
	/**
	 * 购买数量
	 */
	private String quantity;
	/**
	 * 交易金额
	 */
	private String totalFee;
	/**
	 * 公用回传参数
	 */
	private String extraCommonParam;
	/**
	 * 支付类型
	 */
	private String paymentType="1";
	/**
	 * 防钓鱼时间戳
	 */
	private String antiPhishingKey;
	/**
	 * 客户端IP
	 */
	private String exterInvokeIp;

	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		if(price!=null){
			Matcher mat=patNum.matcher(price);
			if(!mat.matches()){
				throw new RuntimeException("商品单价不合法");
			}
			price=format(price);
		}
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		if(quantity!=null){
			Matcher mat=patInt.matcher(quantity);
			if(mat.matches()){
				throw new RuntimeException("购买数量不合法");
			}
		}
		this.quantity = quantity;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		if(totalFee!=null){
			Matcher mat=patNum.matcher(totalFee);
			if(!mat.matches()){
				throw new RuntimeException("交易金额不合法");
			}
			totalFee=format(totalFee);
		}
		this.totalFee = totalFee;
	}

	public String getExtraCommonParam() {
		return extraCommonParam;
	}

	public void setExtraCommonParam(String extraCommonParam) {
		this.extraCommonParam = extraCommonParam;
	}
	
	public String getPaymentType() {
		return paymentType;
	}

	public String getAntiPhishingKey() {
		return antiPhishingKey;
	}

	public void setAntiPhishingKey(String antiPhishingKey) {
		this.antiPhishingKey = antiPhishingKey;
	}

	public String getExterInvokeIp() {
		return exterInvokeIp;
	}

	public void setExterInvokeIp(String exterInvokeIp) {
		this.exterInvokeIp = exterInvokeIp;
	}

	private String format(String str){
		int index=str.indexOf(".");
		if(index<0){
			return str+".00";
		}else if((index+3)>str.length()){
			return str+"0";
		}else{
			return str;
		}
	}
}
