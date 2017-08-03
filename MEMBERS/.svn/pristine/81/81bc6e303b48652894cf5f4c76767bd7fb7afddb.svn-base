package com.daboo.controller.message;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

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

import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.message.model.MOpinionModel;
import com.daboo.message.model.MReplyModel;
import com.daboo.message.service.MOpinionService;
import com.daboo.message.service.MReplyService;
import com.daboo.user.service.MUserInfoService;
/**
 * 意见反馈控制器
 * @author fengchao
 *
 */
@Controller
@RequestMapping(value = "/opinion")
public class OpinionAction extends BaseController {
    //日志对象
	private Logger logger = LoggerFactory.getLogger(OpinionAction.class);
	@Autowired
	private MOpinionService mOpinionService;
	@Autowired
	private MReplyService mReplyService;
	@Autowired
	private MUserInfoService  mUserInfoService;
	/**
	 * @Description 获取意见反馈列表
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="跳转至获取意见反馈列表")
	@RequestMapping(value = "/opinionList" , method =RequestMethod.GET)
	public String goOpinionList(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("转至获取意见反馈列表");
		
		return "message/opinionList";
	}
	/**
	 * @Description 获取意见反馈列表
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="获取意见反馈列表")
	@RequestMapping(value = "/opinionList" , method =RequestMethod.POST)
	public String getOpinionList(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("获取意见反馈列表");
		PageObject po = getPageObject(request,"TIME desc");
		if(StringUtils.isNotBlank(request.getParameter("responsePhone"))){
	       po.getCondition().put("responsePhone", request.getParameter("responsePhone"));
		}
		if(StringUtils.isNotBlank(request.getParameter("comtent"))){
			po.getCondition().put("comtentLike", request.getParameter("comtent"));
		}
		po.getCondition().put("isDeleted", 0);
		GridDataModel data = mOpinionService.getGridDataModelByCondition(po);
		writeToPage(JSONUtils.toJson(data), response);
		return "message/opinionList";
	}
	/**
	 * @Description 获取意见反馈列表
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="跳转到意见反馈详情页面")
	@RequestMapping(value = "/opinionDetail/{id}" , method =RequestMethod.GET)
	public String getOpinionList(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response){
		
		logger.info("跳转到意见反馈详情页面");
		MOpinionModel opinion = null;
		try {
			if(id == null || id == 0){
				request.setAttribute("success", 0);
			}else{
				//根据id查询出此反馈的详情（包含回复）
				opinion = mOpinionService.getOpinionDetail(id);
				request.setAttribute("success", 1);
				request.setAttribute("model", opinion);
			}
		} catch (Exception e) {
			logger.error("跳转到意见反馈详情页面异常",e);
			request.setAttribute("success", 0);
		}
		return "message/detail";
	}
	/**
	 * @Description 添加回复
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="添加回复")
	@RequestMapping(value = "/addReply" , method =RequestMethod.POST)
	public String getOpinionList(MReplyModel model,HttpServletRequest request, HttpServletResponse response){
		
		logger.info("添加回复");
		if(model == null || StringUtils.isBlank(model.getReplyComtent()) || model.getResponseId() == null || model.getReplyComtent().length() > 500){
			request.setAttribute("success", 0);
		}else{
			//检查对应的反馈是否存在
			MOpinionModel opinion = mOpinionService.findById(model.getResponseId());
			if(opinion != null){
				//获取当前登录用户的id
				int currentUserId = (int) (SpringSecurityUtils.getCurrentUser() != null ? SpringSecurityUtils.getCurrentUser().getUserId():0);
				MReplyModel reply = new MReplyModel(model.getResponseId(),model.getReplyComtent(),currentUserId,0,1,new Date(),new Date(),new Date(),0);
				mReplyService.persist(reply);
				//添加成功后返回当前页
				//如果此反馈之前没有回复，则设置为有回复
				MOpinionModel newModel = new MOpinionModel();
				newModel.setId(opinion.getId());
				newModel.setIsReply(1);
				newModel.setUpdateTime(new Date());
				mOpinionService.updateById(newModel);
				//根据id查询出此反馈的详情（包含回复）
				opinion = mOpinionService.getOpinionDetail(model.getResponseId());
				request.setAttribute("success", 1);
				request.setAttribute("model", opinion);
			}else{
				request.setAttribute("success", 0);
			}
		}
		return "message/detail";
	}
	
	/**
	 * @Description 删除已经反馈
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="删除意见反馈")
	@RequestMapping(value = "/delete" , method =RequestMethod.POST)
	public void deleteOpinion(String ids,HttpServletRequest request, HttpServletResponse response){
		
		logger.info("删除意见反馈");
		//获取输出流
		PrintStream ps = null;
		try {
			ps = new PrintStream(response.getOutputStream());
			if(StringUtils.isBlank(ids)){
				ajaxJsonResponse(ps,false,"没有选中要删除的项");
			}
			mOpinionService.deleteOpinion(ids);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ajaxJsonResponseSuccess(ps);
	}
	/**
	 * @Description 删除回复
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="删除回复")
	@RequestMapping(value = "/deleteReply" , method =RequestMethod.POST)
	public void deleteReply(String ids,HttpServletRequest request, HttpServletResponse response){
		
		logger.info("删除回复");
		//获取输出流
		PrintStream ps = null;
		try {
			ps = new PrintStream(response.getOutputStream());
			Integer responseId = Integer.parseInt(request.getParameter("responseId"));
			Integer replyId = Integer.parseInt(request.getParameter("id"));
			MReplyModel model = new MReplyModel();
			model.setId(replyId);
			model.setIsDeleted(1);
			mReplyService.updateById(model);
			//如果此次删除后某条意见反馈已经没有了回复,那么则修改反馈表里面的是否有回复字段的状态
			boolean have = mReplyService.haveReply(responseId);
			if(!have){
				//修改反馈
				MOpinionModel updateModel = new MOpinionModel();
				updateModel.setId(responseId);
				updateModel.setIsReply(0);
				updateModel.setUpdateTime(new Date());
				mOpinionService.updateById(updateModel);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ajaxJsonResponseSuccess(ps);
	}
}
