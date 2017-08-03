package com.daboo.controller.finance;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.util.StringUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.PageObject;
import com.daboo.exchange.model.MExchangeInfoModel;
import com.daboo.exchange.service.MExchangeInfoService;
import com.daboo.finance.service.MCurrencyConvertService;
import com.daboo.reward.service.MUserAccountService;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.system.service.SysDictService;
import com.daboo.user.model.MUserInfoModel;

/**
 * 
 * @Description 资产管理controller 
 * @author dingyang
 * @version 1.0
 * @Create Date 2015年10月29日
 */
@Controller
@RequestMapping("assetes")
public class MAssetesController extends BaseController {
	
	Logger log=LoggerFactory.getLogger(MAssetesController.class);
	
	@Autowired
	private MUserAccountService accountService;
	
	@Autowired
	private MCurrencyConvertService convertService;
	
	@Autowired
	private MemberRewardService memberRewardService;
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private MExchangeInfoService exchangeInfoService;
	
	/**
	 * 进入我的资产流水主页
	 * @FunName toAssetes
	 * @param request
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月29日
	 */
	@OpLog(logDesc="进入我的资产流水主页")
	@RequestMapping("/toAssetes")
	public String toAssetes(HttpServletRequest request){
		
		MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
		if(user!=null){
			user=accountService.findUserAccountByAccountNo(user.getAccountNo());
			request.setAttribute("user", user);
		}
		
		String flag=request.getParameter("flag");
		if(flag.equals("1")){
			log.info("进入我的资产流水主页");
			return "finance/assetes";
		}else if(flag.equals("2")){
			log.info("进入货币转换主页");
			return "finance/transform";
		}
		return null;
	}
	
	/**
	 * 分页获取资产流水信息
	 * @FunName getAccountList
	 * @param request
	 * @param response
	 * @author dingyang
	 * @Create Date 2015年10月30日
	 */
	@OpLog(logDesc="分页获取资产流水信息")
	@RequestMapping("/getAccountList")
	public void getAccountList(HttpServletRequest request,HttpServletResponse response){
		
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		String startDate=request.getParameter("startDate");//注册开始日期
		String endDate=request.getParameter("endDate");//注册结束日期
		String awardtype=request.getParameter("awardtype");//奖项类型
		String businesstype=request.getParameter("businesstype");//业务类型
		String hlgbusinesstype=request.getParameter("hlgbusinesstype");//韩流馆业务类型
		
		pageNumber=pageNumber==null?"1":pageNumber;
		pageSize=pageSize==null?"10":pageSize;
		
		try {
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			
			PageObject po=getPageObject(request);
			po.setCurrPage(Integer.parseInt(pageNumber));
			po.setPageSize(Integer.parseInt(pageSize));
			po.getCondition().put("userId", user.getId());
			
			if(!StringUtils.isEmpty(awardtype)){
				po.getCondition().put("accountType", awardtype);
			}
			if(!StringUtils.isEmpty(businesstype)){
				if(businesstype.equals("hlg")){//韩流馆业务
					if(hlgbusinesstype.equals("all")){
						po.getCondition().put("inoutTypeForhlg", "all");
					}else{
						po.getCondition().put("inoutType", hlgbusinesstype);
					}
					
				}else{
					po.getCondition().put("inoutType", businesstype);
				}
			}
			
			if(!StringUtils.isEmpty(startDate)){
				po.getCondition().put("startDate", startDate);
			}
			if(!StringUtils.isEmpty(endDate)){
				po.getCondition().put("endDate", endDate);
			}
			writeToPage(JSONUtils.toJson(accountService.findListByConditions(po)), response);
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("分页获取资产流水信息异常",e);
			writeToPage(makeBooJson(false,"分页获取资产流水信息异常"),response);
		}
	}
	
