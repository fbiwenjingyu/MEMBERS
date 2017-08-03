package com.daboo.controller.exchange;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.OpLog;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.exchange.enums.GoodTypeEnum;
import com.daboo.exchange.model.MExchangeInfoModel;
import com.daboo.exchange.service.MExchangeInfoService;
import com.daboo.finance.model.MConditions;
import com.daboo.system.entity.SysUser;
import com.daboo.system.service.SysDictService;

@Controller
@RequestMapping(value = "/exchange")
public class MExchangeInfoController  extends BaseController{
	
	private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private MExchangeInfoService mExchangeInfoService;
	@RequestMapping(value = "init")
	@OpLog(logDesc=" 初始兑换记录查询页面")
	public String init(HttpServletRequest request){
		Map<String,String> stateMap = sysDictService.getDetailValueMap("SEND_STATE");
		request.setAttribute("stateCombojson", JSONUtils.toJson(stateMap));
		request.setAttribute("stateCombo", JSONUtils.toCombo(stateMap));
		request.setAttribute("goodTypeJson", JSONUtils.toJson(GoodTypeEnum.toMap()));
		request.setAttribute("goodTypeCombo", JSONUtils.toCombo(GoodTypeEnum.toMap()));
		return "exchange/initexchange";
	}
	
	/**
	  * @Description: 创客收款和发货公用订单查询Controller
	  * @param request
	  * @param response
	  * @author 周锐
	  * @date 2015-8-25
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	@OpLog(logDesc="云碟发货记录查询")
	public void list(HttpServletRequest request,HttpServletResponse response) {
		PageObject po = getPageObject(request,"APPLY_TIME desc");
		po.getCondition().put("recepitPhone",request.getParameter("recepitPhone"));
		po.getCondition().put("state",request.getParameter("state"));
		po.getCondition().put("goodType",request.getParameter("type"));
		po.getCondition().put("startDate",request.getParameter("startDate"));
		po.getCondition().put("endDate",request.getParameter("endDate"));
		writeToPage(JSONUtils.toJson(mExchangeInfoService.queryExchangeInfoByCondition(po)), response);
	}
	
	/**
	 * 导出云蝶发货Excel表格
	 * @FunName export
	 * @param request
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月23日
	 */
	@OpLog(logDesc="导出云蝶发货Excel表格")
	@RequestMapping("/export")
	public String export(HttpServletRequest request,MConditions cons){
		try{
			PageObject po = getPageObject(request,"APPLY_TIME desc");
			
			po.getCondition().put("recepitPhone",request.getParameter("recepitPhone"));
			po.getCondition().put("goodType",request.getParameter("type"));
			po.getCondition().put("state",request.getParameter("state"));
			po.getCondition().put("startDate",request.getParameter("startDate"));
			po.getCondition().put("endDate",request.getParameter("endDate"));
			
			GridDataModel dataModel=mExchangeInfoService.queryExchangeInfoByCondition(po);
			
			if(dataModel!=null){
				request.setAttribute("rows", dataModel.getRows());
			}
			request.setAttribute("stateCombo", sysDictService.getDetailValueMap("SEND_STATE"));
			request.setAttribute("goodTypeMap", GoodTypeEnum.toMap());
		} catch(Exception e){
			log.error("查询云蝶发货列表异常", e);
		}
		return "exchange/export";
	}
	
	/**
	 * 点击确认发货页面
	 * @FunName showConfirmSend
	 * @param request
	 * @return
	 * @author dingyang
	 * @Create Date 2015年8月24日
	 */
	@RequestMapping("/showConfirmSend")
	@OpLog(logDesc="点击确认发货页面")
	public String showConfirmSend(HttpServletRequest request){
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("opType", request.getParameter("opType"));
		request.setAttribute("version", request.getParameter("version"));
		request.setAttribute("kuaidiMap", JSONUtils.toCombo(sysDictService.getDetailNameMap("SEND_COMPANY")));
		return "exchange/confirmSend";
	}
	
	/**
	 * 确认发货，更新发货明细状态为已发货，更新博商库存数量
	 * @FunName confirmSend
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年8月24日
	 */
	@RequestMapping(value="/confirmSend",method=RequestMethod.POST)
	@OpLog(logDesc="确认云碟发货")
	public String confirmSend(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws BusinessException{
		MExchangeInfoModel model = new MExchangeInfoModel();
		Integer id= Integer.valueOf(request.getParameter("id"));
		model.setId(id);
		if("2".equals(request.getParameter("opType"))){//更新发货信息
			String expressCompany=request.getParameter("expressCompany");
			String expressOrder=request.getParameter("order");
			model.setSendTime(new Date());
			model.setExpressCompany(expressCompany);//物流公司
			model.setExpressOrder(expressOrder);//物流单号
			model.setState(2);
			SysUser user = (SysUser)session.getAttribute("user");
			model.setSendUser(user.getUserName());
		}
		else{//取消发货操作
			model.setGoodType(Integer.parseInt(request.getParameter("gType")));//兑换物品类型
			model.setState(3);
		}
		 mExchangeInfoService.updateModelById(model);
		 if("2".equals(request.getParameter("opType"))){
			 return  SUCCESS;
		 }else{
		   writeToPage(makeJsonResponse(true,"取消发货成功") , response);
		   return SUCCESS;
		 }
	}
	
	
	
}
