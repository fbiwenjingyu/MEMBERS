package com.daboo.finance.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
  * @ClassName: RechargeWay
  * @Description: 支付方式
  * @author 马正正
  * @date 2015年10月22日
 */
public enum RechargeWay {
	BACK_STAGE(0,"后台充值"),
	ALI_PAY(1,"支付宝充值"),
	WX_PAY(2,"微信充值");
	
	private int key;
	private String value;
	
	private RechargeWay(int key,String value){
		this.key=key;
		this.value=value;
	}
	
	public int getKey(){
		return key;
	}
	
	public String getValue(){
		return value;
	}
	
	public static RechargeWay get(Integer key){
		if(key!=null){
			for(RechargeWay tmp:RechargeWay.values()){
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
		for(RechargeWay tmp:RechargeWay.values()){
			res.put(""+tmp.getKey(), tmp.getValue());
		}
		return res;
	} 
}
