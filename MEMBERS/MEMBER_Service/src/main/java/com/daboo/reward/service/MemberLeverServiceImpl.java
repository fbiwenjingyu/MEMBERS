package com.daboo.reward.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.exception.BusinessException;
import com.daboo.reward.mapper.MDayPointDetailMapper;
import com.daboo.reward.model.MDayPointDetailModel;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.model.MUserInfoModel;
/**
 * @描述：修改会员的等级和银牌、铜牌数量
 * @author zhouRui
 */
@Service
@Transactional
public class MemberLeverServiceImpl implements MemberLeverService {
	protected static final Logger log = LoggerFactory.getLogger(MemberLeverServiceImpl.class);
	@Autowired
	MDayPointDetailMapper mDayPointDetailMapper ;
	@Autowired
	MUserInfoMapper mUserInfoMapper ;
   /**
   * @描述：修改会员的等级
   * @author zhouRui
   */
	@Override
	public void updateMemberLerver(Date collectDate) throws BusinessException {
		//1.调整铜卡会员等级;要求:日推广积分>1000
		
		updateCopperLerver(collectDate);
		//2.调整银卡会员等级;要求:日推广积分>3000  并且  直推铜卡>=2
		
		updateSilverLerver(collectDate);
		
		//3.调整金卡会员等级;要求:日推广积分>3000  并且  直推银卡>=3
		
		updateGoldLerver(collectDate);
				
		mDayPointDetailMapper.updateDayPointDetailByState(2, 1, collectDate);
	}
	
	/**
	 * 调整铜卡会员等级;要求:日推广积分>1000
	 * @param collectDate
	 * @throws BusinessException
	 */
	public void updateCopperLerver(Date collectDate) throws BusinessException{
		Integer copperLevel = 1;
		List<MDayPointDetailModel> copperList = mDayPointDetailMapper.getDayPointDetailForLevelSetting(collectDate, copperLevel, new BigDecimal(1000));
		
		MUserInfoModel  copperUserInfo = null;
		for (MDayPointDetailModel model : copperList) {
			copperUserInfo = new MUserInfoModel() ;
			copperUserInfo.setId(model.getUserId());
			copperUserInfo.setLevel(copperLevel);			 
			updateUserInfoAndSetParentCardNum(copperUserInfo,model.getRecommendId());			
			
			/*model.setStatus(2);//修改状态
			mDayPointDetailMapper.updateModelById(model);*/
		}
	}
	
	/**
	 * 调整银卡会员等级;要求:日推广积分>3000  并且  直推铜卡>=2
	 * @param collectDate
	 * @throws BusinessException
	 */
	public void updateSilverLerver(Date collectDate) throws BusinessException{
		Integer silverLevel = 2;		
		List<MDayPointDetailModel> silverList = mDayPointDetailMapper.getDayPointDetailForLevelSetting(collectDate, silverLevel, new BigDecimal(3000));		
		MUserInfoModel  silverUserInfo = null;
		for (MDayPointDetailModel model : silverList) {
			if(model.getCopperCardNum() >=2){
				silverUserInfo = new MUserInfoModel() ;
				silverUserInfo.setId(model.getUserId());
				silverUserInfo.setLevel(silverLevel);				
				updateUserInfoAndSetParentCardNum(silverUserInfo,model.getRecommendId());
			}
			/*model.setStatus(2);//修改状态
			mDayPointDetailMapper.updateModelById(model);*/
		}
	}
	
	/**
	 * 调整金卡会员等级;要求:日推广积分>3000  并且  直推银卡>=2
	 * @param collectDate
	 * @throws BusinessException
	 */
	public void updateGoldLerver(Date collectDate) throws BusinessException{
		Integer goldLevel = 3;		
		List<MDayPointDetailModel> goldList = mDayPointDetailMapper.getDayPointDetailForLevelSetting(collectDate, goldLevel, new BigDecimal(7000));		
		MUserInfoModel  goldUserInfo = null;
		for (MDayPointDetailModel model : goldList) {
			if(model.getSilverCardNum() >=2){//直推银卡>=2
				goldUserInfo = new MUserInfoModel() ;
				goldUserInfo.setId(model.getUserId());
				goldUserInfo.setLevel(goldLevel);				
				updateUserInfoAndSetParentCardNum(goldUserInfo,model.getRecommendId());
			}
			/*model.setStatus(2);//修改状态
			mDayPointDetailMapper.updateModelById(model);*/
		}
	}
	/**
	 * 修改会员的等级必须单独的事务进行提交
	 */
	@Override
	//@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=BusinessException.class)
	public void updateUserInfoAndSetParentCardNum(MUserInfoModel userInfo,Integer recommendId) throws BusinessException {
		//修改用户等级
		mUserInfoMapper.updateModelById(userInfo);
		//修改推荐人铜卡数和银卡数量
		updateUserInfoCardNum(recommendId);
	}

	@Override
	public void updateUserInfoCardNum(Integer recommendId)
			throws BusinessException {
		//如果为大博,则不修改
		if(recommendId == null || recommendId == -1){
			return ;
		}
		int bronzeMedalNum = mUserInfoMapper.queryMedalNum(recommendId, 1);
		int silverMedalNum = mUserInfoMapper.queryMedalNum(recommendId, 2);
		//修改推荐人铜卡数和银卡数量
		MUserInfoModel parentModel = new MUserInfoModel();
		parentModel.setId(recommendId);
		parentModel.setCopperCardNum(bronzeMedalNum);
		parentModel.setSilverCardNum(silverMedalNum);
		mUserInfoMapper.updateModelById(parentModel);		
	}

	
	
  /**
   * @描述：修改会员的铜牌和银牌数量
   * 此接口包含了后台手工调整和定时任务执行对会员铜牌、银牌数量的影响
   * recommendUserId：当前会员的推荐人ID
   * @author zhouRui
   */
	/*@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=BusinessException.class)
	public void updateMemberMedalNum(int recommendUserId) throws BusinessException {
		int bronzeMedalNum = mUserInfoMapper.queryMedalNum( recommendUserId,1);
		int silverMedalNum = mUserInfoMapper.queryMedalNum( recommendUserId,2);
	    MUserInfoModel  model = new MUserInfoModel() ;
	    model.setId(recommendUserId);
	    model.setCopperCardNum(bronzeMedalNum);
	    model.setSilverCardNum(silverMedalNum);
		mUserInfoMapper.updateModelById(model);
	}*/
	
    /*public int calculateLevel(BigDecimal point){
    	int level = 0 ;
    	//如果当天获取积分小于等于1000，则为普通级别
    	if(point.compareTo(new BigDecimal(1000))!= 1){
    		level = 0 ;
    	}
    	//如果当天获取积分大于1000小于等于3000，则为铜牌
    	else if (point.compareTo(new BigDecimal(1000))==1 && point.compareTo(new BigDecimal(3000))!=1){
    	    level =1;
    	}
    	//如果当天获取积分大于3000小于等于7000，则为银牌
    	else if (point.compareTo(new BigDecimal(3000))==1 && point.compareTo(new BigDecimal(7000))!=1){
    	    level =2;
    	}
    	//如果当天获取积分大于7000，则为金牌
    	else{
    		 level =3;
    	}
    	return  level;
    }*/


}
