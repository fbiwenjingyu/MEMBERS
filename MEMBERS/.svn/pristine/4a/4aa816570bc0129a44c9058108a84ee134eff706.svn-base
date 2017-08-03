package com.daboo.controller.user;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.system.service.SysDictService;
import com.daboo.user.enums.MUserLevelStatus;
import com.daboo.user.enums.MUserTypeStatus;
import com.daboo.user.model.MUserConditions;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.model.MUserRegModel;
import com.daboo.user.service.MUserInfoService;

@Controller
@RequestMapping(value = "/user")
public class MUserController extends BaseController {
	Logger log = LoggerFactory.getLogger(MUserController.class);
	
	@Autowired
	MUserInfoService mUserInfoService;
	@Autowired
	SysDictService sysDictService;
	@Autowired
	MemberRewardService memberRewardService;
	
	
	/**
	  * @Description: 获取用户消息列表
	  * @param request
	  * @param response
	  * @param fxConditions
	  * @author 肖勇
	  * @date 2015-10-22
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	@OpLog(logDesc="获取用户消息列表")
	public void findByConditions(HttpServletRequest request,HttpServletResponse response , MUserConditions conditions){
		try{
			PageObject po = getPageObject(request);
			po.getCondition().put("accountNo", conditions.getAccountNo());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if(StringUtils.isNotBlank(conditions.getStartDate())){
					Date sDate = format.parse(conditions.getStartDate());
					po.getCondition().put("startDate", sDate);
				}
				
				if(StringUtils.isNotBlank(conditions.getEndDate())){
					Date eDate = format.parse(conditions.getEndDate());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(eDate);
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					po.getCondition().put("endDate", calendar.getTime());
				}
			}catch(ParseException e){}
			
			writeToPage(JSONUtils.toJson(mUserInfoService.findUserInfoPaging(po)), response);
		} catch(Exception e){
			log.error("查询用户消息列表异常", e);
		}
	}
	
	//跳转到列表
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		request.setAttribute("levelMap", JSONUtils.toJson(MUserLevelStatus.getMap()));
		request.setAttribute("typeMap", JSONUtils.toJson(MUserTypeStatus.getMap()));
		String rechargeAmountVallue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		request.setAttribute("rAmount", rechargeAmountVallue);
		return "user/init";
	}
	
	/**
	 * 
	  * @Description: 冻结激活会员
	  * @param Response
	  * @param id  会员ID
	  * @param type -1/1   冻结/激活
	  * @return
	  * @author 肖勇
	  * @date 2015-10-26
	 */
	@RequestMapping(value = "frozen")
	public String frozen(HttpServletResponse response, Integer id, Integer type){
		try{
			if(id != null && type != null){
				mUserInfoService.frozen(id, type);
				writeToPage(makeJsonResponse(true, "操作成功") , response);
			}else{
				writeToPage(makeJsonResponse(true, "操作失败，参数不能为空") , response);
			}
		}catch(Exception e){
			writeToPage(makeJsonResponse(false, "操作失败") , response);
		}
		return "user/init";
	}
	
	
	
