package com.daboo.member.model.request;

import com.daboo.healthy.common.request.Request;

public class MemberUserInfoRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8559761172285377636L;
	
	
	private Integer userId;//用户ID


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
