package com.daboo.reward.model;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MDayPointDetail对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_DAY_POINT_DETAIL",version="version",notColumn={"userIds","copperCardNum","silverCardNum","nums","level","orderByClause","accountNo","userName","allReward","recommendId"})
public class MDayPointDetailModel implements Serializable {

	/** uid */
	private static final long serialVersionUID = -5840252119005117231L;
	/** 编号 */
    private Integer	id;
    /** 统计日期 */
    private Date	collectDate;
    /** 用户ID */
    private Integer	userId;
    
	private List<String> userIds;//多个用户
	/** 直推奖励 */
    private BigDecimal	rewardAmount;
    /** 购物积分（博豆） */
    private Integer	beansNum;
    /** 推广积分 */
    private BigDecimal	pointAmount;
    /** 管理奖励 */
    private BigDecimal	manageAmount;
    /** 辅导奖励 */
    private BigDecimal	coachAmount;
    
    /** 状态   1：汇总日积分；2：计算出会员直推的会员类型；3：计算管理奖励；4：计算辅导奖 */
    private Integer	status;
    /** 乐观锁 */
    private Integer	version;
    
    /** 直推会员铜卡数量 */
    private Integer	copperCardNum;
    /** 直推会员银卡梳理 */
    private Integer	silverCardNum;
   	
    private Integer nums;//统计日期未赠送博豆的数量
    
    /** 会员级别(0 普通 1 铜卡 2银卡 3金卡) */
    private Integer	level;
    
    private String orderByClause;    
    /** 账户编号  */
    private String accountNo;
    /** 用户姓名   */
    private String userName;
    /** 总的奖励（实发） */
    private BigDecimal allReward;
    /** 推荐人 */
    private Integer	recommendId;
    
    /**团队业绩*/
    private Integer teamMoney;
    /**个人业绩*/
    private Integer myMoney;
    
    
	public BigDecimal getAllReward() {
		return allReward;
	}

	public void setAllReward(BigDecimal allReward) {
		this.allReward = allReward;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	/** 设置统计日期*/
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
	/** 取得推广积分 */
	public BigDecimal getPointAmount() {
		return pointAmount;
	}
	
	/** 设置推广积分 */
	public void setPointAmount(BigDecimal pointAmount) {
		this.pointAmount = pointAmount;
	}
	/** 取得直推会员铜卡数量 */
	public Integer getCopperCardNum() {
		return copperCardNum;
	}
	
	/** 设置直推会员铜卡数量 */
	public void setCopperCardNum(Integer copperCardNum) {
		this.copperCardNum = copperCardNum;
	}
	/** 取得直推会员银卡梳理 */
	public Integer getSilverCardNum() {
		return silverCardNum;
	}
	
	/** 设置直推会员银卡梳理 */
	public void setSilverCardNum(Integer silverCardNum) {
		this.silverCardNum = silverCardNum;
	}
	/** 取得乐观锁 */
	public Integer getVersion() {
		return version;
	}
	
	/** 设置乐观锁 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/** 取得直推奖励 */
	public BigDecimal getRewardAmount() {
		return rewardAmount;
	}
	
	/** 设置直推奖励 */
	public void setRewardAmount(BigDecimal rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public final List<String> getUserIds() {
		return userIds;
	}

	public final void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	/** 取得状态   1：汇总日积分；2：计算出会员直推的会员类型；3：计算管理奖励；4：计算辅导奖 */
	public Integer getStatus() {
		return status;
	}
	
	/** 设置状态   1：汇总日积分；2：计算出会员直推的会员类型；3：计算管理奖励；4：计算辅导奖*/
	public void setStatus(Integer status) {
		this.status = status;
	}

	public final Integer getNums() {
		return nums;
	}

	public final void setNums(Integer nums) {
		this.nums = nums;
	}

	
	public final Integer getLevel() {
		return level;
	}

	public final void setLevel(Integer level) {
		this.level = level;
	}
	
	/** 取得管理奖励 */
	public BigDecimal getManageAmount() {
		return manageAmount;
	}
	
	/** 设置管理奖励 */
	public void setManageAmount(BigDecimal manageAmount) {
		this.manageAmount = manageAmount;
	}
	/** 取得辅导奖励 */
	public BigDecimal getCoachAmount() {
		return coachAmount;
	}
	
	/** 设置辅导奖励 */
	public void setCoachAmount(BigDecimal coachAmount) {
		this.coachAmount = coachAmount;
	}
	
	/** 取得购物积分（博豆） */
	public Integer getBeansNum() {
		return beansNum;
	}
	
	/** 设置购物积分（博豆） */
	public void setBeansNum(Integer beansNum) {
		this.beansNum = beansNum;
	}
	
	public final String getOrderByClause() {
		return orderByClause;
	}

	public final void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public Integer getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}
	
	public Integer getTeamMoney() {
		return teamMoney;
	}

	public void setTeamMoney(Integer teamMoney) {
		this.teamMoney = teamMoney;
	}

	public Integer getMyMoney() {
		return myMoney;
	}

	public void setMyMoney(Integer myMoney) {
		this.myMoney = myMoney;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("id=").append(id).append(";");
		sb.append("collectDate=").append(collectDate).append(";");
		sb.append("userId=").append(userId).append(";");
		sb.append("userIds=").append(userIds).append(";");
		sb.append("rewardAmount=").append(rewardAmount).append(";");
		sb.append("beansNum=").append(beansNum).append(";");
		sb.append("pointAmount=").append(pointAmount).append(";");
		sb.append("manageAmount=").append(manageAmount).append(";");
		sb.append("coachAmount=").append(coachAmount).append(";");
		sb.append("status=").append(status).append(";");
		sb.append("version=").append(version).append(";");
		sb.append("copperCardNum=").append(copperCardNum).append(";");
		sb.append("silverCardNum=").append(silverCardNum).append(";");
		sb.append("nums=").append(nums).append(";");
		sb.append("level=").append(level).append(";");
		sb.append("orderByClause=").append(orderByClause).append(";");
		sb.append("accountNo=").append(accountNo).append(";");
		sb.append("userName=").append(userName).append(";");
		sb.append("allReward=").append(allReward).append(";");
		sb.append("recommendId=").append(recommendId).append(";");
		sb.append("myMoney=").append(myMoney).append(";");
		sb.append("teamMoney=").append(teamMoney).append(";");
		return sb.toString();
	}
}
