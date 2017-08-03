package com.daboo.controller.finance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daboo.controller.message.OpinionAction;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.service.MemberRewardService;
/**
 * 奖励查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/reward")
public class MRewardManagerController extends BaseController {

	//日志对象
	private Logger logger = LoggerFactory.getLogger(OpinionAction.class);
	@Autowired
	private MemberRewardService memberRewardService;
	/**
	 * @Description 获取意见反馈列表
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="跳转至系统奖励列表")
	@RequestMapping(value = "/rewardList" , method =RequestMethod.GET)
	public String goRewardList(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("跳转至系统奖励列表");
		
		return "reward/init";
	}
	/**
	 * @Description 获取意见反馈列表
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="查询系统奖励列表")
	@RequestMapping(value = "/rewardList" , method =RequestMethod.POST)
	public String getRewardList(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("查询系统奖励列表");
		PageObject po = getPageObject(request);
		if(StringUtils.isNotBlank(request.getParameter("accountNo"))){
	       po.getCondition().put("accountNo", request.getParameter("accountNo"));
		}
		if(StringUtils.isNotBlank(request.getParameter("startDate"))){
			po.getCondition().put("startDate", request.getParameter("startDate"));
		}
		if(StringUtils.isNotBlank(request.getParameter("endDate"))){
			po.getCondition().put("endDate", request.getParameter("endDate"));
		}
		GridDataModel data = memberRewardService.findByConditions(po);
		writeToPage(JSONUtils.toJson(data), response);
		return "reward/init";
	}
	/**
	 * @Description 获取意见反馈列表
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="跳转至系统奖励详情")
	@RequestMapping(value = "/rewardDetail/{id}/{date}" , method =RequestMethod.GET)
	public String goReward(@PathVariable Integer id,@PathVariable String date,HttpServletRequest request, HttpServletResponse response){
		
		logger.info("跳转至系统奖励详情");
		request.setAttribute("id",id);
		//SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("date", date);
		return "reward/detail";
	}
	
	/**
	 * @Description 获取意见反馈列表
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="获取系统奖励详情")
	@RequestMapping(value = "/rewardDetail" , method =RequestMethod.POST)
	public String getRewardDetail(Integer userId,HttpServletRequest request, HttpServletResponse response){
		
		logger.info("获取系统奖励详情");
		PageObject po = getPageObject(request);
		po.getCondition().put("userId", userId);
		if(StringUtils.isNotBlank(request.getParameter("rewardType"))){
			po.getCondition().put("rewardType", request.getParameter("rewardType"));
		}else{
			po.getCondition().put("rewardTypes","101,102,103");
		}
		if(StringUtils.isNotBlank(request.getParameter("date"))){
			po.getCondition().put("date",request.getParameter("date"));
		}
		GridDataModel data = memberRewardService.findRewardByConditions(po);
		writeToPage(JSONUtils.toJson(data), response);
		return "reward/detail";
	}
}
