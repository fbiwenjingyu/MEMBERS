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
public enum MUserlevelManageStatus {
	WAIT_AUDIT("0","待审核"),
	AUDIT_FAIL("1","审核成功"),
	AUDIT_SUCC("2","审核失败");
	
	private String code;
	private String name;
	
	private MUserlevelManageStatus(String code,String name){
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
	 * 
	  * @Description: 状态转换成map
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public static Map<String, String> getMap(){
		Map<String, String> resMap=new LinkedHashMap<String, String>();
		resMap.put("", "全部");
		resMap.put(WAIT_AUDIT.getCode(), WAIT_AUDIT.getName());
		resMap.put(AUDIT_FAIL.getCode(), AUDIT_FAIL.getName());
		resMap.put(AUDIT_SUCC.getCode(), AUDIT_SUCC.getName());
		return resMap;
	}
}
