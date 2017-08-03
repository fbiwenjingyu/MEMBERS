package com.daboo.reward.model;


import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotation.myibatis.Table;


/**
 * MTaskMonitor对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "M_TASK_MONITOR")
public class MTaskMonitorModel implements Serializable {

	/** uid */
	private static final long serialVersionUID = 5905818814852128124L;
    /** 主键 */
    private Integer	id;
    /** 执行日期 */
    //@Column(name = "RUN_DATE",jdbcType=JdbcType.DATE)
    private Date	runDate;
    /** 任务名称 */
    private String	taskName;
    /** 任务类型 */
    private Integer	taskType;
    /** 运行状态 */
    private Integer	runState;
    
    /** 统计日期 */
    private Date	collectDate;
	
	
	/** 取得主键 */
	public Integer getId() {
		return id;
	}
	
	/** 设置主键 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得执行日期 */
	public Date getRunDate() {
		return runDate;
	}
	
	/** 设置执行日期 */
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	/** 取得任务名称 */
	public String getTaskName() {
		return taskName;
	}
	
	/** 设置任务名称 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/** 取得任务类型 */
	public Integer getTaskType() {
		return taskType;
	}
	
	/** 设置任务类型 */
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	/** 取得运行状态 */
	public Integer getRunState() {
		return runState;
	}
	
	/** 设置运行状态 */
	public void setRunState(Integer runState) {
		this.runState = runState;
	}
	
		
	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("id=").append(id).append(";");
		sb.append("runDate=").append(runDate).append(";");
		sb.append("taskName=").append(taskName).append(";");
		sb.append("taskType=").append(taskType).append(";");
		sb.append("runState=").append(runState).append(";");
		sb.append("collectDate=").append(collectDate).append(";");
		return sb.toString();
	}

}
