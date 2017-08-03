package com.daboo.message.service;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.message.model.MOpinionModel;
import com.daboo.service.base.BaseService;

/**
 * 意见反馈服务接口
 * @author fengchao
 *
 */
@RemoteService
public interface MOpinionService extends BaseService<MOpinionModel,Integer>   {

	/**
	 * @Description 根据id获取反馈的详情
	 * @param   id   反馈表的主键
	 * @return  反馈实体对象
	 * @author fengchao
	 */
	public MOpinionModel getOpinionDetail(Integer id);
	/**
	 * @Description 根据id列表删除反馈
	 * @param   ids  多个主键
	 * @author fengchao
	 */
	public void deleteOpinion(String ids);
}
