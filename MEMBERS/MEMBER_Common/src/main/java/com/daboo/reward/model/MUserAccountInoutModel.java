package com.daboo.reward.model;



import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MUserAccountInout对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_USER_ACCOUNT_INOUT",notColumn={"userIds"})
public class MUserAccountInoutModel implements Serializable {

	/** uid */
	private static final long serialVersionUID = 283362161355020138L;
    /** 主键 */
    private Long	id;
    /** 用户信息 */
    private Integer	userId;
    
    /** 账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分)*/
    private Integer	accountType;
    
    private String userIds;//多个用户用逗号间隔
    /** 账户变动类型   101:直推用户现金奖励;102:管理现金奖励;103:辅导奖励;110:账户充值;111:提现冲正;112:账户转入;120:用户提现;121:账户余额消费;122:账户转出;201:奖励博豆;210:博豆充值;301:奖励推荐积分;310:充积分; */
    private Integer	inoutType;
    /** 变动金额，现金收益 以分为单位 */
    private Long	amount;
    /** 交易流水 */
    private String	tradeNo;
    /** 账户变动描述 */
    private String	inoutDesc;
    /** 创建时间 */
    private Date	createTime;
    
    /** 操作员 */
    private String	operatorNo;
    /** 用户类型 0：会员系统；11：韩流馆 */
    private Integer	userType;
	
	/** 取得主键 */
	public Long getId() {
		return id;
	}
	
	/** 设置主键 */
	public void setId(Long id) {
		this.id = id;
	}
	/** 取得用户信息 */
	public Integer getUserId() {
		return userId;
	}
	
	/** 设置用户信息 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/** 取得账户变动类型   101:直推用户现金奖励;102:管理现金奖励;103:辅导奖励;110:账户充值;111:提现冲正;112:账户转入;120:用户提现;121:账户余额消费;122:账户转出;201:奖励博豆;210:博豆充值;301:奖励推荐积分;310:充积分; */
	public Integer getInoutType() {
		return inoutType;
	}
	
	/** 设置账户变动类型   101:直推用户现金奖励;102:管理现金奖励;103:辅导奖励;110:账户充值;111:提现冲正;112:账户转入;120:用户提现;121:账户余额消费;122:账户转出;201:奖励博豆;210:博豆充值;301:奖励推荐积分;310:充积分; */
	public void setInoutType(Integer inoutType) {
		this.inoutType = inoutType;
	}
	/** 取得变动金额，现金收益 以分为单位 */
	public Long getAmount() {
		return amount;
	}
	
	/** 设置变动金额，现金收益 以分为单位 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	/** 取得交易流水 */
	public String getTradeNo() {
		return tradeNo;
	}
	
	/** 设置交易流水 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/** 取得账户变动描述 */
	public String getInoutDesc() {
		return inoutDesc;
	}
	
	/** 设置账户变动描述 */
	public void setInoutDesc(String inoutDesc) {
		this.inoutDesc = inoutDesc;
	}
	/** 取得创建时间 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 设置创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/** 取得账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分) */
	public Integer getAccountType() {
		return accountType;
	}
	
	/** 设置账账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分) */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public final List<String> getUserIds() {
		if(StringUtils.isEmpty(userIds)){
			return Collections.emptyList();
		}
		return Arrays.asList(userIds.split(","));
	}

	public final void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	 
	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	/** 取得用户类型 0：会员系统；11：韩流馆 */
	public Integer getUserType() {
		return userType;
	}
	
	/** 设置用户类型 0：会员系统；11：韩流馆 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
