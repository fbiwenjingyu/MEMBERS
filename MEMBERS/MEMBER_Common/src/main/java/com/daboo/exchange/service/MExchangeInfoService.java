package com.daboo.exchange.service;


import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.exchange.model.MExchangeInfoModel;
/**
 * @category 云碟兑换信息管理
 * @author zhouRui
 */
@RemoteService
public interface MExchangeInfoService {
  public  Integer insertExchangeInfo(MExchangeInfoModel mode, String operatorNo) throws BusinessException;
  public  GridDataModel queryExchangeInfoByCondition(PageObject po)  ;
  public  int updateModelById(MExchangeInfoModel mode) throws  BusinessException ;
  /**
   * 查询我的兑换云蝶记录列表
   * @FunName queryMyExchangeList
   * @param PageObject
   * @return GridDataModel
   * @author dingyang
   * @Create Date 2015年12月2日
   */
  public GridDataModel queryMyExchangeList(PageObject po);
  
}
