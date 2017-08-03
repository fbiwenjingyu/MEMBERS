package com.daboo.controller.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.daboo.reward.service.MemberLeverService;
import com.daboo.user.enums.MUserLevelStatus;
import com.daboo.user.enums.MUserlevel;
import com.daboo.user.enums.MUserlevelManageStatus;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.model.MUserLevelConditions;
import com.daboo.user.model.MUserlevelManageModel;
import com.daboo.user.service.MUserInfoService;
import com.daboo.user.service.MUserlevelManageService;

@Controller
@RequestMapping(value = "/userLevelManage")
public class MUserlevelManageController extends BaseController {
	Logger log = LoggerFactory.getLogger(MUserlevelManageController.class);
	
	@Autowired
	MUserlevelManageService mUserlevelManageService;
	@Autowired
	MUserInfoService mUserInfoService;
	@Autowired
	MemberLeverService memberLeverService;
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		request.setAttribute("status", JSONUtils.toCombo(MUserlevelManageStatus.getMap()));
		request.setAttribute("statusMap", JSONUtils.toJson(MUserlevelManageStatus.getMap()));
		request.setAttribute("levelMap", JSONUtils.toJson(MUserLevelStatus.getMap()));
		return "userlevel/init";
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	@OpLog(logDesc="获取会员等级管理列表")
	public void findByConditions(HttpServletRequest request,HttpServletResponse response,MUserLevelConditions model){
		try{
			PageObject po = getPageObject(request);
			po.getCondition().put("userId", model.getUserId());
			po.getCondition().put("status", model.getStatus());
			po.getCondition().put("startDate", model.getStartDate());
			po.getCondition().put("endDate", model.getEndDate());
			writeToPage(JSONUtils.toJson(mUserlevelManageService.findLecelChange(po)), response);
		} catch(Exception e){
			log.error("获取会员等级管理列表异常", e);
		}
	}
	
	@RequestMapping(value = "showEdit")
	@OpLog(logDesc="编辑会员等级调整信息")
	public String showEdit(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		MUserlevelManageModel grade = mUserlevelManageService.getModelById(id);
		request.setAttribute("grade", grade);
		return "userlevel/edit";
	}
	@OpLog(logDesc="修改会员等级调整信息")
	@RequestMapping(value = "update")
	public void updateLevelInfo(HttpServletRequest request,HttpServletResponse response,MUserlevelManageModel model){
		try {
			model.setUpdateBy(SpringSecurityUtils.getCurrentUserName());
			mUserlevelManageService.updateLecelChange(model);
			MUserlevelManageModel manageModel=mUserlevelManageService.getModelById(model.getId());
			MUserInfoModel infoModel=mUserInfoService.findUserByAccountNo(manageModel.getUserId());
			//计算各级别会员数量
			if (infoModel!=null) {
				memberLeverService.updateUserInfoCardNum(infoModel.getRecommendId());
				ajaxJsonResponse(response,true,"操作成功");
			}else {
				ajaxJsonResponse(response,false,"用户不存在");
			}
		} catch (BusinessException e) {
			ajaxJsonResponse(response,false,e.getMessage());
		}
	}
	@OpLog(logDesc="跳转添加会员等级调整信息界面")
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request){
		request.setAttribute("level", JSONUtils.toCombo(MUserLevelStatus.getMap()));
		return "userlevel/add";
	}
	
	@OpLog(logDesc="添加会员等级调整信息")
	@RequestMapping(value = "add")
	public String addLevelInfo(HttpServletRequest request,HttpServletResponse response,MUserlevelManageModel model){
		MUserInfoModel mUserInfoModel=mUserInfoService.findUserByAccountNo(model.getUserId());
		model.setOldLevel(mUserInfoModel.getLevel());
		model.setCreateTime(new Date());
		model.setNewLevel(model.getNewLevel());
		model.setStatus(0);
		model.setIsDeleted(0);
		model.setCreateBy(SpringSecurityUtils.getCurrentUserName());
		mUserlevelManageService.addLecelChange(model);
		return "success";
	}

	@OpLog(logDesc="获取会员等级")
	@RequestMapping(value = "getlevel")
	public void getUserLevel(HttpServletRequest request,HttpServletResponse response,String userNo){
		try {
			MUserInfoModel mUserInfoModel=mUserInfoService.findUserByAccountNo(userNo);
			if (mUserInfoModel!=null&&mUserInfoModel.getLevel()!=null) {
				ajaxJsonResponse(response,true,"当前等级为:"+MUserlevel.getNameByCode(mUserInfoModel.getLevel().toString()));
			}else {
				ajaxJsonResponse(response,false,"该会员不存在");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			ajaxJsonResponse(response,false,"服务器异常!");
		}
	}
	
	@OpLog(logDesc="删除信息")
	@RequestMapping(value = "delete")
	public void deleteLevelInfo(HttpServletRequest request,HttpServletResponse response,String id){
		try {
			mUserlevelManageService.deleteLecelChange(Integer.parseInt(id));
			ajaxJsonResponse(response,true,"操作成功");
		} catch (Exception e) {
			ajaxJsonResponse(response,false,"系统异常!");
		}
	}
	
	//会员是否存在
			@RequestMapping(value = "checkMember")
			public void checkMember(HttpServletResponse response, String accountNo){
				try{
					if(StringUtils.isNotBlank(accountNo)){
						Boolean flag = mUserInfoService.checkMember(accountNo);
						if (flag) {
							ajaxJsonResponse(response,true,"验证成功");
						}else {
							ajaxJsonResponse(response,false,"验证失败");
						}
					}
				}catch(Exception e){
					ajaxJsonResponse(response,false,"验证出错");
				}
			}
}
