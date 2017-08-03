package com.daboo.reward.enums;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public enum AccountEnum {

	REWARD_CASH(1, 101, "推荐奖励",0), 
	REWARD_MANAGE_CASH(1, 102, "管理奖励",0), 
	REWARD_TUTOR_CASH(1, 103, "辅导奖励",0),


	DEPOSIT_CASH(1, 110, "现金币充值",0),
	DRAW_REVERSAL_CASH(1, 111, "提现冲正",0),
	TRANSFERS_IN_CASH(1, 112, "现金账户转入",0),
	REVERSAL_CASH_BY_HLG(1, 113, "韩流馆现金币冲正",12),
	STOCK_IN_CASH(1, 114, "股权分红",0),
	EXCHANGESH_BY_CASH_CANCEL(1,115,"取消现金兑换智家套装",0),
	
	DRAW_CASH(1, 120, "用户现金提现",0),  
	PURCHASE_CASH(1, 121, "现金币消费",0),
	TRANSFERS_OUT_CASH(1, 122, "现金账户转出",0),
	CASH_TO_POINT_OUT(1, 123, "现金转积分(出)",0),
	ACTIVATIONUSER_BY_CASH(1,124,"现金激活用户",0),
	PURCHASE_CASH_BY_HLG(1, 125, "韩流馆现金币消费",12),
	CASH_TO_BEANS_OUT(1,126,"现金转博豆(出)",0),
	EXCHANGESH_BY_CASH(1,127,"现金兑换智家套装",0),
	
	REWARD_BEANS(2, 201, "奖励博豆",0), 
	DEPOSIT_BEANS(2, 210, "博豆充值",0),

	CASH_TO_BEANS_IN(2, 212, "现金转博豆(入)",0),
	REVERSAL_BEANS_BY_HLG(2, 213, "韩流馆博豆冲正",12),
	TRANSFERS_IN_BEANS(2, 214, "博豆账户转入",0),
	EXCHANGE_BY_BEAN_CANCEL(2,216,"取消博豆兑换云碟",0),
	EXCHANGESH_BY_BEAN_CANCEL(2,218,"取消博豆兑换智家套装",0),
	BEANS_TO_POINT_OUT(2, 222, "博豆转积分(出)",0),
	ACTIVATIONUSER_BY_BEAN(2,224,"博豆激活用户",0),
	PURCHASE_BEANS_BY_HLG(2, 225, "韩流馆博豆消费",12),
	EXCHANGE_BY_BEAN(2,226,"博豆兑换云碟",0),
	TRANSFERS_OUT_BEANS(2, 227, "博豆账户转出",0),
	EXCHANGESH_BY_BEAN(2,228,"博豆兑换智家套装",0),

	REWARD_POINT(3, 301, "奖励推荐积分",0), 
	DEPOSIT_POINT(3, 310, "积分充值",0),
	BEANS_TO_POINT_IN(3, 312, "博豆转积分(入)",0),
	CASH_TO_POINT_IN(3, 313, "现金转积分(入)",0),
	POINT_IN(3, 314, "推广积分转入",0),
	ACTIVATIONUSER_BY_POINT(3,324,"积分激活用户",0),
	POINT_OUT(3, 325, "推广积分转出",0);

	private Integer accountType;//账户类型 1:现金币;2:博豆(购物积分);3:积分(推广积分)
	private Integer inoutType;//账户变动类型
	private String inoutDesc = null;//账户变动描述
	private Integer userType;//用户类型 用户类型 0：会员系统；11：韩流馆

	private AccountEnum(Integer accountType, Integer inoutType, String inoutDesc,Integer userType) {
		this.accountType = accountType;
		this.inoutType = inoutType;
		this.inoutDesc = inoutDesc;
		this.userType = userType;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public Integer getInoutType() {
		return inoutType;
	}

	public String getInoutDesc() {
		return inoutDesc;
	}
	
	public Integer getUserType() {
		return userType;
	}

	public static AccountEnum getAccountEnumByInoutType(Integer inoutType,AccountEnum defaultEnum){
		for(AccountEnum acc : AccountEnum.values()){
			if(acc.getInoutType().equals(inoutType) ){
				return acc;
			}
		}
		return defaultEnum;
	}
	
	public static void main(String args[]){
 	System.out.println(0x1 << 0);
		for(AccountEnum acc : AccountEnum.values())
		System.out.println(acc.inoutType+":"+acc.inoutDesc+";"+acc.ordinal());
//		
		System.out.println();
		Integer inputtype=301;
		AccountEnum ac=getAccountEnumByInoutType(inputtype,null);
		System.out.println(ac.getAccountType()+"===="+ac.getInoutDesc());
		
		System.out.println(toInoutTypeAndInoutDescMap());
	}
	
	public static Map<String, String> getDeposit(){
		Map<String, String> res=new LinkedHashMap<String, String>();
		res.put("", "全部");
		res.put(DEPOSIT_CASH.accountType+"", DEPOSIT_CASH.inoutDesc);
		res.put(DEPOSIT_BEANS.accountType+"", DEPOSIT_BEANS.inoutDesc);
		res.put(DEPOSIT_POINT.accountType+"", DEPOSIT_POINT.inoutDesc);
		
		return res;
	}
	
	/**
	 * 转成inoutType:inoutDesc 类型的map
	 * @FunName toInoutTypeAndInoutDescMap
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月24日
	 */
	public static Map<String,String>  toInoutTypeAndInoutDescMap(){
		 Map<String,String> map = new TreeMap<String,String>();
		 for(AccountEnum t:AccountEnum.values()){
		     map.put(t.getInoutType()+"", t.getInoutDesc());
		 }	
		 return map;
	}
	
}
