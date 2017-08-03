package com.daboo.message.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.message.mapper.MNoticeContentMapper;
import com.daboo.message.mapper.MNoticeMapper;
import com.daboo.message.model.MNoticeContentModel;
import com.daboo.message.model.MNoticeModel;


/**
 * 
  * @ClassName: MNoticeServiceImpl
  * @Description: 公告
  * @author 马正正
  * @date 2015年10月21日
 */
@Service
@Transactional
public class MNoticeServiceImpl implements MNoticeService {
	private Log log=LogFactory.getLog(getClass());
	@Autowired
	private MNoticeMapper mNoticeMapper;
	@Autowired
	private MNoticeContentMapper mNoticeContentMapper;
	
	/**
	 * 
	  * @Description: 新增公告
	  * @param notice
	  * @param content
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public void add(MNoticeModel notice,String content) throws BusinessException {
		try{
			notice.setId(null);
			notice.setDeployTime(new Date());
			notice.setTopTime(notice.getDeployTime());
			notice.setIsDelete(0);
			mNoticeMapper.insertModel(notice);
			
			MNoticeContentModel nc=new MNoticeContentModel();
			nc.setContent(content);
			nc.setNoticeId(notice.getId());
			mNoticeContentMapper.insertModel(nc);
		} catch(Exception e){
			log.error("新增公告异常",e);
			throw new BusinessException("新增公告异常");
		}
	}
	
	/**
	 * 
	  * @Description: 更新公告
	  * @param notice
	  * @param content
	  * @param isTop
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public void update(MNoticeModel notice,String content,boolean isTop) throws BusinessException {
		try{
			if(notice.getIsDelete()==null || notice.getIsDelete()==0){
				notice.setTopTime(new Date());
				if(notice.getIsTop()==null || notice.getIsTop()==0){
					notice.setDeployTime(notice.getTopTime());
				}
				mNoticeMapper.updateModelById(notice);
				
				MNoticeContentModel nc=new MNoticeContentModel();
				nc.setNoticeId(notice.getId());
				nc=mNoticeContentMapper.getObjectByModel(nc);
				nc.setContent(content);
				mNoticeContentMapper.updateModelById(nc);
			}else{
				notice.setIsDelete(1);
				mNoticeMapper.updateModelById(notice);
			}
		} catch(Exception e){
			log.error("更新公告异常",e);
			throw new BusinessException("更新公告异常");
		}
	}
	
	/**
	 * 
	  * @Description: 根据ID查询
	  * @param noticeId
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月28日
	 */
	public Map<String, Object> findById(int noticeId) throws BusinessException {
		try{
			return mNoticeMapper.findById(noticeId);
		} catch(Exception e){
			log.error("根据ID查询异常",e);
			throw new BusinessException("根据ID查询异常");
		}
	}
	
	/**
	 * 
	  * @Description: 公告查询
	  * @param po
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年10月26日
	 */
	public GridDataModel query(PageObject po) throws BusinessException {
		try{
			RowBounds rowBounds=new RowBounds(po.getOffset(), po.getPageSize());
			
			List<Map<String, Object>> rows=mNoticeMapper.findByCons(po.getCondition(), rowBounds);
			int total=mNoticeMapper.findByConsCount(po.getCondition());
			
			return new GridDataModel(rows, total);
		} catch(Exception e){
			log.error("查询提现单异常",e);
			throw new BusinessException("查询提现单异常");
		}
	}
}
