package com.daboo.reward.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.session.RowBounds;

import com.daboo.reward.model.MDayReportModel;


/**
 * MDayReportDAO接口
 *
 * @author admin
 */
public interface MDayReportMapper extends GenericMapper<MDayReportModel,Integer>{
	//------------------请在此添加自定义方法（开始）------------------

	List<MDayReportModel> selectInOutByConditions(Map<String, Object> condition, RowBounds rb);

	int countInOutByConditions(Map<String, Object> condition);

	//------------------请在此添加自定义方法（结束）------------------

    
}