	//用户注册相关
	//跳转到注册页面
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request){
		return "user/add";
	}
	
	//注册用户
	@RequestMapping(value = "addMember")
	public String addMember(HttpServletRequest request, MUserRegModel model){
		try{
			this.mUserInfoService.addMemberReg(model);
			return "success";
		}catch(Exception e){
			request.setAttribute("errorMsg","新增会员失败");
			return "user/add";
		}
	}
	
	//会员是否存在
	@RequestMapping(value = "checkMember")
	public void checkMember(HttpServletResponse response, String accountNo){
		try{
			if(StringUtils.isNotBlank(accountNo)){
				Boolean flag = mUserInfoService.checkMember(accountNo);
				writeToPage(flag.toString() , response);
			}
		}catch(Exception e){
			writeToPage(makeJsonResponse(false, "操作失败") , response);
		}
	}
	
	//会员是否存在并激活
	@RequestMapping(value = "checkRecommend")
	public void checkRecommend(HttpServletResponse response, String code, Integer type){
		try{
			if(StringUtils.isNotBlank(code)){
				Integer userId = mUserInfoService.checkRecommend(code, type);
				writeToPage(userId+"", response);
			}
		}catch(Exception e){
			writeToPage(makeJsonResponse(false, "操作失败") , response);
		}
	}
	
	//修改会员信息
	@RequestMapping(value = "edit")
	public String add(HttpServletRequest request, Integer id){
		MUserInfoModel model = mUserInfoService.findModelById(id);
		request.setAttribute("model", model);
		return "user/edit";
	}
	
	//修改用户信息
	@RequestMapping(value = "update")
	public String updateMember(HttpServletRequest request, MUserInfoModel model){
		try{
			this.mUserInfoService.updateMember(model);
			return "success";
		}catch(Exception e){
			request.setAttribute("errorMsg","编辑会员失败");
			return "user/edit";
		}
	}
	//会员详情
	@RequestMapping(value = "show")
	public String show(HttpServletRequest request, Integer id){
		request.setAttribute("typeMap", JSONUtils.toJson(MUserLevelStatus.getMap()));
		MUserInfoModel model = mUserInfoService.findModelById(id);
		request.setAttribute("model", model);
		return "user/show";
	}
	
	/**
	 * 
	  * @Description: 跳转到查询会员页面
	  * @return
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	@RequestMapping(value = "toUserRela")
	@OpLog(logDesc="跳转到查询会员页面")
	public String toUserRela(HttpServletRequest request){
		request.setAttribute("userType", JSONUtils.toJson(MUserTypeStatus.getMap()));
		request.setAttribute("userLevel", JSONUtils.toJson(MUserLevelStatus.getMap()));
		return "user/userRelaGrid";
	}
	
	/**
	 * 
	  * @Description: 查询会员关系
	  * @param response
	  * @param memberNo
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	@RequestMapping(value = "queryUserRela")
	@ResponseBody
	@OpLog(logDesc="查询会员关系")
	public List<Map<String, Object>> queryUserRela(HttpServletResponse response,String memberNo){
		try{
			if(memberNo==null || "".equals(memberNo.trim())){
				memberNo="4009969595";
			}
			
			Map<String, Object> up=null;
			Map<String, Object> cur=mUserInfoService.findUserRelaByMemberNo(memberNo);
			List<Map<String, Object>> down=new ArrayList<Map<String, Object>>();
			if(cur!=null){
				if(cur.get("parent")!=null){
					up=mUserInfoService.findUserRelaById((Integer)cur.get("parent"));
					
					if(up!=null){
						down.add(up);
					}
				}
				
				down.add(cur);
				
				int start=down.size()-1;
				int end=down.size();
				int num=10-end;
				for(int i=0;i<num && start<end;i++){
					for(;start<end;start++){
						List<Map<String, Object>> tmp=mUserInfoService.findUserRelaByPid((Integer)down.get(start).get("key"));
						if(tmp!=null){
							down.addAll(tmp);
						}
					}
					
					end=down.size();
				}
				
				return down;
			}
		} catch(Exception e){
			log.error("查询会员关系异常",e);
		}
		
		return null;
	}
	
	/**
	 * 
	  * @Description: 查询会员关系
	  * @param response
	  * @param memberNo
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	@RequestMapping(value = "queryUserRelaGrid")
	@ResponseBody
	@OpLog(logDesc="查询会员关系")
	public GridDataModel queryUserRelaGrid(HttpServletRequest request,HttpServletResponse response,String memberNo){
		GridDataModel down=null;
		try{
			if(memberNo==null){
				return null;
			}
			if("".equals(memberNo.trim())){
				memberNo="4009969595";
			}
			
			Map<String, Object> cur=mUserInfoService.findUserRelaByMemberNo(memberNo);
			if(cur!=null){
				PageObject po = getPageObject(request);
				po.getCondition().put("pid", cur.get("key"));
				down=mUserInfoService.findUserRelaByPidPage(po);
				if(down!=null){
					for(Map<String, Object> tmp:(List<Map<String, Object>>)down.getRows()){
						tmp.put("RECOMMEND_NO", memberNo);
					}
				}
			}
		} catch(Exception e){
			log.error("查询会员关系异常",e);
		}
		
		return down;
	}
	
	@RequestMapping(value = "initPwd")
	public void initPwd(HttpServletRequest request,PrintWriter out, Integer id){
		log.debug("method: initPwd() ");
		String msg = "操作成功";
		boolean result = true;
		try {
			if(id != null){
				MUserInfoModel model = new MUserInfoModel();
				model.setId(id);
				model.setPassword("111111");
				model.setPayPassword("222222");
				mUserInfoService.updateMember(model);
			}
		} catch (Exception e) {
			msg = "系统发生异常！";
			result = false;
		}
		ajaxJsonResponse(out, result, msg);
	}
	
		@RequestMapping(value = "nodeEdit")
		public String nodeEdit(HttpServletRequest request, Integer id){
			MUserInfoModel model = mUserInfoService.findModelById(id);
			request.setAttribute("model", model);
			return "user/nodeUpdate";
		}
		
		//修改节点信息
		@RequestMapping(value = "nodeUpdate")
		public String nodeUpdate(HttpServletRequest request, Integer userId , String accountNo){
			MUserInfoModel model = mUserInfoService.findUserByAccountNo(accountNo);
			try {
				if (model==null) {
					request.setAttribute("errorMsg","用户不存在");
					return "user/nodeUpdate";
				}
				memberRewardService.nodeAdjustment(userId, model.getId());
				return "success";
			} catch (BusinessException e) {
				log.error(e.getMessage());
				request.setAttribute("errorMsg",e.getMessage());
				return "user/nodeUpdate";
			}
		}
}
