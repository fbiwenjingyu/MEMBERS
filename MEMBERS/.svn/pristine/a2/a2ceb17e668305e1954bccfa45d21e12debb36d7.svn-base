package com.daboo.hlg.api.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.util.BigDecimalUtil;
import com.daboo.core.util.CommonUtil;
import com.daboo.core.util.ConfigProperty;
import com.daboo.core.util.DateUtils;
import com.daboo.healthy.common.response.Response;
import com.daboo.healthy.common.utils.MessageUtils;
import com.daboo.hlg.api.service.impl.MessageConstant.ACCOUNT_RECOVERY;
import com.daboo.hlg.api.service.impl.MessageConstant.ACCOUNT_UPDATE;
import com.daboo.hlg.api.service.impl.MessageConstant.CHECK_MEMBER;
import com.daboo.hlg.api.service.impl.MessageConstant.USER_ACCOUNT;
import com.daboo.hlg.api.service.impl.MessageConstant.USER_LOGIN;
import com.daboo.member.model.request.CheckMemberRequest;
import com.daboo.member.model.request.MemberUserAccountRequest;
import com.daboo.member.model.request.MemberUserInfoRequest;
import com.daboo.member.model.request.MemberUserLoginRequest;
import com.daboo.member.model.response.CheckMemberResponse;
import com.daboo.member.model.response.MemberUserInfoResponse;
import com.daboo.member.model.response.MemberUserLoginResponse;
import com.daboo.member.service.MemberUserInfoService;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.model.MUserInfoModel;

 
@Service
@Transactional
public class MemberUserInfoServiceImpl implements MemberUserInfoService{
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private MUserInfoMapper mUserInfoMapper;	
	
	@Autowired
	private MemberRewardService rewardService;
	
	@Autowired
	private MessageSource messageSource;
	/**
	 * 用户登录
	 */
	@Override
	public MemberUserLoginResponse getUserLogin(MemberUserLoginRequest request) {
		log.info("用户登录韩流馆");
		MemberUserLoginResponse response = new MemberUserLoginResponse(); 
		String result = null;
		String msg = null;
		try{
			//①参数校验
			if(StringUtils.isBlank(request.getAccountNo())){
				//手机号为空
				result = USER_LOGIN.ERROR_100;
			}else if(StringUtils.isBlank(request.getPassword())){
				//密码为空
				result = USER_LOGIN.ERROR_101;
			}
			
			if(result != null){
				msg = MessageUtils.getMessage(messageSource,USER_LOGIN.METHOD + result, null, request.getLanguage());
				response.addErrInfo(result, msg, null);
				return response;
			}
			
			//②业务逻辑
			MUserInfoModel model = new MUserInfoModel();
			model.setAccountNo(request.getAccountNo());
			model = this.mUserInfoMapper.getObjectByModel(model);
			if(model != null){
				if(request.getPassword().equalsIgnoreCase(model.getPassword())){
					response.setUserId(model.getId());
				}else{
					result = USER_LOGIN.ERROR_200;
				}
			}else{
				result =USER_LOGIN.ERROR_201;
			}
			
			//业务逻辑异常
			if(result != null){
				msg = MessageUtils.getMessage(messageSource,USER_LOGIN.METHOD + result, null, request.getLanguage());
				response.addErrInfo(result, msg, null);
				return response;
			}
		}catch(Exception e){
			log.error("用户登录韩流馆异常", e);
			result = USER_LOGIN.ERROR_300;
			msg = MessageUtils.getMessage(messageSource,USER_LOGIN.METHOD + result, null, request.getLanguage());
			response.addErrInfo(result, msg, null);
			return response;
		}
		return response;
	}
	
	/**
	 * 根据用户ID获取用户现金和积分余额
	 */
	public MemberUserInfoResponse getUserAccountInfo(MemberUserInfoRequest userInfoRequest) {
		log.info("根据用户ID获取用户现金和积分余额");
		MemberUserInfoResponse userInfoResponse=new MemberUserInfoResponse();
		String resultCode=null;
		String resultMes=null;
		
		try {
			if(userInfoRequest.getUserId() == null){//用户ID为空

				resultCode=USER_ACCOUNT.ERROR_100;
				resultMes=MessageUtils.getMessage(messageSource, USER_ACCOUNT.METHOD+resultCode,null , userInfoRequest.getLanguage());
				userInfoResponse.addErrInfo(resultCode, resultMes, null);
				return userInfoResponse;
			}
			
			MUserInfoModel model = new MUserInfoModel();
			model = mUserInfoMapper.getFullModel(userInfoRequest.getUserId());
			if(model == null){//用户不存在
				
				resultCode=USER_ACCOUNT.ERROR_200;
				resultMes=MessageUtils.getMessage(messageSource, USER_ACCOUNT.METHOD+resultCode,null , userInfoRequest.getLanguage());
				userInfoResponse.addErrInfo(resultCode, resultMes, null);
				return userInfoResponse;
			}
			
			userInfoResponse.setUserId(model.getId());
			
			if(model.getType().equals(1) || model.getType().equals(3)){//正常
				userInfoResponse.setIsFreeze(2);
				
			}else{//冻结
				userInfoResponse.setIsFreeze(1);
				
			}
			//现金余额
			userInfoResponse.setCashBalance(BigDecimalUtil.div(new BigDecimal(model.getBalance()), new BigDecimal(100)));
			//积分余额
			userInfoResponse.setPointsBalance(new BigDecimal(model.getShoppingScore()));
			
		} catch (Exception e) {
			log.error("根据用户ID获取用户现金和积分余额接口异常",e);
			resultCode=USER_ACCOUNT.ERROR_300;
			resultMes=MessageUtils.getMessage(messageSource, USER_ACCOUNT.METHOD+resultCode,null , userInfoRequest.getLanguage());
			userInfoResponse.addErrInfo(resultCode, resultMes, null);
		}
		
		return userInfoResponse;
	}

