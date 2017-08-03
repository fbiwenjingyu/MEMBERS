package com.daboo.member.model.response;

import com.daboo.healthy.common.response.Response;

public class CheckMemberResponse extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8097746036672257345L;
	
	private Integer flag;

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
