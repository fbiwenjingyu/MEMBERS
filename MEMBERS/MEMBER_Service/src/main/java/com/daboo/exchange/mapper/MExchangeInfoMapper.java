package com.daboo.exchange.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.session.RowBounds;

import com.daboo.core.exception.DBException;
import com.daboo.exchange.model.MExchangeInfoModel;


/**
 * MExchangeInfoDAO接口
 *
 * @author 周锐
 */
public interface MExchangeInfoMapper extends GenericMapper<MExchangeInfoModel,Integer>{
	 abstract  Integer  queryTotal(Map<String,Object> condtiton)throws DBException; 
	 abstract  List<MExchangeInfoModel>  queryList(Map<String,Object> condtiton , RowBounds rowBounds)throws DBException; 

    
}
