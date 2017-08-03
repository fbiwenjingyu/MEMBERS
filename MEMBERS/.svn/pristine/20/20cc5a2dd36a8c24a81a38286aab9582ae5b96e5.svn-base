package com.daboo.reward.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotation.myibatis.GenerateMapperSqlUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.business.service.MBusinessCenterService;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.BigDecimalUtil;
import com.daboo.core.util.StringUtils;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.exchange.mapper.MExchangeInfoMapper;
import com.daboo.exchange.model.MExchangeInfoModel;
import com.daboo.hlg.api.service.impl.MessageConstant.ACCOUNT_RECOVERY;
import com.daboo.hlg.api.service.impl.MessageConstant.ACCOUNT_UPDATE;
import com.daboo.reward.enums.AccountEnum;
import com.daboo.reward.mapper.MDayPointDetailMapper;
import com.daboo.reward.mapper.MDayReportMapper;
import com.daboo.reward.mapper.MPointTaskDetailMapper;
import com.daboo.reward.mapper.MPointTaskMapper;
import com.daboo.reward.mapper.MRewardDetailMapper;
import com.daboo.reward.mapper.MStarNodeMapper;
import com.daboo.reward.mapper.MUserAccountInoutHlgMapper;
import com.daboo.reward.mapper.MUserAccountInoutMapper;
import com.daboo.reward.mapper.MUserAccountMapper;
import com.daboo.reward.model.MDayPointDetailModel;
import com.daboo.reward.model.MGradeModel;
import com.daboo.reward.model.MPointTaskDetailModel;
import com.daboo.reward.model.MPointTaskModel;
import com.daboo.reward.model.MRewardDetailModel;
import com.daboo.reward.model.MStarNodeModel;
import com.daboo.reward.model.MUserAccountInoutModel;
import com.daboo.reward.model.MUserAccountModel;
import com.daboo.system.service.SysDictService;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.web.context.ScheduledUtil;
@Service
@Transactional
public class MemberRewardServiceImpl implements MemberRewardService{
	public static final String SYSTEM_USER_NO = "super";
	public static final String SYSTEM_USER_NO_HLG = "super_hlg";//韩流馆接口
	private static final String SYSTEM_AWARD_DESC = "来自【系统奖励】";
	//private static Integer sendPoint = Integer.valueOf("");
	//sysDictService
	//private static Integer sendDays = 150; 
	//private static Integer rewardAmount = 100;
	//private static String rechargeAmount = "2000";
	protected static final Logger LOG = LoggerFactory.getLogger(MemberRewardServiceImpl.class);
	@Autowired
	MStarNodeMapper starNodeMapper;
	
	@Autowired
	MPointTaskMapper pointTaskMapper;
	
	@Autowired
	MPointTaskDetailMapper pointTaskDetailMapper;
	
	@Autowired
	SysDictService sysDictService;
	
	@Autowired
	MUserAccountMapper userAccountMapper;
	
	@Autowired
	MUserAccountInoutMapper userAccountInoutMapper;
	
	@Autowired
	MUserAccountInoutHlgMapper inoutHlgMapper;
	
	@Autowired
	MDayPointDetailMapper dayPointDetailMapper;
	
	@Autowired
	MUserInfoMapper userInfoMapper;
	@Autowired
	MRewardDetailMapper mrewardDetailMapper;
	
