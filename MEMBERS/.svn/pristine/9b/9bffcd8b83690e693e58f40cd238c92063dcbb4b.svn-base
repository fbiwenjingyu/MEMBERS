package com.daboo.finance.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MRecharge对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_RECHARGE",version="version")
public class MRechargeModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /** 递增主键 */
    private Integer	id;
    /** 版本号 */
    private Integer	version;
    /** 流水号 */
    private String	serialNo;
    /** 用户ID **/
    private Integer userId;
    /** 会员编号 */
    private String	memberNo;
    /** 金额 */
    private Integer amount;
    /** 账户类型 */
    private Integer	accountType;
    /** 外部账户 */
    private String outAccount;
    /** 充值方式：0后台充值，1支付宝充值，2微信充值 */
    private Integer	way;
    /** 金额方向：0新增，1减少 */
    private Integer	forward;
    /** 充值状态 */
    private Integer status;
    /** 备注 */
    private String	memo;
    /** 创建时间 */
    private Date	createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 操作员*/
    private String operator;
	
	
	/** 取得递增主键 */
	public Integer getId() {
		return id;
	}
	
	/** 设置递增主键 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得会员编号 */
	public String getMemberNo() {
		return memberNo;
	}
	
	/** 设置会员编号 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getOutAccount() {
		return outAccount;
	}

	public void setOutAccount(String outAccount) {
		this.outAccount = outAccount;
	}

	/** 取得账户类型 */
	public Integer getAccountType() {
		return accountType;
	}
	
	/** 设置账户类型 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	/** 取得充值方式：0后台充值，1支付宝充值，2微信充值 */
	public Integer getWay() {
		return way;
	}
	
	/** 设置充值方式：0后台充值，1支付宝充值，2微信充值 */
	public void setWay(Integer way) {
		this.way = way;
	}
	/** 取得金额方向：0新增，1减少 */
	public Integer getForward() {
		return forward;
	}
	
	/** 设置金额方向：0新增，1减少 */
	public void setForward(Integer forward) {
		this.forward = forward;
	}
	/** 取得备注 */
	public String getMemo() {
		return memo;
	}
	
	/** 设置备注 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/** 取得创建时间 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 设置创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}


}
