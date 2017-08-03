package com.daboo.user.model;

import java.io.Serializable;


/**
  * @ClassName: {@link MTaskConditions}
  * @Description: 任务列表查询条件
  * @author 肖勇
  * @date 2015-11-12
 */
public class MTaskConditions implements Serializable{

	private static final long serialVersionUID = 6508134476254600243L;

	 /** 任务类型 */
    private Integer	taskType;
    /** 运行状态 */
    private Integer	runState;
    
    /** 开始时间 */
    private String	startDate;
    
    /** 结束时间 */
    private String	endDate;


	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getRunState() {
		return runState;
	}

	public void setRunState(Integer runState) {
		this.runState = runState;
	}
	
}
