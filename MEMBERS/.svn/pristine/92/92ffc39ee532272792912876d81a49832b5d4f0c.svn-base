package com.daboo.user.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.session.RowBounds;

import com.daboo.user.model.MUserlevelManageModel;


/**
 * MUserlevelManageDAO接口
 *
 * @author admin
 */
public interface MUserlevelManageMapper extends GenericMapper<MUserlevelManageModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	
	/**
	 * 
	  * @Description: 根据条件分页查询
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public List<MUserlevelManageModel> findByConditions(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 根据条件分页查询统计
	  * @param conditions
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public int findByConditionsCount(Map<String, Object> conditions);
	
	/**
	  * @Description: 逻辑删除
	 */
	public int deleteLecelChangeById(int id);
	//------------------请在此添加自定义方法（结束）------------------

    
}
