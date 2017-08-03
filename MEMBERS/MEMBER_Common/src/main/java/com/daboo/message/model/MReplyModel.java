package com.daboo.message.model;

import java.util.Date;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MReply对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_REPLY", notColumn={"replyName"} )
public class MReplyModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /** 主键 */
    private Integer	id;
    /** 反馈id */
    private Integer	responseId;
    /** 回复内容 */
    private String	replyComtent;
    /** 回复人id */
    private Integer	replyId;
    /** 此回复是否已读  0为未读，1为已读*/
    private Integer isRead;
    /** 是系统管理员留言还是会员留言  */
    private Integer isSystem;
    /** 回复人姓名   */
    private String replyName;
    /** 回复时间 */
    private Date	replyTime;
    /** 创建时间 */
    private Date	createTime;
    /** 修改时间 */
    private Date	updateTime;
    /** 是否删除（0是未删除，1是已经删除） */
    private Integer	isDeleted;
	
    
    
	
	public MReplyModel(Integer responseId, Integer isRead, Integer isSystem,
			Integer isDeleted) {
		super();
		this.responseId = responseId;
		this.isRead = isRead;
		this.isSystem = isSystem;
		this.isDeleted = isDeleted;
	}

	public MReplyModel(Integer responseId, String replyComtent,
			Integer replyId,Integer isRead,Integer isSystem, Date replyTime, Date createTime, Date updateTime,
			Integer isDeleted) {
		super();
		this.responseId = responseId;
		this.replyComtent = replyComtent;
		this.replyId = replyId;
		this.isRead = isRead;
		this.isSystem = isSystem;
		this.replyTime = replyTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDeleted = isDeleted;
	}

	public MReplyModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	/** 取得主键 */
	public Integer getId() {
		return id;
	}
	
	/** 设置主键 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得反馈id */
	public Integer getResponseId() {
		return responseId;
	}
	
	/** 设置反馈id */
	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}
	/** 取得回复内容 */
	public String getReplyComtent() {
		return replyComtent;
	}
	
	/** 设置回复内容 */
	public void setReplyComtent(String replyComtent) {
		this.replyComtent = replyComtent;
	}
	/** 取得回复人id */
	public Integer getReplyId() {
		return replyId;
	}
	
	/** 设置回复人id */
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	/** 取得回复时间 */
	public Date getReplyTime() {
		return replyTime;
	}
	
	/** 设置回复时间 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	/** 取得创建时间 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 设置创建时间 */
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
	/** 取得是否删除（0是未删除，1是已经删除） */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	
	/** 设置是否删除（0是未删除，1是已经删除） */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}


}
