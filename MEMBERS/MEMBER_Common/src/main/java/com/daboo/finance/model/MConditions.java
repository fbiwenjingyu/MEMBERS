package com.daboo.finance.model;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
  * @ClassName: MConditions
  * @Description: http请求条件
  * @author 马正正
  * @date 2015年10月26日
 */
public class MConditions {
	//private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 开始日期
	 */
	private String startDate;
	/**
	 * 结束日期
	 */
	private String endDate;
	/**
	 * 提现单状态
	 */
	private Integer drawStatus;
	/**
	 * 会员编号
	 */
	private String memberNo;
	/**
	 * 会员编号
	 */
	private String mulstatus;
	
	public String getStartDate() {
		return startDate;
	}
	
	/*public void setStartDate(String startDate) {
		this.startDate = toDate(startDate);
	}
*/
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getEndDate() {
		return endDate;
	}

	/*public void setEndDate(String endDate) {
		this.endDate = toDate(endDate);
	}*/

	public Integer getDrawStatus() {
		return drawStatus;
	}

	public void setDrawStatus(Integer drawStatus) {
		this.drawStatus = drawStatus;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = toStr(memberNo);
	}
	
	public String getMulstatus() {
		return mulstatus;
	}

	public void setMulstatus(String mulstatus) {
		this.mulstatus = toStr(mulstatus);
	}
	
	private String toStr(String str){
		if(str!=null && !"".equals(str)){
			return str;
		}
		return null;
	}
	
	/*private Date toDate(String str){
		try{
			if(str!=null && !"".equals(str.trim())){
				return sdf.parse(str);
			}
		} catch(ParseException e){
			
		}
		return null;
	}*/
}
