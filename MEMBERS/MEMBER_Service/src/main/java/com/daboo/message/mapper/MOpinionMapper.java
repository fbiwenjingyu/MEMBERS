package com.daboo.message.mapper;



import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;

import com.daboo.message.model.MOpinionModel;


/**
 * MOpinionDAO接口
 *
 * @author admin
 */
public interface MOpinionMapper extends GenericMapper<MOpinionModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	 * @Description 根据ids删除反馈
	 * @param   ids   要删除的主键
	 * @author fengchao
	 */
	public void deleteOpinionByIds(@Param("ids")String ids);
	//------------------请在此添加自定义方法（结束）------------------

    
}
