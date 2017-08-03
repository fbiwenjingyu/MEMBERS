package com.daboo.message.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.message.mapper.MReplyMapper;
import com.daboo.message.model.MReplyModel;
import com.daboo.service.impl.base.BaseServiceImpl;
@Service
@Transactional
public class MReplyServiceImpl  extends BaseServiceImpl<MReplyModel, Integer>  implements MReplyService{

	@Autowired
	private MReplyMapper mReplyMapper;
	
	@Autowired
	public void setMapper(MReplyMapper mapper) {
		setGenericMapper(mapper);
	}
	/**
	 * @Description 判断此反馈是否有回复
	 * @param   id   反馈的主键id
	 * @return  boolean
	 * @author fengchao
	 */
	public boolean haveReply(Integer id){
		
		return mReplyMapper.getReplyCount(id) > 0 ;
	}
	
	/**
	 * @Description 判断此反馈是否有未读的回复
	 * @param   ids   反馈的主键id(多个)
	 * @return  boolean
	 * @author fengchao
	 */
	public List<Map<String,Object>> haveNoReadReply(String ids){
		
		return mReplyMapper.haveNoReadMes(ids);
		
	}
	/**
	 * @Description 将消息标记为已读
	 * @param   ids   反馈的主键id(多个)
	 * @return  boolean
	 * @author fengchao
	 */
	public void setIsReadByResponseId(Integer id){
		
		mReplyMapper.updateReadByResponseId(id);
	}
}
