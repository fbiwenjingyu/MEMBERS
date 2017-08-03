package com.daboo.reward.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.enums.AccountEnum;
import com.daboo.reward.model.MDayPointDetailModel;
import com.daboo.reward.model.MPointTaskDetailModel;
/*
 * 会员奖励相关服务
 */
@RemoteService
public interface MemberRewardService{
	
	/**
	 * 激活会员
	 * @param userId 用户ID
	 * @param recommendId,推荐人
	 * @param operatorNo,操作员
	 * @param isEmpty,是否空单,空单无博豆
	 * @throws BusinessException
	 */
	public void activationUser(Integer userId,Integer recommendId,String operatorNo,boolean isEmpty) throws BusinessException;
	
	
	/**
	 * 用户再次购买
	 * @param userId
	 * @param buyNum
	 * @param type 付款方式 
	 * @throws BusinessException
	 */
	public void userBuyAgain(Integer userId,Integer buyNum,Integer type,String operatorNo) throws BusinessException;
	/**
	 * 用户账户编号
	 * @param userIds ;多个用户用逗号间隔
	 * @param accEnum
	 * @param amount
	 * @param tradeNo
	 * @param operatorNo
	 * @throws BusinessException
	 */
	public void userAccountChange(String userIds,AccountEnum accEnum,Long amount,String tradeNo,String operatorNo) throws BusinessException;
	
	/**
	 * 插入用户奖励明细
	 * @param userId
	 * @param type
	 * @param amount
	 * @param collectDate
	 * @param fromUserId
	 */
	public void userRewardDetail(Integer userId,Integer type,BigDecimal amount,Date collectDate,Integer fromUserId);
	
	/**
	 * 生成赠送博豆任务
	 */
	public void genPointTaskDetail(Date collectDate);
	
	/**
	 * 生成用户日数据（个人业绩,团队业绩）
	 * @param collectDate
	 */
	public void genDayPointDetailAndReport(Date collectDate);
	/**
	 * 赠送博豆及给相关人员赠送推广积分,每天赠送20博豆,相关人员获取推广积分
	 * 规则：直推几个人送几代，最高送10代
	 * 计算方式：10% * 该会员团队下所有会员到账博豆之和（不计算复利！！！）
	 * @param pointTaskDetailModel
	 */
	public void sendBeansAndPoint(MPointTaskDetailModel pointTaskDetailModel) throws BusinessException;
	
	/**
	 * 获取需要赠送博豆的任务,一次去100条
	 * @throws BusinessException
	 */
	public List<MPointTaskDetailModel> findPointTaskDetails(Date collectDate,Integer rows)  throws BusinessException;
	
	
	
	
	/**
	 * 生成所有管理奖励节点
	 */
	public void calcAllManageAwards(Date date)  throws BusinessException;
	
	

	/**
	 * 生成辅导奖励
	 */
	public void calcCoachAwards(Date date)  throws BusinessException;
	
	/**
	 * 获取用户的所有父节点
	 * @param userId
	 * @return
	 */
	public String getAllParentStrById(Integer userId);
	
	/**
	 * 获取会员奖励列表
	 * @FunName findByConditions
	 * @param po
	 * @return
	 * @author fengchao
	 * @Create Date 2015年10月28日
	 */
	public GridDataModel findByConditions(PageObject po);

	/**
	 * 根据用户id获取奖励详情
	 * @FunName findRewardByConditions
	 * @param po
	 * @return
	 * @author fengchao
	 * @Create Date 2015年10月28日
	 */
	public GridDataModel findRewardByConditions(PageObject po);
	
	/**
	 * 获取统计日期
	 * @param para
	 * @return
	 */
	public MDayPointDetailModel getCollectDate();
	
	
	
	/**
	 * 会员用户提现和取消提现 
	 * @param userId
	 * @param amount
	 * @param tradeNo
	 * @param type  1:提现;2提现取消;3:现金转博豆
	 * @throws BusinessException
	 */
	public void drawCash(Integer userId,Long amount,String tradeNo,Integer type,String operatorNo) throws BusinessException;
	
	
	/**
	 * 会员用户充值,单位分
	 * @param userId
	 * @param amount
	 * @param tradeNo
	 * @param type 账户类型 1:现金;2:博豆;3:积分
	 * @throws BusinessException
	 */
	public void userDeposit(Integer userId,Long amount,String tradeNo,Integer type,String operatorNo) throws BusinessException;
	
	
	/**
	 * 账户余额激活会员
	 * @param userId
	 * @param amount
	 * @param tradeNo
	 * @param type 账户类型 1:现金;2:博豆;3:积分
	 * @param operatorNo 操作员
	 * @param regNum 单数
	 * @throws BusinessException
	 */
	public void userAccountForActivationUser(Integer userId,Long amount,String tradeNo,Integer type,String operatorNo,Integer regNum)  throws BusinessException;
	/**
	 * 会员用户博豆转积分 
	 * @param userId
	 * @param amount
	 * @param tradeNo
	 * @param type 1现金---->积分; 2:博豆--->积分;3:现金 --->博豆
	 * @throws BusinessException
	 */
	public void userAccountTransfers(Integer userId,Long amount,String tradeNo,Integer type,String operatorNo) throws BusinessException;
	
	/**
	 * 用户间账户转账
	 * @param fromUserId
	 * @param toUserId
	 * @param amount
	 * @param tradeNo
	 * @param type
	 * @param operatorNo
	 * @throws BusinessException
	 */
	public void transfersBetweenUsers(Integer fromUserId,Integer toUserId,Long amount,String tradeNo,Integer type,String operatorNo) throws BusinessException;
	/**
	 * 账户消费
	 * @param userId
	 * @param tradeNo;//订单编号
	 * @param pointAmount;//积分
	 * @param cashAmount;//现金
	 */
	public String userAccountUpdateByHlg(Integer userId,String tradeNo,BigDecimal pointAmount,BigDecimal cashAmount)  throws BusinessException;
	
	
	/**
	 * 消费取消
	 * @param userId
	 * @param tradeNo;//订单编号
	 * @param pointAmount;//积分
	 * @param cashAmount;//现金
	 */
	public String userAccountRecoveryByHlg(Integer userId,String tradeNo,BigDecimal pointAmount,BigDecimal cashAmount)  throws BusinessException;
	
	
	/**
	 * 获取可用户预支博豆
	 */
	public Long getAdvances(Integer userId);
	
	
	/**
	 * 扣除预支博豆
	 * @param userId
	 * @param amount
	 */
	public Integer saveAdvances(Integer userId,Integer amount,String operatorNo)  throws BusinessException;
	
	
	/**
	 * 取消扣除预支博豆
	 * @param userId
	 * @param amount
	 */
	public void cancelAdvances(Integer exchangeId,String operatorNo)  throws BusinessException;
	
	/**
	 * 
	 * @param userId
	 * @param cashAmount 现金
	 * @param beans 博豆
	 * @param operatorNo
	 * @return
	 * @throws BusinessException
	 */
	public Integer exchangeSmartHome(Integer userId,Long cashAmount,Integer beans,String operatorNo) throws BusinessException;
	/**
	 * 节点调整
	 * @param userId 要调整的节点ID
	 * @param targetParentId 目标父节点
	 * @throws BusinessException
	 */
	public void nodeAdjustment(Integer userId,Integer targetParentId) throws BusinessException;
	//获取韩流馆流水数据
	//public List<MUserAccountInoutHlgModel> getAccountInoutForHlg(MUserAccountInoutHlgModel model);
}