	@Autowired
	MRewardDetailMapper rewardDetailMapper;
	@Autowired
	MExchangeInfoMapper mExchangeInfoMapper;	
	@Autowired
	MBusinessCenterService mBusinessCenterService;
	/**
	 * 激活会员步骤如下
	 * 1:生成会员账户信息(账户包括  1:现金;2:博豆;3:积分;4:股权)
	 * 2:生成会员层级关系
	 * 3:修改父节点的直推人数
	 * 4:发放直推奖励
	 * 5:生成博豆发放任务,博豆每天凌晨一点执行
	 * 6:计算管理奖
	 * 7：计算辅导奖励
	 * @param userId 用户ID
	 * @param recommendId,推荐人
	 * @param operatorNo,操作员
	 * @param isEmpty,是否空单,空单无博豆;true 则无博豆;false 有博豆
	 * @param recommendNum,推荐人数
	 */
	@Override
	public void activationUser(Integer userId,Integer recommendId,String operatorNo,boolean isEmpty) throws BusinessException {
		MStarNodeModel parentNode = null;
		//if(recommendId != -1){
			parentNode = starNodeMapper.getObjectById(recommendId);
			if(parentNode == null){
				throw new BusinessException("推荐人无效,请选择合适的推荐人"); 
			}
		//}
		//生成会员账户信息(账户包括  1:现金;2:博豆;3:积分)
		Integer accArr[] = {1,2,3};
		for(Integer acc : accArr){
			addUserAccountInfo(userId,acc);
		}
		Date curr_date = new Date();
		//生成会员层级关系
		MStarNodeModel starNodeModel = new MStarNodeModel();
		starNodeModel.setCreateTime(curr_date);
		starNodeModel.setUserId(userId);
		starNodeModel.setParentId(recommendId);
		/*if(parentNode == null){
			starNodeModel.setParentStr(null);
			starNodeModel.setFloors(1);
		}else{*/
			starNodeModel.setParentStr(parentNode.getChildParentStr());
			starNodeModel.setFloors(parentNode.getFloors()+1);
		//}
		starNodeModel.setRecommendNums(0);
		starNodeModel.setVersion(1);
		starNodeMapper.insertModel(starNodeModel);
		
		String rechargeAmountValue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		//相关父节点的直推人数
		//if(recommendId != -1){
			starNodeMapper.updateRecommendNumsById(recommendId);
			if(!isEmpty){
				userInfoMapper.updateMyMoney(recommendId, Integer.parseInt(rechargeAmountValue));
			}
			
			//修改团队业绩
			String parentStr =  getAllParentStrById(recommendId);
			if(!StringUtils.isEmpty(parentStr) && !isEmpty){
				userInfoMapper.updateTeamMoney(parentStr,Integer.parseInt(rechargeAmountValue));
			}
		//}				
		
		//发放直推奖励
		if(!StringUtils.isEmpty(starNodeModel.getParentStr())){
			String userArr[] = starNodeModel.getParentStr().split(",");
			String userStrBefore = null;
			if(userArr.length <= 3){
				userStrBefore = starNodeModel.getParentStr();
			}else{
				for(int i= userArr.length;i>userArr.length-3;i--){
					if(userStrBefore != null){
						userStrBefore += ",";
					}else{
						userStrBefore = "";
					}
					userStrBefore += userArr[i-1];
				}
			}
			
			//判断三代人员是否为正常状态,冻结的人员不奖励
			String userStrAfter = userInfoMapper.findUserIdsByIdAndTypes(userStrBefore, "1,3");
			if(!StringUtils.isEmpty(userStrAfter)){
				
				MUserInfoModel fromUser = userInfoMapper.getObjectById(userId);
				
				BigDecimal rewardAmount = BigDecimalUtil.mul(new BigDecimal(rechargeAmountValue),new BigDecimal(5));
				//将用户直推将插入用户账户及账户变动明细表
				userAccountChange(userStrAfter,AccountEnum.REWARD_CASH,rewardAmount.longValue(),((fromUser == null)?"":fromUser.getAccountNo()),operatorNo);
				//插入日报表--直推奖
				MDayPointDetailModel dayPointDetailModel = new MDayPointDetailModel();
				dayPointDetailModel.setMyMoney(0);
				dayPointDetailModel.setTeamMoney(0);
				dayPointDetailModel.setCopperCardNum(0);
				dayPointDetailModel.setSilverCardNum(0);
				dayPointDetailModel.setCollectDate(curr_date);
				dayPointDetailModel.setPointAmount(new BigDecimal(0));
				List<String> userList = Arrays.asList(userStrAfter.split(","));
				dayPointDetailModel.setUserIds(userList);
				dayPointDetailModel.setVersion(1);
				dayPointDetailModel.setStatus(1);
				dayPointDetailModel.setManageAmount(new BigDecimal(0));
				dayPointDetailModel.setCoachAmount(new BigDecimal(0));
				dayPointDetailModel.setRewardAmount(rewardAmount);
				dayPointDetailModel.setBeansNum(0);
				dayPointDetailMapper.updateDayPointDetailForSend(dayPointDetailModel);
				
				for(String item : userList){
					userRewardDetail(Integer.valueOf(item), AccountEnum.REWARD_CASH.getInoutType(), rewardAmount, new Date(), userId);
				}
				
			}			
		}
		//生成博豆发放任务
		if(!isEmpty){
			MPointTaskModel pointTaskModel = new MPointTaskModel();
		    pointTaskModel.setCreateTime(curr_date);
		    pointTaskModel.setUserId(userId);
		    String sendPointValue = sysDictService.getDetailValue("SEND_POINT","SEND_POINT");
		    pointTaskModel.setPointAmount(Integer.valueOf(sendPointValue));//每天赠送的博豆
		    pointTaskModel.setExecStatus(1);
		    String sendDaysValue = sysDictService.getDetailValue("SEND_DAYS","SEND_DAYS"); 
		    pointTaskModel.setTotalDays(Integer.valueOf(sendDaysValue));//赠送的天数
		    pointTaskModel.setExecDays(0);//已执行的天数
		    pointTaskModel.setVersion(1);
			pointTaskMapper.insertModel(pointTaskModel);
		}
		//计算管理奖励
		String parUserIds = getAllParentStrById(userId);
		//同一等级只有1个可以获取管理奖励,并且激活状态的才赠送
		if(!StringUtils.isEmpty(parUserIds)){
			calcManageAward(userInfoMapper.getUserInfoForAward(parUserIds,"1,3"),curr_date,userId);
		}					
		
		//7计算辅导奖		
		calcCoachAward(curr_date,userId,parUserIds);
		//userInfoMapper.updateAwardFlagByUserId(item, 2);
	}
	
	
	/**
	 * 用户再次购买
	 * 1:生成用户信息
	 * 2:生成会员账户信息(账户包括  1:现金;2:博豆;3:积分)
	 * 3:生成会员层级关系
	 * 4:修改父节点的直推人数
	 * 5:发放直推奖励
	 * 6:生成博豆发放任务
	 * @param userId
	 * @param buyNum
	 * @param type 付款方式
	 * @throws BusinessException
	 */
	public void userBuyAgain(Integer userId,Integer buyNum,Integer type,String operatorNo) throws BusinessException{
		
		String rechargeAmountValue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		
		Integer rAmount = Integer.parseInt(rechargeAmountValue)*buyNum;
		
		String scaleValue = sysDictService.getDetailValue("CASH_SCALE","CASH_SCALE");
		Integer pointScal = 100 - Integer.valueOf(scaleValue);	
		
		MUserInfoModel parUserInfo = userInfoMapper.getObjectById(userId);//父节点用户信息
		if(parUserInfo == null){
			throw new BusinessException("父节点不存在,请选择合适的父节点");
		}
		if (type.equals(1)) {
			userAccountForActivationUser(userId, BigDecimalUtil.mul(new BigDecimal(rAmount), new BigDecimal(100)).longValue() , parUserInfo.getAccountNo(), Integer.valueOf(1),operatorNo,1);
		}else if (type.equals(2)) {
			userAccountForActivationUser(userId, BigDecimalUtil.mul(new BigDecimal(rAmount), new BigDecimal(scaleValue)).longValue() ,parUserInfo.getAccountNo(), Integer.valueOf(1),operatorNo,1);
			userAccountForActivationUser(userId, BigDecimalUtil.div(BigDecimalUtil.mul(new BigDecimal(rAmount), new BigDecimal(pointScal)),new BigDecimal(100)).longValue() , parUserInfo.getAccountNo(), Integer.valueOf(2),operatorNo,1);
		}else if (type.equals(3)) {
			userAccountForActivationUser(userId, BigDecimalUtil.mul(new BigDecimal(rAmount), new BigDecimal(scaleValue)).longValue() , parUserInfo.getAccountNo(), Integer.valueOf(1),operatorNo,1);
			userAccountForActivationUser(userId, BigDecimalUtil.div(BigDecimalUtil.mul(new BigDecimal(rAmount), new BigDecimal(pointScal)),new BigDecimal(100)).longValue() , parUserInfo.getAccountNo(), Integer.valueOf(3),operatorNo,1);
		} else {
			throw new BusinessException("激活方式不存在");
		}

		MUserInfoModel masterUserInfo = null;
		if(parUserInfo.getMasterUserId() != null){
			//获取主账户
			masterUserInfo = userInfoMapper.getObjectById(parUserInfo.getMasterUserId());
			
			if(masterUserInfo == null){
				throw new BusinessException("父节点不存在,请选择合适的父节点");
			}
		}else{
			masterUserInfo = parUserInfo;
		}
				
		MUserInfoModel userInfo = null;
		for(int i=0;i<buyNum;i++){
			userInfo = new MUserInfoModel(masterUserInfo);			
			userInfo.setAccountNo(StringUtils.getUserNoSuffix(masterUserInfo.getAccountNo(), masterUserInfo.getTotalNum()+i));
			userInfo.setMasterUserId(masterUserInfo.getId());
			userInfo.setRecommendId(userId);//设置推荐人
			userInfo.setTotalNum(masterUserInfo.getTotalNum() + buyNum);			
			userInfo.setIsOpend(2);//1空单 2实单
			userInfo.setMyMoney(0);//个人业绩
			userInfo.setTeamMoney(0);//团队业绩
			userInfo.setActivateBy(operatorNo);
			userInfoMapper.insertModel(userInfo);//生成账户信息

			mBusinessCenterService.activeUserToBusinessCenter(userInfo.getAccountNo());
			activationUser(userInfo.getId(),userId,operatorNo,false);
		}
		//masterUserInfo.setTotalNum(masterUserInfo.getTotalNum() + buyNum);
		//修改总人数
		userInfoMapper.updateMasterUserInfoTotalNum(masterUserInfo.getId(),buyNum);		
	}
	
	
	/**
	 * 添加会员账户信息
	 * @param userId
	 * @param accountType
	 */
	public void addUserAccountInfo(Integer userId,Integer accountType){
		MUserAccountModel userAccount = new MUserAccountModel();
		userAccount.setAccountAmount(0l);
		userAccount.setAccountStatus(1);
		userAccount.setUserId(userId);
		userAccount.setAccountType(accountType);
		userAccount.setVersion(1);
		userAccount.setCreateTime(new Date());
		try{
		userAccountMapper.insertModel(userAccount);
		}catch(Exception e){
			LOG.info("add user err, {}",e.getMessage());
		}
	}
	/**
	 * 用户账户编号
	 * @param userIds ;多个用户用逗号间隔
	 * @param accEnum 账户变动类型
	 * @param amount
	 * @param tradeNo
	 * @param operatorNo
	 * @throws BusinessException
	 */
	public void userAccountChange(String userIds,AccountEnum accEnum,Long accountAmount,String tradeNo,String operatorNo) throws BusinessException {
			if(userIds == null){
				throw new BusinessException("请选择用户");
			}
			if(accEnum == null){
				throw new BusinessException("请选择账户变动类型");
			}
			if(StringUtils.isEmpty(tradeNo)){
				throw new BusinessException("交易流水不能为空");
			}
			
			if(accountAmount == null){
				throw new BusinessException("金额不能为空,请检查");
			}
			
			//修改账户余额
			MUserAccountModel userAccount = new MUserAccountModel();
			userAccount.setAccountAmount(accountAmount);
			userAccount.setUpdateTime(new Date());
			userAccount.setUserIds(userIds);
			userAccount.setAccountType(accEnum.getAccountType());
			userAccountMapper.updateModelByUserIds(userAccount);
			
			//插入账户变动明细
			MUserAccountInoutModel inout = new MUserAccountInoutModel();
			inout.setAmount(accountAmount);
			inout.setUserIds(userIds);
			inout.setAccountType(accEnum.getAccountType());
			inout.setInoutType(accEnum.getInoutType());
			inout.setInoutDesc(accEnum.getInoutDesc());
			inout.setTradeNo(tradeNo);
			inout.setCreateTime(new Date());
			inout.setUserType(accEnum.getUserType());
			inout.setOperatorNo(operatorNo);
			userAccountInoutMapper.insertUserAccountInout(inout);
		 
	}
	
	public static void main(String args[]){
		System.out.println(1799*3%20 == 0 ? 1799*3/20 : 1799*3/20 + 1);
		new BigDecimal(10000);
		BigDecimal total = BigDecimalUtil.add(new BigDecimal(10000), new BigDecimal(4000));
		BigDecimal result = BigDecimalUtil.div(BigDecimalUtil.mul(total, new BigDecimal(3)), new BigDecimal(100));
		
		System.out.println(result);
		System.out.println(result.longValue());
		Integer xx = new Integer(10);
		System.out.println(xx.equals(10));
		System.out.println(xx == 10);
		for(int i=1;i<1000;i++){
			System.out.println("i="+i+";"+StringUtils.getUserNoSuffix("x001--",i));
		}
		
		String as = "-1,1,3,4";
		List<String> a = Arrays.asList(as.split(","));
		
		System.out.println("dddd"+a.contains(String.valueOf(1)));
		/*String userArr[] = {"1","2","3"};
		String userStr = null;
		if(userArr.length <=3){
			userStr ="1,2,3";
		}else{
			for(int i= userArr.length;i>userArr.length-3;i--){
				if(userStr != null){
					userStr += ",";
				}else{
					userStr = "";
				}
				userStr += userArr[i-1];
			}
		}
		
		public static void main(String args[]){
	
	
	
		System.out.println(userStr);*/
		//int type = 2;
		//Integer accEnumType = type*100+10;
		//AccountEnum accountEnum = AccountEnum.getAccountEnumByInoutType(accEnumType,null);
	 
		System.out.println(2 <2);
	
		MUserInfoModel f = new MUserInfoModel();
		Field[] fs = f.getClass().getDeclaredFields();
		for(Field d : fs){
			System.out.println("sb.append(\""+d.getName()+"=\").append("+d.getName()+").append(\";\");");
		}
		for(Field d : fs){
			
			System.out.println("this."+d.getName()+" = master."+d.getName()+";");
		}
    	GenerateMapperSqlUtil.main(new String[]{MDayReportMapper.class.getName()});
	}
	
