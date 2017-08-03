package com.daboo.controller.finance;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daboo.core.util.DateUtils;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.util.StringUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.enums.AccountEnum;
import com.daboo.reward.enums.AccountTypeEnum;
import com.daboo.reward.service.MUserAccountService;

/**
 * @Description 会员流水查询controller
 * @author dingyang
 * @version 1.0
 * @Create Date 2015年11月24日
 */
@Controller
@RequestMapping(value="flow")
public class MFlowController extends BaseController {
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MUserAccountService accountService;
	
	@OpLog(logDesc="进入查询会员账户页面")
	@RequestMapping("/init")
	public String init(){
		log.info("进入查询会员账户页面");
		return "flow/init";
	}
	
	@OpLog(logDesc="进入查询系统收支页面")
	@RequestMapping("/inOutInit")
	public String inOutInit(){
		log.info("进入查询系统收支页面");
		return "flow/inOutInit";
	}
	
	@OpLog(logDesc="查询系统收支信息")
	@RequestMapping("/inOutList")
	public void inOutList(HttpServletRequest request,HttpServletResponse response){
		String startDate=StringUtils.trim(request.getParameter("startDate"));
		String endDate=StringUtils.trim(request.getParameter("endDate"));
		try {
			PageObject po=getPageObject(request);
			
			if(!StringUtils.isEmpty(startDate)){
				po.getCondition().put("startDate", startDate);
			}
			if(!StringUtils.isEmpty(endDate)){
				po.getCondition().put("endDate", endDate);
			}
			
			writeToPage(JSONUtils.toJson(accountService.selectInOutByConditions(po)), response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询系统收支异常",e);
			writeToPage(makeJsonResponse(false,"查询系统收支异常"),response);
		}
	}
	
	@OpLog(logDesc="进入查询系统收支详情页面")
	@RequestMapping("/inOutDetailInit")
	public String inOutDetailInit(HttpServletRequest request) throws ParseException{
		log.info("进入查询系统收支详情页面");
		request.setAttribute("collectDate", DateUtils.getDate(request.getParameter("collectDate"), "yyyy-MM-dd HH:mm:ss"));
		return "flow/inOutDetailInit";
	}
	
	@OpLog(logDesc="查询系统收支详情信息")
	@RequestMapping("/inOutDetailList")
	public void inOutDetailList(HttpServletRequest request,HttpServletResponse response){
		String accountNo=StringUtils.trim(request.getParameter("accountNo"));
		String collectDate=StringUtils.trim(request.getParameter("collectDate"));
		
		try {
			PageObject po=getPageObject(request);
			
			if(!StringUtils.isEmpty(accountNo)){
				po.getCondition().put("accountNo", accountNo);
			}
			if(!StringUtils.isEmpty(collectDate)){
				po.getCondition().put("collectDate", collectDate);
			}
			
			writeToPage(JSONUtils.toJson(accountService.selectInOutDetailByConditions(po)), response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询系统收支详情信息异常",e);
			writeToPage(makeJsonResponse(false,"查询系统收支详情信息异常"),response);
		}
	}
	
	@OpLog(logDesc="查询会员账户信息")
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		String accountNo=StringUtils.trim(request.getParameter("accountNo"));
		String startDate=StringUtils.trim(request.getParameter("startDate"));
		String endDate=StringUtils.trim(request.getParameter("endDate"));
		try {
			PageObject po=getPageObject(request);
			
			if(!StringUtils.isEmpty(accountNo)){
				po.getCondition().put("accountNo", accountNo);
			}
			if(!StringUtils.isEmpty(startDate)){
				po.getCondition().put("startDate", startDate);
			}
			if(!StringUtils.isEmpty(endDate)){
				po.getCondition().put("endDate", endDate);
			}
			writeToPage(JSONUtils.toJson(accountService.selectAccountByConditions(po)), response);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询会员流水记录异常",e);
			writeToPage(makeJsonResponse(false,"查询会员账户信息列表异常"),response);
		}
	}
	
	@OpLog(logDesc="进入查询会员流水页面")
	@RequestMapping("/detailInit")
	public String detailInit(HttpServletRequest request){
		log.info("进入查询会员流水页面");
		request.setAttribute("accountTypeMap", JSONUtils.toJson(AccountTypeEnum.toMap()));
		request.setAttribute("accountTypeCombo", JSONUtils.toCombo(AccountTypeEnum.toMap()));
		request.setAttribute("accountMap", JSONUtils.toJson(AccountEnum.toInoutTypeAndInoutDescMap()));
		//request.setAttribute("accountCombo", JSONUtils.toCombo(AccountEnum.toInoutTypeAndInoutDescMap()));
		request.setAttribute("userId", request.getParameter("uId"));
		request.setAttribute("uAN", request.getParameter("uAN"));
		
		return "flow/detailInit";
	}
	
	@OpLog(logDesc="查询会员流水信息")
	@RequestMapping("/detailList")
	public void detailList(HttpServletRequest request,HttpServletResponse response){
		
		String userId=StringUtils.trim(request.getParameter("userId"));
		String accountType=StringUtils.trim(request.getParameter("accountType"));
		//String inoutType=StringUtils.trim(request.getParameter("inoutType"));
		String startDate=StringUtils.trim(request.getParameter("startDate"));
		String endDate=StringUtils.trim(request.getParameter("endDate"));
		
		try {
			
			PageObject po=getPageObject(request);
			
			if(!StringUtils.isEmpty(userId)){
				po.getCondition().put("userId", userId);
			}
			if(!StringUtils.isEmpty(accountType)){
				po.getCondition().put("accountType", accountType);
			}
			if(!StringUtils.isEmpty(startDate)){
				po.getCondition().put("startDate", startDate);
			}
			if(!StringUtils.isEmpty(endDate)){
				po.getCondition().put("endDate", endDate);
			}
			writeToPage(JSONUtils.toJson(accountService.selectFlowByConditions(po)), response);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询会员流水记录异常",e);
			writeToPage(makeJsonResponse(false,"查询会员流水信息列表异常"),response);
		}
	}
}