	/**
	 * 分页获取账户累计流水信息
	 * @FunName getDayPointDetail
	 * @param request
	 * @param response
	 * @author dingyang
	 * @Create Date 2015年12月18日
	 */
	@OpLog(logDesc="分页获取账户累计流水信息")
	@RequestMapping("/getDayPointDetail")
	public void getDayPointDetail(HttpServletRequest request,HttpServletResponse response){
		String startDate=request.getParameter("startDate");//注册开始日期
		String endDate=request.getParameter("endDate");//注册结束日期
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		try {
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			
			PageObject po=getPageObject(request);
			po.setCurrPage(Integer.parseInt(pageNumber));
			po.setPageSize(Integer.parseInt(pageSize));
			po.getCondition().put("userId", user.getId());
			
			if(!StringUtils.isEmpty(startDate)){
				po.getCondition().put("startDate", startDate);
			}
			if(!StringUtils.isEmpty(endDate)){
				po.getCondition().put("endDate", endDate);
			}
			writeToPage(JSONUtils.toJson(accountService.findDayPointDetailByConditions(po)), response);
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("分页获取账户累计流水信息异常",e);
			writeToPage(makeBooJson(false,"分页获取账户累计流水信息异常"),response);
		}
		
	}
	
	
	/**
	 * 分页获取货币转换记录信息
	 * @FunName getTransformList
	 * @param request
	 * @param response
	 * @author dingyang
	 * @Create Date 2015年11月2日
	 */
	@OpLog(logDesc="分页获取货币转换记录信息")
	@RequestMapping("/getTransformList")
	public void getTransformList(HttpServletRequest request,HttpServletResponse response){
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		pageNumber=pageNumber==null?"1":pageNumber;
		pageSize=pageSize==null?"10":pageSize;
		
		try {
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			
			PageObject po=getPageObject(request);
			po.setCurrPage(Integer.parseInt(pageNumber));
			po.setPageSize(Integer.parseInt(pageSize));
			po.getCondition().put("userId", user.getId());
			po.getCondition().put("orderByClause", "CREATE_TIME DESC");
			
			writeToPage(JSONUtils.toJson(convertService.findListByConditions(po)), response);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("分页获取货币转换记录信息异常",e);
			writeToPage(makeBooJson(false,"分页获取货币转换记录信息异常"),response);
		}	
	}
	
	@OpLog(logDesc="进入转换货币页面")
	@RequestMapping("/gotoTransform")
	public String gotoTransform(HttpServletRequest request){
		MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
		if(user!=null){
			user=accountService.findUserAccountByAccountNo(user.getAccountNo());
			request.setAttribute("user", user);
		}
		return "finance/transformForm";
	}
	
	@OpLog(logDesc="进行货币转换操作")
	@RequestMapping("/doTransform")
	public String doTransform(HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		String amount=StringUtils.trim(request.getParameter("tit"));
		String password=request.getParameter("password");
		String convertType=request.getParameter("convertType");
		
		MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
		boolean flag=false;
		String resultMes="";
		try {
			
			flag=convertService.doConvert(user.getId(),user.getAccountNo(),convertType,Integer.parseInt(amount),password,SpringSecurityUtils.getCurrentUserName());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用货币转换接口出错", e);
			flag=false;
			resultMes=e.getMessage();
		}
		
		if(flag){
			redirectAttributes.addFlashAttribute(OPTION_RESULT, SUCCESS_RESULT);
		}else{
			redirectAttributes.addFlashAttribute(OPTION_RESULT, FAILD_RESULT);
			redirectAttributes.addFlashAttribute(FAILD_MESSAGE, resultMes);
		}
		return "redirect:goTransformResult";
	}
	
	@OpLog(logDesc="进入货币转换结果页面")
	@RequestMapping("/goTransformResult")
	public String goTransformResult(){
		return "finance/transformResult";
	}
	
	private String makeBooJson(boolean result,String errMsg){
		return "{\"result\":"+result+",\"msg\":\"" + errMsg + "\"}";
	}
	
