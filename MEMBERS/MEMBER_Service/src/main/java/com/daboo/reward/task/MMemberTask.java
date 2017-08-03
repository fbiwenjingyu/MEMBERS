package com.daboo.reward.task;

import java.util.List;

import com.daboo.reward.model.MTaskMonitorModel;


public interface MMemberTask{

	/**
	 * 描述：赠送会员积分
	 */
	public void execSendPoint(MTaskMonitorModel job);

	
	/**
	 * 描述： 定时任务每天凌晨2点过10分钟执行更新会员等级 作者：周锐
	 */
	public void execUpdateLevel(MTaskMonitorModel job);
	
	
	
	/**
	 * 描述： 定时任务每天凌晨2点过10分钟执行更新会员等级 作者：db
	 */
	public void execManageAwards(MTaskMonitorModel job);
	
	
	/**
	 * 计算辅导奖励
	 * @param job
	 */
	public void execCoachAwards(MTaskMonitorModel job);
	
	
	/**
	 * 对账数据
	 * 1.根据日期生成要对账的数据
	 * 2.获取韩流馆数据
	 * 3.生成有差异的数据
	 * @param job
	 */
	public void execNetSystemRecords(MTaskMonitorModel job);
	
	public List<MTaskMonitorModel> getTasks();

}
