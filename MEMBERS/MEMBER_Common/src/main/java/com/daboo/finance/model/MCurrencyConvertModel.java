package com.daboo.finance.model;

import java.util.Date;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;

/**
 * MCurrencyConvert对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 * 
 */
@Table(pkId = { "id" }, tabName = "M_CURRENCY_CONVERT")
public class MCurrencyConvertModel implements Serializable {

	/** uid */
	private static final long serialVersionUID = 1L;
	/** 主键ID */
	private Integer id;
	/** 流水号 */
	private String serialNo;
	/** 用户ID */
	private Integer userId;
	/** 会员账号 */
	private String accountNo;
	/** 转换类型，1：[博豆] 转 [积分] */
	private String convertType;
	/** 转换数值，如果是现金则单位分 */
	private Integer convertAmount;
	/** 获得数值，如果是现金则单位分 */
	private Integer getAmount;
	/** 转换比例 */
	private String fee;
	/** 状态，1：成功，0:失败 */
	private String state;
	/** 创建时间 */
	private Date createTime;
	/** 备注 */
	private String remark;

	/** 取得主键ID */
	public Integer getId() {
		return id;
	}

	/** 设置主键ID */
	public void setId(Integer id) {
		this.id = id;
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

	/** 取得会员账号 */
	public String getAccountNo() {
		return accountNo;
	}

	/** 设置会员账号 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/** 取得转换类型，1：[博豆] 转 [积分] */
	public String getConvertType() {
		return convertType;
	}

	/** 设置转换类型，1：[博豆] 转 [积分] */
	public void setConvertType(String convertType) {
		this.convertType = convertType;
	}

	/** 取得转换数值，如果是现金则单位分 */
	public Integer getConvertAmount() {
		return convertAmount;
	}

	/** 设置转换数值，如果是现金则单位分 */
	public void setConvertAmount(Integer convertAmount) {
		this.convertAmount = convertAmount;
	}

	/** 取得获得数值，如果是现金则单位分 */
	public Integer getGetAmount() {
		return getAmount;
	}

	/** 设置获得数值，如果是现金则单位分 */
	public void setGetAmount(Integer getAmount) {
		this.getAmount = getAmount;
	}

	/** 取得转换率 */
	public String getFee() {
		return fee;
	}

	/** 设置转换率 */
	public void setFee(String fee) {
		this.fee = fee;
	}

	/** 取得状态，1：成功，0:失败 */
	public String getState() {
		return state;
	}

	/** 设置状态，1：成功，0:失败 */
	public void setState(String state) {
		this.state = state;
	}

	/** 取得创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 设置创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 取得备注 */
	public String getRemark() {
		return remark;
	}

	/** 设置备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
