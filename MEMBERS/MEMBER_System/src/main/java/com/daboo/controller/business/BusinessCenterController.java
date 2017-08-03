package com.daboo.controller.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daboo.business.enums.ActiveStateEnum;
import com.daboo.business.enums.OpenStateEnum;
import com.daboo.business.model.MBusinessCenterModel;
import com.daboo.business.model.MBusinessUserModel;
import com.daboo.business.service.MBusinessCenterService;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.util.StringUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.PageObject;
import com.daboo.user.enums.MUserlevel;

/**
 * 
 * @Description 商务中心controller 
 * @author dingyang
 * @version 1.0
 * @Create Date 2015年10月22日
 */
@Controller
@RequestMapping("busCenter")
public class BusinessCenterController extends BaseController {
	
	private Logger log=LoggerFactory.getLogger(BusinessCenterController.class);
	
	@Autowired
	private MBusinessCenterService businessCenterService;
	
	@OpLog(logDesc = "进入商务中心管理页面")
	@RequestMapping("/init")
	public String init(HttpServletRequest request){
		log.info("进入商务中心管理页面");
		request.setAttribute("activeMap", JSONUtils.toJson(ActiveStateEnum.toMap()));
		request.setAttribute("openMap", JSONUtils.toJson(OpenStateEnum.toMap()));
		return "business/init";
	}
	
	@OpLog(logDesc="查询商务中心列表数据")
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		
		String openState=StringUtils.trim(request.getParameter("flag"));
		String accountNo=StringUtils.trim(request.getParameter("accountNo"));
		String startDate=StringUtils.trim(request.getParameter("startDate"));
		String endDate=StringUtils.trim(request.getParameter("endDate"));
		
		try {
			PageObject po=getPageObject(request);
			po.getCondition().put("openState", openState);
			if(!StringUtils.isEmpty(accountNo)){
				po.getCondition().put("accountNo", accountNo);
			}
			if(!StringUtils.isEmpty(startDate)){
				po.getCondition().put("startDate", startDate);
			}
			if(!StringUtils.isEmpty(endDate)){
				po.getCondition().put("endDate", endDate);
			}
			writeToPage(JSONUtils.toJson(businessCenterService.findByConditions(po)), response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询商务中心列表异常",e);
			writeToPage(makeBooJson(false,"查询商务中心列表异常"),response);
		}
	}
	
	/**
	 * 开通为商务中心
	 * @FunName openToBusiness
	 * @param request
	 * @param response
	 * @author dingyang
	 * @Create Date 2015年10月23日
	 */
	@OpLog(logDesc="开通为商务中心")
	@RequestMapping("/openToBusiness")
	public void openToBusiness(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String version=request.getParameter("version");
		MBusinessCenterModel model=new MBusinessCenterModel();
		model.setId(Integer.parseInt(id));
		model.setOperater(SpringSecurityUtils.getCurrentUserName());
		model.setVersion(Integer.parseInt(version));
		
		boolean flag=false;
		try {
			flag=businessCenterService.openToBusinessCenter(model);
			log.info("开通为商务中心成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用开通为商务中心接口发生异常");
			writeToPage(makeBooJson(false,"调用开通为商务中心接口异常"),response);
		}
		
		if(flag){
			writeToPage(makeBooJson(true,"开通为商务中心成功"),response);
		}else{
			writeToPage(makeBooJson(false,"开通为商务中心失败"),response);
		}
	}
	
	@OpLog(logDesc="删除申请商务中心记录")
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		int flag=0;
		try {
			flag=businessCenterService.deleteOne(Integer.parseInt(id));
			log.info("删除申请商务中心记录成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用删除申请商务中心记录接口发生异常");
			writeToPage(makeBooJson(false,"调用删除申请商务中心记录接口发生异常"),response);
		}
		
