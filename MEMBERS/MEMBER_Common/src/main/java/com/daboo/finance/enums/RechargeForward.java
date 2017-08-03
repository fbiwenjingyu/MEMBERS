package com.daboo.finance.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum RechargeForward {
	ADD(0,"增加"),
	SUB(1,"减少");
	
	private int key;
	private String value;
	
	private RechargeForward(int key,String value){
		this.key=key;
		this.value=value;
	}
	
	public int getKey(){
		return key;
	}
	
	public String getValue(){
		return value;
	}
	
	public static RechargeForward get(Integer key){
		if(key!=null){
			for(RechargeForward tmp:RechargeForward.values()){
				if(tmp.getKey()==key){
					return tmp;
				}
			}
		}
		
		return null;
	}
	
	public static Map<String, String> getComb(){
		Map<String, String> res=new LinkedHashMap<String, String>();
		res.put("", "全部");
		for(RechargeForward tmp:RechargeForward.values()){
			res.put(""+tmp.getKey(), tmp.getValue());
		}
		return res;
	} 
}
