package com.daboo.message.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.session.RowBounds;

import com.daboo.message.model.MNoticeModel;


/**
 * MNoticeDAO接口
 *
 * @author admin
 */
public interface MNoticeMapper extends GenericMapper<MNoticeModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	 * 
	  * @Description: 根据ID查询
	  * @param noticeId
	  * @return
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public Map<String, Object> findById(Integer noticeId);
	/**
	 * 
	  * @Description: 根据条件分页查询
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 马正正
	  * @date 2015年8月18日
	 */
	public List<Map<String, Object>> findByCons(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 根据条件分页查询统计
	  * @param conditions
	  * @return
	  * @author 马正正
	  * @date 2015年8月19日
	 */
	public int findByConsCount(Map<String, Object> conditions);
	//------------------请在此添加自定义方法（结束）------------------

    
}
