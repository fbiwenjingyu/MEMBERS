package com.daboo.business.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.daboo.business.model.MBusinessCenterModel;
import com.daboo.business.model.MBusinessUserModel;


/**
 * MBusinessCenterDAO接口
 *
 * @author admin
 */
public interface MBusinessCenterMapper extends GenericMapper<MBusinessCenterModel,Integer>{
	//------------------请在此添加自定义方法（开始）------------------

	List<MBusinessCenterModel> findByConditions(Map<String, Object> condition,
			RowBounds rb);

	int countByConditions(Map<String, Object> condition);
	int openToBC(MBusinessCenterModel model);
	
	int notOpenToBC(MBusinessCenterModel model);
	
	int activeBC(MBusinessCenterModel model);
	
	int freezeBC(MBusinessCenterModel model);
	
	
	
	/**
	 * 根据会员账号激活商务中心表状态
	 * @FunName activeBCByAccountNo
	 * @param accountNo
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月18日
	 */
	int activeBCByAccountNo(@Param("accountNo") String accountNo);
	
	/**
	 * 根据会员账号冻结商务中心表状态
	 * @FunName freezeBCByAccountNo
	 * @param accountNo
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月18日
	 */
	int freezeBCByAccountNo(@Param("accountNo") String accountNo);
		
	//更新用户表为激活的商务中心状态
	int updateFreezedToActive(@Param("accountNo") String accountNo);
	
	//开通普通会员为激活的商务中心
	int updateUserToBC(@Param("accountNo") String accountNo);
	
	//更新用户表为冻结的商务中心状态
	int updateBCToFreezed(@Param("accountNo") String accountNo);
	
	/**
	 * 查询商务中心会员列表
	 * @FunName findUserList
	 * @param accountNo
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月28日
	 */
	public List<MBusinessUserModel> findUserListForBC(@Param("accountNo") String accountNo);
	
	void deleteByAccountNo(@Param("accountNo") String accountNo);
	
	//------------------请在此添加自定义方法（结束）------------------


    
}
