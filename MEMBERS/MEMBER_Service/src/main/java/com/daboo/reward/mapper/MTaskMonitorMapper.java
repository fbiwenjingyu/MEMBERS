package com.daboo.reward.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.session.RowBounds;

import com.daboo.reward.model.MTaskMonitorModel;


/**
 * MTaskMonitorDAO接口
 * @author admin
 */
public interface MTaskMonitorMapper extends GenericMapper<MTaskMonitorModel, Integer>{
	
	/**
	  * @Description: 分页查询用户信息
	  * @param conditions
	  * @return
	  * @author 肖勇
	  * @date 2015-11-12
	 */
	public List<MTaskMonitorModel> findTaskPaging(Map<String, Object> conditions, RowBounds rowBounds);
	
	/**
	  * @Description: 分页的总条数
	  * @param conditions
	  * @return
	  * @author 肖勇
	  * @date 2015-11-12
	 */
	public int findTaskCount(Map<String, Object> conditions);
	
    
}
