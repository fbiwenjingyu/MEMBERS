package com.daboo.finance.service;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;

@RemoteService
public interface MCurrencyConvertService {

	/**
	 * 分页查询货币转换记录
	 * @FunName findListByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月2日
	 */
	public  GridDataModel findListByConditions(PageObject po);

	/**
	 * 货币转换[博豆转积分]
	 * @FunName doConvert
	 * @param userId 用户ID
	 * @param accountNo 用户账号
	 * @param convertType 转换类型
	 * @param amount 转换数值
	 * @param password 密码
	 * @return boolean
	 * @author dingyang
	 * @Create Date 2015年11月3日
	 */
	public boolean doConvert(Integer userId, String accountNo,String convertType, int amount,
			String password,String operatorNo) throws BusinessException;

}
