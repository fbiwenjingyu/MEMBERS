package com.daboo.member.model.request;

import com.daboo.healthy.common.request.Request;

public class CheckMemberRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -683164163005962258L;
	
	
	private String accountNo;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
