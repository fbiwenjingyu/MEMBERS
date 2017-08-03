package com.daboo.business.model;



import java.util.Date;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MBusinessCenter对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_BUSINESS_CENTER")
public class MBusinessCenterModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /** 主键ID */
    private Integer	id;
    /** 商务中心编号 */
    private String	businessNo;
    /** 会员账号 */
    private String	accountNo;
    /** 电话 */
    private String	phone;
    /** 姓名 */
    private String	realName;
    /** 开通状态，0待开通，1已开通 */
    private String	openState;
    /** 存活状态，0未激活，1已激活，2已冻结 */
    private String	activeState;
    /** 创建时间 */
    private Date	createTime;
    /** 更新时间 */
    private Date	updateTime;
    /** 备注 */
    private String	remark;
    //操作人
    private String operater;
	//版本号
    private Integer version;
	
	/** 取得主键ID */
	public Integer getId() {
		return id;
	}
	
	/** 设置主键ID */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得商务中心编号 */
	public String getBusinessNo() {
		return businessNo;
	}
	
	/** 设置商务中心编号 */
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	/** 取得会员账号 */
	public String getAccountNo() {
		return accountNo;
	}
	
	/** 设置会员账号 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/** 取得电话 */
	public String getPhone() {
		return phone;
	}
	
	/** 设置电话 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/** 取得姓名 */
	public String getRealName() {
		return realName;
	}
	
	/** 设置姓名 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/** 取得开通状态，0待开通，1已开通 */
	public String getOpenState() {
		return openState;
	}
	
	/** 设置开通状态，0待开通，1已开通 */
	public void setOpenState(String openState) {
		this.openState = openState;
	}
	/** 取得存活状态，0未激活，1已激活，2已冻结 */
	public String getActiveState() {
		return activeState;
	}
	
	/** 设置存活状态，0未激活，1已激活，2已冻结 */
	public void setActiveState(String activeState) {
		this.activeState = activeState;
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
	/** 取得备注 */
	public String getRemark() {
		return remark;
	}
	
	/** 设置备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}


}
