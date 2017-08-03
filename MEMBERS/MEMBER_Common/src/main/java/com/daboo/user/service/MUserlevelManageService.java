package com.daboo.user.service;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.user.model.MUserRegModel;
import com.daboo.user.model.MUserlevelManageModel;

@RemoteService
public interface MUserlevelManageService {
	/**
	 * 条件查询级别调整信息
	 * @param po
	 * @return
	 */
	public GridDataModel findLecelChange(PageObject po);
	
	/**
	 * 新增级别调整信息
	 * @param po
	 * @return
	 */
	public void addLecelChange(MUserlevelManageModel model);
	
	/**
	 * 删除级别调整信息
	 * @param po
	 * @return
	 */
	public void deleteLecelChange(Integer id);
	
	/**
	 * 修改级别调整信息
	 * @param po
	 * @return
	 */
	public void updateLecelChange(MUserlevelManageModel model) throws BusinessException;
	
	/**
	 * 获取级别调整信息
	 * @param po
	 * @return
	 */
	public MUserlevelManageModel getModelById(Integer id);
	
	/**
	 * 开通会员服务
	 * @param regModel
	 * @param operatorNo
	 * @param isEmpty
	 * @throws BusinessException
	 */
	
	public void openUserService(MUserRegModel regModel,String operatorNo,boolean isEmpty)  throws BusinessException;
	/**
	 * 激活会员 前台
	 * @param memberId 被激活会员id
	 * @param userId 用户id
	 * @param type 激活方式
	 * @return
	 */
	public void openMember(Integer memberId, Integer userId, Integer type,String password,String operatorNo) throws BusinessException;
	
	/**
	 * 激活会员 后台
	 * @param memberId 被激活会员id
	 * @param operatorNo 用户id
	 * @param type 激活方式(1:空单,2:实单)
	 * @return
	 */
	public void openUser(Integer memberId,String operatorNo,Integer type) throws BusinessException;
}
