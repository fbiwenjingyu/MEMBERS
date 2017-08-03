package com.daboo.member.model.response;

import java.math.BigDecimal;

import com.daboo.healthy.common.response.Response;

public class MemberUserInfoResponse extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8097746036672257345L;
	
	private Integer userId;//用户ID
	
	private BigDecimal pointsBalance;//积分余额

	private BigDecimal cashBalance;//现金余额
	
	private Integer isFreeze; //冻结标志 1:冻结;2:正常

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getPointsBalance() {
		return pointsBalance;
	}

	public void setPointsBalance(BigDecimal pointsBalance) {
		this.pointsBalance = pointsBalance;
	}

	public BigDecimal getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(BigDecimal cashBalance) {
		this.cashBalance = cashBalance;
	}

	public Integer getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Integer isFreeze) {
		this.isFreeze = isFreeze;
	}
	
	
}
