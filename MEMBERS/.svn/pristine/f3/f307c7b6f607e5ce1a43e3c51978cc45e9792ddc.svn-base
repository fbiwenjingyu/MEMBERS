package com.daboo.user.mapper;



import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;

import com.daboo.user.model.MUserRegModel;


/**
 * MUserRegDAO接口
 *
 * @author admin
 */
public interface MUserRegMapper extends GenericMapper<MUserRegModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	  * @Description: 删除注册信息
	  * @param accountNo 账号
	  * @return
	  * @author 肖勇
	  * @date 2015-11-25
	 */
	public Integer deleteUserRegByAccountNo(@Param("accountNo")String accountNo);
	
	//------------------请在此添加自定义方法（结束）------------------

    
}
