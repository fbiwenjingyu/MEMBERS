package com.daboo.finance.service;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.finance.model.MTransferDetailModel;
import com.daboo.reward.model.MStarNodeModel;

@RemoteService
public interface MTransferDetailService {
	/**
	  * @Description: 分页获取转账记录
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	public GridDataModel findTransferList(PageObject po);
	
	/**
	  * @Description: 新增转账
	  * @param page
	  * @return
	  * @author 朱冲
	 * @throws BusinessException 
	  * @date 2015-10-22
	 */
	public void addTransfer(MTransferDetailModel model) throws BusinessException;
	
	
	public boolean checkAccount(Integer userId,Long transferAmount, Integer type) throws BusinessException;
	
	public MStarNodeModel getMStarNode(Integer userId);
	
	/**
	 * 检查现金币转账是否需求限制,true:需要，false：不需要，默认不需要
	 * @FunName cashCheckIsOpen
	 * @return
	 * @author dingyang
	 * @Create Date 2016年1月19日
	 */
	public boolean cashTransferCheckIsOpen();
}
