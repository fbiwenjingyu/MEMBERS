package com.daboo.reward.service;

import java.util.Date;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.user.model.MUserInfoModel;

@RemoteService
public interface MemberLeverService {
 /**
  * @描述：修改会员的等级和银牌、铜牌数量
  * @author zhouRui
  */
   public void  updateMemberLerver(Date collectDate) throws BusinessException;
   
   /**
    * 推荐人用户ID
    * @param recommendId
    * @throws BusinessException
    */
   public void updateUserInfoCardNum(Integer recommendId)throws BusinessException;
   
   public void updateUserInfoAndSetParentCardNum(MUserInfoModel model,Integer recommendId)throws BusinessException;
   
}
