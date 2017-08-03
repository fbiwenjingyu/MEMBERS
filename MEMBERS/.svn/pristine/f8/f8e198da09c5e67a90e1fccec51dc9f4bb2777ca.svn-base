package com.daboo.controller.membermanage;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.daboo.business.service.MBusinessCenterService;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.service.MUserAccountService;
import com.daboo.reward.service.MemberLeverService;
import com.daboo.system.service.SysDictService;
import com.daboo.user.enums.MUserLevelStatus;
import com.daboo.user.enums.MUserlevel;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.model.MUserRegModel;
import com.daboo.user.service.MUserInfoService;
import com.daboo.user.service.MUserlevelManageService;

@Controller
@RequestMapping(value = "/memberManage")
public class MemberManageController extends BaseController {
	Logger log = LoggerFactory.getLogger(MemberManageController.class);
	
	@Autowired
	MUserlevelManageService mUserlevelManageService;
	@Autowired
	MUserInfoService mUserInfoService;
	@Autowired
	MemberLeverService memberLeverService;
	@Autowired
	private MBusinessCenterService businessCenterService;
	@Autowired
	MUserAccountService mUserAccountService;
	@Autowired
	SysDictService sysDictService;
	
	/**
	 * 跳转到会员开通
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toMemberAdd")
	public String toMemberAdd(HttpServletRequest request){
		request.setAttribute("levelMap", JSONUtils.toCombo(MUserlevel.getMap()));
		//激活所需的现金
		String rechargeAmountVallue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		request.setAttribute("rAmount", rechargeAmountVallue);
		//激活所需的现金比例
		String cashScale = sysDictService.getDetailValue("CASH_SCALE","CASH_SCALE");
		request.setAttribute("cashScale", cashScale);
		
		return "membermanage/memberAdd";
	}
	
	/**
	 * 查询未开通会员
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findUnOpenUser")
	public void findUnOpenUser(HttpServletRequest request,HttpServletResponse response){
		try{
			String page=request.getParameter("pageNumber");
			String rows=request.getParameter("pageSize");
			page=page==null?"1":page;
			rows=rows==null?"10":rows;
			
			PageObject po=new PageObject();
			po.setCurrPage(Integer.parseInt(page));
			po.setPageSize(Integer.parseInt(rows));
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			po.getCondition().put("recommendId", user.getId());
			po.getCondition().put("accountNo", request.getParameter("accountNo"));
			po.getCondition().put("realName", request.getParameter("realName"));
			po.getCondition().put("startDate", request.getParameter("startDate"));
			po.getCondition().put("endDate", request.getParameter("endDate"));
			po.getCondition().put("level", request.getParameter("level"));
			writeToPage(JSONUtils.toJson(mUserInfoService.findUnOpenUserForVip(po)), response);
		} catch(Exception e){
			log.error("查询未开通会员异常", e);
		}
	}
	
	/**
	 * 查询已开通会员
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findOpenUser")
	public void findOpenUser(HttpServletRequest request,HttpServletResponse response){
		try{
			String page=request.getParameter("pageNumber");
			String rows=request.getParameter("pageSize");
			page=page==null?"1":page;
			rows=rows==null?"10":rows;
			
			PageObject po=new PageObject();
			po.setCurrPage(Integer.parseInt(page));
			po.setPageSize(Integer.parseInt(rows));
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			po.getCondition().put("recommendId", user.getId());
			po.getCondition().put("accountNo", request.getParameter("accountNo"));
			po.getCondition().put("realName", request.getParameter("realName"));
			po.getCondition().put("startDate", request.getParameter("startDate"));
			po.getCondition().put("endDate", request.getParameter("endDate"));
			po.getCondition().put("level", request.getParameter("level"));
			writeToPage(JSONUtils.toJson(mUserInfoService.findOpenUserForVip(po)), response);
		} catch(Exception e){
			log.error("查询已开通会员异常", e);
		}
	}
	
	//保存用户消息
		@RequestMapping(value = "addMember")
		public void addMember(HttpServletRequest request,HttpServletResponse response, MUserRegModel model){
			try{
				MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
				user= mUserInfoService.getFullModel(user.getId());
				if (user.getType() == 1 ||user.getType() == 2){
					model.setRecommendNo(user.getAccountNo());
				}
				if(StringUtils.isNotBlank(model.getRecommendNo())){
					MUserInfoModel model2 = mUserInfoService.findUserByAccountNo(model.getRecommendNo());
					if (model2==null) {
						ajaxJsonResponse(response,false,"推荐人不存在");
					}else{
						model.setRecommendId(model2.getId());
						model.setAgentId(user.getId());
					}
					
				}else {
					model.setRecommendId(user.getId());
					model.setAgentId(-1);
				}
				model.setAccountNo(model.getAccountNo().trim());
				this.mUserInfoService.addMemberReg(model);
				ajaxJsonResponse(response,true,"操作成功");
				
			}catch(Exception e){
				ajaxJsonResponse(response,false,"系统异常");
			}
		}
		
		//会员是否已激活
		@RequestMapping(value = "checkRecommend")
		public void checkRecommend(HttpServletResponse response, String recommendNo){
			try{
				if(StringUtils.isNotBlank(recommendNo)){
					Boolean flag = mUserInfoService.checkRecommend(recommendNo);
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
		
		//会员是否存在
		@RequestMapping(value = "checkMember")
		public void checkMember(HttpServletResponse response, String accountNo){
			try{
				if(StringUtils.isNotBlank(accountNo)){
					Boolean flag = mUserInfoService.checkMember(accountNo);
					if (flag) {
						ajaxJsonResponse(response,false,"验证失败");
					}else {
						ajaxJsonResponse(response,true,"验证成功");
					}
				}
			}catch(Exception e){
				ajaxJsonResponse(response,false,"验证出错");
			}
		}
	
	/**
	 * 跳转到会员信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toMemberInfo")
	public String toMemberInfo(HttpServletRequest request){
		request.setAttribute("levelMap", JSONUtils.toCombo(MUserlevel.getMap()));
		String rechargeAmountVallue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		request.setAttribute("rAmount", rechargeAmountVallue);
		return "membermanage/memberInfo";
	}
	
	/**
	 * 查询会员信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findMemberInfo")
	public void findMemberInfo(HttpServletRequest request,HttpServletResponse response){
		try{
			String page=request.getParameter("pageNumber");
			String rows=request.getParameter("pageSize");
			page=page==null?"1":page;
			rows=rows==null?"10":rows;
			
			PageObject po=new PageObject();
			po.setCurrPage(Integer.parseInt(page));
			po.setPageSize(Integer.parseInt(rows));
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			if (request.getParameter("totalId").equals("0")) {
				po.getCondition().put("recommendId", user.getId());
			}else {
				MUserInfoModel model=mUserInfoService.findUserByAccountNo(request.getParameter("totalId"));
				if (model!=null) {
					po.getCondition().put("recommendId", model.getId());
				}else {
					writeToPage(JSONUtils.toJson(new GridDataModel(null, 0)), response);
					return;
				}
			}
			po.getCondition().put("accountNo", request.getParameter("accountNo"));
			po.getCondition().put("realName", request.getParameter("realName"));
			po.getCondition().put("startDate", request.getParameter("startDate"));
			po.getCondition().put("endDate", request.getParameter("endDate"));
			po.getCondition().put("level", request.getParameter("level"));
			po.getCondition().put("type", request.getParameter("type"));
			if (request.getParameter("level").equals("null")) {
				po.getCondition().put("level", "");
			}
			writeToPage(JSONUtils.toJson(mUserInfoService.findTeamMember(po)), response);
		} catch(Exception e){
			log.error("查询未开通会员异常", e);
		}
	}
	
	/**
	 * 跳转到添加好友
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toFriendAdd")
	public String toFriendAdd(HttpServletRequest request){
		request.setAttribute("levelMap", JSONUtils.toCombo(MUserLevelStatus.getMap()));
		return "membermanage/friendAdd";
	}

	
	
	/**
	 * 进入申请服务中心页面
	 * @FunName toApplyBC
	 * @param request
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月3日
	 */
	@OpLog(logDesc="进入申请服务中心页面")
	@RequestMapping("toApplyBC")
	public String toApplyBC(HttpServletRequest request){
		MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
		JSONObject result=businessCenterService.checkIsBusinessCenter(user.getAccountNo());
		
		request.setAttribute("result", result);
		
		return "membermanage/service";
	}
	
