package com.daboo.message.service;


import java.util.List;
import java.util.Map;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.message.model.MReplyModel;
import com.daboo.service.base.BaseService;
@RemoteService
public interface MReplyService extends BaseService<MReplyModel, Integer> {

	
	/**
	 * @Description 判断此反馈是否有回复
	 * @param   id   反馈的主键id
	 * @return  boolean
	 * @author fengchao
	 */
	public boolean haveReply(Integer id);
	/**
	 * @Description 判断此反馈是否有未读的回复
	 * @param   ids   反馈的主键id(多个)
	 * @return  boolean
	 * @author fengchao
	 */
	public List<Map<String,Object>> haveNoReadReply(String ids);
	/**
	 * @Description 将消息标记为已读
	 * @param   ids   反馈的主键id(多个)
	 * @return  boolean
	 * @author fengchao
	 */
	public void setIsReadByResponseId(Integer id);

}
