package com.daboo.reward.enums;

import java.util.Map;
import java.util.TreeMap;


public enum AccountTypeEnum {

	CASH("1","现金币"),
	BEANS("2","博豆"),
	POINTS("3","积分");
	
	private AccountTypeEnum(String code, String name){
		this.code = code;
		this.name = name;
	}
	
    private String code;
	private String name;
	
	public String getCode() {
		return code;
	}	

	public String getName() {
		return name;
	}	
	
	public static String  getNameByCode(String code){
		String name = "";
		for(AccountTypeEnum  t : AccountTypeEnum.values() ){
			if( t.getCode().equals(code)){
				name = t.getName();
				break;
			}
		}
		return name;
	}
	
	public static Map<String,String>  toMap(){
		 Map<String,String> map = new TreeMap<String,String>();
		 map.put("", "全部");
		 for(AccountTypeEnum t:AccountTypeEnum.values()){
		     map.put(t.getCode(), t.getName());
		 }	
		 return map;
	}
	
	public static Map<String,String>  toComMap(){
		 Map<String,String> map = new TreeMap<String,String>();
		 for(AccountTypeEnum t:AccountTypeEnum.values()){
		     map.put(t.getCode(), t.getName());
		 }	
		 return map;
	}
}
