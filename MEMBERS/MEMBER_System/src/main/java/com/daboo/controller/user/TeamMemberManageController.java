package com.daboo.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.system.service.SysDictService;
import com.daboo.user.enums.MUserLevelStatus;
import com.daboo.user.model.MUserInfoConditions;
import com.daboo.user.service.MUserInfoService;
import com.daboo.user.service.MUserlevelManageService;

@Controller
@RequestMapping(value = "/teamMemberManage")
public class TeamMemberManageController extends BaseController {
	Logger log = LoggerFactory.getLogger(TeamMemberManageController.class);
	@Autowired
	MUserInfoService mUserInfoService;
	@Autowired
	MemberRewardService memberRewardService;
	@Autowired
	MUserlevelManageService mUserlevelManageService;
	@Autowired
	SysDictService sysDictService;
	
	@RequestMapping(value = "teamInit")
	public String initTeam(HttpServletRequest request){
		request.setAttribute("levelMap", JSONUtils.toJson(MUserLevelStatus.getMap()));
		String rechargeAmountVallue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		request.setAttribute("rAmount", rechargeAmountVallue);
		return "user/teamInit";
	}
	
	@RequestMapping(value = "teamList")
	@ResponseBody
	@OpLog(logDesc="获取团队会员列表")
	public void findTeamByConditions(HttpServletRequest request,HttpServletResponse response,String userId){
		try{
			PageObject po = getPageObject(request);
			po.getCondition().put("recommendNo", userId);
			writeToPage(JSONUtils.toJson(mUserInfoService.findTeam(po)), response);
		} catch(Exception e){
			log.error("获取团队会员列表异常", e);
		}
	}
	
	@RequestMapping(value = "unOpenUserInit")
	public String initUnOpenUser(HttpServletRequest request){
		request.setAttribute("levelMap", JSONUtils.toJson(MUserLevelStatus.getMap()));
		return "user/unOpenUserInit";
	}
	
	@RequestMapping(value = "unOpenUserList")
	@ResponseBody
	@OpLog(logDesc="获取待激活会员列表")
	public void findUnOpenUserByConditions(HttpServletRequest request,HttpServletResponse response,MUserInfoConditions conditions){
		try{
			PageObject po = getPageObject(request);
			po.getCondition().put("recommendNo", conditions.getUserId());
			po.getCondition().put("startDate", conditions.getStartDate());
			po.getCondition().put("endDate", conditions.getEndDate());
			writeToPage(JSONUtils.toJson(mUserInfoService.findUnOpenUser(po)), response);
		} catch(Exception e){
			log.error("获取待激活会员列表异常", e);
		}
	}
	
	@OpLog(logDesc="激活会员")
	@RequestMapping(value = "update")
	public void openUser(HttpSession session,HttpServletRequest request,HttpServletResponse response,Integer id,Integer type){
		try {
			mUserlevelManageService.openUser(id,SpringSecurityUtils.getCurrentUserName(),type);
			ajaxJsonResponse(response,true,"操作成功");
		} 
		catch (BusinessException e) {
			ajaxJsonResponse(response,false,e.getMessage());
		}
	}
	
	@OpLog(logDesc="删除未激活会员")
	@RequestMapping(value = "delete")
	public void deleteLevelInfo(HttpServletRequest request,HttpServletResponse response,Integer id){
		try {
			mUserInfoService.deleteUnOpenUser(id);
			ajaxJsonResponse(response,true,"删除成功");
		} catch (BusinessException e) {
			ajaxJsonResponse(response,false,e.getMessage());
		}
	}
}
