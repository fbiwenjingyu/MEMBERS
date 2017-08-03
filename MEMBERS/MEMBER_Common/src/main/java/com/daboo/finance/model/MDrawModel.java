package com.daboo.finance.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MDraw对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_DRAW",version="version")
public class MDrawModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /** 递增主键 */
    private Integer	id;
    /** 版本号 */
    private Integer	version;
    /** 流水号 */
    private String	serialNo;
    /** 用户ID */
    private Integer	userId;
    /** 会员编号 */
    private String memberNo;
    /** 持卡人姓名 */
    private String	cardOwnerName;
    /** 所属行名称 */
    private String	bankName;
    /** 开户行城市 */
    private String	openBankCity;
    /** 卡号 */
    private String	cardNo;
    /** 提现金额 */
    private Integer	amount;
    /** 提现手续费 */
    private Integer	fee;
    /** 到账金额 */
    private Integer	arrivalAmount;
    /** 积分 */
    private Integer jf;
    /** 提现状态：0待审核 ，1审核不通过， 2审核成功或待提现， 3已提现， 4提现失败， 5提现成功 */
    private Integer	status;
    /** 提现备注 */
    private String	memo;
    /** 创建时间 */
    private Date	createTime;
    /** 更新时间 */
    private Date	updateTime;
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
	/** 取得版本号 */
	public Integer getVersion() {
		return version;
	}
	
	/** 设置版本号 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/** 取得流水号 */
	public String getSerialNo() {
		return serialNo;
	}
	
	/** 设置流水号 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/** 取得用户ID */
	public Integer getUserId() {
		return userId;
	}
	
	/** 设置用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/** 取得持卡人姓名 */
	public String getCardOwnerName() {
		return cardOwnerName;
	}
	
	/** 设置持卡人姓名 */
	public void setCardOwnerName(String cardOwnerName) {
		this.cardOwnerName = cardOwnerName;
	}
	/** 取得所属行名称 */
	public String getBankName() {
		return bankName;
	}
	
	/** 设置所属行名称 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/** 取得开户行城市 */
	public String getOpenBankCity() {
		return openBankCity;
	}
	
	/** 设置开户行城市 */
	public void setOpenBankCity(String openBankCity) {
		this.openBankCity = openBankCity;
	}
	/** 取得卡号 */
	public String getCardNo() {
		return cardNo;
	}
	
	/** 设置卡号 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/** 取得提现金额 */
	public Integer getAmount() {
		return amount;
	}
	
	/** 设置提现金额 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/** 取得提现手续费 */
	public Integer getFee() {
		return fee;
	}
	
	/** 设置提现手续费 */
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	/** 取得到账金额 */
	public Integer getArrivalAmount() {
		return arrivalAmount;
	}
	
	/** 设置到账金额 */
	public void setArrivalAmount(Integer arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}
	/** 取得提现状态：0待审核 ，1审核不通过， 2审核成功或待提现， 3已提现， 4提现失败， 5提现成功 */
	public Integer getStatus() {
		return status;
	}
	
	/** 设置提现状态：0待审核 ，1审核不通过， 2审核成功或待提现， 3已提现， 4提现失败， 5提现成功 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/** 取得提现备注 */
	public String getMemo() {
		return memo;
	}
	
	/** 设置提现备注 */
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
	/** 取得更新时间 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 设置更新时间 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Integer getJf() {
		return jf;
	}

	public void setJf(Integer jf) {
		this.jf = jf;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
