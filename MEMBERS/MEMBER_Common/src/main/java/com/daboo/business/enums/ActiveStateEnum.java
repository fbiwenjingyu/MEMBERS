package com.daboo.business.enums;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @Description  商务中心存活状态枚举
 * @author dingyang
 * @version 1.0
 * @Create Date 2015年10月22日
 */
public enum ActiveStateEnum {
	/**
	 * "0","未激活"
	 */
	NOT_ACTIVE("0","未激活"),
	/**
	 * "1","已激活"
	 */
	ACTIVATED("1","已激活"),
	/**
	 * "2","已冻结"
	 */
	FREEZEACTIVE("2","已冻结");

    private String code;
	private String name;
	
	private ActiveStateEnum(String code, String name){
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}	

	public String getName() {
		return name;
	}	
	
	public static String  getNameByCode(String code){
		String name = "";
		for(ActiveStateEnum  t : ActiveStateEnum.values() ){
			if( t.getCode().equals(code)){
				name = t.getName();
				break;
			}
		}
		return name;
	}
	
	public static Map<String,String>  toMap(){
		 Map<String,String> map = new TreeMap<String,String>();
		 for(ActiveStateEnum t:ActiveStateEnum.values()){
		     map.put(t.getCode(), t.getName());
		 }	
		 return map;
	}
}

