package com.daboo.message.model;

import java.util.Date;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MNotice对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_NOTICE",notColumn={"isTop"})
public class MNoticeModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /** 递增主键 */
    private Integer	id;
    /** 公告标题 */
    private String	title;
    /** 发布人 */
    private String	deployBy;
    /** 发布日期 */
    private Date	deployTime;
    /** 置顶时间 */
    private Date	topTime;
    /** 0：未删除 1：已删除 */
    private Integer	isDelete;
    
    private Integer isTop;
	
	
	/** 取得递增主键 */
	public Integer getId() {
		return id;
	}
	
	/** 设置递增主键 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得公告标题 */
	public String getTitle() {
		return title;
	}
	
	/** 设置公告标题 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/** 取得发布人 */
	public String getDeployBy() {
		return deployBy;
	}
	
	/** 设置发布人 */
	public void setDeployBy(String deployBy) {
		this.deployBy = deployBy;
	}
	/** 取得发布日期 */
	public Date getDeployTime() {
		return deployTime;
	}
	
	/** 设置发布日期 */
	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}
	/** 取得置顶时间 */
	public Date getTopTime() {
		return topTime;
	}
	
	/** 设置置顶时间 */
	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
	/** 取得0：未删除 1：已删除 */
	public Integer getIsDelete() {
		return isDelete;
	}
	
	/** 设置0：未删除 1：已删除 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}


}
