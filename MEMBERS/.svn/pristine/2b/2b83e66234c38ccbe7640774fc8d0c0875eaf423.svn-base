package com.daboo.reward.model;



import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotation.myibatis.Table;

import com.daboo.core.util.StringUtils;


/**
 * MStarNode对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"userId"} ,autoIncrement=false,tabName = "M_STAR_NODE",version="version")
public class MStarNodeModel implements Serializable {

	/** uid */
	private static final long serialVersionUID = -8464233248324587924L;
	
	
    /** 用户ID */
    private Integer	userId;
    /** 父节点,推荐人 */
    private Integer	parentId;
    /** 上层节点IDS,最多存十代 */
    private String	parentStr;
    /** 直推人数 */
    private Integer	recommendNums;
    /** 层数 */
    private Integer	floors;
    /** 创建时间 */
    private Date	createTime;
    /** 乐观锁 */
    private Integer	version;
	
	
	/** 取得用户ID */
	public Integer getUserId() {
		return userId;
	}
	
	/** 设置用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/** 取得父节点,推荐人 */
	public Integer getParentId() {
		return parentId;
	}
	
	/** 设置父节点,推荐人 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/** 取得上层节点IDS,最多存十代 */
	public String getParentStr() {
		return parentStr;
	}
	
	/** 设置上层节点IDS,最多存十代 */
	public void setParentStr(String parentStr) {
		this.parentStr = parentStr;
	}
	/** 取得直推人数 */
	public Integer getRecommendNums() {
		return recommendNums;
	}
	
	/** 设置直推人数 */
	public void setRecommendNums(Integer recommendNums) {
		this.recommendNums = recommendNums;
	}
	/** 取得层数 */
	public Integer getFloors() {
		return floors;
	}
	
	/** 设置层数 */
	public void setFloors(Integer floors) {
		this.floors = floors;
	}
	
	/** 取得创建时间 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 设置创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/** 取得乐观锁 */
	public Integer getVersion() {
		return version;
	}
	
	/** 设置乐观锁 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
     * 获取父节点字符串
     * @param pStr
     * @param pid
     * @return
     */
	public String getChildParentStr(){
		if(StringUtils.isEmpty(parentStr)){
			return String.valueOf(userId);
		}else{
    		String arr[] = parentStr.split(",");
        	if(arr.length >= 10){//只获取1-10 层的父级节点
        		StringBuffer sb = new StringBuffer();
        		for(int i=1;i<10;i++){
        			if(i>1){
        				sb.insert(0,",");
        			}
        			sb.insert(0,arr[arr.length-i]);
        		}
        		sb.append(",").append(userId);
        		//return parentStr.substring(parentStr.indexOf(",")+1)+","+userId;
        		return sb.toString();
        	}
        	return parentStr+","+userId;
    	}
	}
	
	
	public boolean equals(Object other) {
        if (this == other) return true;
        if ( !(other instanceof MStarNodeModel) ) return false;
        final MStarNodeModel model = (MStarNodeModel) other;
        if ( model.userId != getUserId() ) return false;
        return true;
	}
	
	
	public static void main(String args[]){
		MStarNodeModel s = new MStarNodeModel();
		s.setParentStr("2,5,15,17,35,168,169,170,171,172,173,174,175,176,177");
		//s.setParentStr("1,2,3,4,5,6,7,8,9");
		s.setUserId(99999);
		
		System.out.println(s.getChildParentStr());
	}
}
