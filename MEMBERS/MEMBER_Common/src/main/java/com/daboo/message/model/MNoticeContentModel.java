package com.daboo.message.model;

import java.io.Serializable;

import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MNoticeContent对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_NOTICE_CONTENT")
public class MNoticeContentModel implements Serializable {

	/** uid */
	private static final long serialVersionUID = -3511759680860702749L;
	private Integer id;
    /**  */
    private Integer	noticeId;
    /**  */
    private String	content;
	
	
	/** 取得 */
	public Integer getNoticeId() {
		return noticeId;
	}
	
	/** 设置 */
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	/** 取得 */
	public String getContent() {
		return content;
	}
	
	/** 设置 */
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
