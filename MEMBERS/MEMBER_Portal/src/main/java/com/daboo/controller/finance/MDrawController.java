package com.daboo.controller.finance;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.enums.DrawStatus;
import com.daboo.finance.model.MDrawModel;
import com.daboo.finance.service.MDrawService;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.service.MUserInfoService;

/**
 * 
  * @ClassName: MDrawController
  * @Description: 提现
  * @author 马正正
  * @date 2015年10月29日
 */
@Controller
@RequestMapping(value = "/draw")
public class MDrawController extends BaseController {
	private Log log=LogFactory.getLog(getClass());
	@Autowired
	private MDrawService mDrawService;
	@Autowired
	private MUserInfoService mUserInfoService;
	
	/**
	 * 
	  * @Description: 跳转到提现记录查询
	  * @param request
	  * @return
	  * @author 马正正
	  * @date 2015年10月30日
	 */
	@RequestMapping(value = "toDraw")
	public String toDraw(HttpServletRequest request){
		try{
			String page=request.getParameter("page");
			String rows=request.getParameter("rows");
			page=page==null?"1":page;
			rows=rows==null?"10":rows;
			
			PageObject po=new PageObject();
			po.setCurrPage(Integer.parseInt(page));
			po.setPageSize(Integer.parseInt(rows));
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			po.getCondition().put("userId", user.getId());
			po.getCondition().put("memberNo", user.getAccountNo());
			
			GridDataModel gdm=mDrawService.query(po);
			
			request.setAttribute("gdm", gdm);
			request.setAttribute("po", po);
			request.setAttribute("status", DrawStatus.getComb());
		} catch(Exception e){
			log.error("跳转到提现记录查询异常", e);
		}
		
		return "finance/withdraw";
	}
	
	/**
	 * 
	  * @Description: 跳转到提现申请页
	  * @return
	  * @author 马正正
	  * @date 2015年10月30日
	 */
	@RequestMapping(value = "toAdd")
	public String toAdd(HttpServletRequest request){
		MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
		user=mUserInfoService.findModelById(user.getId());
		request.setAttribute("cardNo", user.getCardNo());
		return "finance/withdrawForm";
	}
	
	/**
	 * 
	  * @Description: 提现
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,MDrawModel draw,String password){
		String msg=null;
		try{
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			draw.setUserId(user.getId());
			draw.setAmount(draw.getAmount()*100);
			mDrawService.apply(draw.getUserId(), draw.getAmount(), draw.getCardNo(), password,SpringSecurityUtils.getCurrentUserName());
		} catch(Exception e){
			log.error("提现异常", e);
			msg=e.getMessage();
			if(msg==null){
				msg=e.toString();
			}
		}
		
		if(msg==null){
			return "finance/withdrawSucc";
		}else{
			request.setAttribute("msg", msg);
			return "finance/withdrawFail";
		}
	}
	
	@RequestMapping(value = "calFee")
	@ResponseBody
	public void calFee(HttpServletRequest request,HttpServletResponse response,Integer amount){
		try{
			Map<String, Integer> cf=mDrawService.calFee(amount*100);
			
			writeToPage("{\"code\":0,\"msg\":\"操作成功\",\"fee\":"+cf.get("fee")+",\"ari\":"+cf.get("ari")+",\"fen\":"+cf.get("fen")+"}", response);
		} catch(Exception e){
			log.error("更新提现单状态异常", e);
			writeToPage("{\"code\":1,\"msg\":\""+e.getMessage()+"\"}", response);
		}
	}
}
