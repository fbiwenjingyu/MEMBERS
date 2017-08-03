package com.daboo.reward.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.BigDecimalUtil;
import com.daboo.member.service.MemberUserInfoService;
import com.daboo.reward.enums.AccountEnum;
import com.daboo.reward.mapper.MPointTaskMapper;
import com.daboo.reward.mapper.MUserAccountInoutHlgMapper;
import com.daboo.reward.model.MPointTaskDetailModel;
import com.daboo.reward.model.MTaskMonitorModel;
import com.daboo.reward.model.MUserAccountInoutHlgModel;
import com.daboo.reward.service.MemberLeverService;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.reward.service.MemberRewardServiceImpl;
import com.daboo.reward.service.TaskMonitorService;

/**
 * @author 豆波 描述：会员系统定时任务控制
 */
@Service
@Transactional
public class MMemberTaskImpl implements MMemberTask{

	private static final Logger logger = LoggerFactory.getLogger(MMemberTaskImpl.class);
	 
	@Autowired
	MemberRewardService rewardService;

	@Autowired
	MemberLeverService memberLeverService;

	@Autowired
	TaskMonitorService taskMonitorService;
	
	@Autowired
	MemberUserInfoService  memberUserInfoService;
	
	@Autowired
	MPointTaskMapper pointTaskMapper;
	
	@Autowired
	MUserAccountInoutHlgMapper inoutHlgMapper;
	/**
	 * 赠送博豆和积分
	 * 
	 * @throws BusinessException
	 */
	
	private void sendBeansAndPointForPage(Date collectDate) throws BusinessException {
		int row = 100;
		List<MPointTaskDetailModel> details = null;
		while (true) {
			details = rewardService.findPointTaskDetails(collectDate,row);
			for (MPointTaskDetailModel item : details) {
				rewardService.sendBeansAndPoint(item);
			}
			if (details == null || details.size() < row) {
				break;
			}
		}
	}

	/**
	 * 描述：赠送会员积分
	 */
	@Override
	public void execSendPoint(MTaskMonitorModel job) {
		/*if (execSendPointRunning) {
			return;
		} else {
			execSendPointRunning = true;
		}*/
		
		try {
			logger.info("MPointTask begin at {}.......", new Date());
			
			job.setRunState(1);//相关执行状态
			taskMonitorService.updateModel(job);
			 
			// 生成赠送博豆任务
			rewardService.genPointTaskDetail(job.getCollectDate());			
			
			// 赠送博豆和积分
			sendBeansAndPointForPage(job.getCollectDate());
			
			//生成用户日数据（个人业绩,团队业绩,系统日报表）genDayPointDetail
			rewardService.genDayPointDetailAndReport(job.getCollectDate());
			
			job.setRunState(2);
			job.setRunDate(new Date());
			taskMonitorService.updateModel(job);
			logger.info("MPointTask Success at {}.......", new Date());
		} catch (Exception e) {
			 
			job.setRunState(3);
			job.setRunDate(new Date());

			try {
				taskMonitorService.updateModel(job);
			} catch (BusinessException e1) {
				logger.error("执行异常时，更新任务状态出错");
			}
			logger.info("MPointTask err at {}.......", new Date());
			logger.info(e.getMessage(), e);
		} /*finally {
			execSendPointRunning = false;
		}*/
	}

	
	
	
	/**
	 * 描述： 定时任务每天凌晨2点过10分钟执行更新会员等级 作者：周锐
	 */
	@Override
	public void execUpdateLevel(MTaskMonitorModel job) {
		 
		/**
		 * 逻辑说明 第一步:校验送推广积分和博豆是否执行完成，如果没有执行完成，则隔5分钟再次执行
		 * 第二步:执行时，首先生成一条跟新会员等级的任务，状态为执行中
		 * 第三步：执行完成，如果正常则更新任务状态为完成，如果失败则更新状态为失败，失败状态的可以人工干预执行
		 */

		try {
			// 如果校验送推广积分和博豆没有执行完成，则直接返回
			if (taskMonitorService.checkJosState(job.getTaskType()-1, job.getCollectDate(), 2) == null) {
				return;
			}

			 

			logger.info("execUpdateLevel begin at {}.......", new Date());
			// 获取统计日期
			//MDayPointDetailModel dyaPoint = rewardService.getCollectDate();

			memberLeverService.updateMemberLerver(job.getCollectDate());
			
			logger.info("execUpdateLevel Success at {}.......", new Date());

			job.setRunState(2);
			job.setRunDate(new Date());
			taskMonitorService.updateModel(job);
		} catch (Exception e) {
			
			job.setRunState(3);
			job.setRunDate(new Date());
			try {
				taskMonitorService.updateModel(job);
			} catch (BusinessException e1) {
				logger.error("执行异常时，更新任务状态出错");
			}
			logger.info("execUpdateLevel err at {}.......", new Date());
			logger.info(e.getMessage(), e);
		}
	}
	
	
	
