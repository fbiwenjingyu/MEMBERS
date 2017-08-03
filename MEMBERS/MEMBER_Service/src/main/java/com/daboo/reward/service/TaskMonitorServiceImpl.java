package com.daboo.reward.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.mapper.MTaskMonitorMapper;
import com.daboo.reward.model.MTaskMonitorModel;
import com.daboo.web.context.ScheduledUtil;

/**
 * @author zhouRui
 * 描述：监控定时任务服务类
 */
@Service
@Transactional
public class TaskMonitorServiceImpl implements TaskMonitorService{
	protected static final Logger log = LoggerFactory.getLogger(TaskMonitorServiceImpl.class);
	@Autowired
	MTaskMonitorMapper mTaskMonitorMapper;
	/**
	 * 描述：检测定时任务状态
	 * taskType： 1-博豆和积分奖励任务  2-会员级别更新任务  3-管理奖励任务  4-辅导奖任务
	 * date： 任务执行日期
	 */
	@Override
	public MTaskMonitorModel checkJosState(int taskType, Date date,int runState) throws BusinessException {
		MTaskMonitorModel model = new MTaskMonitorModel();
		model.setTaskType(taskType);
		model.setCollectDate(date);
		model.setRunState(runState);
		return mTaskMonitorMapper.getObjectByModel(model);
	}
	
	
	@Override
	public int insertModel(MTaskMonitorModel model) throws BusinessException {
		 mTaskMonitorMapper.insertModel(model);
		 return model.getId();
	}
	@Override
	public int updateModel(MTaskMonitorModel model) throws BusinessException {
		return mTaskMonitorMapper.updateModelById(model);
	}


	/*@Override
	public MTaskMonitorModel checkJobExist(MTaskMonitorModel model) {
		return mTaskMonitorMapper.getObjectByModel(model);
	}*/


	@Override
	public List<MTaskMonitorModel> getTasks() {
		MTaskMonitorModel model = new MTaskMonitorModel();
		model.setRunState(0);
		return mTaskMonitorMapper.getListByModel(model);
	}


	@Override
	public MTaskMonitorModel getMTaskMonitorModelById(Integer id) {
		return mTaskMonitorMapper.getObjectById(id);
	}


	@Override
	public void reRunTask(MTaskMonitorModel model) {
		ScheduledUtil.getInstance().addTaskQueue(model);
	}
	
	/**
	  * @Description: 分页获取用户消息
	  * @param page
	  * @return 
	  * @author 肖勇
	  * @date 2015-11-12
	 */
	public GridDataModel findTaskPaging(PageObject po){
		//分页消息
		RowBounds rowBounds=new RowBounds(po.getOffset(), po.getPageSize());
		List<MTaskMonitorModel> rows = mTaskMonitorMapper.findTaskPaging(po.getCondition(), rowBounds);
		int total = mTaskMonitorMapper.findTaskCount(po.getCondition());
		return new GridDataModel(rows, total);
	}
	
	
}