	@OpLog(logDesc="进入兑换云蝶页面")
	@RequestMapping("/toExchange")
	public String toExchange(HttpServletRequest request){
		MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
		if(user!=null){
			user=accountService.findUserAccountByAccountNo(user.getAccountNo());
			request.setAttribute("user", user);
		}
		//获取可预支博豆
		Long adv=memberRewardService.getAdvances(user.getId());
		if(adv!=null){
			request.setAttribute("advances",adv);
		}
		//兑换云蝶所需博豆
		request.setAttribute("exchangeAmount", sysDictService.getDetailValue("EXCHANGE_AMOUNT","EXCHANGE_AMOUNT"));
		//兑换智家所需现金
		request.setAttribute("exchangeCash", sysDictService.getDetailValue("EXCHANGE_ZHIJIA","CASH"));
		//兑换智家所需积分
		request.setAttribute("exchangeJifen", sysDictService.getDetailValue("EXCHANGE_ZHIJIA","JIFEN"));
		
		return "finance/exchange";
	}
	
	@OpLog(logDesc="进入兑换云蝶填写地址页面")
	@RequestMapping("/toExchangeForm")
	public String toExchangeForm(HttpServletRequest request){
		request.setAttribute("exNum", request.getParameter("exNum"));
		request.setAttribute("gType", request.getParameter("gType"));
		return "finance/exchangeForm";
	}
	
	@OpLog(logDesc="确认兑换云蝶操作")
	@RequestMapping("/doExchange")
	public void doExchange(HttpServletRequest request,HttpServletResponse response){
		String exNum=request.getParameter("exNum");//兑换数量
		String gType=request.getParameter("gType");//兑换物品类型，单数为云蝶，双数为安防套装
		String shName=StringUtils.trim(request.getParameter("shName"));
		String shPhone=StringUtils.trim(request.getParameter("shPhone"));
		String selectProvince=request.getParameter("province");
		String selectCity=request.getParameter("city");
		String selectArea=request.getParameter("area");
		String fullAddress=StringUtils.trim(request.getParameter("fullAddress"));
		int exNumber=Integer.parseInt(exNum);
		MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
		
		MExchangeInfoModel model=new MExchangeInfoModel();
		
		model.setUserId(user.getId());
		Integer type=Integer.parseInt(gType);
		Integer goodType= (type.intValue())%2==0?2:1 ;
		model.setGoodType(goodType);//兑换物品类型，1云蝶，2套装
		model.setApplyTime(new Date());//申请时间
		model.setExchangeNum(exNumber);//兑换数量
		model.setRecepitPhone(shPhone);
		model.setRecepitUserName(shName);
		model.setProvince(selectProvince);
		model.setCity(selectCity);
		model.setArea(selectArea);
		model.setFullAddress(fullAddress);
		model.setState(1);
		model.setVersion(0);
		
		int result=0;
	
		try {
			result=exchangeInfoService.insertExchangeInfo(model,SpringSecurityUtils.getCurrentUserName());
			if(result>0){
				writeToPage(makeBooJson(true,"兑换成功，我们将尽快为您发货。"),response);
			}else{
				writeToPage(makeBooJson(false,"兑换失败，请稍后再试。"),response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用积分兑换物品接口异常",e);
			writeToPage(makeBooJson(false,"调用积分兑换物品接口异常"),response);
		}
	}
	
	@OpLog(logDesc="进入查询兑换记录页面")
	@RequestMapping("/toExchangeList")
	public String toExchangeList(){
		return "finance/exchangeList";
	}
	
	@OpLog(logDesc="分页查询兑换记录信息")
	@RequestMapping("/getExchangeList")
	public void getExchangeList(HttpServletRequest request,HttpServletResponse response){
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		pageNumber=pageNumber==null?"1":pageNumber;
		pageSize=pageSize==null?"10":pageSize;
		
		try {
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			
			PageObject po=getPageObject(request);
			po.setCurrPage(Integer.parseInt(pageNumber));
			po.setPageSize(Integer.parseInt(pageSize));
			po.getCondition().put("userId", user.getId());		
			po.getCondition().put("orderByClause", "APPLY_TIME DESC");
			
			writeToPage(JSONUtils.toJson(exchangeInfoService.queryMyExchangeList(po)), response);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("分页查询兑换记录信息异常",e);
			writeToPage(makeBooJson(false,"分页查询兑换记录信息异常"),response);
		}	
	}
	
}
