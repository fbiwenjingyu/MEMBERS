package com.daboo.finance.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum RechargeStatus {
	ING(0,"充值中"),
	FAIL(1,"充值失败"),
	SUCC(2,"充值成功");
	
	private int key;
	private String value;
	
	private RechargeStatus(int key,String value){
		this.key=key;
		this.value=value;
	}
	
	public int getKey(){
		return key;
	}
	
	public String getValue(){
		return value;
	}
	
	public static RechargeStatus get(Integer key){
		if(key!=null){
			for(RechargeStatus tmp:RechargeStatus.values()){
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
		for(RechargeStatus tmp:RechargeStatus.values()){
			res.put(""+tmp.getKey(), tmp.getValue());
		}
		return res;
	} 
}