	/**
	 * 生成赠送博豆任务明细
	 */
	@Override
	public void genPointTaskDetail(Date collectDate) {
		pointTaskMapper.genPointTaskDetail(collectDate);
	}
	
	/**
	 * 赠送博豆及给相关人员赠送推广积分,每天赠送20博豆,相关人员获取推广积分
	 * 规则：直推几个人送几代，最高送10代
	 * 计算方式：10% * 该会员团队下所有会员到账博豆之和（不计算复利！！！）
	 * @param pointTaskDetailModel
	 * @throws BusinessException 
	 */
	@Override
	public void sendBeansAndPoint(MPointTaskDetailModel pointTaskDetailModel) throws BusinessException {
		// 给用户赠送博豆
		userAccountChange(String.valueOf(pointTaskDetailModel.getUserId()),AccountEnum.REWARD_BEANS, (long)pointTaskDetailModel.getPointAmount(),SYSTEM_AWARD_DESC,SYSTEM_USER_NO);
		MDayPointDetailModel dayBeansModel = new MDayPointDetailModel();
		dayBeansModel.setMyMoney(0);
		dayBeansModel.setTeamMoney(0);
		dayBeansModel.setCollectDate(pointTaskDetailModel.getCollectDate());
		dayBeansModel.setPointAmount(new BigDecimal(0));
		dayBeansModel.setRewardAmount(new BigDecimal(0));
		dayBeansModel.setManageAmount(new BigDecimal(0));
		dayBeansModel.setCoachAmount(new BigDecimal(0));
		List<String> userIdList = new ArrayList<String>();
		userIdList.add(String.valueOf(pointTaskDetailModel.getUserId()));
		dayBeansModel.setUserIds(userIdList);
		dayBeansModel.setVersion(1);
		dayBeansModel.setStatus(1);
		dayBeansModel.setBeansNum(pointTaskDetailModel.getPointAmount());
		//插入日报表----购物积分(博豆)
		dayPointDetailMapper.updateDayPointDetailForSend(dayBeansModel);
		
		
		
		MStarNodeModel starNodeModel = starNodeMapper.getObjectById(pointTaskDetailModel.getUserId());
		if(starNodeModel != null && !StringUtils.isEmpty(starNodeModel.getParentStr())){
			List<MStarNodeModel> nodes = starNodeMapper.findUserForSendPoint(starNodeModel);
			StringBuffer userIds = new StringBuffer();
			
			for(MStarNodeModel detail : nodes){
				if(userIds.length()>0){
					userIds.append(",");
				}
				userIds.append(detail.getUserId());
				
			}
			if(userIds.length() >0){
				MUserInfoModel userModel = userInfoMapper.getObjectById(pointTaskDetailModel.getUserId());
				
				//修改用户账户信息-每天赠送20博豆
				userAccountChange(userIds.toString(),AccountEnum.REWARD_POINT, BigDecimalUtil.mul(new BigDecimal(pointTaskDetailModel.getPointAmount()), new BigDecimal(0.1)).longValue(),((userModel == null)?"":userModel.getAccountNo()),SYSTEM_USER_NO);
			
				MDayPointDetailModel dayPointDetailModel = new MDayPointDetailModel();
				dayPointDetailModel.setMyMoney(0);
				dayPointDetailModel.setTeamMoney(0);
				dayPointDetailModel.setCollectDate(pointTaskDetailModel.getCollectDate());
				dayPointDetailModel.setCopperCardNum(0);
				dayPointDetailModel.setSilverCardNum(0);
				dayPointDetailModel.setPointAmount(BigDecimalUtil.mul(new BigDecimal(pointTaskDetailModel.getPointAmount()), new BigDecimal(0.1)));
				dayPointDetailModel.setRewardAmount(new BigDecimal(0));
				dayPointDetailModel.setManageAmount(new BigDecimal(0));
				dayPointDetailModel.setCoachAmount(new BigDecimal(0));
				dayPointDetailModel.setUserIds(Arrays.asList(userIds.toString().split(",")));
				dayPointDetailModel.setVersion(1);
				dayPointDetailModel.setStatus(1);
				dayPointDetailModel.setBeansNum(0);
				//插入日报表----推广积分日
				dayPointDetailMapper.updateDayPointDetailForSend(dayPointDetailModel);
			}
				
			
		}
		
		
		pointTaskDetailModel.setExecStatus(2);
		pointTaskDetailModel.setExecTime(new Date());
		//修改用户执行状态
		pointTaskDetailMapper.updateModelById(pointTaskDetailModel);
	}
	
	/**
	 * 获取需要赠送博豆的任务,一次去100条
	 */
	@Override
	public List<MPointTaskDetailModel> findPointTaskDetails(Date collectDate,Integer rows) throws BusinessException {
		MPointTaskDetailModel pointTaskDetail = new MPointTaskDetailModel();
		pointTaskDetail.setExecStatus(1);
		pointTaskDetail.setCollectDate(collectDate);
		return pointTaskDetailMapper.getListByModel(pointTaskDetail, new RowBounds(0,rows));
	}
	
	/**
	 * 生成管理节点
	 */
	@Override
	public void calcAllManageAwards(Date date) throws BusinessException {
		//计算出管理奖励的统计时间
		/*MDayPointDetailModel para = new MDayPointDetailModel();
		dayPointDetailMapper.getCollectDate(para);
		if(para.getNums() > 0 ){
			LOG.error("{} 还有 博豆未赠送完,请稍后再执行,未完成任务数量 = {}",para.getCollectDate(),para.getNums());
			return ;
		}*/
		//生成管理奖励
		//List<MDayPointDetailModel> dayDetails = null;
		//MDayPointDetailModel dayPointModel = null;
		//Set<Integer> gradeSet = new HashSet<Integer>();//系统所有奖励等级
		/*Map<Integer,MGradeModel> gradeMap = new HashMap<Integer,MGradeModel>();
		gradeMap.put(1, new MGradeModel(1,"铜卡",5,new BigDecimal(1000)));
		gradeMap.put(2, new MGradeModel(2,"银卡",8,new BigDecimal(3000)));
		gradeMap.put(3, new MGradeModel(3,"金卡",10,new BigDecimal(7000)));*/
		
		List<Integer> userIdList = userInfoMapper.findUserIdForAward(date,0);
		//MDayPointDetailModel para = null;
		for(Integer userId : userIdList){
			/*para.setPointAmount(new BigDecimal(1000));
			para.setStatus(2);
			para.setOrderByClause("C.FLOORS DESC");
			dayDetails	 = dayPointDetailMapper.findDayPointDetailForAward(para, new RowBounds(0,1));
			if(dayDetails == null || dayDetails.size() == 0){
				break;
			}
			dayPointModel = dayDetails.get(0);*/
			//para = new MDayPointDetailModel();
			//判断用户是否激活
			String userIds = getAllParentStrById(userId);
			if(StringUtils.isEmpty(userIds)){
				userInfoMapper.updateAwardFlagByUserId(userId, 1);
				continue;
			}
			//para.setCollectDate(date);
			/*if(StringUtils.isEmpty(userIds)){
				userIds = String.valueOf(dayPointModel.getUserId());
			}else{
				userIds += dayPointModel.getUserId()+",";
			}*/
			//para.setUserIds(Arrays.asList(userIds.split(",")));
			/*MUserInfoModel userInfoPara = new MUserInfoModel();
			userInfoPara.setUserIds(userIds);*/
			//dayDetails = dayPointDetailMapper.findDayPointDetailForAward(para,new RowBounds());
			//同一等级只有1个可以获取管理奖励,并且激活状态的才赠送
			calcManageAward(userInfoMapper.getUserInfoForAward(userIds,"1,3"),date,userId);
			
			userInfoMapper.updateAwardFlagByUserId(userId, 1);
			
		}
		//dayPointDetailMapper.updateDayPointDetailByState(3, 2, date);
	}
	
