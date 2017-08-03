package com.daboo.reward.mapper;



import java.util.List;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.session.RowBounds;

import com.daboo.reward.model.MPointTaskDetailModel;


/**
 * MPointTaskDetailDAO接口
 *
 * @author admin
 */
public interface MPointTaskDetailMapper extends GenericMapper<MPointTaskDetailModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	 * 分页查询
	 * @param pointTaskDetailModel
	 * @param rowBounds
	 * @return
	 */
	public List<MPointTaskDetailModel> getListByModel(MPointTaskDetailModel pointTaskDetailModel,RowBounds rowBounds);
	//------------------请在此添加自定义方法（结束）------------------

    
}
