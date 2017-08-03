package com.daboo.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.daboo.business.enums.ActiveStateEnum;
import com.daboo.business.enums.OpenStateEnum;
import com.daboo.business.mapper.MBusinessCenterMapper;
import com.daboo.business.model.MBusinessCenterModel;
import com.daboo.business.model.MBusinessUserModel;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.SerialNoUtil;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.model.MUserInfoModel;

@Service
public class MBusinessCenterServiceImpl implements MBusinessCenterService {

	private Logger log=LoggerFactory.getLogger(MBusinessCenterServiceImpl.class);
	
	@Autowired
	private MBusinessCenterMapper businessCenterMapper;
	
	@Autowired
	private MUserInfoMapper mUserInfoMapper;
	
	/**
	 * 分页查询商务中心列表
	 * @FunName findByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月23日
	 */
	public GridDataModel findByConditions(PageObject po) {
		
		RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
		List<MBusinessCenterModel> rows=businessCenterMapper.findByConditions(po.getCondition(),rb);
		int count=businessCenterMapper.countByConditions(po.getCondition());
		
		return new GridDataModel(rows, count);
	}

	
	/**
	 * 申请成为服务中心
	 * @FunName applyToBusinessCenter
	 * @param accountNo 会员账户
	 * @return true成功，false失败
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public boolean applyToBusinessCenter(String accountNo) throws BusinessException {
		log.info("申请成为服务中心,会员账户为："+accountNo);
		boolean flag=false;
		MUserInfoModel user= new MUserInfoModel();
		user.setAccountNo(accountNo);
		user.setIsDeleted(0);
		try {
			user=mUserInfoMapper.getObjectByModel(user);
			
			if(user==null){
				throw new BusinessException("根据用户账户查询用户为空");
			}
			
			if(user.getType().intValue() != 1){
				throw new BusinessException("普通激活的会员才能申请为服务中心");
			}
			
			businessCenterMapper.deleteByAccountNo(accountNo);
			
			MBusinessCenterModel model=new MBusinessCenterModel();
			model.setBusinessNo(SerialNoUtil.getSerialNo("business","BC"));
			model.setAccountNo(accountNo);
			model.setPhone(user.getPhone());
			model.setRealName(user.getRealName());
			model.setOpenState(OpenStateEnum.WAITOPEN.getCode());
			model.setActiveState(ActiveStateEnum.NOT_ACTIVE.getCode());
			model.setCreateTime(new Date());
			model.setUpdateTime(new Date());
			model.setVersion(1);
			businessCenterMapper.insertModel(model);
			flag=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("申请成为服务中心发生异常",e);
			flag=false;
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException(e.getMessage());
			}
		}
		if(flag){
			return true;
		}
		return false;
	}
	
	/**
	 * 激活会员成服务中心，无需审核流程
	 * @FunName activeUserToBusinessCenter
	 * @param accountNo
	 * @return
	 * @throws BusinessException
	 * @author dingyang
	 * @Create Date 2015年12月11日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public boolean activeUserToBusinessCenter(String accountNo) throws BusinessException {
		log.info("激活会员成为服务中心,会员账户为："+accountNo);
		boolean flag=false;
		MUserInfoModel user= new MUserInfoModel();
		user.setAccountNo(accountNo);
		user.setIsDeleted(0);
		try {
			user=mUserInfoMapper.getObjectByModel(user);
			
			if(user==null){
				throw new BusinessException("根据用户账户查询用户为空");
			}
			
			businessCenterMapper.deleteByAccountNo(accountNo);
			
			MBusinessCenterModel model=new MBusinessCenterModel();
			model.setBusinessNo(SerialNoUtil.getSerialNo("business","BC"));
			model.setAccountNo(accountNo);
			model.setPhone(user.getPhone());
			model.setRealName(user.getRealName());
			model.setOpenState(OpenStateEnum.OPENED.getCode());//已开通
			model.setActiveState(ActiveStateEnum.ACTIVATED.getCode());//已激活
			model.setCreateTime(new Date());
			model.setUpdateTime(new Date());
			model.setVersion(1);
			businessCenterMapper.insertModel(model);
			flag=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("激活会员成为服务中心发生异常",e);
			flag=false;
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException(e.getMessage());
			}
		}
		if(flag){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 检查用户账户是否申请为服务中心
	 * @FunName checkIsBusinessCenter
	 * @param accountNo 账户
	 * @return  -1：会员状态已冻结 ; -2:未激活的会员;0：没有申请过 ; 1:正在申请中,待开通;2:已拒绝开通; 3:已开通已激活; 4：已开通已冻结 ; 
	 * @author dingyang
	 * @Create Date 2015年10月27日
	 */
	public JSONObject checkIsBusinessCenter(String accountNo){
		MBusinessCenterModel model=new MBusinessCenterModel();
		model.setAccountNo(accountNo);
		MUserInfoModel user= new MUserInfoModel();
		user.setAccountNo(accountNo);
		user.setIsDeleted(0);
		
		try {
			model=businessCenterMapper.getObjectByModel(model);
			user=mUserInfoMapper.getObjectByModel(user);
			if(user==null){
				return parseResult(98, "查询用户信息为空");
			}
			int type=user.getType().intValue();//会员类型(-1冻结的会员 0未激活 1激活的普通 2 冻结的服务中心  3激活的服务中心)
			if(type == -1){
				return parseResult(-1,"会员状态已冻结，不能申请为服务中心，如有问题请联系：400-996-9595");
			}
			if(type == 0){
				return parseResult(-2,"未激活的会员不能申请为服务中心，如有问题请联系：400-996-9595");
			}
			
			if(model == null && type == 1){
				log.info("此用户账户暂未申请为服务中心");
				return parseResult(0, "没有申请过,可以申请");
			}
			if(type == 1 && model.getOpenState().equals(OpenStateEnum.WAITOPEN.getCode()) && model.getActiveState().equals(ActiveStateEnum.NOT_ACTIVE.getCode())){
				return parseResult(1, "您申请成为服务中心正在审核中，请勿重新申请！");
			}
			if(type == 1 && model.getOpenState().equals(OpenStateEnum.REFUSED.getCode()) && model.getActiveState().equals(ActiveStateEnum.NOT_ACTIVE.getCode())){
				
				return parseResult(2, "您的申请已被拒绝,【备注："+model.getRemark()+"】,是否重新申请？");
			}
			if(type == 3 && model.getOpenState().equals(OpenStateEnum.OPENED.getCode()) && model.getActiveState().equals(ActiveStateEnum.ACTIVATED.getCode())){
				return parseResult(3, "您已经成功申请为服务中心！");
			}
			if(type == 2 && model.getOpenState().equals(OpenStateEnum.OPENED.getCode()) && model.getActiveState().equals(ActiveStateEnum.FREEZEACTIVE.getCode())){
				return parseResult(4,"您的服务中心状态已被冻结，如有问题请联系：400-996-9595");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("检查用户账户是否申请为服务中心发生异常",e);
		}
		return parseResult(99, "未知状态，请求异常");
	}
	
	private JSONObject parseResult(int type,String mes){
		JSONObject obj=new JSONObject();
		obj.put("type", type);
		obj.put("resultMes", mes);
		return obj;
	}
	
	/**
	 * 开通为服务中心
	 * @FunName openToBusinessCenter
	 * @param model
	 * @return
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年10月26日
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public boolean openToBusinessCenter(MBusinessCenterModel model) throws BusinessException {
		int result=0;
		try {
			result=businessCenterMapper.openToBC(model);
			if(result <=0 ){
				throw new BusinessException("更改商务中心表记录状态未成功");
			}
			//是否更新用户表的类型字段？
			MBusinessCenterModel mb=findModelById(model.getId());
			if(mb==null){
				throw new BusinessException("根据主键ID查询商务中心表记录为空");
			}
			
			result=businessCenterMapper.updateUserToBC(mb.getAccountNo());
			if(result <=0 ){
				throw new BusinessException("更改用户表记录状态未成功");
			}
			log.info("开通为服务中心成功");
		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("开通会员为服务中心发生异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("开通会员为服务中心发生异常");
			}
		}
		return true;
	}
	
	/**
	 * 拒绝开通为服务中心
	 * @FunName notOpenToBusinessCenter
	 * @param model
	 * @return
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年10月26日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public boolean notOpenToBusinessCenter(MBusinessCenterModel model)
			throws BusinessException {
		int result=0;
		try {
			result=businessCenterMapper.notOpenToBC(model);
			
			log.info("拒绝开通为服务中心成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("拒绝开通会员为服务中心发生异常",e);
			throw new BusinessException("拒绝开通会员为服务中心发生异常");
		}
		if(result>0){
			return true;
		}else{
			throw new BusinessException("拒绝开通商务中心状态更改未成功");
		}
	}
	
	/**
	 * 根据主键删除申请商务中心记录
	 * @FunName deleteOne
	 * @param id
	 * @return
	 * @author dingyang
	 * @throws BusinessException 
	 * @Create Date 2015年10月27日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public int deleteOne(Integer id) throws BusinessException{
		try {
			return businessCenterMapper.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据主键删除申请商务中心记录出错",e);
			throw new BusinessException("根据主键删除申请商务中心记录");
		}
	}
	
	/**
	 * 激活商务中心
	 * @FunName activeBusinessCenter
	 * @param model
	 * @return
	 * @throws BusinessException
	 * @author dingyang
	 * @Create Date 2015年10月27日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public boolean activeBusinessCenter(MBusinessCenterModel model) throws BusinessException{
		int result=0;
		try {
			result=businessCenterMapper.activeBC(model);
			if(result<=0){
				throw new BusinessException("激动商务中心状态更改未成功");
			}
			
			//是否更新用户表的类型字段？
			MBusinessCenterModel mb=findModelById(model.getId());
			if(mb==null){
				throw new BusinessException("根据主键ID查询商务中心表记录为空");
			}
			
			result=businessCenterMapper.updateFreezedToActive(mb.getAccountNo());
			if(result<=0){
				throw new BusinessException("修改用户表状态未成功");
			}
			
			log.info("激活为服务中心成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("激活为服务中心发生异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("激活为服务中心发生异常");
			}
		}
		return true;
		
	}
	/**
	 * 冻结商务中心
	 * @FunName activeBusinessCenter
	 * @param model
	 * @return
	 * @throws BusinessException
	 * @author dingyang
	 * @Create Date 2015年10月27日
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public boolean freezeBusinessCenter(MBusinessCenterModel model) throws BusinessException{
		int result=0;
		try {
			result=businessCenterMapper.freezeBC(model);
			if(result<=0){
				throw new BusinessException("冻结商务中心状态更改未成功");
			}
			//是否更新用户表的类型字段？
			MBusinessCenterModel mb=findModelById(model.getId());
			if(mb==null){
				throw new BusinessException("根据主键ID查询商务中心表记录为空");
			}
			
			result=businessCenterMapper.updateBCToFreezed(mb.getAccountNo());
			if(result<=0){
				throw new BusinessException("修改用户表状态未成功");
			}
			
			log.info("冻结为服务中心成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("冻结服务中心发生异常",e);
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("冻结服务中心发生异常");
			}
		}
		return true;
	}
	
	public MBusinessCenterModel findModelById(int id){
		return businessCenterMapper.getObjectById(id);
	}
	
	/**
	 * 根据账户查询商务中心列表
	 * @FunName findUserListForBC
	 * @param accountNo
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月28日
	 */
	public List<MBusinessUserModel> findUserListForBC(String accountNo){
		
		List<MBusinessUserModel> list=new ArrayList<MBusinessUserModel>();
		try {
			list=businessCenterMapper.findUserListForBC(accountNo);
			if(list!=null && list.size() >0){
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据账户查询商务中心列表异常",e);
		}
		return null;
	}
	
	
}
