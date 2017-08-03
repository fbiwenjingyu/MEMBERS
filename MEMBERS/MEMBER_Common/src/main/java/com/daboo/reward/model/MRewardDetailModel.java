package com.daboo.reward.model;



import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MRewardDetail对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_REWARD_DETAIL",notColumn={"accountNo"})
public class MRewardDetailModel implements Serializable {

	
	/** uid */
	private static final long serialVersionUID = 8898972731326851400L;
    /** 编号 */
    private Integer	id;
    /** 统计日期 */
    private Date	collectDate;
    /** 用户ID */
    private Integer	userId;
    /** 奖励来源对应的用户ID */
    private Integer	fromUserId;
    /** 奖励类型 */
    private Integer	rewardType;
    /** 奖励金额 */
    private BigDecimal	rewardAmount;
    /** 乐观锁 */
    private Integer	version;
    
    /** 用户编号  */
    private String accountNo;
    
    
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	/** 取得编号 */
	public Integer getId() {
		return id;
	}
	
	/** 设置编号 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得统计日期 */
	public Date getCollectDate() {
		return collectDate;
	}
	
	/** 设置统计日期 */
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
	/** 取得用户ID */
	public Integer getUserId() {
		return userId;
	}
	
	/** 设置用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/** 取得奖励来源对应的用户ID */
	public Integer getFromUserId() {
		return fromUserId;
	}
	
	/** 设置奖励来源对应的用户ID */
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	/** 取得奖励类型 */
	public Integer getRewardType() {
		return rewardType;
	}
	
	/** 设置奖励类型 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	/** 取得奖励金额 */
	public BigDecimal getRewardAmount() {
		return rewardAmount;
	}
	
	/** 设置奖励金额 */
	public void setRewardAmount(BigDecimal rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	/** 取得乐观锁 */
	public Integer getVersion() {
		return version;
	}
	
	/** 设置乐观锁 */
	public void setVersion(Integer version) {
		this.version = version;
	}


}