	/**
	 * 计算管理奖励
	 * @param dayDetails
	 * @throws BusinessException 
	 */
	public void calcManageAward(List<MUserInfoModel> userInfos,Date collectDate,Integer fromUserId) throws BusinessException{
		MGradeModel base  = null;
		Set<Integer> gradeSet = new HashSet<Integer>();//系统所有奖励等级
		Map<Integer,MGradeModel> gradeMap = ScheduledUtil.getInstance().getGradeMap();
		gradeSet.addAll(gradeMap.keySet());
		Integer lastAwardScale = 0;
		MUserInfoModel fromUserInfo = userInfoMapper.getObjectById(fromUserId);
		for(MUserInfoModel item : userInfos){
			if(item.getLevel() < 1){
				
				continue;
			}
			
			base = gradeMap.get(item.getLevel());
			if(base == null){
				continue;
			}
			//int hasDeduct = item.getPointAmount().compareTo(base.getGradeAmount());
			//boolean checkNum  = true;
			
			/*if(item.getLevel() ==2){
				if(item.getCopperCardNum() == null || item.getCopperCardNum() <2){
					checkNum = false;
				}
			}
			
			if (item.getLevel() == 3) {
				if (item.getSilverCardNum() == null || item.getSilverCardNum() < 3) {
					checkNum = false;
				}
			}*/
			
			if(gradeSet.contains(item.getLevel())  /*&& checkNum*/){
				
				//计算管理节点奖励比例
				Integer currScale = base.getScale();
				if(currScale == null){
					currScale = 0;
				}
				
				Integer scale = (currScale - lastAwardScale);
				LOG.info("用户 = {} ,统计日期 = {},管理节点奖励比例  = {}",item.getId(),collectDate,scale);
				
				if(scale > 0){
					//将节点管理奖励添加到节对应账户中
					deductManageAward(item,collectDate,scale,fromUserInfo);
					lastAwardScale = currScale;
				}
				
				gradeSet.remove(item.getLevel());
			}
		}
	}
	
	public MDayPointDetailModel getMDayPointDetailModel(Date date,Integer userId){
		MDayPointDetailModel para = new MDayPointDetailModel();
		para.setCollectDate(date);
		para.setUserId(userId);
		MDayPointDetailModel result = dayPointDetailMapper.getObjectByModel(para);
		if(result == null){
			para.setManageAmount(new BigDecimal(0));
			para.setRewardAmount(new BigDecimal(0));
		}
		return result;
	}
	
	/**
	 * 获取奖励
	 * @param date
	 * @param userId
	 * @param fromUserId
	 * @return
	 */
	public BigDecimal getRewardAmountById(Date date,Integer userId,Integer fromUserId){
		//Map<Integer,BigDecimal> result = new HashMap<Integer,BigDecimal>();
		MRewardDetailModel para = new MRewardDetailModel();
		para.setCollectDate(date);
		para.setUserId(userId);
		para.setFromUserId(fromUserId);
		List<MRewardDetailModel> details = rewardDetailMapper.getListByModel(para);
		BigDecimal total = new BigDecimal(0);
		
		for(MRewardDetailModel item : details){
			if(AccountEnum.REWARD_CASH.getInoutType().equals(item.getRewardType())   || AccountEnum.REWARD_MANAGE_CASH.getInoutType().equals(item.getRewardType())){
				total = BigDecimalUtil.add(total,item.getRewardAmount());
				}
		}
		
		
		return total;
	}
	
	
	public Map<Integer,BigDecimal> getRewardAmountById(Date date,Integer fromUserId){
		//Map<Integer,BigDecimal> result = new HashMap<Integer,BigDecimal>();
		MRewardDetailModel para = new MRewardDetailModel();
		para.setCollectDate(date);
		para.setFromUserId(fromUserId);
		List<MRewardDetailModel> details = rewardDetailMapper.getListByModel(para);
		//BigDecimal total = new BigDecimal(0);
		Map<Integer,BigDecimal> result = new HashMap<Integer,BigDecimal>();
		for(MRewardDetailModel item : details){
			if(AccountEnum.REWARD_CASH.getInoutType().equals(item.getRewardType())   || AccountEnum.REWARD_MANAGE_CASH.getInoutType().equals(item.getRewardType())){				
				if(result.containsKey(item.getUserId())){
					result.put(item.getUserId(),BigDecimalUtil.add(result.get(item.getUserId()),item.getRewardAmount()));
				}else{
					result.put(item.getUserId(),item.getRewardAmount());
				}
			}
		}
		
		
		return result;
	}
	
	/**
	 * 将节点管理奖励添加到节对应账户中
	 * @param dayPointDetail
	 * @throws BusinessException 
	 */
	public void deductManageAward(MUserInfoModel userInfo,Date collectDate,Integer scale,MUserInfoModel userFrom) throws BusinessException{
		String rechargeAmountVallue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		BigDecimal amount100 = BigDecimalUtil.mul(new BigDecimal(rechargeAmountVallue),new BigDecimal(scale));
		//
		userAccountChange(String.valueOf(userInfo.getId()),AccountEnum.REWARD_MANAGE_CASH,amount100.longValue(),userFrom.getAccountNo(),SYSTEM_USER_NO);
		
		MDayPointDetailModel dayPointDetailModel = new MDayPointDetailModel();
		dayPointDetailModel.setMyMoney(0);
		dayPointDetailModel.setTeamMoney(0);
		dayPointDetailModel.setCollectDate(collectDate);
		dayPointDetailModel.setPointAmount(new BigDecimal(0));
		List<String> users = new ArrayList<String>();
		users.add(String.valueOf(userInfo.getId()));
		dayPointDetailModel.setUserIds(users);
		dayPointDetailModel.setVersion(1);
		dayPointDetailModel.setStatus(3);
		dayPointDetailModel.setManageAmount(amount100);
		dayPointDetailModel.setCoachAmount(new BigDecimal(0));
		dayPointDetailModel.setRewardAmount(new BigDecimal(0));
		dayPointDetailModel.setBeansNum(0);
		dayPointDetailMapper.updateDayPointDetailForSend(dayPointDetailModel);
		
		userRewardDetail(userInfo.getId(), AccountEnum.REWARD_MANAGE_CASH.getInoutType(), amount100,collectDate, userFrom.getId());
	}
	
	/**
	 * 获取用户的所有父节点
	 */
	@Override
	public String getAllParentStrById(Integer userId) {
		MStarNodeModel starNode = starNodeMapper.getObjectById(userId);
		if(StringUtils.isEmpty(starNode.getParentStr())){
			return null;
		}
		if(starNode.getFloors() <= 10){
			return starNode.getParentStr();
		}
		String parentStr = starNode.getParentStr();
		MStarNodeModel frist  =  starNodeMapper.getObjectById(Integer.parseInt(parentStr.substring(0,parentStr.indexOf(","))));
		if(frist == null){
			return parentStr;
		}
		return StringUtils.isEmpty(frist.getParentStr())?parentStr:getAllParentStrById(frist.getUserId())+","+parentStr;
	}
	
	/**
	 * 生成辅导奖励
	 */
	@Override
	public void calcCoachAwards(Date date) throws BusinessException {
		// 计算出管理奖励的统计时间
		/*MDayPointDetailModel para = new MDayPointDetailModel();
		dayPointDetailMapper.getCollectDate(para);
		if (para.getNums() > 0) {
			LOG.error("{} 还有 博豆未赠送完,请稍后再执行,未完成任务数量 = {}",para.getCollectDate(), para.getNums());
			return;
		}*/
		
		//List<MDayPointDetailModel> dayDetails = dayPointDetailMapper.findDayPointDetailForCoachAwards(para);
		List<Integer> userIdList = userInfoMapper.findUserIdForAward(date,1);
		for(Integer item : userIdList){
			
			calcCoachAward(date,item,"parentStr");
			userInfoMapper.updateAwardFlagByUserId(item, 2);
		}
		//dayPointDetailMapper.updateDayPointDetailByState(4, 3, date);

	}
	
	
	
