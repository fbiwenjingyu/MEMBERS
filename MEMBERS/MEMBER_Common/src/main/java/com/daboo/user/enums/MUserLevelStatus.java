package com.daboo.user.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
  * @ClassName: FxUserTypeStatus
  * @Description: 用户基本级别
  * @author 肖勇
  * @date 2015年10月23日
 */
public enum MUserLevelStatus {
	ONE("0","普通"),
	TWO("1","铜卡"),
	THREE("2","银卡"),
	FOUR("3","金卡");
	
	private String code;
	private String name;
	
	private MUserLevelStatus(String code,String name){
		this.code=code;
		this.name=name;
	}

	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	  * @Description: 可选值
	  * @return
	  * @author 肖勇
	  * @date 2015-8-24
	 */
	public static Map<String, String> getMap(){
		Map<String, String> resMap=new LinkedHashMap<String, String>(0);
		resMap.put(ONE.getCode(), ONE.getName());
		resMap.put(TWO.getCode(), TWO.getName());
		resMap.put(THREE.getCode(), THREE.getName());
		resMap.put(FOUR.getCode(), FOUR.getName());
		return resMap;
	}
}