	@Override
	public Response updateAccountForConsume(MemberUserAccountRequest userAccountRequest) {
		log.info("用户账户消费韩流馆");
		Response response = new Response(); 
		String result = null;
		String msg = null;
		try{
			//①参数校验
			if(userAccountRequest.getUserId() == null || userAccountRequest.getUserId() < 1){
				//手机号为空
				result = ACCOUNT_UPDATE.ERROR_100;
			}else if(StringUtils.isBlank(userAccountRequest.getTradeNo())){
				//密码为空
				result = ACCOUNT_UPDATE.ERROR_102;
			}
			if(userAccountRequest.getCashAmount().intValue() < 0 || userAccountRequest.getPointAmount().intValue() < 0){
				//密码为空
				result = ACCOUNT_UPDATE.ERROR_101;
			}
			if(result != null){
				msg = MessageUtils.getMessage(messageSource,ACCOUNT_UPDATE.METHOD + result, null, userAccountRequest.getLanguage());
				response.addErrInfo(result, msg, null);
				return response;
			}
			
			//②业务逻辑
			result = rewardService.userAccountUpdateByHlg(userAccountRequest.getUserId(),userAccountRequest.getTradeNo(),userAccountRequest.getPointAmount(),BigDecimalUtil.mul(userAccountRequest.getCashAmount(),new BigDecimal(100)));
			
			
			//业务逻辑异常
			if(result != null){
				msg = MessageUtils.getMessage(messageSource,ACCOUNT_UPDATE.METHOD + result, null, userAccountRequest.getLanguage());
				response.addErrInfo(result, msg, null);
				return response;
			}
		}catch(Exception e){
			log.error("用户账户消费韩流馆异常", e);
			result = ACCOUNT_UPDATE.ERROR_300;
			msg = MessageUtils.getMessage(messageSource,ACCOUNT_UPDATE.METHOD + result, null, userAccountRequest.getLanguage());
			response.addErrInfo(result, msg, null);
			return response;
		}
		return response;
	}

	@Override
	public Response updateAccountForRecovery(
			MemberUserAccountRequest request) {
		log.info("用户账户消费取消韩流馆");
		Response response = new Response(); 
		String result = null;
		String msg = null;
		try{
			//①参数校验
			if(request.getUserId() == null || request.getUserId() < 1){
				//手机号为空
				result = ACCOUNT_RECOVERY.ERROR_100;
			}else if(StringUtils.isBlank(request.getTradeNo())){
				//密码为空
				result = ACCOUNT_RECOVERY.ERROR_102;
			}
			if(request.getCashAmount().intValue() < 0 || request.getPointAmount().intValue() < 0){
				//密码为空
				result = ACCOUNT_RECOVERY.ERROR_101;
			}
			if(result != null){
				msg = MessageUtils.getMessage(messageSource,ACCOUNT_RECOVERY.METHOD + result, null, request.getLanguage());
				response.addErrInfo(result, msg, null);
				return response;
			}
			
			//②业务逻辑
			result = rewardService.userAccountRecoveryByHlg(request.getUserId(),request.getTradeNo(),request.getPointAmount(),BigDecimalUtil.mul(request.getCashAmount(),new BigDecimal(100)));
			
			
			//业务逻辑异常
			if(result != null){
				msg = MessageUtils.getMessage(messageSource,ACCOUNT_RECOVERY.METHOD + result, null, request.getLanguage());
				response.addErrInfo(result, msg, null);
				return response;
			}
		}catch(Exception e){
			log.error("用户账户消费取消韩流馆异常", e);
			result = ACCOUNT_RECOVERY.ERROR_300;
			msg = MessageUtils.getMessage(messageSource,ACCOUNT_RECOVERY.METHOD + result, null, request.getLanguage());
			response.addErrInfo(result, msg, null);
			return response;
		}
		return response;
	}

	@Override
	public String getMemberSystemRecordsFromNet(Date date) {
		TreeMap<String,String> t = new TreeMap<String,String>();
		t.put("appkey", ConfigProperty.getProperty("donet.appkey"));
		t.put("date", DateUtils.getShortDateString(date));
		try {
			return CommonUtil.doPost(ConfigProperty.getProperty("donet.url")+"GetMemberSystemRecords", t, 3000, 15000, null, ConfigProperty.getProperty("donet.secretKey"));
		} catch (IOException e) {
			log.error("调用.net 接口报错",e);
		}
		return null;
 
	}

	@Override
	public CheckMemberResponse checkMember(CheckMemberRequest request) {
		log.info("检测用户是否存在");
		CheckMemberResponse response = new CheckMemberResponse(); 
		String result = null;
		String msg = null;
		try{
			if(StringUtils.isBlank(request.getAccountNo())){
				result = CHECK_MEMBER.ERROR_100;
				msg = MessageUtils.getMessage(messageSource,CHECK_MEMBER.METHOD + result, null, request.getLanguage());
				response.addErrInfo(result, msg, null);
				return response;
			}
			
			MUserInfoModel model = new MUserInfoModel();
			model.setAccountNo(request.getAccountNo());
			model = this.mUserInfoMapper.getObjectByModel(model);
			if(model != null){
				response.setFlag(1);
			}else{
				response.setFlag(0);
			}
			return response;
		}catch(Exception e){
			log.error("检测用户是否存在", e);
			result = CHECK_MEMBER.ERROR_300;
			msg = MessageUtils.getMessage(messageSource,CHECK_MEMBER.METHOD + result, null, request.getLanguage());
			response.addErrInfo(result, msg, null);
			return response;
		}
	}

}
