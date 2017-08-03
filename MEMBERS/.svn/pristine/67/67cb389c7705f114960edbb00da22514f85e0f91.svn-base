package com.daboo.finance.model;
import java.util.Date;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;


/**
 * FxDrawCashLog对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={} ,tabName = "M_DRAW_CASH_LOG")
public class MDrawCashLogModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /**  */
    private Integer	id;
    /**  */
    private Integer	userId;
    /** 请求提现id，用户查询此次提现是否成功  */
    private String	cashId;
    /** 订单id （提现表主键） */
    private Integer orderId;
    /** 提现金额 */
    private Integer amount;
    /** 0.已发送请求 1.发送成功 (code返回0或-1都算发送成功)2.发送失败 3.提现成功 4.提现失败 */
    private Integer	status;
    /**  */
    private Date	createTime;
    /**  */
    private Date	updateTime;
    /**  */
    private Integer	isDeleted;
	
    
	
	public MDrawCashLogModel(Integer id, Integer status, Date updateTime) {
		super();
		this.id = id;
		this.status = status;
		this.updateTime = updateTime;
	}

	public MDrawCashLogModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MDrawCashLogModel(Integer userId, String cashId,Integer orderId, Integer amount,Integer status,
			Date createTime, Date updateTime, Integer isDeleted) {
		super();
		this.userId = userId;
		this.cashId = cashId;
		this.orderId = orderId;
		this.amount = amount;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDeleted = isDeleted;
	}
    
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/** 取得 */
	public Integer getId() {
		return id;
	}
	
	/** 设置 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得 */
	public Integer getUserId() {
		return userId;
	}
	
	/** 设置 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/** 取得 */
	public String getCashId() {
		return cashId;
	}
	
	/** 设置 */
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	/** 取得 */
	public Integer getStatus() {
		return status;
	}
	
	/** 设置 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/** 取得 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 设置 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/** 取得 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	
	/** 设置 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}


}
