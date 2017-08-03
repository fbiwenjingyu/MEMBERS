package com.daboo.controller.finance;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.enums.DrawStatus;
import com.daboo.finance.model.MConditions;
import com.daboo.finance.service.MDrawService;

/**
 * 
  * @ClassName: MDrawController
  * @Description: 提现
  * @author 马正正
  * @date 2015年10月26日
 */
@Controller
@RequestMapping(value = "/draw")
public class MDrawController extends BaseController {
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private MDrawService mDrawService;
	
	/**
	 * 
	  * @Description: 跳转到查询页面
	  * @param request
	  * @return
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	@RequestMapping(value = "init")
	@OpLog(logDesc="跳转到查询页面")
	public String init(HttpServletRequest request){
		request.setAttribute("statusCombo", JSONUtils.toCombo(DrawStatus.getComb()));
		request.setAttribute("statusMap", JSONUtils.toJson(DrawStatus.getComb()));
		return "draw/init";
	}
	
	/**
	 * 
	  * @Description: 查询提现记录
	  * @param request
	  * @param response
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	@OpLog(logDesc="查询提现记录")
	public void list(HttpServletRequest request,HttpServletResponse response,MConditions cons){
		try{
			PageObject po = getPageObject(request);
			po.getCondition().put("drawStatus", cons.getDrawStatus());
			po.getCondition().put("startDate", cons.getStartDate());
			po.getCondition().put("endDate", cons.getEndDate());
			po.getCondition().put("memberNo", cons.getMemberNo());
			po.getCondition().put("mulstatus", cons.getMulstatus()==null?"=0":cons.getMulstatus());
			
			writeToPage(JSONUtils.toJson(mDrawService.query(po)), response);
		} catch(Exception e){
			log.error("查询提现单异常", e);
		}
	}
	
	/**
	 * 
	  * @Description: 跳转到编辑页面
	  * @param request
	  * @return
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	@RequestMapping(value = "toEdit")
	@OpLog(logDesc="跳转到编辑页面")
	public String toEdit(HttpServletRequest request){
		Map<String, String> statusMap=DrawStatus.getComb();
		statusMap.remove("");
		statusMap.remove(DrawStatus.AUDIT_WAIT.getKey()+"");
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		
		return "draw/edit";
	}
	
	/**
	 * 
	  * @Description: 更新订单状态
	  * @param request
	  * @param response
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	@OpLog(logDesc="更新订单状态")
	@RequestMapping(value = "changeStatus")
	@ResponseBody
	public void changeStatus(HttpServletRequest request,HttpServletResponse response){
		try{
			int drawId=Integer.parseInt(request.getParameter("drawId"));
			int drawStatus=Integer.parseInt(request.getParameter("drawStatus"));
			String drawMemo=request.getParameter("drawMemo");
			
			mDrawService.changeStatus(drawId, drawStatus, drawMemo,SpringSecurityUtils.getCurrentUserName());
			
			writeToPage("{\"code\":0,\"msg\":\"操作成功\"}", response);
		} catch(Exception e){
			log.error("更新提现单状态异常", e);
			writeToPage("{\"code\":1,\"msg\":\""+e.getMessage()+"\"}", response);
		}
	}
	
	/**
	 * 导出提现Excel表格
	 * @FunName export
	 * @param request
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月23日
	 */
	@OpLog(logDesc="导出提现Excel表格")
	@RequestMapping("/export")
	public String export(HttpServletRequest request,MConditions cons){
		try{
			PageObject po = getPageObject(request);
			po.getCondition().put("drawStatus", cons.getDrawStatus());
			po.getCondition().put("startDate", cons.getStartDate());
			po.getCondition().put("endDate", cons.getEndDate());
			po.getCondition().put("memberNo", cons.getMemberNo());
			
			GridDataModel dataModel=mDrawService.query(po);
			if(dataModel!=null){
				request.setAttribute("rows", dataModel.getRows());
			}
			request.setAttribute("statusMap", DrawStatus.getComb());
		} catch(Exception e){
			log.error("查询提现单异常", e);
		}
		return "draw/export";
	}
	
}
