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
 * @author 豆波 描述：会员系统定时任务---奖励
 */
@Repository("awardTask")
public class MAwardTask{

	private static final Logger LOG = LoggerFactory.getLogger(MAwardTask.class);
	//private static boolean execManagerAwardsRunning = false;
	@Autowired
	MemberRewardService rewardService;

	@Autowired
	TaskMonitorService taskMonitorService;

	/**
	 * 描述： 定时任务每天凌晨2点过10分钟执行更新会员等级 作者：db
	 */
	public void execManageAwards() {
		/*LOG.info("MAwardTask execManageAwards run at = {} , state = {}",new Date(),execManagerAwardsRunning);
		if (execManagerAwardsRunning) {
			return;
		} else {
			execManagerAwardsRunning = true;
		}*/
		/**
		 * 逻辑说明 第一步:校验送推广积分和博豆是否执行完成，如果没有执行完成，则隔5分钟再次执行
		 * 第二步:执行时，首先生成一条跟新会员等级的任务，状态为执行中
		 * 第三步：执行完成，如果正常则更新任务状态为完成，如果失败则更新状态为失败，失败状态的可以人工干预执行
		 */
		int id = 0;
		try {
			// 如果校验修改用户级别任务有没有执行完成，则直接返回
			if (taskMonitorService.checkJosState(2, new Date(), 2) == null) {
				return;
			}

			// 判断当天任务是否成功执行
			MTaskMonitorModel checkJob = new MTaskMonitorModel();
			checkJob.setRunDate(new Date());
			checkJob.setTaskType(3);
			// 判断任务是否成功执行
			/*MTaskMonitorModel tmp = taskMonitorService.checkJobExist(checkJob);
			if (tmp != null && tmp.getRunState() == 2) {
				return;
			}
			if (tmp == null) {
				MTaskMonitorModel model = new MTaskMonitorModel();
				model.setRunDate(new Date());
				model.setRunState(1);
				model.setTaskName("计算管理奖励");
				model.setTaskType(3);
				id = taskMonitorService.insertModel(model);
			} else {
				id = tmp.getId();
			}*/
			LOG.info("execManageAwards begin at {}.......", new Date());
			//rewardService.calcAllManageAwards();
			LOG.info("execManageAwards Success at {}.......", new Date());

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
			//execManagerAwardsRunning = false;
			LOG.info("execManageAwards err at {}.......", new Date());
			LOG.error(e.getMessage(), e);
		}
		//execManagerAwardsRunning = false;
	}

}
