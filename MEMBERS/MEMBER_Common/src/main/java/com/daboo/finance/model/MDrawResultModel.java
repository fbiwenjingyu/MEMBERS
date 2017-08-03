package com.daboo.finance.model;

import java.io.Serializable;

public class MDrawResultModel implements Serializable {

	/**
	  * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	  */
	
	private static final long serialVersionUID = 1L;
	//返回的状态码（是否成功0是成功，1是失败 -1是未知）
    private String code;
    //快钱的订单号
    private String dealId;
    //快钱返回的错误代码
    private String failureCause;
    //.net封装的错误msg
    private String alertMsg;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getFailureCause() {
		return failureCause;
	}
	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
    
    
}
