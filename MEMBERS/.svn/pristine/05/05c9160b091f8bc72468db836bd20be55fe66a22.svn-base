package com.daboo.user.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.business.service.MBusinessCenterService;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.BigDecimalUtil;
import com.daboo.core.util.MD5Encoder;
import com.daboo.core.util.StringUtils;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.system.service.SysDictService;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.mapper.MUserRegMapper;
import com.daboo.user.mapper.MUserlevelManageMapper;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.model.MUserRegModel;
import com.daboo.user.model.MUserlevelManageModel;
import com.danga.MemCached.MemCachedClient;

@Service
@Transactional
public class MUserlevelManageServiceImpl implements MUserlevelManageService {
	protected static final Logger log = LoggerFactory.getLogger(MUserlevelManageServiceImpl.class);
	@Autowired
	MUserlevelManageMapper mUserlevelManageMapper;
	@Autowired
	MUserInfoMapper mUserInfoMapper;
	@Autowired
	MemberRewardService memberRewardService;
	@Autowired
	MUserInfoService mUserInfoService;
	@Autowired
	MUserRegMapper mUserRegMapper;
	@Autowired
	SysDictService sysDictService;
	@Autowired
	MBusinessCenterService mBusinessCenterService;
	@Autowired
	private MemCachedClient memCachedClient;
	
	@Override
	public GridDataModel findLecelChange(PageObject po) {
		RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserlevelManageModel> rows = mUserlevelManageMapper.findByConditions(
				po.getCondition(), rowBounds);
		int total = mUserlevelManageMapper.findByConditionsCount(po.getCondition());
		return new GridDataModel(rows, total);
	}

	@Override
	public void addLecelChange(MUserlevelManageModel model) {
		mUserlevelManageMapper.insertModel(model);
	}

	@Override
	public void deleteLecelChange(Integer id) {
		mUserlevelManageMapper.deleteLecelChangeById(id);
	}

	@Override
	public void updateLecelChange(MUserlevelManageModel model) throws BusinessException{
		MUserlevelManageModel newModel=mUserlevelManageMapper.getObjectById(model.getId());
		newModel.setAuthTime(new Date());
		newModel.setStatus(model.getStatus());
		newModel.setUpdateTime(new Date());
		newModel.setUpdateBy(model.getUpdateBy());
		int count=mUserlevelManageMapper.updateModelById(newModel);
		if (count>0&&model.getStatus()==1) {
			MUserInfoModel mUserInfoModel = new MUserInfoModel();
			mUserInfoModel.setAccountNo(newModel.getUserId());
			mUserInfoModel.setIsDeleted(0);
			mUserInfoModel=mUserInfoMapper.getObjectByModel(mUserInfoModel);
			mUserInfoModel.setLevel(newModel.getNewLevel());
			mUserInfoModel.setUpdateTime(new Date());
			mUserInfoMapper.updateModelById(mUserInfoModel);
		}
	}

	@Override
	public MUserlevelManageModel getModelById(Integer id) {
		return mUserlevelManageMapper.getObjectById(id);
	}

