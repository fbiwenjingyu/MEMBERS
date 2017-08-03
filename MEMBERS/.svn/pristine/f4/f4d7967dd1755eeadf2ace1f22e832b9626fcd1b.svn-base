package com.daboo.user.mapper;



import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.model.MUserRegModel;


/**
 * MUserInfoDAO接口
 *
 * @author admin
 */
public interface MUserInfoMapper extends GenericMapper<MUserInfoModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	
	/**
	  * @Description: 分页查询用户信息
	  * @param conditions
	  * @return
	  * @author 肖勇
	  * @date 2015-10-22
	 */
	public List<MUserInfoModel> findUserInfoPaging(Map<String, Object> conditions, RowBounds rowBounds);
	
	/**
	  * @Description: 分页的总条数
	  * @param conditions
	  * @return
	  * @author 肖勇
	  * @date 2015-10-22
	 */
	public int findUserInfoCount(Map<String, Object> conditions);
	/**
	  * @Description: 分页获取用户信息
	  * @param page
	  * @return  MUserInfoModel
	  * @author fengchao
	  * @date 2015-10-27
	 */
	public MUserInfoModel findUserById(@Param("id")Integer id);
	


	public int queryMedalNum(@Param("recommendId")Integer recommendId,@Param("level") int level);
	

	/**
	 * 
	  * @Description: 根据条件分页查询团队成员 后台
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public List<MUserInfoModel> findTeamByConditions(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 根据条件分页查询统计团队成员 后台
	  * @param conditions
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public int findTeamCountByConditions(Map<String, Object> conditions);
	
	/**
	 * 
	  * @Description: 根据条件分页查询待激活会员 后台
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public List<MUserRegModel> findUnOpenByConditions(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 根据条件分页查询统计待激活会员 后台
	  * @param conditions
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public int findUnOpenCountByConditions(Map<String, Object> conditions);
	
	/**
	 * 
	  * @Description: 根据条件分页查询待激活会员 前台
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public List<MUserRegModel> findUnOpenByConditionsForVip(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 根据条件分页查询统计待激活会员 前台
	  * @param conditions
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public int findUnOpenCountByConditionsForVip(Map<String, Object> conditions);
	
	/**
	 * 
	  * @Description: 根据条件分页查询已激活会员 前台
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public List<MUserInfoModel> findOpenByConditionsForVip(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 根据条件分页查询统计已激活会员 前台
	  * @param conditions
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public int findOpenCountByConditionsForVip(Map<String, Object> conditions);
	
	/**
	 * 
	  * @Description: 分页获取团队信息 前台
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public List<MUserRegModel> findTeamMemberByConditionsForVip(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 分页获取团队信息数量 前台
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public int findTeamMemberCountByConditionsForVip(Map<String, Object> conditions);
	/**
	  * @Description: TODO
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @return
	  * @author 肖勇
	  * @date 2015-10-28
	 */
	public int updateAreaById(Integer id);
	
	
	/**
	 * 
	  * @Description: 根据会员编号查询会员
	  * @param memberNo
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public Map<String, Object> findUserRelaByMemberNo(String memberNo);
	
	/**
	 * 
	  * @Description: 根据会员ID查询会员
	  * @param memberId
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public Map<String, Object> findUserRelaById(Integer memberId);
	
	/**
	 * 
	  * @Description: 根据PID查询会员
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 马正正
	  * @date 2015年12月1日
	 */
	public List<Map<String, Object>> findUserRelaByPidPage(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 根据PID查询会员
	  * @param conditions
	  * @return
	  * @author 马正正
	  * @date 2015年12月1日
	 */
	public int findUserRelaByPidCount(Map<String, Object> conditions);
	
	/**
	 * 
	  * @Description: 根据PID查询会员
	  * @param pid
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public List<Map<String, Object>> findUserRelaByPid(Integer pid);

	
	/**
	  * @Description: 获取用户详细信息
	  * @param id
	  * @return
	  * @author 肖勇
	  * @date 2015-10-30
	 */
	public MUserInfoModel getFullModel(Integer id);


	
	
	/**
	 * 根据账户查询用户资产信息
	 * @FunName findUserAccountByAccountNo
	 * @param accountNo 用户账户
	 * @return balance:余额; shoppingScore:购物积分; generalizeScore:推广积分
	 * @author dingyang
	 * @Create Date 2015年10月30日
	 */
	public MUserInfoModel findUserAccountByAccountNo(@Param("accountNo")String accountNo);
	
	/**
	  * @Description: 根据accountNo查询已激活会员
	  * @param id
	  * @return
	  * @author 朱冲
	  * @date 2015-10-30
	 */
	public int checkRecommendCount(String accountNo);
	
	/**
	 * 查询需要奖励的用户ID
	 * @param collectDate
	 * @param awardFlag
	 * @return
	 */
	public List<Integer> findUserIdForAward(@Param("collectDate")Date collectDate,@Param("awardFlag")Integer awardFlag);
	
	
	public List<MUserInfoModel> getUserInfoForAward(@Param("userIds") String userIds,@Param("types")String types);
	/**
	 * 修改用户奖励标记
	 * @param id
	 * @param awardFlag
	 * @return
	 */
	public int updateAwardFlagByUserId(@Param("id")Integer id,@Param("awardFlag")Integer awardFlag);
	
	
	/**
	 * 获取正常状态的用户ID
	 * @return
	 */
	public String findUserIdsByIdAndTypes(@Param("userIds") String userIds,@Param("types")String types);
	
	/**
	 * 修改团队业绩
	 * @param parentStr
	 * @param teamMoney
	 * @return
	 */
	public int updateTeamMoney(@Param("parentStr") String parentStr,@Param("teamMoney")Integer teamMoney);
	 
	/**
	 * 修改个人业绩
	 * @param id
	 * @param myMoney
	 * @return
	 */
	public int updateMyMoney(@Param("id") Integer id,@Param("myMoney")Integer myMoney);
	
	
	/**
	 * 减少个人业绩
	 * @param id
	 * @param myMoney
	 * @return
	 */
	public int reduceMyMoney(@Param("id") Integer id,@Param("myMoney")Integer myMoney);
	
	
	/**
	 * 减少团队业绩
	 * @param parentStr
	 * @param teamMoney
	 * @return
	 */
	public int reduceTeamMoney(@Param("parentStr") String parentStr,@Param("teamMoney")Integer teamMoney);
	
	/**
	 * 获取会员购买记录
	 * @FunName findBuyListByConditions
	 * @param condition
	 * @param rb
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	public List<MUserInfoModel> findBuyListByConditions(Map<String, Object> condition, RowBounds rb);
	
	/**
	 * 获取会员购买记录总条数
	 * @FunName countBuyListByConditions
	 * @param condition
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	public int countBuyListByConditions(Map<String, Object> condition);
	
	/**
	 * 修改主账户用户总数
	 * @param id
	 * @param totalNum
	 * @return
	 */
	public int updateMasterUserInfoTotalNum(@Param("id") Integer id,@Param("totalNum")Integer totalNum);
	//------------------请在此添加自定义方法（结束）------------------

    
}
