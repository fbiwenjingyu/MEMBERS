package com.daboo.user.model;

import java.io.Serializable;


/**
  * @ClassName: FxUserConditions
  * @Description: 用户列表查询条件
  * @author 肖勇
  * @date 2015-8-20
 */
public class MUserConditions implements Serializable{

	private static final long serialVersionUID = 6508134476254600243L;

	/** 用户编号 */
    private String	accountNo;
    
    /** 推荐人编号 */
    private String	recommendNo;
    
    /** 开始时间 */
    private String	startDate;
    
    /** 结束时间 */
    private String	endDate;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getRecommendNo() {
		return recommendNo;
	}

	public void setRecommendNo(String recommendNo) {
		this.recommendNo = recommendNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