	/*public void calcCoachAward(Date date,Integer userId) throws BusinessException{
		
		
		MStarNodeModel starNode = starNodeMapper.getObjectById(userId);
		if(starNode == null || starNode.getParentId() == -1){
			return;
		}
		*//**
		 * 普通会员 -代奖励 10%
		 * 铜卡会员 -代奖励 10%,二代奖励 5%
		 * 银卡,金卡会员 -代奖励 10%,二代奖励 5%,三代奖励3%
		 * 
		 *//*
		 
		//代奖励 10%

		//MDayPointDetailModel first = getMDayPointDetailModel(date, starNode.getParentId());
		calcCoachAwardDetail(date,String.valueOf(starNode.getParentId()),BigDecimalUtil.div(BigDecimalUtil.mul(getRewardAmountById(date, starNode.getParentId(), userId),new BigDecimal(1)),new BigDecimal(10)),userId);
		
		 
		String arr[] = starNode.getParentStr().split(",");
		if(arr == null || arr.length == 1){
			return;
		}
		//二代奖励 5%
		if(arr.length>1){
			MUserInfoModel secondUser = userInfoMapper.getObjectById(Integer.valueOf(arr[arr.length-2]));
			if(secondUser.getLevel() >=1){
				
				calcCoachAwardDetail(date,arr[arr.length-2],BigDecimalUtil.div(BigDecimalUtil.mul(getRewardAmountById(date, secondUser.getId(), userId) ,new BigDecimal(5)),new BigDecimal(100)),userId);
			
			}
		}
		//三代奖励3%
		if(arr.length>2){
			MUserInfoModel thirdUser = userInfoMapper.getObjectById(Integer.valueOf(arr[arr.length-3]));
			if(thirdUser.getLevel() >=2){
			
			calcCoachAwardDetail(date,arr[arr.length-3],BigDecimalUtil.div(BigDecimalUtil.mul(getRewardAmountById(date, thirdUser.getId(), userId),new BigDecimal(3)),new BigDecimal(100)),userId);
			}
		}
	}*/
	
	public void calcCoachAward(Date date,Integer userId,String parStr) throws BusinessException{
		//String parStr = getAllParentStrById(userId);
		/*MStarNodeModel starNode = starNodeMapper.getObjectById(userId);
		if(starNode == null || starNode.getParentId() == -1){
			return;
		}
		
		String arr[] = starNode.getParentStr().split(",");
		if(arr == null || arr.length == 1){
			return;
		}*/
		if(StringUtils.isEmpty(parStr)){
			return;
		}
		
		String arr[] = parStr.split(",");
		if(arr == null || arr.length == 1){
			return;
		}
		
		//获取所有父节点的奖励
		Map<Integer,BigDecimal> awards = getRewardAmountById(date, userId);
		
		/**
		 * 普通会员 -代奖励 10%
		 * 铜卡会员 -代奖励 10%,二代奖励 5%
		 * 银卡,金卡会员 -代奖励 10%,二代奖励 5%,三代奖励3%
		 * 
		 */		 	
		 
		int arrSize = arr.length;
		for(int i=0;i< arrSize-1 ; i++){	
			String nextId = arr[i+1];
			//普通会员 -代奖励 10%
			if(awards.containsKey(Integer.valueOf(nextId))){
				calcCoachAwardDetail(date,arr[i],BigDecimalUtil.div(BigDecimalUtil.mul(awards.get(Integer.valueOf(nextId)),new BigDecimal(1)),new BigDecimal(10)),Integer.valueOf(nextId));
			}
			
			MUserInfoModel userModel = userInfoMapper.getObjectById(Integer.valueOf(arr[i]));
			// 铜卡会员 -代奖励 10%,二代奖励 5%
			if(i  < (arrSize-2) && userModel.getLevel() >= 1){
				if(awards.containsKey(Integer.valueOf(arr[i+2]))){
				calcCoachAwardDetail(date,arr[i],BigDecimalUtil.div(BigDecimalUtil.mul(awards.get(Integer.valueOf(arr[i+2])),new BigDecimal(5)),new BigDecimal(100)),Integer.valueOf(arr[i+2]));
				}
			}
			//银卡,金卡会员 -代奖励 10%,二代奖励 5%,三代奖励3%
			if(i< (arrSize - 3) && userModel.getLevel() >= 2){
				if(awards.containsKey(Integer.valueOf(arr[i+3]))){
					calcCoachAwardDetail(date,arr[i],BigDecimalUtil.div(BigDecimalUtil.mul(awards.get(Integer.valueOf(arr[i+3])),new BigDecimal(3)),new BigDecimal(100)),Integer.valueOf(arr[i+3]));
				}
			}			
		}
		
		
	}
	
	public void calcCoachAwardDetail(Date	collectDate,String userIds,BigDecimal amount,Integer curr_user) throws BusinessException{
		if(amount.compareTo(new BigDecimal(0)) != 1){
			return;
		}
		MUserInfoModel fromUser = userInfoMapper.getObjectById(curr_user);
		//计算辅导奖励
		userAccountChange(userIds,AccountEnum.REWARD_TUTOR_CASH, amount.longValue(),fromUser.getAccountNo(),SYSTEM_USER_NO);
	
		MDayPointDetailModel dayPointDetailModel = new MDayPointDetailModel();
		dayPointDetailModel.setMyMoney(0);
		dayPointDetailModel.setTeamMoney(0);
		dayPointDetailModel.setCollectDate(collectDate);
		dayPointDetailModel.setPointAmount(new BigDecimal(0));
		dayPointDetailModel.setRewardAmount(new BigDecimal(0));
		dayPointDetailModel.setManageAmount(new BigDecimal(0));
		dayPointDetailModel.setCoachAmount(amount);
		List<String> userList = Arrays.asList(userIds.split(","));
		dayPointDetailModel.setUserIds(userList);
		dayPointDetailModel.setVersion(1);
		dayPointDetailModel.setStatus(4);
		dayPointDetailModel.setBeansNum(0);
		//插入日报表----推广辅导奖励
		dayPointDetailMapper.updateDayPointDetailForSend(dayPointDetailModel);
		for(String item : userList){
			userRewardDetail(Integer.valueOf(item), AccountEnum.REWARD_TUTOR_CASH.getInoutType(), amount, collectDate, curr_user);
		}
	}
	/**
	 * 获取会员奖励列表
	 * @FunName findByConditions
	 * @param po
	 * @return
	 * @author fengchao
	 * @Create Date 2015年10月28日
	 */
	public GridDataModel findByConditions(PageObject po){
		
		RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
		List<MDayPointDetailModel> rows=dayPointDetailMapper.findByConditions(po.getCondition(),rb);
		int count=dayPointDetailMapper.countByConditions(po.getCondition());
		//计算奖励实发
		calculateAllReward(rows);
		return new GridDataModel(rows, count);
	}
	
	/**
	 * 根据用户id获取奖励详情
	 * @FunName calculateAllReward
	 * @param List<MDayPointDetailModel>  奖励集合
	 * @author fengchao
	 * @Create Date 2015年10月28日
	 */
	public void calculateAllReward(List<MDayPointDetailModel> list){
		if(list != null && list.size() > 0){
			for(MDayPointDetailModel model : list){
				model.setAllReward(model.getRewardAmount().add(model.getManageAmount()).add(model.getCoachAmount()));
			}
		}
	}
	/**
	 * 根据用户id获取奖励详情
	 * @FunName findRewardByConditions
	 * @param po
	 * @return
	 * @author fengchao
	 * @Create Date 2015年10月28日
	 */
	public GridDataModel findRewardByConditions(PageObject po){
		
		RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserAccountInoutModel> rows=mrewardDetailMapper.findByConditions(po.getCondition(),rb);
		int count=mrewardDetailMapper.countByConditions(po.getCondition());
		
		return new GridDataModel(rows, count);
	}
	/**
	 * 根据用户id获取奖励详情
	 * @FunName findRewardByConditions
	 * @param po
	 * @return
	 * @author fengchao
	 * @Create Date 2015年10月28日
	 */
	public GridDataModel findRewardDetailByConditions(PageObject po){
		
		RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserAccountInoutModel> rows=userAccountInoutMapper.findByConditions(po.getCondition(),rb);
		int count=userAccountInoutMapper.countByConditions(po.getCondition());
		
		return new GridDataModel(rows, count);
	}
	@Override
	public MDayPointDetailModel getCollectDate() {
		MDayPointDetailModel para = new MDayPointDetailModel();
		dayPointDetailMapper.getCollectDate(para);
		return para;
	}
	
	
	/**
	 * 会员用户提现和取消提现 
	 * @param userId
	 * @param amount
	 * @param tradeNo
	 * @param type  1:提现;2提现取消;3:现金转博豆
	 * @throws BusinessException
	 */
	@Override
	public void drawCash(Integer userId, Long amount, String tradeNo,
			Integer type,String operatorNo) throws BusinessException {
		if(userId == null){
			throw new BusinessException("用户编号不能为空");
		}
		if(StringUtils.isEmpty(tradeNo)){
			throw new BusinessException("交易流水不能为空");
		}
		if(amount == null || amount <1){
			throw new BusinessException("提现金额不对,请检查");
		}
		if(type == null || (!(type == 1 || type == 2 || type == 3))){
			throw new BusinessException("操作类型不对,请检查");
		}
		Long realAmount = amount;
		AccountEnum accEnum = null;
		if(type == 3){//现金转博豆
			//userAccountTransfers(userId,amount,tradeNo,1,operatorNo);		
			userAccountChange(String.valueOf(userId),AccountEnum.CASH_TO_POINT_IN,amount,tradeNo,operatorNo);
			
			return;
		}else if(type ==1){
			accEnum = AccountEnum.DRAW_CASH;
			realAmount = amount * -1;
			
			MUserAccountModel accModel = new MUserAccountModel();
			accModel.setAccountType(accEnum.getAccountType());
			accModel.setUserId(userId);
			MUserAccountModel userModel = userAccountMapper.getObjectByModel(accModel);
			if(userModel == null){
				throw new BusinessException("用户账户信息不存在,请检查");
			}
			if(userModel.getAccountAmount() < amount){
				throw new BusinessException("账户余额不足,请检查");
			}
			
		}else{
			accEnum = AccountEnum.DRAW_REVERSAL_CASH;
		}
		//将用户直推将插入用户账户及账户变动明细表
		userAccountChange(String.valueOf(userId),accEnum,realAmount,tradeNo,operatorNo);
	}
	
