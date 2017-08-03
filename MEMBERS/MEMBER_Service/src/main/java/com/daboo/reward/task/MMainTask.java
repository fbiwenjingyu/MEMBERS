package com.daboo.reward.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daboo.core.exception.BusinessException;
import com.daboo.reward.model.MDayPointDetailModel;
import com.daboo.reward.model.MTaskMonitorModel;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.reward.service.TaskMonitorService;
import com.daboo.web.context.ScheduledUtil;

/**
 * 定时任务总任务
 */
@Repository("mainTask")
public class MMainTask {
	private static final Logger logger = LoggerFactory.getLogger( MMainTask.class );

	@Autowired
	TaskMonitorService taskMonitorService;
	
	@Autowired
	MemberRewardService rewardService;
	
	
	
	/**
	 * 任务清单生成
	 * 
	 * @throws BusinessException
	 */
	public void mainTaskGen() throws BusinessException {
		
		MDayPointDetailModel dyaPoint = rewardService.getCollectDate();
		
		MTaskMonitorModel model = null;
		
		
		String arr[] = {"赠送博豆任务", "更新会员等级"/*, "计算管理奖励", "计算辅导奖励"*/,"系统对账" };
		for (int i = 0; i < arr.length; i++) {
			model = new MTaskMonitorModel();
			model.setRunDate(new Date());
			model.setRunState(0);
			// 获取统计日期
			
			model.setCollectDate(dyaPoint.getCollectDate());
			
			model.setTaskType(i+1);
			model.setTaskName(arr[i]);
			try{
				//插入任务
				taskMonitorService.insertModel(model);
				//执行任务
				ScheduledUtil.getInstance().addTaskQueue(model);
			}catch(Exception e){
				logger.error(e.getMessage(),e);
			}
			
		}

	}
}
