package com.daboo.controller.finance;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.SerialNoUtil;
import com.daboo.core.util.SpringSecurityUtils;
import com.daboo.core.web.BaseController;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.model.MTransferDetailModel;
import com.daboo.finance.service.MTransferDetailService;
import com.daboo.reward.enums.AccountTypeEnum;
import com.daboo.reward.service.MUserAccountService;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.service.MUserInfoService;

@Controller
@RequestMapping(value = "/transfer")
public class MTransferDetailController extends BaseController {
	private Log log=LogFactory.getLog(getClass());
	@Autowired
	MTransferDetailService mTransferDetailService;
	@Autowired
	MUserInfoService mUserInfoService;
	@Autowired
	MUserAccountService accountService;	
	@Autowired 
	MemberRewardService rewardService;
	/**
	 * 跳转转账页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toTransfer")
	public String toTransfer(HttpServletRequest request){
		MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
		if(user!=null){
			user=accountService.findUserAccountByAccountNo(user.getAccountNo());
			request.setAttribute("user", user);
		}
		return "finance/account";
	}
	
	/**
	 * 查询转账记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list")
	public void findTransferList(HttpServletRequest request,HttpServletResponse response){
		try{
			String page=request.getParameter("pageNumber");
			String rows=request.getParameter("pageSize");
			page=page==null?"1":page;
			rows=rows==null?"10":rows;
			
			PageObject po=new PageObject();
			po.setCurrPage(Integer.parseInt(page));
			po.setPageSize(Integer.parseInt(rows));
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			po.getCondition().put("userId", user.getId());
			po.getCondition().put("type", request.getParameter("type"));
			po.getCondition().put("startDate", request.getParameter("startDate"));
			po.getCondition().put("endDate", request.getParameter("endDate"));
			writeToPage(JSONUtils.toJson(mTransferDetailService.findTransferList(po)), response);
		} catch(Exception e){
			log.error("查询转账记录异常",e);
		}
	}
	
	/**
	 * 新增转账记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add")
	public void addTransfer(HttpServletRequest request,HttpServletResponse response,MTransferDetailModel model){
		try {
			MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
			MUserInfoModel receiveUser=mUserInfoService.findUserByAccountNo(model.getReceivePhone());
			if (receiveUser==null) {
				ajaxJsonResponse(response,false,"转入账户不存在");
				return;
			}
			if (receiveUser.getId().equals(user.getId())) {
				ajaxJsonResponse(response,false,"不能给自己转账");
				return;
			}
			if (user.getType()!=1&&user.getType()!=3) {
				ajaxJsonResponse(response,false,"账户已被冻结");
				return;
			}
			if (receiveUser.getType()!=1&&receiveUser.getType()!=3) {
				ajaxJsonResponse(response,false,"转入账户已被冻结");
				return;
			}
			model.setSerialNo(SerialNoUtil.getSerialNo("transfer", "ZZ"));
			model.setTransferId(user.getId());
			model.setTransferName(user.getRealName());
			model.setTransferType(1);
			model.setTransferPhone(user.getPhone());
			if (model.getType()==1) {//现金类型,需求判断是否有限制
				
				if(mTransferDetailService.cashTransferCheckIsOpen()){
					if (!checkTeam(user.getId(),receiveUser.getId())) {//检查是否属于同一团队成员
						ajaxJsonResponse(response,false,"现金币只能转给团队成员");
						return;
					}
				}
				model.setTransferAmount(model.getTransferAmount()*100);
				
			}else {//博豆和积分类型
				if (!checkTeam(user.getId(),receiveUser.getId())) {//检查是否属于同一团队成员
					ajaxJsonResponse(response,false,"博豆和积分只能转给团队成员");
					return;
				}
			}
			model.setTransferState(1);
			model.setReceiveId(receiveUser.getId());
			model.setReceiveName(receiveUser.getRealName());
			model.setCreateTime(new Date());
			model.setArrivalTime(new Date());
			model.setIsDeleted(0);
			try {
				mTransferDetailService.addTransfer(model);
				ajaxJsonResponse(response,true,"操作成功");
			} catch (BusinessException e) {
				ajaxJsonResponse(response,false,e.getMessage());
			}
		} catch (Exception e) {
			log.error("新增转账记录异常:"+e);
			ajaxJsonResponse(response,false,"系统异常");
		}
	}
	
	//校验是否为团队成员
	private boolean checkTeam(Integer userId, Integer receiveId) {
		//MStarNodeModel userStarNode = mTransferDetailService.getMStarNode(userId);
		String fromStr = rewardService.getAllParentStrById(userId);
		//MStarNodeModel receiveStarNode = mTransferDetailService.getMStarNode(receiveId);
		String receiveStr = rewardService.getAllParentStrById(receiveId);
		boolean flag = false;
		//if (!StringUtils.isEmpty(fromStr)&&receiveStarNode!=null) {
			if (!StringUtils.isEmpty(fromStr)) {
				String[] userStar= fromStr.split(",");
				for (int i = 0; i < userStar.length; i++) {
					if (userStar[i].equals(receiveId.toString())) {
						flag=true;
					}
				}
			}
			if (!StringUtils.isEmpty(receiveStr)) {
				String[] receiveStar= receiveStr.split(",");
				
				for (int i = 0; i < receiveStar.length; i++) {
					if (receiveStar[i].equals(userId.toString())) {
						flag=true;
					}
				}
			}
		//}
		return flag;
	}

	/**
	 * 校验账户余额
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkAccount")
	public void checkAccount(HttpServletRequest request,HttpServletResponse response,MTransferDetailModel model){
		try{
			if(model.getTransferAmount()!=null&&StringUtils.isNotBlank(model.getReceivePhone())){
				MUserInfoModel user = SpringSecurityUtils.getCurrentUser();
				Boolean flag = mTransferDetailService.checkAccount(user.getId(),model.getTransferAmount(),model.getType());
				if (flag) {
					ajaxJsonResponse(response,true,"验证成功");
				}else {
					ajaxJsonResponse(response,false,"验证失败");
				}
			}
		}catch(Exception e){
			ajaxJsonResponse(response,false,"验证出错");
		}
	}
	
	/**
	 * 校验账户
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkMember")
	public void checkMember(HttpServletRequest request,HttpServletResponse response,MTransferDetailModel model){
		try{
			if(StringUtils.isNotBlank(model.getReceivePhone())){
				MUserInfoModel receiveUser=mUserInfoService.findUserByAccountNo(model.getReceivePhone());
				if (receiveUser!=null) {
					ajaxJsonResponse(response,true,"验证成功");
				}else {
					ajaxJsonResponse(response,false,"验证失败");
				}
			}
		}catch(Exception e){
			ajaxJsonResponse(response,false,"验证出错");
		}
	}
	
	/**
	 * 跳转转账页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toAdd")
	public String toAdd(HttpServletRequest request){
		request.setAttribute("transferType", JSONUtils.toCombo(AccountTypeEnum.toComMap()));
		return "finance/accountForm";
	}
	
	@RequestMapping(value = "success")
	public String success(HttpServletRequest request){
		return "finance/accountSucc";
	}
	
	@RequestMapping(value = "fail")
	public String fail(HttpServletRequest request){
		request.setAttribute("msg", request.getParameter("msg"));
		return "finance/accountFail";
	}
}
