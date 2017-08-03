package com.daboo.reward.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daboo.core.exception.BusinessException;
import com.daboo.reward.model.MTaskMonitorModel;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.reward.service.TaskMonitorService;

/**
 * @author 豆波 描述：会员系统定时任务---辅导奖励
 */
@Repository("coachTask")
public class MCoachAwardsTask {

	private static final Logger LOG = LoggerFactory
			.getLogger(MCoachAwardsTask.class);
	//private static boolean execCoachAwardsRunning = false;
	@Autowired
	MemberRewardService rewardService;

	@Autowired
	TaskMonitorService taskMonitorService;

	public void execCoachAwards() {

		//LOG.info("MCoachAwardsTask execCoachAwards run at = {} , state = {}",new Date(),execCoachAwardsRunning);
		/*if (execCoachAwardsRunning) {
			return;
		} else {
			execCoachAwardsRunning = true;
		}*/
		/**
		 * 逻辑说明 第一步:校验辅导奖励是否执行完成，如果没有执行完成，则隔5分钟再次执行
		 * 第二步:执行时，首先生成一条计算辅导奖励的任务，状态为执行中
		 * 第三步：执行完成，如果正常则更新任务状态为完成，如果失败则更新状态为失败，失败状态的可以人工干预执行
		 */
		int id = 0;
		try {
			// 如果校验修改用户级别任务有没有执行完成，则直接返回
			if (taskMonitorService.checkJosState(3, new Date(), 2) == null) {
				return;
			}

			// 判断当天任务是否成功执行
			MTaskMonitorModel checkJob = new MTaskMonitorModel();
			checkJob.setRunDate(new Date());
			checkJob.setTaskType(4);
			// 判断任务是否成功执行
			/*MTaskMonitorModel tmp = taskMonitorService.checkJobExist(checkJob);
			if (tmp != null && tmp.getRunState() == 2) {
				return;
			}
			if (tmp == null) {
				MTaskMonitorModel model = new MTaskMonitorModel();
				model.setRunDate(new Date());
				model.setRunState(1);
				model.setTaskName("计算辅导奖励");
				model.setTaskType(4);
				id = taskMonitorService.insertModel(model);
			} else {
				id = tmp.getId();
			}*/
			
			LOG.info("execCoachAwards begin at {}.......", new Date());
			//rewardService.calcCoachAwards();
			LOG.info("execCoachAwards Success at {}.......", new Date());

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
				LOG.error("执行异常时，更新任务状态出错");
			}
			//execCoachAwardsRunning = false;
			LOG.info("execCoachAwards err at {}.......", new Date());
			LOG.error(e.getMessage(), e);
		}
		//execCoachAwardsRunning = false;
	}

}
