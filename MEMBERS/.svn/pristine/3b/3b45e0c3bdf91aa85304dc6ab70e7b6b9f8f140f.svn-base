package com.daboo.controller.message;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daboo.core.util.OpLog;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.message.model.MOpinionModel;
import com.daboo.message.model.MReplyModel;
import com.daboo.message.service.MOpinionService;
import com.daboo.message.service.MReplyService;
import com.daboo.user.model.MUserInfoModel;
/**
 * 前台意见反馈控制器
 * @author fengchao
 *
 */
@Controller
@RequestMapping(value = "/message")
public class OpinionAction extends BaseController {
    /** 日志对象   */
	private Logger logger = LoggerFactory.getLogger(OpinionAction.class);
	@Autowired
	private MOpinionService mOpinionService;
	@Autowired
	private MReplyService mReplyService;
	/**
	 * @Description 新增意见反馈
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="跳转到消息首页")
	@RequestMapping(value = "/goMessage" , method =RequestMethod.GET)
	public String goMessageCenter(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("跳转到消息首页");
		//获取系统当前登录用户
				MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
				//根据model查询此用户提的反馈
				PageObject po = getPageObject(request);
				String pageNumber=request.getParameter("pageNumber");
				String pageSize=request.getParameter("pageSize");
				pageNumber=pageNumber==null?"1":pageNumber;
				pageSize=pageSize==null?"10":pageSize;
				po.setCurrPage(Integer.parseInt(pageNumber));
				po.setPageSize(Integer.parseInt(pageSize));
				po.getCondition().put("isDeleted", 0);
				po.getCondition().put("responsePhone",model.getAccountNo() );
				po.getCondition().put("orderByClause", "CREATE_TIME DESC");
				GridDataModel data = mOpinionService.getGridDataModelByCondition(po);
				List<MOpinionModel> opinions = judgeHaveNoRead(data.getRows());
				data.setRows(opinions);
				request.setAttribute("data", data);
				request.setAttribute("currentPage", pageNumber);
		return "message/messageCenter";
	}
	/**
	 * @Description 新增意见反馈
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="显示我的反馈")
	@RequestMapping(value = "/showOpinion" , method =RequestMethod.GET)
	public String showMessageList(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("显示我的反馈");
		//获取系统当前登录用户
		MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
	//	PageObject po = getPageObject(request);
	//	po.getCondition().put("isDeleted", 0);
	//	GridDataModel data = mOpinionService.getGridDataModelByCondition(po);
		//根据model查询此用户提的反馈
		MOpinionModel opinion = new MOpinionModel();
		opinion.setIsDeleted(0);
		opinion.setResponsePhone(model.getAccountNo());
		List<MOpinionModel> opinions = mOpinionService.getListByModel(opinion);
		request.setAttribute("opinions", opinions);
		return "message/messageCenter";
	}
	
	/**
	 * @Description 新增意见反馈
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="跳转至新增意见反馈")
	@RequestMapping(value = "/addOpinion" , method =RequestMethod.GET)
	public String goAddOpinion(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("跳转至新增意见反馈");
		
		return "message/addOpinion";
	}
	/**
	 * @Description 新增意见反馈
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="新增意见反馈")
	@RequestMapping(value = "/addOpinion" , method =RequestMethod.POST)
	public String addOpinion(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("新增意见反馈");
		boolean pass= true;
		 //获取系统当前登录用户
		 MUserInfoModel model = SpringSecurityUtils.getCurrentUser();
		if(StringUtils.isBlank(request.getParameter("title"))){
			pass =false;
			request.setAttribute("errorMsg", "标题不能为空");
		}if(request.getParameter("title").length() > 30){
			pass =false;
			request.setAttribute("title",request.getParameter("title") );
			request.setAttribute("comtent", request.getParameter("comtent"));
			request.setAttribute("errorMsg", "标题长度不能超过30个字");
		}else if(StringUtils.isBlank(request.getParameter("comtent"))){
			pass =false;
			request.setAttribute("errorMsg", "反馈内容不能为空");
		}if(request.getParameter("comtent").length() > 300){
			pass =false;
			request.setAttribute("title",request.getParameter("title") );
			request.setAttribute("comtent", request.getParameter("comtent"));
			request.setAttribute("errorMsg", "反馈内容不能超过300个字");
		}else{
		MOpinionModel addModel = new MOpinionModel(model.getAccountNo(),request.getParameter("title"),request.getParameter("comtent"),
				new Date(),0,new Date(),new Date(),0);
		mOpinionService.persist(addModel);
		}
		String path = "message/messageCenter";
		//查询此用户的全部反馈
		if(pass){
			getAllOpinion(request, model.getAccountNo());
		}else{
			path = "message/addOpinion";
		}
		return path;
	}
	/**
	 * @Description 跳转到回复详情页面
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="跳转到回复详情页面")
	@RequestMapping(value = "/toReply/{id}" , method =RequestMethod.GET)
	public String goReply(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response){
		
		logger.info("跳转到回复详情页面");
		MOpinionModel opinion = null;
		//根据id查询出此反馈的详情（包含回复）
		opinion = mOpinionService.getOpinionDetail(id);
		//将所有的消息设置为已读
		mReplyService.setIsReadByResponseId(id);		
		request.setAttribute("model", opinion);
		return "message/replyList";
	}
	/**
	 * @Description 添加回复
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="添加回复")
	@RequestMapping(value = "/addReply" , method =RequestMethod.POST)
	public String addReply(MReplyModel model, HttpServletRequest request, HttpServletResponse response){
		
		logger.info("添加回复");
		MOpinionModel opinion = null;
		if(model == null || StringUtils.isBlank(model.getReplyComtent()) || model.getResponseId() == null){
			request.setAttribute("success", 0);
			request.setAttribute("errorMsg", "输入不能为空");
		}else{
			//检查对应的反馈是否存在
			opinion = mOpinionService.findById(model.getResponseId());
			if(opinion != null){
				//获取当前登录用户的id
				int currentUserId = (int) (SpringSecurityUtils.getCurrentUser() != null ? SpringSecurityUtils.getCurrentUser().getId():0);
				MReplyModel reply = new MReplyModel(model.getResponseId(),model.getReplyComtent(),currentUserId,0,0,new Date(),new Date(),new Date(),0);
				mReplyService.persist(reply);
				//如果此反馈之前没有回复，则修改此反馈是否有回复的状态
				if(opinion.getIsReply().intValue() == 0){
					MOpinionModel newModel = new MOpinionModel();
					newModel.setId(opinion.getId());
					newModel.setIsReply(1);
					mOpinionService.updateById(newModel);
				}
				//添加成功后返回当前页
				request.setAttribute("success", 1);
				
			}else{
				request.setAttribute("success", 0);
				request.setAttribute("errorMsg", "此反馈不存在");
			}
		}
		//根据id查询出此反馈的详情（包含回复）
		opinion = mOpinionService.getOpinionDetail(model.getResponseId());
		request.setAttribute("model", opinion);
		return "message/replyList";
	}
	/**
	 * @Description 根据登录号查询所属的所有反馈
	 * @return  String
	 * @author fengchao
	 */
	public void getAllOpinion(HttpServletRequest request , String accountNo){
		
		PageObject po = getPageObject(request);
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		pageNumber=pageNumber==null?"1":pageNumber;
		pageSize=pageSize==null?"10":pageSize;
		po.setCurrPage(Integer.parseInt(pageNumber));
		po.setPageSize(Integer.parseInt(pageSize));
		po.getCondition().put("isDeleted", 0);
		po.getCondition().put("responsePhone",accountNo );
		GridDataModel data = mOpinionService.getGridDataModelByCondition(po);
		request.setAttribute("data", data);
		request.setAttribute("currentPage", pageNumber);
	}
	/**
	 * @Description 新增意见反馈
	 * @return  String
	 * @author fengchao
	 */
	@OpLog(logDesc="跳转至帮助页面")
	@RequestMapping(value = "/goHelp" , method =RequestMethod.GET)
	public String goHelp(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("跳转至帮助页面");
		
		return "help";
	}
	/**
	 * @Description 判断是否有未读消息
	 * @return  String
	 * @author fengchao
	 */
	public List<MOpinionModel> judgeHaveNoRead(List<?> list){
		
		List<MOpinionModel> opinions = null;
		if(list != null && list.size() > 0){
		  opinions = new ArrayList<MOpinionModel>();
		  for(Object obj : list){
			  opinions.add((MOpinionModel)obj);
		  }
		  String ids = getResponseIds(opinions);
		  //批量获取反馈的回复数
		  List<Map<String,Object>> maps = mReplyService.haveNoReadReply(ids);
		  for(Map<String,Object> map : maps){
			  for(Map.Entry<String, Object> entry : map.entrySet()){
				  if(entry.getKey().equals("responseId")){
					  for(MOpinionModel opinion : opinions){
						  if(opinion.getId().intValue() == ((Integer)entry.getValue()).intValue()){
							  if(((Integer)entry.getValue()).intValue() > 0) opinion.setIsNoRead(1);
						  }
					  }
				  }
			   }
		  }
		}
		return opinions;
	}
	/**
	 * @Description 获取list里面的所有主键
	 * @return  String
	 * @author fengchao
	 */
	public String getResponseIds(List<MOpinionModel> list){
		StringBuilder builder = null;
		if(list != null && list.size() > 0){
			builder = new StringBuilder();
			for(MOpinionModel opinion : list){
				builder.append(opinion.getId()).append(",");
			}
		}
		return builder == null ? "" : builder.toString().substring(0,builder.length() - 1);
	}
}
