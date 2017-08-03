package com.daboo.reward.mapper;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.daboo.reward.model.MDayPointDetailModel;


/**
 * MDayPointDetailDAO接口
 *
 * @author admin
 */
public interface MDayPointDetailMapper extends GenericMapper<MDayPointDetailModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	public int updateDayPointDetailForSend(MDayPointDetailModel dayPointDetailModel);
	
	/**
	 * 获取统计日期
	 * @param dayPointDetailModel
	 */
	public void getCollectDate(MDayPointDetailModel dayPointDetailModel);
	
	/**
	 * 生成用户日数据（个人业绩,团队业绩）
	 * @param collectDate
	 */
	public void genDayPointDetailAndReport(@Param("collectDate") Date collectDate);
	/**
	 * 查询数据未管理奖励准备
	 * @param dayPointDetailModel
	 * @param rowBounds
	 * @return
	 */
	public List<MDayPointDetailModel> findDayPointDetailForAward(MDayPointDetailModel dayPointDetailModel,RowBounds rowBounds);
	
	
	/**
	 * 查找满足 直推奖 和 管理奖 大于 0 的所有数据
	 * @param dayPointDetailModel
	 * @return
	 */
	public List<MDayPointDetailModel> findDayPointDetailForCoachAwards(MDayPointDetailModel dayPointDetailModel);
	/**
	 * 分页查询
	 * @param Map
	 * @param rowBounds
	 * @return
	 */
	List<MDayPointDetailModel> findByConditions(Map<String, Object> condition,RowBounds rb);
	/**
	 * 获取总数
	 * @param Map
	 * @return
	 */
	int countByConditions(Map<String, Object> condition);
	
	/**
	 * 修改日报表状态
	 * @param newStatus
	 * @param oldStatus
	 * @param collectDate
	 * @return
	 */
	int updateDayPointDetailByState(@Param("newStatus")Integer newStatus,@Param("oldStatus")Integer oldStatus,@Param("collectDate")Date collectDate);
	
	
	/**
	 * 查询需要调整会员等级的数据
	 * @param collectDate
	 * @param level
	 * @param pointAmount
	 * @return
	 */
	public List<MDayPointDetailModel> getDayPointDetailForLevelSetting(@Param("collectDate")Date collectDate,@Param("level")Integer level,@Param("pointAmount")BigDecimal pointAmount);

	/**
	 * 根据用户ID查询累计奖励流水
	 * @FunName findDayPointDetailByUserId
	 * @param condition
	 * @param rb
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月18日
	 */
	public List<MDayPointDetailModel> findDayPointDetailByUserId(Map<String, Object> condition, RowBounds rb);

	public int countDayPointDetailByUserId(Map<String, Object> condition);
	
	//------------------请在此添加自定义方法（结束）------------------
    
}
