package com.daboo.reward.mapper;



import java.util.Date;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;

import com.daboo.reward.model.MUserAccountInoutHlgModel;


/**
 * MUserAccountInoutHlgDAO接口
 *
 * @author admin
 */
public interface MUserAccountInoutHlgMapper extends GenericMapper<MUserAccountInoutHlgModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	
	public int updateHlgFlag(@Param("collectDate") Date collectDate);
	//------------------请在此添加自定义方法（结束）------------------

    
}
