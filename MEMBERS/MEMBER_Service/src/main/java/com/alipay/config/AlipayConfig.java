package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088412863686176";
	
	// 收款支付宝账号
	public static String seller_email = "jiuwu@gangbeng.com";
	
	// 商户的私钥
	public static String key = "";
	
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOxA++nSSjrVHe1sxkiSSRstiVPoU1tAxjIx7nkOAzI9rb25/5gmvCKb5aXyo7xNgf5ESsFykU6xVoKJPDbjBgMvB93ZbgJs19F3aKMRnxyLpmu/bwZBsq/PSvk98Sd2291f9Vvj1l6ygXcW9MT8R30030kK282vI6Ex5zcQn44tAgMBAAECgYAL8HzKpEX8EykgBo8+u6XWKcb25pbP1cYuYzeaBotCzrspC21PUyXO2fIRIOIFciVPpEPi4tdVEsE8d5MDcxWqmqaQzvdYHpjzbKwrXPOupAG/CMdfKPPwP/ySBMoN+28dQl9K9QH+ruUFpOfOVONty7rOSvf9IACCqX6eqMCcAQJBAP6rqVMVOlfECeWUvO7xuYyzCtNNkufuNGq+XrUXmVJy8b0Yml/ECmZsPhD/DJk2v0GL/QEySOC5Xxu5fjbCxH0CQQDtfLXzyMZi6FaYCu4WYkmq0v0r8dd/3b3fOtNRgEwsDffjcCGlstEi/CM7Ve8O1SwVSd2GFbleDokK8vdaiY9xAkBWU0v/PLfdHjeYmdV9TXTvC5XE0IvWkHk2IbeqvQgc2Ze12IwjoClCVLipPDtCCDvGdTRa3nezrUJe/H6bA2RlAkB9JCgk6/lOCSwINko7VVOIRmHOFEQARvJUfQT5f2ekF0A2gtHYJJ/7lW1jSOcNo9r05NVsYGorW/Fu6ZTaCn6hAkEAoYuIP3yQMwAE2tw5sucfv3j2ycoFEuHHc9JF1lq7//GlVZOGVCPjIuZxWVZWwsDrKL1qFgbTrTQPqG2C0P8KFw==";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

	/**
	 * 接口名称
	 */
	public static final String service="create_direct_pay_by_user";
	/**
	 * 异步通知
	 */
	public static final String notify_url="http://vip.daboowifi.net/recharge/alipayNotify";
	/**
	 * 同步通知
	 */
	public static final String return_url="http://vip.daboowifi.net/recharge/alipayReturn";
	/**
	 * 商品展示网址
	 */
	public static final String show_url="http://vip.daboowifi.net/recharge/toRecharge";
}