	/**
	 * 会员用户充值,单位分
	 * @param userId
	 * @param amount
	 * @param tradeNo
	 * @param type 账户类型 1:现金;2:博豆;3:积分
	 * @throws BusinessException
	 */
	@Override
	public void userDeposit(Integer userId, Long amount, String tradeNo,Integer type,String operatorNo)
			throws BusinessException {
		if(userId == null){
			throw new BusinessException("用户编号不能为空");
		}
		if(StringUtils.isEmpty(tradeNo)){
			throw new BusinessException("交易流水不能为空");
		}
		if(amount == null){
			throw new BusinessException("充值金额不对,请检查");
		}
		if(type == null || type <1 || type >3){
			throw new BusinessException("充值类型不对,请检查");
		}
		//Integer accEnumType = type*100+10;
		Map<Integer,AccountEnum> map = new HashMap<Integer,AccountEnum>();
		map.put(1, AccountEnum.DEPOSIT_CASH);
		map.put(2, AccountEnum.DEPOSIT_BEANS);
		map.put(3, AccountEnum.DEPOSIT_POINT);
		AccountEnum accountEnum = map.get(type);
		MUserAccountModel accModel = new MUserAccountModel();
		accModel.setAccountType(accountEnum.getAccountType());
		accModel.setUserId(userId);
		MUserAccountModel userModel = userAccountMapper.getObjectByModel(accModel);
		if(userModel == null){
			throw new BusinessException("用户账户信息不存在,请检查");
		}
		/*if(amount < 0 && (amount + userModel.getAccountAmount()) < 0){
			throw new BusinessException("充值金额不能大于账户余额,请检查");
		}*/
		//将用户直推将插入用户账户及账户变动明细表
		userAccountChange(String.valueOf(userId),accountEnum,amount,tradeNo,operatorNo);
	}
	/**
	 * 会员用户博豆转积分 
	 * @param userId
	 * @param amount
	 * @param tradeNo
	 * @param type 1现金---->积分; 2:博豆--->积分; 3:现金 --->博豆
	 * @throws BusinessException
	 */
	@Override
	public void userAccountTransfers(Integer userId, Long amount, String tradeNo,Integer type,String operatorNo)
			throws BusinessException {
		if(userId == null){
			throw new BusinessException("用户编号不能为空");
		}
		if(StringUtils.isEmpty(tradeNo)){
			throw new BusinessException("交易流水不能为空");
		}
		if(amount == null){
			throw new BusinessException("转化金额不对,请检查");
		}
		if(type == null || type <1 || type >3){
			throw new BusinessException("充值类型不对,请检查");
		}
		
		AccountEnum accountIn = null,accountOut = null;
		Long inAmount = amount;
		Integer accountType = 1;
		if(type == 1){
			accountIn = AccountEnum.CASH_TO_POINT_IN;
			accountOut = AccountEnum.CASH_TO_POINT_OUT;
			inAmount = amount / 100;
		}else if(type == 2){
			accountIn = AccountEnum.BEANS_TO_POINT_IN;
			accountOut = AccountEnum.BEANS_TO_POINT_OUT;
			accountType = 2;
		}else if(type == 3){
			accountIn = AccountEnum.CASH_TO_BEANS_IN;
			accountOut = AccountEnum.CASH_TO_BEANS_OUT;
			accountType = 1;
			inAmount = amount / 100;
		}
		
		
		MUserAccountModel accModel = new MUserAccountModel();		
		accModel.setAccountType(accountType);
		accModel.setUserId(userId);
		MUserAccountModel userModel = userAccountMapper.getObjectByModel(accModel);
		
		if(userModel == null){
			throw new BusinessException("用户账户信息不存在,请检查");
		}
		if( userModel.getAccountAmount()  < amount ){
			throw new BusinessException("转换金额不能大于账户余额,请检查");
		}
		
		//账户出账--------将插入用户账户及账户变动明细表
		userAccountChange(String.valueOf(userId),accountOut,amount*-1,tradeNo,operatorNo);
		//账户入账--------将插入用户账户及账户变动明细表
		userAccountChange(String.valueOf(userId),accountIn,inAmount,tradeNo,operatorNo);
		
	}
	
	
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
	public void transfersBetweenUsers(Integer fromUserId,Integer toUserId,Long amount,String tradeNo,Integer type,String operatorNo) throws BusinessException{
		if(fromUserId == null){
			throw new BusinessException("用户编号不能为空");
		}
		if(toUserId == null){
			throw new BusinessException("目标用户编号不能为空");
		}
		if(StringUtils.isEmpty(tradeNo)){
			throw new BusinessException("交易流水不能为空");
		}
		if(amount == null){
			throw new BusinessException("转账金额不对,请检查");
		}
		if(type == null || type <1 || type >3){
			throw new BusinessException("转账类型不对,请检查");
		}
		MUserAccountModel accModel = new MUserAccountModel();
		accModel.setAccountType(type);
		accModel.setUserId(fromUserId);
		MUserAccountModel userModel = userAccountMapper.getObjectByModel(accModel);
		
		if(userModel == null){
			throw new BusinessException("用户账户信息不存在,请检查");
		}
		if( userModel.getAccountAmount()  < amount ){
			throw new BusinessException("转换金额不能大于账户余额,请检查");
		}
		
		
		AccountEnum accountIn = null,accountOut = null;
		if(type == 1){
			accountIn = AccountEnum.TRANSFERS_IN_CASH;
			accountOut = AccountEnum.TRANSFERS_OUT_CASH;
		}else if(type == 2){
			accountIn = AccountEnum.TRANSFERS_IN_BEANS;
			accountOut = AccountEnum.TRANSFERS_OUT_BEANS;
		}else if(type == 3){
			accountIn = AccountEnum.POINT_IN;
			accountOut = AccountEnum.POINT_OUT;
		}
		//账户出账--------将插入用户账户及账户变动明细表
		userAccountChange(String.valueOf(fromUserId),accountOut,amount*-1,getAccountNoByUserId(toUserId),operatorNo);
		//账户入账--------将插入用户账户及账户变动明细表
		userAccountChange(String.valueOf(toUserId),accountIn,amount,getAccountNoByUserId(fromUserId),operatorNo);
		
	}
	
