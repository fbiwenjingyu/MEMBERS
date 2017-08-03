package com.daboo.user.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
  * @ClassName: FxUserTypeStatus
  * @Description: 用户基本类型
  * @author 肖勇
  * @date 2015年10月23日
 */
public enum MUserTypeStatus {
	ONE("-1","冻结的会员"),
	TWO("0","未激活"),
	THREE("1","已激活"),
	FOUR("2","冻结的服务中心"),
	FIVE("3","服务中心");
	
	private String code;
	private String name;
	
	private MUserTypeStatus(String code,String name){
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
		resMap.put(FIVE.getCode(), FIVE.getName());
		return resMap;
	}
}
