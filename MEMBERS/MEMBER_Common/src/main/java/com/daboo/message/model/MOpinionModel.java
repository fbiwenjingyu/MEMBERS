package com.daboo.message.model;

import java.util.Date;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;
import java.util.*;


/**
 * MOpinion对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_OPINION", notColumn={"replys","comtentLike","isNoRead"})
public class MOpinionModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /** 主键 */
    private Integer	id;
    /** 反馈者手机 */
    private String	responsePhone;
    /** 反馈标题   */
    private String title;
    /** 反馈内容 */
    private String	comtent;
    
    private String	comtentLike;
    /** 反馈时间 */
    private Date	time;
    /** 是否有未读的回复 1为有未读，0为没有  */
    private Integer isNoRead;
    /** 是否有回答（0是没有，1是有） */
    private Integer	isReply;
    /** 创建时间 */
    private Date	createTime;
    /** 修改时间 */
    private Date	updateTime;
    /** 是否删除 */
    private Integer	isDeleted;
	
    public MOpinionModel(){
    	
    	
    }
    public MOpinionModel(String responsePhone, String title,
			String comtent,  Date time, Integer isReply,
			Date createTime, Date updateTime, Integer isDeleted) {
		super();
		this.responsePhone = responsePhone;
		this.title = title;
		this.comtent = comtent;
		this.time = time;
		this.isReply = isReply;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDeleted = isDeleted;
		this.replys = replys;
	}
    
    
	public Integer getIsNoRead() {
		return isNoRead;
	}
	public void setIsNoRead(Integer isNoRead) {
		this.isNoRead = isNoRead;
	}


	//此反馈的所有回复
    private List<MReplyModel> replys;
    
    
	public List<MReplyModel> getReplys() {
		return replys;
	}

	public void setReplys(List<MReplyModel> replys) {
		this.replys = replys;
	}

	/** 取得主键 */
	public Integer getId() {
		return id;
	}
	
	/** 设置主键 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得反馈者手机 */
	public String getResponsePhone() {
		return responsePhone;
	}
	
	/** 设置反馈者手机 */
	public void setResponsePhone(String responsePhone) {
		this.responsePhone = responsePhone;
	}
	/** 取得反馈内容 */
	public String getTitle() {
		return title;
	}
	
	/** 设置反馈内容 */
	public void setTitle(String title) {
		this.title = title;
	}
	/** 取得反馈内容 */
	public String getComtent() {
		return comtent;
	}
	
	/** 设置反馈内容 */
	public void setComtent(String comtent) {
		this.comtent = comtent;
	}
	/** 取得反馈时间 */
	public Date getTime() {
		return time;
	}
	
	/** 设置反馈时间 */
	public void setTime(Date time) {
		this.time = time;
	}
	/** 取得是否有回答（0是没有，1是有） */
	public Integer getIsReply() {
		return isReply;
	}
	
	/** 设置是否有回答（0是没有，1是有） */
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
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
	/** 取得是否删除 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	
	/** 设置是否删除 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public final String getComtentLike() {
		return comtentLike;
	}

	public final void setComtentLike(String comtentLike) {
		this.comtentLike = comtentLike;
	}


}
