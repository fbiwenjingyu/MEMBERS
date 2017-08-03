package com.daboo.user.model;


import java.util.Date;
import java.io.Serializable;

import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MUserInfo对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_USER_INFO", notColumn={"userIds","types","parentNo", "recommendNo", "balance","recommendName","shoppingScore","generalizeScore"})
public class MUserInfoModel implements Serializable {
	
	public MUserInfoModel(){
	}
	
	public MUserInfoModel(MUserInfoModel master){
		this.phone = master.phone;
		this.password = master.password;
		this.payPassword = master.payPassword;
		this.realName = master.realName;
		this.type = 1;//正常
		this.level = 0;
		this.registerMoney = master.registerMoney;
		this.agentId = master.agentId;
		this.recommendId = master.recommendId;
		this.province = master.province;
		this.city = master.city;
		this.area = master.area;
		this.fullAddress = master.fullAddress;
		this.bankName = master.bankName;
		this.subbranchBank = master.subbranchBank;
		this.cardName = master.cardName;
		this.cardNo = master.cardNo;
		this.createTime = new Date();
		this.activateTime = new Date();
		this.updateTime = new Date();
		this.isDeleted = 0;
		this.copperCardNum = 0;
		this.silverCardNum = 0;
		this.awardFlag = 0;
		this.totalNum = master.totalNum;
		/*this.accountNo = master.accountNo;
		this.masterUserId = master.masterUserId;*/
	}
	
	/** uid */
	private static final long serialVersionUID = -8383319895737093656L;
    /** 主键，用户ID */
    private Integer	id;
    

    private String	userIds;
    
    /** 登录账号 */
    private String	accountNo;
    /** 联系电话 */
    private String	phone;
    /** 登录密码 */
    private String	password;
    /** 支付密码 */
    private String	payPassword;
    /** 真实姓名 */
    private String	realName;
    /** 会员类型(-1冻结的会员 0未激活 1激活的普通 2 冻结的服务中心  3激活的服务中心) */
    private Integer	type;
    //有效类型
    private String	types;
    
    /** 会员级别(0 普通 1 铜卡 2银卡 3金卡) */
    private Integer	level;
    /**注册金额(元)**/
    private Integer registerMoney;
    /** 代理人 */
    private Integer	agentId;
    /** 推荐人 */
    private Integer	recommendId;
    /** 省份 */
    private String	province;
    /** 市 */
    private String	city;
    /** 区域 */
    private String	area;
    /** 详细地址 */
    private String	fullAddress;
    /** 开户行 */
    private String	bankName;
    /** 开户支行 */
    private String	subbranchBank;
    /** 持卡人姓名 */
    private String	cardName;
    /** 银行卡号 */
    private String	cardNo;
    /** 创建时间 */
    private Date createTime;
    /**激活时间*/
    private Date activateTime;
    /** 更新时间 */
    private Date	updateTime;
    /** 0：未删除  1：已删除 */
    private Integer	isDeleted;
    /** 直推会员铜卡数量 */
    private Integer	copperCardNum;
    /** 直推会员银卡梳理 */
    private Integer	silverCardNum;
    /**激活方式（1空单 2实单）*/
    private Integer isOpend;
    
    /**团队业绩*/
    private Integer teamMoney;
    /**个人业绩*/
    private Integer myMoney;
    
    /**冗余字段*/
    /**服务中心编号*/
    private String parentNo;
    /**推荐人编号*/
    private String recommendNo;
    /**推荐人名字*/
    private String recommendName;
    
    /**余额*/
    private Integer balance;
   /**购物积分*/
    private Integer shoppingScore;
    /**推广积分*/
    private Integer generalizeScore;
    
    /** 奖励处理标记 */
    private Integer	awardFlag;
    /** 激活人 */
    private String	activateBy;
	/** 用户总数 */
    private Integer	totalNum;
    
    /** 主账号 */
    private Integer	masterUserId;
    
	/** 取得主键，用户ID */
	public Integer getId() {
		return id;
	}
	
	/** 设置主键，用户ID */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得登录账号 */
	public String getAccountNo() {
		return accountNo;
	}
	
	/** 设置登录账号 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/** 取得联系电话 */
	public String getPhone() {
		return phone;
	}
	
	/** 设置联系电话 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/** 取得登录密码 */
	public String getPassword() {
		return password;
	}
	
	/** 设置登录密码 */
	public void setPassword(String password) {
		this.password = password;
	}
	/** 取得支付密码 */
	public String getPayPassword() {
		return payPassword;
	}
	
	/** 设置支付密码 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	/** 取得真实姓名 */
	public String getRealName() {
		return realName;
	}
	
	/** 设置真实姓名 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/** 取得会员类型(-1冻结的会员 0未激活 1激活的普通 2 冻结的服务中心  3激活的服务中心) */
	public Integer getType() {
		return type;
	}
	
	/** 设置会员类型(-1冻结的会员 0未激活 1激活的普通 2 冻结的服务中心  3激活的服务中心) */
	public void setType(Integer type) {
		this.type = type;
	}
	/** 取得会员级别(0 普通 1 铜卡 2银卡 3金卡) */
	public Integer getLevel() {
		return level;
	}
	
	/** 设置会员级别(0 普通 1 铜卡 2银卡 3金卡) */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	/** 取得代理人 */
	public Integer getAgentId() {
		return agentId;
	}
	
	/** 设置代理人 */
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	/** 取得推荐人 */
	public Integer getRecommendId() {
		return recommendId;
	}
	
