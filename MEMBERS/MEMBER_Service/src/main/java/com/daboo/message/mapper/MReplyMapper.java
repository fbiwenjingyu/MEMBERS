package com.daboo.message.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;

import com.daboo.message.model.MReplyModel;


/**
 * MReplyDAO接口
 *
 * @author admin
 */
public interface MReplyMapper extends GenericMapper<MReplyModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	 * @Description 获取意见反馈id查询它的所有回复
	 * @return  List<MReplyModel>
	 * @param id 反馈主键
	 * @author fengchao
	 */
	public List<MReplyModel> getListByOpinionId(@Param("id")Integer id);
	/**
	 * @Description 获取意见反馈id删除下面的所有回复
	 * @param 反馈主键列表
	 * @author fengchao
	 */
	public void deleteByResponseId(@Param("responseIds") String responseIds);
	/**
	 * @Description 获取此评论的回复总数
	 * @param   id   反馈的主键id
	 * @return  int
	 * @author fengchao
	 */
	public Integer getReplyCount(@Param("responseId") Integer responseId);
	/**
	 * @Description 获取反馈的总未读消息数
	 * @param   String   反馈的主键id
	 * @return  int
	 * @author fengchao
	 */
	public List<Map<String,Object>> haveNoReadMes(@Param("ids") String ids);
	/**
	 * @Description 修改已读
	 * @param   String   反馈的主键id
	 * @return  int
	 * @author fengchao
	 */
	public void updateReadByResponseId(@Param("responseId")Integer responseId );
	//------------------请在此添加自定义方法（结束）------------------

    
}