	/**
	 * 申请为服务中心操作
	 * @FunName applyToBC
	 * @param request
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月3日
	 */
	@OpLog(logDesc="申请为服务中心操作")
	@RequestMapping("applyToBC")
	public String applyToBC(HttpServletRequest request){
		boolean flag=false;
		String resultMes="";
		
		try {
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			
			flag=businessCenterService.applyToBusinessCenter(user.getAccountNo());
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("申请为服务中心操作异常",e);
			flag=false;
			resultMes=e.getMessage();
		}
		
		if(flag){
			request.setAttribute(OPTION_RESULT, SUCCESS_RESULT);
		}else{
			request.setAttribute(OPTION_RESULT, FAILD_RESULT);
			request.setAttribute(FAILD_MESSAGE, resultMes);
		}
		return "membermanage/serviceResult";
	}
	
	// 验证账户余额
	@RequestMapping(value = "checkAccount")
	public void checkAccount(HttpServletResponse response, Integer type, Integer memberId) {
		MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
		try {
			MUserRegModel regModel = mUserInfoService.getUserRegModel(memberId);
			model = mUserAccountService.findUserAccountByAccountNo(model
					.getAccountNo());
			writeToPageForResponseBody(makeJsonReResponse(checkAccount(model, type,regModel.getRegNum()), ""),
					response);
		} catch (Exception e) {
			log.error("验证账户余额出错" + e);
			writeToPageForResponseBody(makeJsonReResponse("300", "系统异常"),
					response);
		}
	}
	
