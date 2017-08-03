package com.daboo.reward.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daboo.core.exception.BusinessException;
import com.daboo.reward.model.MDayPointDetailModel;
import com.daboo.reward.model.MTaskMonitorModel;
import com.daboo.reward.service.MemberLeverService;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.reward.service.TaskMonitorService;
@Repository("levelSetTask")
public class MLevelSettingTask {
	private static final Logger logger = LoggerFactory
			.getLogger(MLevelSettingTask.class);
	//private static boolean execUpdateLevelRunning = false;
	
	@Autowired
	TaskMonitorService taskMonitorService;
	@Autowired
	MemberRewardService rewardService;

	@Autowired
	MemberLeverService memberLeverService;
	/**
	 * 描述： 定时任务每天凌晨2点过10分钟执行更新会员等级 作者：周锐
	 */
	public void execUpdateLevel() {
		/*if (execUpdateLevelRunning) {
			return;
		} else {
			execUpdateLevelRunning = true;
		}*/
		/**
		 * 逻辑说明 第一步:校验送推广积分和博豆是否执行完成，如果没有执行完成，则隔5分钟再次执行
		 * 第二步:执行时，首先生成一条跟新会员等级的任务，状态为执行中
		 * 第三步：执行完成，如果正常则更新任务状态为完成，如果失败则更新状态为失败，失败状态的可以人工干预执行
		 */
		Date date = new Date();
		int id = 0;
		try {
			// 如果校验送推广积分和博豆没有执行完成，则直接返回
			if (taskMonitorService.checkJosState(1, date, 2) == null) {
				return;
			}

			// 判断当天任务是否成功执行
			MTaskMonitorModel checkJob = new MTaskMonitorModel();
			checkJob.setRunDate(date);
			checkJob.setTaskType(2);
			// 判断任务是否成功执行
			/*MTaskMonitorModel tmp = taskMonitorService.checkJobExist(checkJob);
			if (tmp != null && tmp.getRunState() == 2) {
				return;
			}
			if (tmp == null) {
				MTaskMonitorModel model = new MTaskMonitorModel();
				model.setRunDate(date);
				model.setRunState(1);
				model.setTaskName("更新会员等级");
				model.setTaskType(2);
				id = taskMonitorService.insertModel(model);
			} else {
				id = tmp.getId();
			}*/

			logger.info("execUpdateLevel begin at {}.......", date);
			// 获取统计日期
			MDayPointDetailModel dyaPoint = rewardService.getCollectDate();

			memberLeverService.updateMemberLerver(dyaPoint.getCollectDate());
			logger.info("execUpdateLevel Success at {}.......", date);

			MTaskMonitorModel updateModel = new MTaskMonitorModel();
			updateModel.setId(id);
			updateModel.setRunState(2);
			taskMonitorService.updateModel(updateModel);
		} catch (Exception e) {
			MTaskMonitorModel updateModel = new MTaskMonitorModel();
			updateModel.setId(id);
			updateModel.setRunState(3);
			try {
				taskMonitorService.updateModel(updateModel);
			} catch (BusinessException e1) {
				logger.error("执行异常时，更新任务状态出错");
			}
			logger.info("execUpdateLevel err at {}.......", date);
			logger.info(e.getMessage(), e);
		}/* finally {
			execUpdateLevelRunning = false;
		}*/
	}
}
