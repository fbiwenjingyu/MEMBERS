package com.daboo.web.context;
 


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.daboo.reward.model.MGradeModel;
import com.daboo.reward.model.MTaskMonitorModel;
import com.daboo.reward.task.MMemberTask;
 

/**
 * TreeNodeUtil工具类
 * 
 * @author db
 * 
 */
public class ScheduledUtil{
	/** 初始化实例 */ 
	private static final Logger logger = LoggerFactory.getLogger( ScheduledUtil.class );
	
	 
	private static  ScheduledUtil INSTANCE = new ScheduledUtil();
	 
	
	
 
		private TaskQueue<MTaskMonitorModel> taskQueue = new TaskQueue<MTaskMonitorModel>();
		 
		private MMemberTask memberTask = null;
		
		
		private Map<Integer,MGradeModel> gradeMap = new HashMap<Integer,MGradeModel>();
		
	
	private ScheduledUtil() {
		gradeMap.put(1, new MGradeModel(1,"铜卡",5,new BigDecimal(1000)));
		gradeMap.put(2, new MGradeModel(2,"银卡",8,new BigDecimal(3000)));
		gradeMap.put(3, new MGradeModel(3,"金卡",10,new BigDecimal(7000)));
	}
	
	

	public Map<Integer, MGradeModel> getGradeMap() {
		return gradeMap;
	}



	public boolean contains(MTaskMonitorModel orderInfo){
		return taskQueue.getBlockingQueue().contains(orderInfo);
	}

	/**
	 * 将数据放入队列
	 * 
	 * @param orderInfo
	 */
	public  void addTaskQueue(MTaskMonitorModel orderInfo) {
		try {
			taskQueue.put(orderInfo);
		} catch (Exception e) {
			logger.error("addTreeNodeQueue error.", e);
		}
	}

 
	//初始化方法
		public void init(){
			if(memberTask == null){
				logger.error("memberTask is empty ");
				memberTask = ObjectFactory.getBean(MMemberTask.class);
			}
			List<MTaskMonitorModel> jobs = memberTask.getTasks();
			for(MTaskMonitorModel detail : jobs){
				addTaskQueue(detail);
			}
			new Thread() {
				@Override
				public void run() {
					/**
					 * 查找已成功付款,未生成节点数据
					 */
					
					
					while (true) {
						if (taskQueue.isEmpty()) {
							try {
								Thread.sleep(1000);
								//logger.info("queue is empty ,thread sleep = {}",1000);
							} catch (InterruptedException e) {
								logger.error(e.getMessage(), e);
							}
						} else {
						MTaskMonitorModel model = taskQueue.take();
						logger.info("orderInfo = {}", model);
						logger.info("begin exec MTaskMonitorModel = {} ........",model);
						if (model.getRunState() != 2) {
							switch (model.getTaskType()) {
							case 1:// 发放博豆
								memberTask.execSendPoint(model);
								break;
							case 2:// 调整会员级别
								memberTask.execUpdateLevel(model);
								break;
							/*case 3:// 发放管理奖励
								memberTask.execManageAwards(model);
								break;
							case 4:// 发放辅导奖励
								memberTask.execCoachAwards(model);
								break;
							case 5:// 系统对账
								memberTask.execNetSystemRecords(model);
								break;*/
							case 3:// 系统对账
								memberTask.execNetSystemRecords(model);
								break;
							default:
								memberTask.execSendPoint(model);
								;
							}

						}
							
						logger.info("end exec MTaskMonitorModel = {} !!!!!!!!!!!!", model);
						}
					}
				}
			}.start();
		}

	public static ScheduledUtil getInstance() {
		return INSTANCE;
	}
}
