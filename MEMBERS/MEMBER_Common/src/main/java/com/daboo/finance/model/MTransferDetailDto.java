package com.daboo.finance.model;

import java.io.Serializable;
import java.util.Date;

public class MTransferDetailDto implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /** 转账人姓名 */
    private String	transferName;
    /** 转账人电话 */
    private String	transferPhone;
    /** 转账金额，单位(现金单位分) */
    private String	transferAmount;
    /** 转账货币类型,1:现金;2:博豆;3:积分 */
    private Integer	type;
    /** 创建时间 */
    private Date createTime;
    /** 转账类型,1:转出;2:转入 */
    private Integer	transferType;
    
    
	public String getTransferName() {
		return transferName;
	}
	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}
	public String getTransferPhone() {
		return transferPhone;
	}
	public void setTransferPhone(String transferPhone) {
		this.transferPhone = transferPhone;
	}
	public String getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getTransferType() {
		return transferType;
	}
	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
    
    
}
