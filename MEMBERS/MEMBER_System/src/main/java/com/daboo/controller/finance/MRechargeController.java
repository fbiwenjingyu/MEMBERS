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
import com.daboo.finance.enums.RechargeForward;
import com.daboo.finance.enums.RechargeStatus;
import com.daboo.finance.enums.RechargeWay;
import com.daboo.finance.model.MConditions;
import com.daboo.finance.model.MRechargeModel;
import com.daboo.finance.service.MRechargeService;
import com.daboo.reward.enums.AccountEnum;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.service.MUserInfoService;

/**
 * 
  * @ClassName: MRechargeController
  * @Description: 充值
  * @author 马正正
  * @date 2015年10月26日
 */
@Controller
@RequestMapping(value = "/recharge")
public class MRechargeController extends BaseController {
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private MRechargeService mRechargeService;
	@Autowired
	private MUserInfoService mUserInfoService;
	
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
		request.setAttribute("accountTypeCombo", JSONUtils.toCombo(AccountEnum.getDeposit()));
		request.setAttribute("rechargeForwardCombo", JSONUtils.toCombo(RechargeForward.getComb()));
		request.setAttribute("rechargeWayCombo", JSONUtils.toCombo(RechargeWay.getComb()));
		request.setAttribute("accountType", JSONUtils.toJson(AccountEnum.getDeposit()));
		request.setAttribute("rechargeStatus", JSONUtils.toJson(RechargeStatus.getComb()));
		request.setAttribute("rechargeForward", JSONUtils.toJson(RechargeForward.getComb()));
		request.setAttribute("rechargeWay", JSONUtils.toJson(RechargeWay.getComb()));
		return "recharge/init";
	}
	
	/**
	 * 
	  * @Description: 查询充值记录
	  * @param request
	  * @param response
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	@OpLog(logDesc="查询充值记录")
	public void list(HttpServletRequest request,HttpServletResponse response,MConditions cons){
		try{
			PageObject po = getPageObject(request);
			
			po.getCondition().put("accountType", request.getParameter("accountType"));
			po.getCondition().put("way", request.getParameter("way"));
			po.getCondition().put("forward", request.getParameter("forward"));
			po.getCondition().put("startDate", cons.getStartDate());
			po.getCondition().put("endDate", cons.getEndDate());
			po.getCondition().put("memberNo", cons.getMemberNo());
			
			writeToPage(JSONUtils.toJson(mRechargeService.query(po)), response);
		} catch(Exception e){
			log.error("查询提现单异常", e);
		}
	}
	
	/**
	 * 导出充值Excel表格
	 * @FunName export
	 * @param request
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月15日
	 */
	@OpLog(logDesc="导出充值Excel表格")
	@RequestMapping("/export")
	public String export(HttpServletRequest request,MConditions cons){
		try{
			PageObject po = getPageObject(request);
			
			po.getCondition().put("accountType", request.getParameter("accountType"));
			po.getCondition().put("way", request.getParameter("way"));
			po.getCondition().put("forward", request.getParameter("forward"));
			po.getCondition().put("startDate", cons.getStartDate());
			po.getCondition().put("endDate", cons.getEndDate());
			po.getCondition().put("memberNo", cons.getMemberNo());
			
			GridDataModel dataModel=mRechargeService.query(po);
			if(dataModel!=null){
				request.setAttribute("rows", dataModel.getRows());
			}
			request.setAttribute("accountType", AccountEnum.getDeposit());
			request.setAttribute("rechargeStatus", RechargeStatus.getComb());
			request.setAttribute("rechargeForward", RechargeForward.getComb());
			request.setAttribute("rechargeWay", RechargeWay.getComb());
			
		} catch(Exception e){
			log.error("查询充值列表异常", e);
		}
		return "recharge/export";
	}
	
	/**
	 * 
	  * @Description: 跳转到充值页面
	  * @param request
	  * @return
	  * @author 马正正
	  * @date 2015年10月27日
	 */
	@RequestMapping(value = "toRecharge")
	@OpLog(logDesc="跳转到充值页面")
	public String toRecharge(HttpServletRequest request){
		Map<String, String> accountType=AccountEnum.getDeposit();
		accountType.remove("");
		Map<String, String> rechargeForward=RechargeForward.getComb();
		rechargeForward.remove("");
		request.setAttribute("accountType", JSONUtils.toCombo(accountType));
		request.setAttribute("rechargeForward", JSONUtils.toCombo(rechargeForward));
		request.setAttribute("accountTypeMap", JSONUtils.toJson(accountType));
		request.setAttribute("rechargeForwardMap", JSONUtils.toJson(rechargeForward));
		return "recharge/recharge";
	}
	
	/**
	 * 
	  * @Description: 根据会员编号查询会员
	  * @param request
	  * @param response
	  * @author 马正正
	  * @date 2015年10月27日
	 */
	@RequestMapping(value = "findMemByNo")
	@ResponseBody
	@OpLog(logDesc="根据会员编号查询会员")
	public void findMemByNo(HttpServletRequest request,HttpServletResponse response){
		try{
			String memberNo=request.getParameter("memberNo");
			boolean res=mUserInfoService.findUserByAccountNo(memberNo)==null?false:true;
			
			writeToPage(res+"", response);
		} catch(Exception e){
			log.error("根据会员编号查询会员异常", e);
			writeToPage("{\"code\":1,\"msg\":\""+e.getMessage()+"\"}", response);
		}
	}
	
	/**
	 * 
	  * @Description: 充值
	  * @param request
	  * @param response
	  * @param recharge
	  * @author 马正正
	  * @date 2015年10月27日
	 */
	@RequestMapping(value = "recharge")
	@ResponseBody
	@OpLog(logDesc="充值")
	public void recharge(HttpServletRequest request,HttpServletResponse response,
			MRechargeModel recharge){
		try{
			if(recharge.getAccountType()==1){
				recharge.setAmount(recharge.getAmount()*100);
			}
			recharge.setOperator(SpringSecurityUtils.getCurrentUserName());
			MUserInfoModel user=mUserInfoService.findUserByAccountNo(recharge.getMemberNo());
			recharge.setUserId(user.getId());
			mRechargeService.background(recharge);
			writeToPage("{\"code\":0,\"msg\":\"操作成功\"}", response);
		} catch(Exception e){
			log.error("充值异常", e);
			writeToPage("{\"code\":1,\"msg\":\""+e.getMessage()+"\"}", response);
		}
	}
}
