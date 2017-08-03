package com.daboo.reward.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.session.RowBounds;

import com.daboo.reward.model.MRewardDetailModel;
import com.daboo.reward.model.MUserAccountInoutModel;


/**
 * MRewardDetailDAO接口
 *
 * @author admin
 */
public interface MRewardDetailMapper extends GenericMapper<MRewardDetailModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	 * 分页查询
	 * @param Map
	 * @param rowBounds
	 * @return
	 */
	List<MUserAccountInoutModel> findByConditions(Map<String, Object> condition,RowBounds rb);
	/**
	 * 获取总数
	 * @param Map
	 * @return
	 */
	int countByConditions(Map<String, Object> condition);
	//------------------请在此添加自定义方法（结束）------------------

    
}
