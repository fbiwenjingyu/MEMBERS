package com.daboo.reward.service;

import java.util.Date;
import java.util.List;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.model.MTaskMonitorModel;

@RemoteService
public interface TaskMonitorService {
  abstract MTaskMonitorModel  checkJosState(int type ,Date date,int runState) throws BusinessException;
  
  //public MTaskMonitorModel checkJobExist(MTaskMonitorModel model);
  abstract int  insertModel(MTaskMonitorModel model) throws BusinessException;
  abstract int  updateModel(MTaskMonitorModel model) throws BusinessException;
  
  public List<MTaskMonitorModel> getTasks();

  
  /**
    * @Description: 获取任务列表
    * @param po
    * @return
    * @author 肖勇
    * @date 2015-11-12
   */
  public GridDataModel findTaskPaging(PageObject po);

  
  public MTaskMonitorModel getMTaskMonitorModelById(Integer id);
  
  /**
   * 重新执行任务
   * @param model
   */
  public void reRunTask(MTaskMonitorModel model);

}
