package com.daboo.finance.enums;

public class MDrawLogStatus {

	/**
	  * @Fields ALREADY_SEND : 已发送提现
	  */
	public static final int ALREADY_SEND = 0;
	
	/**
	  * @Fields SUCCESS_SEND : 发送成功
	  */
	public static final int SUCCESS_SEND = 1;
	
	/**
	  * @Fields FAILED_SEND : 发送提现请求失败
	  */
	public static final int FAILED_SEND = 2;
	
	/**
	  * @Fields SUCCESS_CASH : 提现成功
	  */
	public static final int SUCCESS_CASH = 3;
	
	/**
	  * @Fields FAILED_CASH : 提现失败
	  */
	public static final int FAILED_CASH = 4;
}
