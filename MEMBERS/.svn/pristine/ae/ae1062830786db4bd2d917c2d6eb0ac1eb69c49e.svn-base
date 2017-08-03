package com.daboo.controller.index;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daboo.core.exception.BusinessException;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.message.service.MNoticeService;

/**
 * 
  * @ClassName: MIndexController
  * @Description: 提现
  * @author 马正正
  * @date 2015年10月29日
 */
@Controller
@RequestMapping(value = "")
public class MIndexController extends BaseController {
	private Log log=LogFactory.getLog(getClass());
	@Autowired
	private MNoticeService mNoticeService;
	
	/**
	 * 
	  * @Description: 跳转到首页
	  * @return
	  * @author 马正正
	  * @date 2015年10月30日
	 */
	@RequestMapping(value = "toIndex")
	public String toIndex(HttpServletRequest request){
		try{
			String page=request.getParameter("page");
			String rows=request.getParameter("rows");
			page=page==null?"1":page;
			rows=rows==null?"10":rows;
			
			PageObject po=new PageObject();
			po.setCurrPage(Integer.parseInt(page));
			po.setPageSize(Integer.parseInt(rows));
			
			GridDataModel gdm=mNoticeService.query(po);
			
			request.setAttribute("gdm", gdm);
			request.setAttribute("po", po);
		} catch(Exception e){
			log.error("跳转到提现记录查询异常", e);
		}
		return "index";
	}
	
	
	@RequestMapping("")
	public String main() {
		return "redirect:/toIndex";
	}
	/**
	 * 
	  * @Description: 跳转到公告详情页
	  * @param request
	  * @param noticeId
	  * @return
	  * @author 马正正
	  * @date 2015年10月30日
	 */
	@RequestMapping(value = "notice/detail")
	public String toNoticeDetail(HttpServletRequest request,Integer noticeId){
		try {
			Map<String, Object> notice=mNoticeService.findById(noticeId);
			request.setAttribute("notice", notice);
		} catch (BusinessException e) {
			log.error("跳转到公告详情页异常", e);
		}
		
		return "noticeDetail";
	}
}