	/**
	 * 描述： 定时任务每天凌晨2点过10分钟执行更新会员等级 作者：db
	 */
	@Override
	public void execManageAwards(MTaskMonitorModel job) {
		
		/**
		 * 逻辑说明 第一步:校验送推广积分和博豆是否执行完成，如果没有执行完成，则隔5分钟再次执行
		 * 第二步:执行时，首先生成一条跟新会员等级的任务，状态为执行中
		 * 第三步：执行完成，如果正常则更新任务状态为完成，如果失败则更新状态为失败，失败状态的可以人工干预执行
		 */
	
		try {
			// 如果校验修改用户级别任务有没有执行完成，则直接返回
			if (taskMonitorService.checkJosState(job.getTaskType()-1, job.getCollectDate(), 2) == null) {
				return;
			}

			 
			logger.info("execManageAwards begin at {}.......", new Date());
			rewardService.calcAllManageAwards(job.getCollectDate());
			logger.info("execManageAwards Success at {}.......", new Date());

			 
			job.setRunState(2);
			job.setRunDate(new Date());
			taskMonitorService.updateModel(job);

		} catch (Exception e) {
			 
			job.setRunState(3);
			job.setRunDate(new Date());
			try {
				taskMonitorService.updateModel(job);
			} catch (BusinessException e1) {
				logger.error("执行异常时，更新任务状态出错");
			}
			logger.info("execManageAwards err at {}.......", new Date());
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 计算辅导奖励
	 * @param job
	 */
	@Override
	public void execCoachAwards(MTaskMonitorModel job) {

		/**
		 * 逻辑说明 第一步:校验辅导奖励是否执行完成，如果没有执行完成，则隔5分钟再次执行
		 * 第二步:执行时，首先生成一条计算辅导奖励的任务，状态为执行中
		 * 第三步：执行完成，如果正常则更新任务状态为完成，如果失败则更新状态为失败，失败状态的可以人工干预执行
		 */
		
		try {
			// 如果校验修改用户级别任务有没有执行完成，则直接返回
			if (taskMonitorService.checkJosState(job.getTaskType()-1, job.getCollectDate(), 2) == null) {
				return;
			}

			 
			
			logger.info("execCoachAwards begin at {}.......", new Date());
			rewardService.calcCoachAwards(job.getCollectDate());
			logger.info("execCoachAwards Success at {}.......", new Date());

		 
			job.setRunState(2);
			job.setRunDate(new Date());
			taskMonitorService.updateModel(job);

		} catch (Exception e) {
			job.setRunState(3);
			job.setRunDate(new Date());
			try {
				taskMonitorService.updateModel(job);
			} catch (BusinessException e1) {
				logger.error("执行异常时，更新任务状态出错");
			}
			logger.info("execCoachAwards err at {}.......", new Date());
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public List<MTaskMonitorModel> getTasks() {
		return taskMonitorService.getTasks();
	}
	
	/**
	 * 对账数据
	 * 1.根据日期生成要对账的数据
	 * 2.获取韩流馆数据
	 * 3.生成有差异的数据
	 */
	@Override
	public void execNetSystemRecords(MTaskMonitorModel job) {
		logger.info("execNetSystemRecords begin at {}.......", new Date());
		try {
			// 生成对账数据
			pointTaskMapper.genHlgConsumptionData(job.getCollectDate());

			String json = memberUserInfoService.getMemberSystemRecordsFromNet(job.getCollectDate());
			if (!StringUtils.isEmpty(json)) {
				JSONObject jsonObject = JSON.parseObject(json);
				if (jsonObject.getInteger("successCount") > 0) {
					JSONArray jsonArr = jsonObject.getJSONArray("value");
					for (int i = 0; i < jsonArr.size(); i++) {
						JSONObject item = (JSONObject) jsonArr.get(i);
						MUserAccountInoutHlgModel para = new MUserAccountInoutHlgModel();
						para.setUserType(11);
						para.setTradeNo(item.getString("tradeNo"));
						if (item.getInteger("type").equals(3)) {// 消费
							para.setInoutTypes("125,225");// 消费
						} else {
							para.setInoutTypes("113,213");// 冲正
						}

						List<MUserAccountInoutHlgModel> hlgItems = inoutHlgMapper.getListByModel(para);

						BigDecimal point = item.getBigDecimal("point");

						BigDecimal cash = item.getBigDecimal("cash");
						Integer flag = item.getInteger("status");// 成功标记 状态
																	// 2:成功;1:失败

						// Integer stat = 2;
						if (hlgItems == null || hlgItems.size() == 0) {
							if (flag == 2) {
								if (cash.compareTo(new BigDecimal(0)) > 0) {
									MUserAccountInoutHlgModel err = new MUserAccountInoutHlgModel();
									err.setId(-1);
									err.setUserType(12);
									err.setOperatorNo(MemberRewardServiceImpl.SYSTEM_USER_NO);
									err.setAccountType(1);
									err.setAmount(BigDecimalUtil.mul(cash,new BigDecimal(100)).intValue());
									err.setCreateTime(new Date());
									err.setExecFlag(3);
									if (item.getInteger("type").equals(3)) {// 消费
										err.setInoutDesc(AccountEnum.PURCHASE_CASH_BY_HLG.getInoutDesc());
										err.setInoutType(AccountEnum.PURCHASE_CASH_BY_HLG.getInoutType());
									} else {
										err.setInoutDesc(AccountEnum.REVERSAL_CASH_BY_HLG.getInoutDesc());
										err.setInoutType(AccountEnum.REVERSAL_CASH_BY_HLG.getInoutType());
									}
									err.setTradeNo(item.getString("tradeNo"));
									inoutHlgMapper.insertModel(err);
								}
								if (point.intValue() > 0) {
									MUserAccountInoutHlgModel err = new MUserAccountInoutHlgModel();
									err.setId(-1);
									err.setUserType(12);
									err.setOperatorNo(MemberRewardServiceImpl.SYSTEM_USER_NO);
									err.setAccountType(2);
									err.setAmount(point.intValue());
									err.setCreateTime(new Date());
									err.setExecFlag(3);
									if (item.getInteger("type").equals(3)) {// 消费
										err.setInoutDesc(AccountEnum.PURCHASE_BEANS_BY_HLG.getInoutDesc());
										err.setInoutType(AccountEnum.PURCHASE_BEANS_BY_HLG.getInoutType());
									} else {
										err.setInoutDesc(AccountEnum.REVERSAL_BEANS_BY_HLG.getInoutDesc());
										err.setInoutType(AccountEnum.REVERSAL_BEANS_BY_HLG.getInoutType());
									}
									err.setTradeNo(item.getString("tradeNo"));
									inoutHlgMapper.insertModel(err);
								}
							}

							// err.setAccountType(accountType);
						}
						for (MUserAccountInoutHlgModel hlgDetail : hlgItems) {
							Integer dataflag = 2;

							if (flag.equals(2)) {
								dataflag = 2;
							} else {
								dataflag = 3;
							}

							if (hlgDetail.getAccountType().equals(1)) {
								if (cash.compareTo(BigDecimalUtil.mul(
										new BigDecimal(hlgDetail.getAmount()),
										new BigDecimal(100))) != 0) {
									dataflag = 3;
								}

							} else if (hlgDetail.getAccountType().equals(2)) {
								if (point.intValue() != hlgDetail.getAmount().intValue()) {
									dataflag = 3;
								}
							}

							hlgDetail.setExecFlag(dataflag);

							inoutHlgMapper.updateModelById(hlgDetail);

						}
					}
				}
			}
			inoutHlgMapper.updateHlgFlag(job.getCollectDate());
			job.setRunState(2);
			job.setRunDate(new Date());
			taskMonitorService.updateModel(job);

		} catch (Exception e) {
			job.setRunState(3);
			job.setRunDate(new Date());
			try {
				taskMonitorService.updateModel(job);
			} catch (BusinessException e1) {
				logger.error("执行异常时，更新任务状态出错");
			}
			logger.info("execNetSystemRecords err at {}.......", new Date());
			logger.error(e.getMessage(), e);
		}
	}

}