	/** 设置推荐人 */
	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}
	/** 取得省份 */
	public String getProvince() {
		return province;
	}
	
	/** 设置省份 */
	public void setProvince(String province) {
		this.province = province;
	}
	/** 取得市 */
	public String getCity() {
		return city;
	}
	
	/** 设置市 */
	public void setCity(String city) {
		this.city = city;
	}
	/** 取得区域 */
	public String getArea() {
		return area;
	}
	
	/** 设置区域 */
	public void setArea(String area) {
		this.area = area;
	}
	/** 取得详细地址 */
	public String getFullAddress() {
		return fullAddress;
	}
	
	/** 设置详细地址 */
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	/** 取得开户行 */
	public String getBankName() {
		return bankName;
	}
	
	/** 设置开户行 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/** 取得开户支行 */
	public String getSubbranchBank() {
		return subbranchBank;
	}
	
	/** 设置开户支行 */
	public void setSubbranchBank(String subbranchBank) {
		this.subbranchBank = subbranchBank;
	}
	/** 取得持卡人姓名 */
	public String getCardName() {
		return cardName;
	}
	
	/** 设置持卡人姓名 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	/** 取得银行卡号 */
	public String getCardNo() {
		return cardNo;
	}
	
	/** 设置银行卡号 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	/** 取得0：未删除  1：已删除 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	
	/** 设置0：未删除  1：已删除 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getRegisterMoney() {
		return registerMoney;
	}

	public void setRegisterMoney(Integer registerMoney) {
		this.registerMoney = registerMoney;
	}

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}

	public String getRecommendNo() {
		return recommendNo;
	}

	public void setRecommendNo(String recommendNo) {
		this.recommendNo = recommendNo;
	}

	public Date getActivateTime() {
		return activateTime;
	}

	public void setActivateTime(Date activateTime) {
		this.activateTime = activateTime;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getShoppingScore() {
		return shoppingScore;
	}

	public void setShoppingScore(Integer shoppingScore) {
		this.shoppingScore = shoppingScore;
	}

	public Integer getGeneralizeScore() {
		return generalizeScore;
	}

	public void setGeneralizeScore(Integer generalizeScore) {
		this.generalizeScore = generalizeScore;
	}

	public final Integer getCopperCardNum() {
		return copperCardNum;
	}

	public final void setCopperCardNum(Integer copperCardNum) {
		this.copperCardNum = copperCardNum;
	}

	public final Integer getSilverCardNum() {
		return silverCardNum;
	}

	public final void setSilverCardNum(Integer silverCardNum) {
		this.silverCardNum = silverCardNum;
	}

	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public Integer getAwardFlag() {
		return awardFlag;
	}

	public void setAwardFlag(Integer awardFlag) {
		this.awardFlag = awardFlag;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getActivateBy() {
		return activateBy;
	}

	public void setActivateBy(String activateBy) {
		this.activateBy = activateBy;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Integer getIsOpend() {
		return isOpend;
	}

	public void setIsOpend(Integer isOpend) {
		this.isOpend = isOpend;
	}
	
	/** 取得用户总数 */
	public Integer getTotalNum() {
		return totalNum;
	}
	
	/** 设置用户总数 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	/** 取得主账号 */
	public Integer getMasterUserId() {
		return masterUserId;
	}
	
	/** 设置主账号 */
	public void setMasterUserId(Integer masterUserId) {
		this.masterUserId = masterUserId;
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
		sb.append("userIds=").append(userIds).append(";");
		sb.append("accountNo=").append(accountNo).append(";");
		sb.append("phone=").append(phone).append(";");
		sb.append("password=").append(password).append(";");
		sb.append("payPassword=").append(payPassword).append(";");
		sb.append("realName=").append(realName).append(";");
		sb.append("type=").append(type).append(";");
		sb.append("types=").append(types).append(";");
		sb.append("level=").append(level).append(";");
		sb.append("registerMoney=").append(registerMoney).append(";");
		sb.append("agentId=").append(agentId).append(";");
		sb.append("recommendId=").append(recommendId).append(";");
		sb.append("province=").append(province).append(";");
		sb.append("city=").append(city).append(";");
		sb.append("area=").append(area).append(";");
		sb.append("fullAddress=").append(fullAddress).append(";");
		sb.append("bankName=").append(bankName).append(";");
		sb.append("subbranchBank=").append(subbranchBank).append(";");
		sb.append("cardName=").append(cardName).append(";");
		sb.append("cardNo=").append(cardNo).append(";");
		sb.append("createTime=").append(createTime).append(";");
		sb.append("activateTime=").append(activateTime).append(";");
		sb.append("updateTime=").append(updateTime).append(";");
		sb.append("isDeleted=").append(isDeleted).append(";");
		sb.append("copperCardNum=").append(copperCardNum).append(";");
		sb.append("silverCardNum=").append(silverCardNum).append(";");
		sb.append("parentNo=").append(parentNo).append(";");
		sb.append("recommendNo=").append(recommendNo).append(";");
		sb.append("recommendName=").append(recommendName).append(";");
		sb.append("balance=").append(balance).append(";");
		sb.append("shoppingScore=").append(shoppingScore).append(";");
		sb.append("generalizeScore=").append(generalizeScore).append(";");
		sb.append("myMoney=").append(myMoney).append(";");
		sb.append("teamMoney=").append(teamMoney).append(";");
		sb.append("awardFlag=").append(awardFlag).append(";");
		sb.append("activateBy=").append(activateBy).append(";");
		sb.append("totalNum=").append(totalNum).append(";");
		sb.append("masterUserId=").append(masterUserId).append(";");
		return sb.toString();
	}

}
