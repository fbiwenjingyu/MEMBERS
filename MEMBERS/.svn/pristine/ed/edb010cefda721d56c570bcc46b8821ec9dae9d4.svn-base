package com.daboo.finance.mapper;



import java.util.List;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;

import com.daboo.finance.model.MDrawCashLogModel;


/**
 * FxDrawCashLogDAO接口
 *
 * @author admin
 */
public interface MDrawCashLogMapper extends GenericMapper<MDrawCashLogModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	
	public int insertModelReturnId(MDrawCashLogModel model);
	
	public List<MDrawCashLogModel> selectCashList(@Param("status")Integer status);
	
	public void updateModelByOrderId(MDrawCashLogModel model);
	//------------------请在此添加自定义方法（结束）------------------

    
}