		if(flag>0){
			writeToPage(makeBooJson(true,"删除申请商务中心记录成功"),response);
		}else{
			writeToPage(makeBooJson(false,"删除申请商务中心记录失败"),response);
		}
	}
	
	@RequestMapping("/audit")
	@OpLog(logDesc="进入审核商务中心申请页面")
	public String audit(HttpServletRequest request){
		String id=request.getParameter("id");
		
		MBusinessCenterModel model=	businessCenterService.findModelById(Integer.parseInt(id));
		if(model!=null){
			request.setAttribute("model", model);
		}
		return "business/audit";
	}
	
	@RequestMapping("/opera")
	@OpLog(logDesc="审核商务中心申请操作")
	public  String opera(HttpServletRequest request){
		String id=request.getParameter("Bid");
		String version=request.getParameter("version");
		String flag=request.getParameter("flag");
		String remark=request.getParameter("remark");
		
		MBusinessCenterModel model=new MBusinessCenterModel();
		model.setId(Integer.parseInt(id));
		model.setOperater(SpringSecurityUtils.getCurrentUserName());
		model.setVersion(Integer.parseInt(version));
		if(remark!=null && !"".equals(remark)){
			model.setRemark(remark);
		}else{
			model.setRemark("");
		}
		
		if(flag.equals("1")){//开通
			
			boolean result=false;
			try {
				result=businessCenterService.openToBusinessCenter(model);
				log.info("开通为商务中心成功");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("调用开通为商务中心接口发生异常");
				result=false;
			}
			
			if(result){
				request.setAttribute(OPTION_RESULT, SUCCESS_RESULT);
			}else{
				request.setAttribute(OPTION_RESULT, FAILD_RESULT);
				request.setAttribute(FAILD_MESSAGE, "开通为商务中心操作失败，用户状态异常");
			}
		}else if(flag.equals("2")){//拒绝
			
			boolean result=false;
			try {
				result=businessCenterService.notOpenToBusinessCenter(model);
				log.info("拒绝开通为商务中心成功");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("拒绝调用开通为商务中心接口发生异常");
				result=false;
			}
			
			if(result){
				request.setAttribute(OPTION_RESULT, SUCCESS_RESULT);
			}else{
				request.setAttribute(OPTION_RESULT, FAILD_RESULT);
				request.setAttribute(FAILD_MESSAGE, "拒绝开通为商务中心操作失败");
			}
		}
		return SUCCESS;
	}
	
	@RequestMapping("/freeze")
	@OpLog(logDesc="冻结商务中心操作")
	public void freeze(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String version=request.getParameter("version");
		MBusinessCenterModel model=new MBusinessCenterModel();
		model.setId(Integer.parseInt(id));
		model.setOperater(SpringSecurityUtils.getCurrentUserName());
		model.setVersion(Integer.parseInt(version));
		
		boolean flag=false;
		try {
			flag=businessCenterService.freezeBusinessCenter(model);
			log.info("冻结为商务中心成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用冻结商务中心接口发生异常");
			writeToPage(makeBooJson(false,"调用冻结商务中心接口异常"),response);
		}
		
		if(flag){
			writeToPage(makeBooJson(true,"冻结商务中心成功"),response);
		}else{
			writeToPage(makeBooJson(false,"冻结商务中心失败"),response);
		}
		
	}
	
	@RequestMapping("/active")
	@OpLog(logDesc="激活商务中心操作")
	public void active(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String version=request.getParameter("version");
		MBusinessCenterModel model=new MBusinessCenterModel();
		model.setId(Integer.parseInt(id));
		model.setOperater(SpringSecurityUtils.getCurrentUserName());
		model.setVersion(Integer.parseInt(version));
		
		boolean flag=false;
		try {
			flag=businessCenterService.activeBusinessCenter(model);
			log.info("激活为商务中心成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用激活商务中心接口发生异常");
			writeToPage(makeBooJson(false,"调用激活商务中心接口异常"),response);
		}
		
		if(flag){
			writeToPage(makeBooJson(true,"激活商务中心成功"),response);
		}else{
			writeToPage(makeBooJson(false,"激活商务中心失败"),response);
		}
	}
	
	
	@OpLog(logDesc="查询商务中心下会员列表")
	@RequestMapping("/showList")
	public String showList(HttpServletRequest request){
		String accountNo=request.getParameter("id");
		
		List<MBusinessUserModel> list=businessCenterService.findUserListForBC(accountNo);
		
		if(list!=null){
			for (MBusinessUserModel u : list) {
				if(u.getLevel()!=null){
					u.setLevelName(MUserlevel.getNameByCode(u.getLevel()+""));
				}else{
					u.setLevelName("暂无");
				}
			}
			request.setAttribute("users", list);
		}
		
		return "business/userList";
	}
	
	
	@RequestMapping("/addTest")
	public void addTest(HttpServletRequest request,HttpServletResponse response){
		String accountNo=request.getParameter("nameY");
		try {
			businessCenterService.applyToBusinessCenter(accountNo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		writeToPage(makeBooJson(true,"测试成功"),response);
		
	}
	
	
	private String makeBooJson(boolean result,String errMsg){
		return "{\"success\":"+result+",\"msg\":\"" + errMsg + "\"}";
	}
	
}
