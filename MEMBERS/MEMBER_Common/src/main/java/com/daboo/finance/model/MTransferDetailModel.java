package com.daboo.finance.model;



import java.util.Date;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MTransferDetail对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={} ,tabName = "M_TRANSFER_DETAIL",notColumn={"password"})
public class MTransferDetailModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /** 主键ID */
    private Integer	id;
    /** 交易号 */
    private String	serialNo;
    /** 转账人ID */
    private Integer	transferId;
    /** 转账人姓名 */
    private String	transferName;
    /** 转账人电话 */
    private String	transferPhone;
    /** 转账金额，单位(现金单位分) */
    private Long	transferAmount;
    /** 转账类型,1:平台账户转账(2:转账到银行卡) */
    private Integer	transferType;
    /** 转账货币类型,1:现金;2:博豆;3:积分 */
    private Integer	type;
    /** 转账状态,1：成功，2：失败 */
    private Integer	transferState;
    /** 收款人ID */
    private Integer	receiveId;
    /** 收款人电话 */
    private String	receivePhone;
    /** 收款人姓名 */
    private String	receiveName;
    /** 收款人开户行 */
    private String	bankName;
    /** 收款人开户城市 */
    private String	openCity;
    /** 持卡人姓名 */
    private String	cardName;
    /** 银行卡号 */
    private String	cardNo;
    /** 创建时间 */
    private Date	createTime;
    /** 到账时间 */
    private Date	arrivalTime;
    /** 是否删除，0否，1是 */
    private Integer	isDeleted;
	
    private String password;
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public String getSerialNo() {
		return serialNo;
	}
	
	/** 设置 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/** 取得 */
	public Integer getTransferId() {
		return transferId;
	}
	
	/** 设置 */
	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}
	/** 取得 */
	public String getTransferName() {
		return transferName;
	}
	
	/** 设置 */
	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}
	/** 取得 */
	public String getTransferPhone() {
		return transferPhone;
	}
	
	/** 设置 */
	public void setTransferPhone(String transferPhone) {
		this.transferPhone = transferPhone;
	}
	/** 取得 */
	public Long getTransferAmount() {
		return transferAmount;
	}
	
	/** 设置 */
	public void setTransferAmount(Long transferAmount) {
		this.transferAmount = transferAmount;
	}
	/** 取得 */
	public Integer getTransferType() {
		return transferType;
	}
	
	/** 设置 */
	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
	/** 取得 */
	public Integer getTransferState() {
		return transferState;
	}
	
	/** 设置 */
	public void setTransferState(Integer transferState) {
		this.transferState = transferState;
	}
	/** 取得 */
	public Integer getReceiveId() {
		return receiveId;
	}
	
	/** 设置 */
	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}
	/** 取得 */
	public String getReceivePhone() {
		return receivePhone;
	}
	
	/** 设置 */
	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}
	/** 取得 */
	public String getReceiveName() {
		return receiveName;
	}
	
	/** 设置 */
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	/** 取得 */
	public String getBankName() {
		return bankName;
	}
	
	/** 设置 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/** 取得 */
	public String getOpenCity() {
		return openCity;
	}
	
	/** 设置 */
	public void setOpenCity(String openCity) {
		this.openCity = openCity;
	}
	/** 取得 */
	public String getCardName() {
		return cardName;
	}
	
	/** 设置 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	/** 取得 */
	public String getCardNo() {
		return cardNo;
	}
	
	/** 设置 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/** 取得 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 设置 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/** 取得 */
	public Date getArrivalTime() {
		return arrivalTime;
	}
	
	/** 设置 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/** 取得 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	
	/** 设置 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


}
