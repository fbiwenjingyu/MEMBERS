package com.daboo.exchange.enums;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description 兑换物品类型枚举
 * @author dingyang
 * @version 1.0
 * @Create Date 2016年1月8日
 */
public enum GoodTypeEnum {
	/**
	 * 1,"云碟"
	 */
	YUNDIE("1","云碟"),
	/**
	 * 2,"安防套装"
	 */
	ANFANGTAOZHUANG("2","安防套装");

    private String code;
	private String name;
	
	private GoodTypeEnum(String code, String name){
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
		for(GoodTypeEnum  t : GoodTypeEnum.values() ){
			if( t.getCode().equals(code)){
				name = t.getName();
				break;
			}
		}
		return name;
	}
	
	public static Map<String,String>  toMap(){
		 Map<String,String> map = new TreeMap<String,String>();
		 for(GoodTypeEnum t:GoodTypeEnum.values()){
		     map.put(t.getCode(), t.getName());
		 }	
		 return map;
	}
}

