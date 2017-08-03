package com.daboo.finance.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotation.myibatis.GenericMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.daboo.finance.model.MTransferDetailModel;


/**
 * MTransferDetailDAO接口
 *
 * @author admin
 */
public interface MTransferDetailMapper extends GenericMapper<MTransferDetailModel,Integer>{

	//------------------请在此添加自定义方法（开始）------------------
	/**
	 * 
	  * @Description: 分页获取转账记录
	  * @param conditions
	  * @param rowBounds
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public List<MTransferDetailModel> findTransferListByConditions(Map<String, Object> conditions,RowBounds rowBounds);
	
	/**
	 * 
	  * @Description: 分页获取转账记录数量
	  * @param conditions
	  * @return
	  * @author 朱冲
	  * @date 2015年10月23日
	 */
	public int findTransferListCountByConditions(Map<String, Object> conditions);

	//检查现金币转账是否需求限制
	public String cashTransferCheckIsOpen(@Param("dictName") String dictName,@Param("detaileName")String detaileName);

    
	//------------------请在此添加自定义方法（结束）------------------
}
