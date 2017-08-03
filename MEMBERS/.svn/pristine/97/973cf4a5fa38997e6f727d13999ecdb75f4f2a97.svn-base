package com.daboo.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.daboo.core.util.DateUtils;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.model.MTaskMonitorModel;
import com.daboo.reward.service.TaskMonitorService;
import com.daboo.user.model.MTaskConditions;

@Controller
@RequestMapping(value = "/task")
public class MTaskController extends BaseController {
	Logger log = LoggerFactory.getLogger(MTaskController.class);
	
	@Autowired
	TaskMonitorService taskMonitorService;
	
	/**
	  * @Description: 获取任务信息列表
	  * @param request
	  * @param response
	  * @param fxConditions
	  * @author 肖勇
	  * @date 2015-10-22
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	@OpLog(logDesc="获取任务信息列表")
	public void findTaskByConditions(HttpServletRequest request,HttpServletResponse response, MTaskConditions conditions){
		try{
			PageObject po = getPageObject(request);
			po.getCondition().put("runState", conditions.getRunState());
			po.getCondition().put("taskType", conditions.getTaskType());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if(StringUtils.isNotBlank(conditions.getStartDate())){
					Date sDate = format.parse(conditions.getStartDate());
					po.getCondition().put("startDate", sDate);
				}
				
				if(StringUtils.isNotBlank(conditions.getEndDate())){
					Date eDate = format.parse(conditions.getEndDate());
					po.getCondition().put("endDate", eDate);
				}
			}catch(ParseException e){}
			writeToPage(JSONUtils.toJson(taskMonitorService.findTaskPaging(po)), response);
		} catch(Exception e){
			log.error("查询用户消息列表异常", e);
		}
	}
	
	@RequestMapping(value = "reRun")
	@ResponseBody
	@OpLog(logDesc="重新执行任务")
	public void reRun(HttpServletRequest request,HttpServletResponse response,Integer id){
		MTaskMonitorModel task = taskMonitorService.getMTaskMonitorModelById(id);
		//执行任务
		taskMonitorService.reRunTask(task);
		writeToPage(makeJsonResponse(true, "操作成功") , response);
	}
	
	//跳转到列表
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		return "task/taskInit";
	}
	
	@RequestMapping("/showAddTask")
	@OpLog(logDesc="进入新增任务监控页面")
	public String showAddTask(HttpServletRequest request){
		
		return "task/addTask";
	}
	
	@RequestMapping("/addNewTask")
	@OpLog(logDesc="新增任务监控操作")
	public String addNewTask(HttpServletRequest request){
		boolean result = false;
		String taskName=request.getParameter("taskName");
		String collectDate=request.getParameter("collectDate");
		String taskType=request.getParameter("taskType");
		
		Date cDate=null;
		String errMes="";
		try {
			cDate=DateUtils.getDateFromString(collectDate);
			if(cDate==null){
				throw new BusinessException("输入的统计日期格式有误");
			}
			if(taskType==null || "".equals(taskType)){
				throw new BusinessException("输入的任务类型为空");
			}
			
			MTaskMonitorModel model=new MTaskMonitorModel();
			model.setTaskName(taskName);
			model.setTaskType(Integer.parseInt(taskType));
			model.setRunDate(new Date());
			model.setRunState(0);
			model.setCollectDate(cDate);
			//调用新增任务监控接口
			
			int res=taskMonitorService.insertModel(model);
			result=res>0?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			log.error("新增任务监控操作发生异常",e);
			if(e instanceof BusinessException){
				errMes=e.getMessage();
			}else{
				errMes="新增任务监控操作失败";
			}
		}
		
		if(result){
			request.setAttribute(OPTION_RESULT, SUCCESS_RESULT);
		}else{
			request.setAttribute(OPTION_RESULT, FAILD_RESULT);
			request.setAttribute(FAILD_MESSAGE, errMes);
		}
		return SUCCESS;
	}
}
