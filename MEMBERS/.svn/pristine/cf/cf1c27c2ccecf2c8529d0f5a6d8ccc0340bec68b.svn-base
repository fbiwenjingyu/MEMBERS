package com.daboo.user.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
  * @ClassName: MUserlevelManageStatus
  * @Description: 会员等级管理状态
  * @author 朱冲
  * @date 2015年10月23日
 */
public enum MUserlevel {
	LEVEL_0("0","普通"),
	LEVEL_1("1","铜卡"),
	LEVEL_2("2","银卡"),
	LEVEL_3("3","金卡");
	
	private String code;
	private String name;
	
	private MUserlevel(String code,String name){
		this.code=code;
		this.name=name;
	}

	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public static String  getNameByCode(String code){
		String name = "";
		for(MUserlevel  t : MUserlevel.values() ){
			if( t.getCode().equals(code)){
				name = t.getName();
				break;
			}
		}
		return name;
	}
	
	/**
	 * 
	  * @Description: 状态转换成map
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public static Map<String, String> getMap(){
		Map<String, String> resMap=new LinkedHashMap<String, String>();
		resMap.put("", "全部");
		resMap.put(LEVEL_0.getCode(), LEVEL_0.getName());
		resMap.put(LEVEL_1.getCode(), LEVEL_1.getName());
		resMap.put(LEVEL_2.getCode(), LEVEL_2.getName());
		resMap.put(LEVEL_3.getCode(), LEVEL_3.getName());
		return resMap;
	}
}
