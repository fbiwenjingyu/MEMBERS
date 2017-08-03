package com.daboo.user.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
  * @ClassName: MUserLevelConditions
  * @Description: 查询条件
  * @author 朱冲
  * @date 2015年10月23日
 */
public class MUserLevelConditions {
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 会员编号
	 */
	private String userId;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 开始日期
	 */
	private Date startDate;
	/**
	 * 结束日期
	 */
	private Date endDate;
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		try{
			if(startDate!=null && !"".equals(startDate.trim())){
				this.startDate = sdf.parse(startDate);
			}
		} catch(ParseException e){
			
		}
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		try{
			if(endDate!=null && !"".equals(endDate.trim())){
				Date dt=sdf.parse(endDate);
				Calendar cal=Calendar.getInstance();
				cal.setTime(dt);
				cal.add(Calendar.DATE, 1);
				this.endDate = cal.getTime();
			}
		} catch(ParseException e){
			
		}
	}
}
