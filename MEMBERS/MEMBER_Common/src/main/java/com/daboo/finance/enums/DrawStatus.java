package com.daboo.finance.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
  * @ClassName: DrawStatus
  * @Description: 提现状态
  * @author 马正正
  * @date 2015年10月22日
 */
public enum DrawStatus {
	AUDIT_WAIT(0,"待审核"),
	AUDIT_FAIL(1,"审核不通过"),
	AUDIT_SUCC(2,"审核通过"),
	DRAW_ING(3,"提现中"),
	DRAW_FAIL(4,"提现失败"),
	DRAW_SUCC(5,"提现成功"),
	DRAW_PAUS(6,"提现暂停");
	
	private int key;
	private String value;
	
	private DrawStatus(int key,String value){
		this.key=key;
		this.value=value;
	}
	
	public int getKey(){
		return key;
	}
	
	public String getValue(){
		return value;
	}
	
	public static Map<String, String> getComb(){
		Map<String, String> res=new LinkedHashMap<String, String>();
		res.put("", "全部");
		for(DrawStatus tmp:DrawStatus.values()){
			res.put(""+tmp.getKey(), tmp.getValue());
		}
		return res;
	}
}