	/**
	 * 根据用户ID查询用户账号信息
	 * @FunName getAccountNoByUserId
	 * @param userId
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月17日
	 */
	private String getAccountNoByUserId(Integer userId){
		String accountNo=null;
		try {
			if(userId!=null){
				MUserInfoModel user= userInfoMapper.getObjectById(userId);
				if(user!=null){
					accountNo=user.getAccountNo();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("根据用户ID查询用户信息异常",e);
		}
		return accountNo;
	}
	
	
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
	@Override
	public void userAccountForActivationUser(Integer userId, Long amount,
			String tradeNo, Integer type,String operatorNo,Integer regNum) throws BusinessException {
		
		if(userId == null){
			throw new BusinessException("用户编号不能为空");
		}
		if(StringUtils.isEmpty(tradeNo)){
			throw new BusinessException("交易流水不能为空");
		}
		if(amount == null){
			throw new BusinessException("转化金额不对,请检查");
		}
		if(regNum == null || regNum <= 0){
			regNum = 1;
		}
		if(type == null || type <1 || type >3){
			throw new BusinessException("账户类型不对,请检查");
		}
		Long totalAmount = regNum * amount;
		MUserAccountModel accModel = new MUserAccountModel();
		accModel.setAccountType(type);
		accModel.setUserId(userId);
		MUserAccountModel userModel = userAccountMapper.getObjectByModel(accModel);
		
		if(userModel == null){
			throw new BusinessException("用户账户信息不存在,请检查");
		}
		if( userModel.getAccountAmount()  < totalAmount ){
			throw new BusinessException("账户余额不足,请检查");
		}
		AccountEnum accountEnum = null;
		if(type.equals(1)){
			accountEnum = AccountEnum.ACTIVATIONUSER_BY_CASH;
			
		}else if(type.equals(2)){
			accountEnum = AccountEnum.ACTIVATIONUSER_BY_BEAN;
			
		}else{
			accountEnum = AccountEnum.ACTIVATIONUSER_BY_POINT;
		}
		//账户出账--------将插入用户账户及账户变动明细表
		userAccountChange(String.valueOf(userId),accountEnum,totalAmount*-1,tradeNo,operatorNo);
	}
	
	/**
	 * 插入用户奖励明细
	 */
	@Override
	public void userRewardDetail(Integer userId, Integer type, BigDecimal amount,
			Date collectDate, Integer fromUserId) {
		MRewardDetailModel detail = new MRewardDetailModel();
		detail.setUserId(userId);
		detail.setRewardType(type);
		detail.setCollectDate(collectDate);
		detail.setFromUserId(fromUserId);
		detail.setRewardAmount(amount);
		detail.setVersion(0);
		rewardDetailMapper.insertModel(detail);
	}

	@Override
	public String userAccountUpdateByHlg(Integer userId, String tradeNo,
			BigDecimal pointAmount, BigDecimal cashAmount) throws BusinessException {

		MUserAccountModel accModel = new MUserAccountModel();
		//accModel.setAccountType(accEnum.getAccountType());
		accModel.setUserId(userId);
		List<MUserAccountModel> userModels = userAccountMapper.getListByModel(accModel);
		if(userModels == null || userModels.size() <2){
			return ACCOUNT_UPDATE.ERROR_200;
		}
		boolean checkCash = false,checkPoint = false;
		for(MUserAccountModel item : userModels){
			if(item.getAccountType() == 1){//现金
				if(cashAmount.compareTo(new BigDecimal(item.getAccountAmount())) == 1){
					checkCash = true;
				}
			}
			if(item.getAccountType() == 2){//购物积分
				if(pointAmount.compareTo(new BigDecimal(item.getAccountAmount())) == 1){
					checkPoint = true;
				}
			}
		}
		
		if(checkCash){
			return ACCOUNT_UPDATE.ERROR_202;
		}
		
		if(checkPoint){
			return ACCOUNT_UPDATE.ERROR_201;
		}
		
		//扣积分
		if(pointAmount.longValue()>0l){
			userAccountChange(String.valueOf(userId),AccountEnum.PURCHASE_BEANS_BY_HLG,-1*pointAmount.longValue(),tradeNo,SYSTEM_USER_NO_HLG);
		}
		
		//扣现金
		if(cashAmount.longValue()>0l){
			userAccountChange(String.valueOf(userId),AccountEnum.PURCHASE_CASH_BY_HLG,-1*cashAmount.longValue(),tradeNo,SYSTEM_USER_NO_HLG);
		}
		return null;
		
	}

	@Override
	public String userAccountRecoveryByHlg(Integer userId, String tradeNo,
			BigDecimal pointAmount, BigDecimal cashAmount)
			throws BusinessException {
		MUserAccountModel accModel = new MUserAccountModel();
		accModel.setUserId(userId);
		List<MUserAccountModel> userModels = userAccountMapper
				.getListByModel(accModel);
		if (userModels == null || userModels.size() < 2) {
			return ACCOUNT_RECOVERY.ERROR_200;
		}

		MUserAccountInoutModel inout = new MUserAccountInoutModel();
		inout.setUserId(userId);
		inout.setTradeNo(tradeNo);

		List<MUserAccountInoutModel> result = userAccountInoutMapper
				.getListByModel(inout);
		if (result == null || result.size() == 0) {
			return ACCOUNT_RECOVERY.ERROR_201;
		}
		for (MUserAccountInoutModel inoutItem : result) {
			if (AccountEnum.REVERSAL_CASH_BY_HLG.getInoutType() == inoutItem.getInoutType()
					|| AccountEnum.REVERSAL_BEANS_BY_HLG.getInoutType() == inoutItem.getInoutType()) {
				return ACCOUNT_RECOVERY.ERROR_202;
			}
		}

		// 加积分
		if (pointAmount.longValue() > 0l) {
			userAccountChange(String.valueOf(userId),
					AccountEnum.REVERSAL_BEANS_BY_HLG, pointAmount.longValue(),
					tradeNo, SYSTEM_USER_NO_HLG);
		}

		// 加现金
		if (cashAmount.longValue() > 0l) {
			userAccountChange(String.valueOf(userId),
					AccountEnum.REVERSAL_CASH_BY_HLG, cashAmount.longValue(),
					tradeNo, SYSTEM_USER_NO_HLG);
		}

		return null;
	}
	
	
	/**
	 * 获取可预支博豆
	 */
	@Override
	public Long getAdvances(Integer userId){
		/*MUserAccountInoutModel paraInout = new MUserAccountInoutModel();
		paraInout.setUserId(userId);
		paraInout.setAccountType(2);
		MUserAccountInoutModel inout = userAccountInoutMapper.getObjectByModel(paraInout);*/
		MPointTaskModel paraTask = new MPointTaskModel();
		paraTask.setUserId(userId);
		MPointTaskModel pointTask = pointTaskMapper.getObjectByModel(paraTask);
		return /*((inout == null)?0:inout.getAmount()) +*/((pointTask == null)?0l:(pointTask.getTotalDays() - pointTask.getExecDays())*pointTask.getPointAmount());
	}
	
	@Override
	public Integer saveAdvances(Integer userId, Integer amount,String operatorNo)   throws BusinessException{
		if(userId == null){
			throw new BusinessException("用户编号不能为空");
		}
		if(amount == null){
			throw new BusinessException("预支金额不能为空");
		}
		MUserAccountModel paraInout = new MUserAccountModel();
		paraInout.setUserId(userId);
		paraInout.setAccountType(2);
		MUserAccountModel inout = userAccountMapper.getObjectByModel(paraInout);
		MPointTaskModel paraTask = new MPointTaskModel();
		paraTask.setUserId(userId);
		MPointTaskModel pointTask = pointTaskMapper.getObjectByModel(paraTask);
		
		Long result = ((inout == null)?0:inout.getAccountAmount()) +((pointTask == null)?0:(pointTask.getTotalDays() - pointTask.getExecDays())*pointTask.getPointAmount());
		if(amount > result.intValue()){
			throw new BusinessException("超过可预支额度");
		}
		//boolean hasAmount = false;
		Integer needAccount = 0;
		if(pointTask != null){
			Integer needDay = amount / pointTask.getPointAmount() ;			
			if(needDay > (pointTask.getTotalDays() - pointTask.getExecDays())){
				needDay = pointTask.getTotalDays() - pointTask.getExecDays();				
				needAccount = amount - needDay * pointTask.getPointAmount();
				//hasAmount = true;
			}
			pointTask.setExecDays(pointTask.getExecDays() + needDay);
			
			pointTaskMapper.updateModelById(pointTask);
		}else{
			needAccount = amount ;
		}
		
		if(needAccount > 0){
			MUserInfoModel userInfo = userInfoMapper.getObjectById(userId);
			userAccountChange(String.valueOf(userId), AccountEnum.EXCHANGE_BY_BEAN, (long)needAccount*-1, ((userInfo == null)?"":userInfo.getAccountNo()), operatorNo);
		}
		return needAccount;
	}
	
	
	@Override
	public Integer exchangeSmartHome(Integer userId, Long cashAmount,
			Integer beans, String operatorNo) throws BusinessException {
		if(userId == null){
			throw new BusinessException("用户编号不能为空");
		}
		if(cashAmount == null){
			throw new BusinessException("现金不能为空");
		}
		
		MUserAccountModel paraInout = new MUserAccountModel();
		paraInout.setUserId(userId);
		paraInout.setAccountType(2);
		MUserAccountModel inout = userAccountMapper.getObjectByModel(paraInout);
		MPointTaskModel paraTask = new MPointTaskModel();
		paraTask.setUserId(userId);
		MPointTaskModel pointTask = pointTaskMapper.getObjectByModel(paraTask);
		
		
		Long result = ((inout == null)?0:inout.getAccountAmount()) +((pointTask == null)?0:(pointTask.getTotalDays() - pointTask.getExecDays())*pointTask.getPointAmount());
		if(beans > result.intValue()){
			throw new BusinessException("超过可预支额度");
		}
		
		MUserAccountModel cashInout = new MUserAccountModel();
		cashInout.setUserId(userId);
		cashInout.setAccountType(1);
		MUserAccountModel cashAccount = userAccountMapper.getObjectByModel(cashInout);
		
		if(cashAccount == null ||  cashAmount > cashAccount.getAccountAmount()){
			throw new BusinessException("现金余额不足，请充值！");
		}
		
		
		//扣现金
		MUserInfoModel userInfo = userInfoMapper.getObjectById(userId);
		userAccountChange(String.valueOf(userId), AccountEnum.EXCHANGESH_BY_CASH,cashAmount*-1, ((userInfo == null)?"":userInfo.getAccountNo()), operatorNo);
		
		//扣博豆
		Integer needAccount = 0;
		if(pointTask != null){
			
			Integer  needDays  = beans%pointTask.getPointAmount() == 0 ? beans/pointTask.getPointAmount() : beans/pointTask.getPointAmount() + 1;
			
			if(pointTask.getTotalDays() - pointTask.getExecDays() >= needDays){
				pointTask.setExecDays(pointTask.getExecDays() + needDays);
				
				
			}else{
				
				needAccount = beans - (pointTask.getTotalDays() - pointTask.getExecDays())*pointTask.getPointAmount();
				pointTask.setExecDays(pointTask.getTotalDays());
			}
			pointTaskMapper.updateModelById(pointTask);
			
		}else{
			
			
			needAccount = beans ;
			
		}
		
		if(needAccount > 0){
			userAccountChange(String.valueOf(userId), AccountEnum.EXCHANGESH_BY_BEAN, (long)needAccount*-1, ((userInfo == null)?"":userInfo.getAccountNo()), operatorNo);
		}
		return needAccount;
	}

	@Override
	public void cancelAdvances(Integer exchangeId,String operatorNo) throws BusinessException {
		MExchangeInfoModel exchange =	mExchangeInfoMapper.getObjectById(exchangeId);
		if(exchange == null){
			throw new BusinessException("兑换云碟数据不存在");
		}
		
		if(exchange.getOverdraftBeans() > 0){
			MPointTaskModel paraTask = new MPointTaskModel();
			paraTask.setUserId(exchange.getUserId());
			MPointTaskModel task  = pointTaskMapper.getObjectByModel(paraTask);
			if(task != null){
				Integer  needDays  = exchange.getOverdraftBeans()%task.getPointAmount() == 0 ? exchange.getOverdraftBeans()/task.getPointAmount() : exchange.getOverdraftBeans()/task.getPointAmount() + 1;
				task.setExecDays(task.getExecDays() - needDays);
				pointTaskMapper.updateModelById(task);
			}
		}
		MUserInfoModel userInfo = userInfoMapper.getObjectById(exchange.getUserId());
		if(exchange.getBeans() != null && exchange.getBeans() > 0){
			
			if(exchange.getGoodType().equals(1)){
				userAccountChange(String.valueOf(exchange.getUserId()), AccountEnum.EXCHANGE_BY_BEAN_CANCEL, (long)exchange.getBeans(), ((userInfo == null)?"":userInfo.getAccountNo()), operatorNo);	
			}else{
				userAccountChange(String.valueOf(exchange.getUserId()), AccountEnum.EXCHANGESH_BY_BEAN_CANCEL, (long)exchange.getBeans(), ((userInfo == null)?"":userInfo.getAccountNo()), operatorNo);
			}		
		}
		
		if(exchange.getCash() != null && exchange.getCash() > 0){
			userAccountChange(String.valueOf(exchange.getUserId()), AccountEnum.EXCHANGESH_BY_CASH_CANCEL, (long)exchange.getCash(), ((userInfo == null)?"":userInfo.getAccountNo()), operatorNo);
		}
	}

	/**
	 * 生成用户日数据（个人业绩,团队业绩）
	 * @param collectDate
	 */
	@Override
	public void genDayPointDetailAndReport(Date collectDate) {
		dayPointDetailMapper.genDayPointDetailAndReport(collectDate);
	}

	/**
	 * 节点调整
	 * @param userId 要调整的节点ID
	 * @param targetParentId 目标父节点
	 * @throws BusinessException
	 */
	@Override
	public void nodeAdjustment(Integer userId, Integer targetParentId)
			throws BusinessException {
		if(targetParentId == null){
			throw new BusinessException("目标父节点用户不存在");
		}
		
		if(userId == null){
			throw new BusinessException("用户不存在");
		}
		if(userId.equals(-1)){
			throw new BusinessException("大博账户不能调整");
		}
		if(userId.equals(targetParentId)){
			throw new BusinessException("目标节点和要调整的节点为同一个人");
		}
		String tPStr = getAllParentStrById(targetParentId);
		//
		if(!StringUtils.isEmpty(tPStr)){
			List<String> strArr = Arrays.asList(tPStr.split(","));
			if(strArr.contains(String.valueOf(userId))){

				throw new BusinessException("目标节点为节点的下级，不能调整！");
			}
		}
		MStarNodeModel startNode = starNodeMapper.getObjectById(userId);
		if(startNode == null){
			throw new BusinessException("节点不存在");
		}
		
		MUserInfoModel userInfo = userInfoMapper.getObjectById(userId);
		
		if(userInfo.getRecommendId().equals(targetParentId)){
			LOG.info("目标节点和节点父节点一致，不需要调整");
			return;
		}
		
		userInfo.setRecommendId(targetParentId);
		//修改用户推荐人
		userInfoMapper.updateModelById(userInfo);
		
		//减少原单的推荐人数
		starNodeMapper.reduceRecommendNumsById(startNode.getParentId());
		
		//增加目标节点推荐人数
		starNodeMapper.updateRecommendNumsById(targetParentId);
		
		
		MStarNodeModel targetStartNode = starNodeMapper.getObjectById(targetParentId);
		
		String rechargeAmountValue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
		//空单标记
		boolean isEmpty = false;
		if(userInfo.getIsOpend() != null && userInfo.getIsOpend().equals(1)){
			isEmpty = true;
		}
		
		
		Map<Integer,String> oldStrMap = getOldParStr(startNode);
		swapNode(startNode,targetStartNode,Integer.valueOf(rechargeAmountValue),isEmpty,oldStrMap);
		
		/*startNode.setFloors(targetStartNode.getFloors()+1);
		startNode.setParentStr(targetStartNode.getChildParentStr());
		starNodeMapper.updateModelById(startNode);
		
		
		MStarNodeModel para = new MStarNodeModel();
		para.setParentId(userId);
		List<MStarNodeModel> parList = starNodeMapper.getListByModel(para);
		//ss
		
		for(MStarNodeModel nodeItem : parList){
			swapNode(nodeItem,startNode);
		}*/
		
		
		
	}
	
	public void swapNode(MStarNodeModel nodeItem,MStarNodeModel parent,Integer moneyAmount,boolean isEmpty,Map<Integer,String> oldParStrMap){
		// 个人和团队业绩调整
		if (!isEmpty) {
			// 减少原个人业绩
			userInfoMapper.reduceMyMoney(nodeItem.getParentId(), moneyAmount);
			// 减少团队业绩
			String parentStr = oldParStrMap.get(nodeItem.getParentId());//getAllParentStrById(nodeItem.getParentId());
			if (!StringUtils.isEmpty(parentStr)) {
				userInfoMapper.reduceTeamMoney(parentStr, moneyAmount);
			}

			// 增加个人业绩
			userInfoMapper.updateMyMoney(parent.getUserId(), moneyAmount);
			// 增加团队业绩
			String tParentStr = getAllParentStrById(parent.getUserId());
			if (!StringUtils.isEmpty(tParentStr)) {
				userInfoMapper.updateTeamMoney(tParentStr, moneyAmount);
			}

		}
		
		nodeItem.setFloors(parent.getFloors()+1);
		nodeItem.setParentId(parent.getUserId());
		nodeItem.setParentStr(parent.getChildParentStr());
		starNodeMapper.updateModelById(nodeItem);
		
		
		
		
		
		
		
		
		MStarNodeModel para = new MStarNodeModel();
		para.setParentId(nodeItem.getUserId());
		List<MStarNodeModel> parList = starNodeMapper.getListByModel(para);
		
		for(MStarNodeModel childItem : parList){
			MUserInfoModel cuserInfo = userInfoMapper.getObjectById(childItem.getUserId());			
			boolean cisEmpty = false;
			if(cuserInfo.getIsOpend() != null && cuserInfo.getIsOpend().equals(1)){
				cisEmpty = true;
			}			
			swapNode(childItem,nodeItem,moneyAmount,cisEmpty,oldParStrMap);
		}
	}

	/*@Override
	public List<MUserAccountInoutHlgModel> getAccountInoutForHlg(
			MUserAccountInoutHlgModel model) {
		return inoutHlgMapper.getListByModel(model);
	}*/
	
	public Map<Integer,String> getOldParStr(MStarNodeModel startNode){
		Map<Integer,String> oldParStr = new HashMap<Integer,String>();
		if(!oldParStr.containsKey(startNode.getParentId())){
			oldParStr.put(startNode.getParentId(), getAllParentStrById(startNode.getParentId()));			
		}
		
		
		MStarNodeModel para = new MStarNodeModel();
		para.setParentId(startNode.getUserId());
		List<MStarNodeModel> parList = starNodeMapper.getListByModel(para);
		
		for(MStarNodeModel childItem : parList){
			 	oldParStr.putAll(getOldParStr(childItem));	
		}
		LOG.info("map = {}",oldParStr);
		return oldParStr;
	}


	
}
