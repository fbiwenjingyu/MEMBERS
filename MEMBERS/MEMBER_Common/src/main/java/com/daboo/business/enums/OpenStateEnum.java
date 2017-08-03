package com.daboo.business.enums;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @Description 商务中心开通状态
 * @author dingyang
 * @version 1.0
 * @Create Date 2015年10月22日
 */
public enum OpenStateEnum {
	/**
	 * "0","待开通"
	 */
	WAITOPEN("0","待开通"),
	/**
	 * "1","已开通"
	 */
	OPENED("1","已开通"),
	/**
	 * "2","已拒绝"
	 */
	REFUSED("2","已拒绝");

    private String code;
	private String name;
	
	private OpenStateEnum(String code, String name){
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
		for(OpenStateEnum  t : OpenStateEnum.values() ){
			if( t.getCode().equals(code)){
				name = t.getName();
				break;
			}
		}
		return name;
	}
	
	public static Map<String,String>  toMap(){
		 Map<String,String> map = new TreeMap<String,String>();
		 for(OpenStateEnum t:OpenStateEnum.values()){
		     map.put(t.getCode(), t.getName());
		 }	
		 return map;
	}
}
