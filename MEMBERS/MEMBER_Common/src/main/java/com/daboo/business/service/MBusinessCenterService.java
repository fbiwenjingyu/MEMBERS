package com.daboo.business.service;

import java.util.List;

import org.springframework.remoting.service.annotation.RemoteService;

import com.alibaba.fastjson.JSONObject;
import com.daboo.business.model.MBusinessCenterModel;
import com.daboo.business.model.MBusinessUserModel;
import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;

/**
 * 
 * @Description 商务中心服务接口 
 * @author dingyang
 * @version 1.0
 * @Create Date 2015年10月26日
 */
@RemoteService
public interface MBusinessCenterService {

	/**
	 * 分页查询商务中心列表
	 * @FunName findByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月23日
	 */
	public GridDataModel findByConditions(PageObject po);
	
	/**
	 * 申请成为服务中心
	 * @FunName applyToBusinessCenter
	 * @param accountNo 会员账户
	 * @return true成功，false失败
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年10月26日
	 */
	public boolean applyToBusinessCenter(String accountNo) throws BusinessException;

	/**
	 * 激活会员成服务中心，无需审核流程
	 * @FunName activeUserToBusinessCenter
	 * @param accountNo
	 * @return
	 * @throws BusinessException
	 * @author dingyang
	 * @Create Date 2015年12月11日
	 */
	public boolean activeUserToBusinessCenter(String accountNo) throws BusinessException ;
	
	/**
	 * 开通为服务中心
	 * @FunName openToBusinessCenter
	 * @param model
	 * @return
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年10月26日
	 */
	public boolean openToBusinessCenter(MBusinessCenterModel model) throws BusinessException;
	
	/**
	 * 拒绝开通为服务中心
	 * @FunName notOpenToBusinessCenter
	 * @param model
	 * @return
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年10月26日
	 */
	public boolean notOpenToBusinessCenter(MBusinessCenterModel model) throws BusinessException;
	
	/**
	 * 根据主键删除申请商务中心记录
	 * @FunName deleteOne
	 * @param id
	 * @return
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年10月27日
	 */
	public int deleteOne(Integer id) throws BusinessException;
	
	/**
	 * 激活商务中心
	 * @FunName activeBusinessCenter
	 * @param model
	 * @return
	 * @throws BusinessException
	 * @author dingyang
	 * @Create Date 2015年10月27日
	 */
	public boolean activeBusinessCenter(MBusinessCenterModel model) throws BusinessException;
	
	/**
	 * 冻结商务中心
	 * @FunName activeBusinessCenter
	 * @param model
	 * @return
	 * @throws BusinessException
	 * @author dingyang
	 * @Create Date 2015年10月27日
	 */
	public boolean freezeBusinessCenter(MBusinessCenterModel model) throws BusinessException;
	
	/**
	 * 根据主键ID获取对象
	 * @FunName findModelById
	 * @param id
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月27日
	 */
	public MBusinessCenterModel findModelById(int id);
	

	/**
	 * 根据账户查询商务中心列表
	 * @FunName findUserListForBC
	 * @param accountNo
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月28日
	 */
	public List<MBusinessUserModel> findUserListForBC(String accountNo);
	
	/**
	 * 检查用户账户是否申请为服务中心
	 * @FunName checkIsBusinessCenter
	 * @param accountNo 账户
	 * @return -1：会员状态已冻结 ; -2:未激活的会员;0：没有申请过 ; 1:正在申请中,待开通;2:已拒绝开通; 3:已开通已激活; 4：已开通已冻结 ; 
	 * @author dingyang
	 * @Create Date 2015年10月27日
	 */
	public JSONObject checkIsBusinessCenter(String accountNo);

}
