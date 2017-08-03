package com.daboo.reward.mapper;



import java.util.List;

import org.apache.ibatis.annotation.myibatis.GenericMapper;

import com.daboo.reward.model.MStarNodeModel;


/**
 * MStarNodeDAO接口
 *
 * @author admin
 */
public interface MStarNodeMapper extends GenericMapper<MStarNodeModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	 * 修改用户的直推人数 
	 * @param userId
	 * @return
	 */
	public int updateRecommendNumsById(Integer userId);
	
	/**
	 * 减少用户推荐人数--调整节点使用
	 * @param userId
	 * @return
	 */
	public int reduceRecommendNumsById(Integer userId);
	/**
	 * 修改用户的团队人数 
	 * @param userId
	 * @return
	 */
	//public int updateTeamNums(@Param("parentStr")String parentStr);
	
	
	/**
	 * 查询需要赠送推广积分的用户
	 * @param starNodeModel
	 * @return
	 */
	List<MStarNodeModel> findUserForSendPoint(MStarNodeModel starNodeModel);
	
	
	/**
	 * 查找所有末级节点
	 * @return
	 */
	//List<MStarNodeModel> findAllLastStageNode();
	//------------------请在此添加自定义方法（结束）------------------

    
}
