package com.daboo.reward.model;



import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MUserAccount对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_USER_ACCOUNT",version="version",notColumn={"userIds"})
public class MUserAccountModel implements Serializable {

	/** uid */
	private static final long serialVersionUID = -3309763392394759432L;
    /** 账号ID */
    private Integer	id;
    /** 用户ID */
    private Integer	userId;
    
    private String userIds;//多个用户用,间隔
    
    /** 账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分)*/
    private Integer	accountType;
    /** 账户余额，单位分 */
    private Long	accountAmount;
    /** 账号状态 1 :   正常;2：冻结 */
    private Integer	accountStatus;
    /** 版本信息 */
    private Integer	version;
    /** 开户时间 */
    private Date	createTime;
    /** 修改时间 */
    private Date	updateTime;
	
	
	/** 取得账号ID */
	public Integer getId() {
		return id;
	}
	
	/** 设置账号ID */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得用户ID */
	public Integer getUserId() {
		return userId;
	}
	
	/** 设置用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/** 取得账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分) */
	public Integer getAccountType() {
		return accountType;
	}
	
	/** 设置账账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分) */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	/** 取得账户余额，单位分 */
	public Long getAccountAmount() {
		return accountAmount;
	}
	
	/** 设置账户余额，单位分 */
	public void setAccountAmount(Long accountAmount) {
		this.accountAmount = accountAmount;
	}
	/** 取得账号状态 1 :   正常;2：冻结 */
	public Integer getAccountStatus() {
		return accountStatus;
	}
	
	/** 设置账号状态 1 :   正常;2：冻结 */
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	/** 取得版本信息 */
	public Integer getVersion() {
		return version;
	}
	
	/** 设置版本信息 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/** 取得开户时间 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 设置开户时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/** 取得修改时间 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 设置修改时间 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	/*public final List<String> getUserIds() {
		if(StringUtils.isEmpty(userIds)){
			return Collections.emptyList();
		}
		return Arrays.asList(userIds.split(","));
	}*/

	public final String getUserIds() {
		return userIds;
	}

	public final void setUserIds(String userIds) {
		this.userIds = userIds;
	}


}
