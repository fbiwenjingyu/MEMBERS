package com.daboo.reward.mapper;



import java.util.Date;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;

import com.daboo.reward.model.MPointTaskModel;


/**
 * MPointTaskDAO接口
 *
 * @author admin
 */
public interface MPointTaskMapper extends GenericMapper<MPointTaskModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	//生成每天需要赠送的博豆明细
	public int genPointTaskDetail(@Param("collectDate") Date collectDate);
	
	/**
	 * 生成对账数据
	 * @param collectDate
	 * @return
	 */
	public int genHlgConsumptionData(@Param("collectDate") Date collectDate);
	//------------------请在此添加自定义方法（结束）------------------

    
}