	/**
	 * 激活会员 前台
	 * @param memberId 被激活会员id
	 * @param userId 用户id
	 * @param type 激活方式
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=BusinessException.class)
	public void openMember(Integer userId, Integer memberId, Integer type ,String password,String operatorNo)
			throws BusinessException {
		try {
			String ckpwd=checkPayPwd(userId, password);
			if(!"ok".equals(ckpwd)){//支付密码校验
				throw new BusinessException(ckpwd);
			}
			if (memCachedClient.add("memberId"+memberId, 0, new Date(1000))) {
				String rechargeAmountValue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
				String scaleValue = sysDictService.getDetailValue("CASH_SCALE","CASH_SCALE");
				Integer pointScal = 100 - Integer.valueOf(scaleValue);	
				
				MUserRegModel regModel = mUserRegMapper.getObjectById(memberId);
				if (regModel==null) {
					throw new BusinessException("待激活用户不存在");
				}
				if (type==1) {
					memberRewardService.userAccountForActivationUser(userId, BigDecimalUtil.mul(new BigDecimal(rechargeAmountValue), new BigDecimal(100)).longValue() , regModel.getAccountNo(), Integer.valueOf(1),operatorNo,regModel.getRegNum());
				}else if (type==2) {
					memberRewardService.userAccountForActivationUser(userId, BigDecimalUtil.mul(new BigDecimal(rechargeAmountValue), new BigDecimal(scaleValue)).longValue() , regModel.getAccountNo(), Integer.valueOf(1),operatorNo,regModel.getRegNum());
					memberRewardService.userAccountForActivationUser(userId, BigDecimalUtil.div(BigDecimalUtil.mul(new BigDecimal(rechargeAmountValue), new BigDecimal(pointScal)),new BigDecimal(100)).longValue() , regModel.getAccountNo(), Integer.valueOf(2),operatorNo,regModel.getRegNum());
				}else if (type==3) {
					memberRewardService.userAccountForActivationUser(userId, BigDecimalUtil.mul(new BigDecimal(rechargeAmountValue), new BigDecimal(scaleValue)).longValue() , regModel.getAccountNo(), Integer.valueOf(1),operatorNo,regModel.getRegNum());
					memberRewardService.userAccountForActivationUser(userId, BigDecimalUtil.div(BigDecimalUtil.mul(new BigDecimal(rechargeAmountValue), new BigDecimal(pointScal)),new BigDecimal(100)).longValue() , regModel.getAccountNo(), Integer.valueOf(3),operatorNo,regModel.getRegNum());
				} else {
					throw new BusinessException("激活方式不存在");
				}
				
				openUserService(regModel,operatorNo,false);
				//用户表
				/*MUserInfoModel  model = new MUserInfoModel();
				BeanUtils.copyProperties(model, regModel);
				model.setId(null);
				model.setType(3);
				model.setLevel(0);
				model.setAwardFlag(0);
				model.setActivateTime(new Date());
				model.setActivateBy(operatorNo);
				model.setCopperCardNum(0);
				model.setSilverCardNum(0);
				model.setTotalNum(1);
				model.setMyMoney(0);
				model.setTeamMoney(0);
				model.setIsOpend(2);
				model.setIsDeleted(0);
				model.setUpdateTime(new Date());
				mUserInfoMapper.insertModel(model);
				mBusinessCenterService.activeUserToBusinessCenter(model.getAccountNo());
				//删除编号相同的会员
				mUserRegMapper.deleteUserRegByAccountNo(regModel.getAccountNo());
				memberRewardService.activationUser(model.getId(), model.getRecommendId(),operatorNo,false);*/
			}else {
				throw new BusinessException("操作过于频繁");
			}
		}catch (BusinessException e){
			log.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}catch (Exception e){
			log.error("激活异常",e.getMessage());
			throw new BusinessException("激活异常");
		}
		
	}
	
	/**
	 * 
	 * @param regModel 
	 * @param operatorNo 操作员
	 * @param isEmpty 空单标记
	 */
	@Override
	public void openUserService(MUserRegModel regModel, String operatorNo,boolean isEmpty) throws BusinessException {
		try {
			MUserInfoModel  model = new MUserInfoModel();
			BeanUtils.copyProperties(model, regModel);
			model.setId(null);
			model.setType(3);
			model.setLevel(0);
			model.setAwardFlag(0);
			model.setActivateTime(new Date());
			model.setActivateBy(operatorNo);
			model.setCopperCardNum(0);
			model.setSilverCardNum(0);
			model.setTotalNum(1);
			model.setMyMoney(0);
			model.setTeamMoney(0);
			model.setIsOpend(isEmpty?1:2);
			model.setIsDeleted(0);
			model.setUpdateTime(new Date());
			model.setRegisterMoney(Integer.parseInt(sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT")));
			//删除编号相同的会员
			mUserRegMapper.deleteUserRegByAccountNo(regModel.getAccountNo());	
			
			mUserInfoMapper.insertModel(model);
			mBusinessCenterService.activeUserToBusinessCenter(model.getAccountNo());			
			memberRewardService.activationUser(model.getId(), model.getRecommendId(),operatorNo,isEmpty);
			//大于1的时候
			if(regModel.getRegNum() >1){				
				MUserInfoModel userInfo = null;
				for(int subInx=1; subInx < regModel.getRegNum(); subInx++){
					userInfo = new MUserInfoModel(model);			
					userInfo.setAccountNo(StringUtils.getUserNoSuffix(model.getAccountNo(), subInx));
					userInfo.setMasterUserId(model.getId());
					userInfo.setRecommendId(model.getId());//设置推荐人
					userInfo.setTotalNum(model.getTotalNum() + subInx);			
					userInfo.setIsOpend(isEmpty?1:2);//1空单 2实单
					userInfo.setMyMoney(0);//个人业绩
					userInfo.setTeamMoney(0);//团队业绩
					userInfo.setActivateBy(operatorNo);
					userInfo.setType(model.getType());
					mUserInfoMapper.insertModel(userInfo);//生成账户信息
					mBusinessCenterService.activeUserToBusinessCenter(userInfo.getAccountNo());
					
					memberRewardService.activationUser(userInfo.getId(),model.getId(),operatorNo,isEmpty);
				}
				//修改总人数
				mUserInfoMapper.updateMasterUserInfoTotalNum(model.getId(),regModel.getRegNum()-1);
			}
		} catch (BusinessException e) {
			log.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			log.error("激活异常", e.getMessage());
			throw new BusinessException("激活异常");
		}
	}
	/**
	 * 激活会员 后台
	 * @param memberId 被激活会员id
	 * @param userId 用户id
	 * @param type 激活方式
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=BusinessException.class)
	public void openUser(Integer memberId,String operatorNo,Integer type) throws BusinessException {
		try {
			MUserRegModel regModel = mUserRegMapper.getObjectById(memberId);
			if (regModel==null) {
				throw new BusinessException("该用户已激活");
			}
			//用户表
			/*MUserInfoModel  model = new MUserInfoModel();
			BeanUtils.copyProperties(model, regModel);
			model.setId(null);
			model.setType(3);
			model.setLevel(0);
			model.setAwardFlag(0);
			model.setActivateTime(new Date());
			model.setActivateBy(operatorNo);
			model.setCopperCardNum(0);
			model.setSilverCardNum(0);
			model.setTotalNum(1);
			model.setMyMoney(0);
			model.setTeamMoney(0);
			model.setIsOpend(type);
			model.setIsDeleted(0);
			model.setUpdateTime(new Date());
			mUserInfoMapper.insertModel(model);
			mBusinessCenterService.activeUserToBusinessCenter(model.getAccountNo());
			//删除编号相同的会员
			mUserRegMapper.deleteUserRegByAccountNo(regModel.getAccountNo());
			memberRewardService.activationUser(model.getId(), model.getRecommendId(),operatorNo,type==1?true:false);*/
			
			openUserService(regModel,operatorNo,type==1?true:false);
		}catch (BusinessException e){
			log.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			log.error("激活异常",e.getMessage());
			throw new BusinessException("激活异常");
		}
		
	}

	/**
	 * 
	  * @Description: 支付密码校验
	  * @param userId
	  * @param pwd
	  * @return
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public String checkPayPwd(int userId,String pwd){
		MUserInfoModel user=mUserInfoMapper.getObjectById(userId);
		if(user==null){
			return "用户不存在";
		}else if(user.getPayPassword()==null){
			return "密码为空";
		}else if(!user.getPayPassword().equals(MD5Encoder.encode(pwd))){
			return "密码错误";
		}else{
			return "ok";
		}
	}
}
