package com.daboo.message.service;

import java.util.Map;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.message.model.MNoticeModel;

/**
 * 
  * @ClassName: MNoticeService
  * @Description: 公告
  * @author 马正正
  * @date 2015年10月21日
 */
@RemoteService
public interface MNoticeService {
	/**
	 * 
	  * @Description: 新增公告
	  * @param notice
	  * @param content
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public void add(MNoticeModel notice,String content) throws BusinessException;
	
	/**
	 * 
	  * @Description: 更新公告
	  * @param notice
	  * @param content
	  * @param isTop
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public void update(MNoticeModel notice,String content,boolean isTop) throws BusinessException;
	
	/**
	 * 
	  * @Description: 根据ID查询
	  * @param noticeId
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public Map<String, Object> findById(int noticeId) throws BusinessException;
	
	/**
	 * 
	  * @Description: 公告查询
	  * @param po
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public GridDataModel query(PageObject po) throws BusinessException;
}
