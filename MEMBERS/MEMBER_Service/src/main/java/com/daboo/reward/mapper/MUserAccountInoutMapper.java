package com.daboo.reward.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.session.RowBounds;

import com.daboo.reward.model.MUserAccountInoutModel;


/**
 * MUserAccountInoutDAO接口
 *
 * @author admin
 */
public interface MUserAccountInoutMapper extends GenericMapper<MUserAccountInoutModel,Long>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	 * 一次插入多个用户账户变动 
	 * @param userAccountInoutModel
	 * @return
	 */
	public int insertUserAccountInout(MUserAccountInoutModel userAccountInoutModel);
	/**
	 * 分页查询
	 * @param Map
	 * @param rowBounds
	 * @return
	 */
	List<MUserAccountInoutModel> findByConditions(Map<String, Object> condition,RowBounds rb);
	/**
	 * 获取总数
	 * @param Map
	 * @return
	 */
	int countByConditions(Map<String, Object> condition);
	//------------------请在此添加自定义方法（结束）------------------
	public List<MUserAccountInoutModel> findListByConditions(Map<String, Object> condition, RowBounds rb);
	int countListByConditions(Map<String, Object> condition);
    
	/**
	 * 根据条件查询会员流水记录
	 * @FunName selectFlowByConditions
	 * @return List
	 * @author dingyang
	 * @Create Date 2015年11月24日
	 */
	public List<Map<String, Object>> selectFlowByConditions(Map<String, Object> condition,RowBounds rb);
	
	/**
	 * 统计查询会员流水总条数
	 * @FunName countFlowByConditions
	 * @param condition
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月24日
	 */
	public int countFlowByConditions(Map<String, Object> condition);
	
	public List<Map<String, Object>> selectAccountByConditions(Map<String, Object> condition,RowBounds rb);
	
	public int countAccountByConditions(Map<String, Object> condition);
}
