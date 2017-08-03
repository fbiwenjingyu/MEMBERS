package com.daboo.controller.finance;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.enums.RechargeForward;
import com.daboo.finance.enums.RechargeStatus;
import com.daboo.finance.enums.RechargeWay;
import com.daboo.finance.model.MRechargeModel;
import com.daboo.finance.service.MRechargeService;
import com.daboo.reward.enums.AccountEnum;
import com.daboo.user.model.MUserInfoModel;

/**
 * 
  * @ClassName: MRechargeController
  * @Description: 充值
  * @author 马正正
  * @date 2015年10月29日
 */
@Controller
@RequestMapping(value = "/recharge")
public class MRechargeController extends BaseController {
	private Log log=LogFactory.getLog(getClass());
	@Autowired
	private MRechargeService mRechargeService;
	
	/**
	 * 
	  * @Description: 跳转到充值记录查询
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	@RequestMapping(value = "toRecharge")
	public String toRecharge(HttpServletRequest request){
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
			
			GridDataModel gdm=mRechargeService.query(po);
			
			request.setAttribute("gdm", gdm);
			request.setAttribute("po", po);
			request.setAttribute("way", RechargeWay.getComb());
			request.setAttribute("status", RechargeStatus.getComb());
		} catch(Exception e){
			log.error("跳转到充值记录查询异常", e);
		}
		
		return "finance/recharge"; 
	}
	
	/**
	 * 
	  * @Description: 跳转到充值页
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	@RequestMapping(value = "toAdd")
	public String toAdd(HttpServletRequest request){
		Map<String, String> way=RechargeWay.getComb();
		way.remove("");way.remove("0");way.remove("2");
		Map<String, String> accountType=AccountEnum.getDeposit();
		accountType.remove("");accountType.remove("3");
		request.setAttribute("way", way);
		request.setAttribute("accountType", accountType);
		return "finance/rechargeForm";
	}
	
	/**
	 * 
	  * @Description: 充值
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	@RequestMapping(value = "add")
	public void add(HttpServletResponse response,MRechargeModel recharge){
		PrintWriter pw=null;
		try{
			recharge.setForward(RechargeForward.ADD.getKey());
			if(recharge.getAccountType()==1){
				recharge.setAmount(recharge.getAmount()*100);
			}
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			recharge.setUserId(user.getId());
			recharge.setMemberNo(user.getAccountNo());
			recharge.setOperator(SpringSecurityUtils.getCurrentUserName());
			if(RechargeWay.ALI_PAY==RechargeWay.get(recharge.getWay())){
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Type", "text/html;charset=UTF-8");
				
				pw=response.getWriter();
				
				String res=mRechargeService.aliReq(recharge);
				
				pw.write(res);
			}
		} catch(Exception e){
			log.error("充值异常", e);
		} finally{
			if (pw!=null) {
				pw.flush();
				pw.close();
			}
		}
	}
	
	/**
	 * 
	  * @Description: 异步回调
	  * @param request
	  * @param response
	  * @author 马正正
	  * @date 2015年12月1日
	 */
	@RequestMapping(value = "alipayNotify")
	public void alipayNotify(HttpServletRequest request,HttpServletResponse response){
		log.info("=========alipayNotify=========");
		PrintWriter pw = null;
		try {
			mRechargeService.aliRes(request.getParameterMap(),false);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Type", "text/html;charset=UTF-8");
			pw = response.getWriter();
			pw.write("success");
			pw.flush();
		} catch (Exception e) {
			log.error("异步回调异常", e);
		} finally{
			if(pw!=null){
				pw.close();
			}
		}
	}
	
	/**
	 * 
	  * @Description: 同步回调
	  * @param request
	  * @return
	  * @author 马正正
	  * @date 2015年12月1日
	 */
	@RequestMapping(value = "alipayReturn")
	public String alipayReturn(HttpServletRequest request){
		log.info("=========alipayReturn=========");
		String returnUrl="finance/rechargeFail";
		try {
			mRechargeService.aliRes(request.getParameterMap(),true);
			returnUrl="finance/rechargeSucc";
		} catch (Exception e) {
			log.error("同步回调异常", e);
		}
		return returnUrl;
	}
}
