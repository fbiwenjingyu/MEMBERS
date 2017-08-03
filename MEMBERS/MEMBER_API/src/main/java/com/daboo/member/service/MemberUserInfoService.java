package com.daboo.member.service;

import java.util.Date;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.healthy.common.response.Response;
import com.daboo.member.model.request.CheckMemberRequest;
import com.daboo.member.model.request.MemberUserAccountRequest;
import com.daboo.member.model.request.MemberUserInfoRequest;
import com.daboo.member.model.request.MemberUserLoginRequest;
import com.daboo.member.model.response.CheckMemberResponse;
import com.daboo.member.model.response.MemberUserInfoResponse;
import com.daboo.member.model.response.MemberUserLoginResponse;

@RemoteService
public interface MemberUserInfoService{
	
	/**
	 * 用户登录
	 * @param userLogin
	 * @return
	 */
	public MemberUserLoginResponse getUserLogin(MemberUserLoginRequest userLogin);
	
	/**
	 * 获取用户账户信息
	 * @param userInfoRequest
	 * @return
	 */
	public MemberUserInfoResponse getUserAccountInfo(MemberUserInfoRequest userInfoRequest);
	
	
	/**
	 * 用户账户消费
	 * @param userAccountRequest
	 * @return
	 */
	public Response updateAccountForConsume(MemberUserAccountRequest userAccountRequest);
	
	
	/**
	 * 用户账户消费冲正
	 * @param userAccountRequest
	 * @return
	 */
	public Response updateAccountForRecovery(MemberUserAccountRequest userAccountRequest);
	
	/**
	 * 获取对账信息
	 * @param date
	 * @return
	 */
	public String getMemberSystemRecordsFromNet(Date  date);
	
	/**
	 * 判断用户是否存在
	 * @param date
	 * @return
	 */
	public CheckMemberResponse  checkMember(CheckMemberRequest request);
	
}
