package com.daboo.reward.model;

import java.util.Date;
import java.io.Serializable;
import org.apache.ibatis.annotation.myibatis.Table;

/**
 * MPointTask对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 * 
 */
@Table(pkId = { "taskId" }, tabName = "M_POINT_TASK", version = "version")
public class MPointTaskModel implements Serializable {

	/** uid */
	private static final long serialVersionUID = -7289567465469149036L;

	/** 任务ID */
	private Integer taskId;
	/** 任务创建时间 */
	private Date createTime;
	/** 任务需要执行的时间 */
	private Date currExecDate;
	/** 任务实际执行时间 */
	private Date lastExecDate;
	/** 用户ID */
	private Integer userId;
	/** 积分金额 */
	private Integer pointAmount;
	/** 执行状态 1：正常;2:暂停 */
	private Integer execStatus;
	/** 需要执行的天数 */
	private Integer totalDays;
	/** 已执行的天数 */
	private Integer execDays;
	/** 乐观锁 */
	private Integer version;

	/** 取得任务ID */
	public Integer getTaskId() {
		return taskId;
	}

	/** 设置任务ID */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	/** 取得任务创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 设置任务创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 取得任务需要执行的时间 */
	public Date getCurrExecDate() {
		return currExecDate;
	}

	/** 设置任务需要执行的时间 */
	public void setCurrExecDate(Date currExecDate) {
		this.currExecDate = currExecDate;
	}

	/** 取得任务实际执行时间 */
	public Date getLastExecDate() {
		return lastExecDate;
	}

	/** 设置任务实际执行时间 */
	public void setLastExecDate(Date lastExecDate) {
		this.lastExecDate = lastExecDate;
	}

	/** 取得用户ID */
	public Integer getUserId() {
		return userId;
	}

	/** 设置用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 取得积分金额 */
	public Integer getPointAmount() {
		return pointAmount;
	}

	/** 设置积分金额 */
	public void setPointAmount(Integer pointAmount) {
		this.pointAmount = pointAmount;
	}

	/** 取得执行状态 1：正常;2:暂停 */
	public Integer getExecStatus() {
		return execStatus;
	}

	/** 设置执行状态 1：正常;2:暂停 */
	public void setExecStatus(Integer execStatus) {
		this.execStatus = execStatus;
	}

	/** 取得需要执行的天数 */
	public Integer getTotalDays() {
		return totalDays;
	}

	/** 设置需要执行的天数 */
	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	/** 取得已执行的天数 */
	public Integer getExecDays() {
		return execDays;
	}

	/** 设置已执行的天数 */
	public void setExecDays(Integer execDays) {
		this.execDays = execDays;
	}

	/** 取得乐观锁 */
	public Integer getVersion() {
		return version;
	}

	/** 设置乐观锁 */
	public void setVersion(Integer version) {
		this.version = version;
	}

}
