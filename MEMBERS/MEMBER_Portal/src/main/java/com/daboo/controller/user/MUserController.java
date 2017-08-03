package com.daboo.controller.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.user.enums.MUserLevelStatus;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.model.MUserPwdConditions;
import com.daboo.user.service.MUserInfoService;

@Controller
@RequestMapping(value = "/user")
public class MUserController extends BaseController {
	Logger log = LoggerFactory.getLogger(MUserController.class);
	
	@Autowired
	MUserInfoService mUserInfoService;
	
	@RequestMapping("person")
	public String logout(HttpServletRequest request) {
		MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
		model = mUserInfoService.getFullModel(model.getId());
		request.setAttribute("model", model);
		
		if(model.getLevel() != null){
			request.setAttribute("level", MUserLevelStatus.getMap().get(model.getLevel().toString()));
		}
		return "user/person";
	}
	
	
	@RequestMapping("updatePwd")
	public void updatePwd(PrintWriter out, MUserPwdConditions con) {
		MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
		Integer code = mUserInfoService.updatePwd(model.getId(), con.getOldPwd(), con.getPwd(), con.getType());
		String msg = null;
		boolean result = true;
		if(code == 2){
			msg = "支付密码和登录密码不能相同";
			result = false;
		}else if(code == 3){
			msg = "原密码不正确，请检查";
			result = false;
		}
		BaseController.ajaxJsonResponse(out, result, msg);
	}
	
	//修改用户信息
	@RequestMapping(value = "update")
	public void updateMember(PrintWriter out, MUserInfoModel model){
		try{
			MUserInfoModel m = SpringSecurityUtils.getCurrentUser();
			model.setId(m.getId());
			this.mUserInfoService.updateMember(model);
			BaseController.ajaxJsonResponse(out, true, "操作成功");
		}catch(Exception e){
			BaseController.ajaxJsonResponse(out, false, "系统异常");
		}
	}
	
	//页面跳转
	@RequestMapping(value = "service")
	public String service(){
		return "user/service";
	}
	
	//获取账号信息
	@RequestMapping(value = "getAccount")
	public void getAccount(HttpServletResponse response, MUserPwdConditions con) {
		MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
		model = mUserInfoService.getFullModel(model.getId());
		BaseController.writeToPage(JSONUtils.toJson(model).toString(), response);
	} 
	
}