	// 激活会员
		@RequestMapping(value = "openMember")
		public void openMember(HttpServletResponse response, Integer type ,Integer memberId,String password) {
			MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
			try {
				MUserRegModel regModel = mUserInfoService.getUserRegModel(memberId);
				model = mUserAccountService.findUserAccountByAccountNo(model
						.getAccountNo());
				String checkResult=checkAccount(model, type,regModel.getRegNum());
				if (checkResult=="200") {
					//激活会员
					mUserlevelManageService.openMember(model.getId(),memberId,type,password,SpringSecurityUtils.getCurrentUserName());
					writeToPageForResponseBody(makeJsonReResponse("200", "激活成功"),
							response);
				}else if(checkResult=="201"){
					writeToPageForResponseBody(makeJsonReResponse("201", "账户不存在"),
							response);
				}else if(checkResult=="202"){
					writeToPageForResponseBody(makeJsonReResponse("202", "账户余额不足"),
							response);
				}
			} catch (BusinessException e) {
				log.error("激活会员出错" + e);
				writeToPageForResponseBody(makeJsonReResponse("300", e.getMessage()),
						response);
			}
		}
		
	// check账户余额
	/**
	 * 
	 * @param model
	 * @param type
	 * @param buyNum 购买数量
	 * @return
	 */
	public String checkAccount(MUserInfoModel model, Integer type,Integer buyNum) {
		//激活所需的现金,单位元
		String rechargeAmountVallue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		int rAmount=Integer.parseInt(rechargeAmountVallue)*buyNum;
		//激活所需的现金比例
		String cashScale = sysDictService.getDetailValue("CASH_SCALE","CASH_SCALE");
		int cashScaleInt=Integer.parseInt(cashScale);
		int ba=rAmount*cashScaleInt/100;
		int ss=rAmount-rAmount*cashScaleInt/100;
				
		if (model == null) {
			//账户不存在
			return "201";
		} else {
			if (type == 1) {
				if (model.getBalance().intValue()/100 >= rAmount) {
					return "200";
				}
			} else if (type == 2) {
				if (model.getBalance().intValue()/100 >= ba
						&& model.getShoppingScore().intValue() >= ss) {
					return "200";
				}
			} else if (type == 3) {
				if (model.getBalance().intValue()/100 >= ba
						&& model.getGeneralizeScore().intValue() >= ss) {
					return "200";
				}
			}
			//余额不足
			return "202";
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
	
	/**
	 * 进入再次购买主页
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	@OpLog(logDesc="进入再次购买页面")
	@RequestMapping(value="/toBuyAgain")
	public String toBuyAgain(){
		return "membermanage/buy";
	}
	
	/**
	 * 进入再次购买Form页面
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	@OpLog(logDesc="进入再次购买Form页面")
	@RequestMapping(value="/toBuyForm")
	public String toBuyForm(HttpServletRequest request){
		//激活所需的现金
		String rechargeAmountVallue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		request.setAttribute("rAmount", rechargeAmountVallue);
		//激活所需的现金比例
		String cashScale = sysDictService.getDetailValue("CASH_SCALE","CASH_SCALE");
		request.setAttribute("cashScale", cashScale);
		return "membermanage/buyForm";
	}
	
	@OpLog(logDesc="确定再次购买操作")
	@RequestMapping("/doBuyAgain")
	public String doBuyAgain(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		boolean flag=false;
		String resultMes="";
		String buyNum=request.getParameter("buyNum");
		String type=request.getParameter("type");
		String password=request.getParameter("password");
		
		try {
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			
			flag=mUserAccountService.doBuyAgain(password,user.getId(),Integer.parseInt(buyNum),Integer.parseInt(type),SpringSecurityUtils.getCurrentUserName());
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用再次购买接口操作异常",e);
			flag=false;
			resultMes=e.getMessage();
		}
		
		if(flag){
			redirectAttributes.addFlashAttribute(OPTION_RESULT, SUCCESS_RESULT);
		}else{
			redirectAttributes.addFlashAttribute(OPTION_RESULT, FAILD_RESULT);
			redirectAttributes.addFlashAttribute(FAILD_MESSAGE, resultMes);
		}
		return "redirect:goBuyResult";
		
	}
	
	@RequestMapping("/goBuyResult")
	@OpLog(logDesc = "进入再次购买结果页面")
	public String goBuyResult(){
		return "membermanage/buyResult";
	}
	
	/**
	 * 分页获取购买记录信息
	 * @FunName getSubAccountList
	 * @param request
	 * @param response
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	@OpLog(logDesc="分页获取购买记录信息")
	@RequestMapping("/getSubAccountList")
	public void getSubAccountList(HttpServletRequest request,HttpServletResponse response){
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		String startDate=request.getParameter("startDate");//注册开始日期
		String endDate=request.getParameter("endDate");//注册结束日期
		
		pageNumber=pageNumber==null?"1":pageNumber;
		pageSize=pageSize==null?"10":pageSize;
		
		try {
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			
			PageObject po=getPageObject(request);
			po.setCurrPage(Integer.parseInt(pageNumber));
			po.setPageSize(Integer.parseInt(pageSize));
			if(!StringUtils.isEmpty(startDate)){
				po.getCondition().put("startDate", startDate);
			}
			if(!StringUtils.isEmpty(endDate)){
				po.getCondition().put("endDate", endDate);
			}
			
			po.getCondition().put("userId", user.getId());
			
			writeToPage(JSONUtils.toJson(mUserInfoService.findBuyListByConditions(po)), response);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("分页获取购买记录信息异常",e);
			writeToPage(makeBooJson(false,"分页获取购买记录信息异常"),response);
		}	
	}
	
	private String makeBooJson(boolean result,String errMsg){
		return "{\"result\":"+result+",\"msg\":\"" + errMsg + "\"}";
	}
	
	/**
	 * 再次购买时检查账户余额是否足够
	 * @FunName checkAccountEnough
	 * @param response
	 * @param type 支付方式
	 * @param num 购买数量
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	@OpLog(logDesc="再次购买时检查账户余额是否足够")
	@RequestMapping(value = "/checkAccountEnough")
	public void checkAccountEnough(HttpServletResponse response, Integer type , Integer num) {
		MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
		try {
			model = mUserAccountService.findUserAccountByAccountNo(model.getAccountNo());
			
			//购买所需的现金,单位元
			String rechargeAmountVallue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
			int rAmount=Integer.parseInt(rechargeAmountVallue)*num;
			//购买所需的现金比例
			String cashScale = sysDictService.getDetailValue("CASH_SCALE","CASH_SCALE");
			int cashScaleInt=Integer.parseInt(cashScale);
			int ba=rAmount*cashScaleInt/100;
			int ss=rAmount-rAmount*cashScaleInt/100;
					
			String result=null;
			if (model == null) {
				result=makeJsonReResponse("301","账户不存在");
			} else {
				if (type == 1) {//现金币
					if (model.getBalance().intValue()/100 < rAmount) {
						result=makeJsonReResponse("200","账户现金币不足，请先充值");
					}
				} else if (type == 2) {//现金币+博豆
					if (model.getBalance().intValue()/100 < ba) {
						result=makeJsonReResponse("200","账户现金币不足，请先充值");
					}else if(model.getShoppingScore().intValue() < ss){
						result=makeJsonReResponse("201","账户博豆不足，请先充值足够的博豆");
					}
					
				} else if (type == 3) {//现金币+积分
					if (model.getBalance().intValue()/100 < ba) {
						result=makeJsonReResponse("200","账户现金币不足，请先充值");
					}else if(model.getGeneralizeScore().intValue() < ss){
						result=makeJsonReResponse("202","账户积分不足，请先转换足够的积分");
					}
				}
			}
			
			if(com.daboo.core.util.StringUtils.isEmpty(result)){//如果结果为空，则说明校验通过
				writeToPage(makeJsonReResponse("100", "校验通过"), response);
			}else{
				writeToPage(result, response);
			}
			
		} catch (Exception e) {
			log.error("验证账户余额出错" + e);
			writeToPage(makeJsonReResponse("300", "系统异常"), response);
		}
	}
	
	
	
}
