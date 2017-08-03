package com.daboo.reward.service;


import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.user.model.MUserInfoModel;

/**
 * 用户账户服务接口
 * 
 * @author dingyang
 * @version 1.0
 * @Create Date 2015年10月30日
 */
@RemoteService
public interface MUserAccountService {
	
	
	/**
	 * 根据账户查询用户资产信息
	 * @FunName findUserAccountByAccountNo
	 * @param accountNo 用户账户
	 * @return balance:余额; shoppingScore:购物积分; generalizeScore:推广积分
	 * @author dingyang
	 * @Create Date 2015年10月30日
	 */
	public MUserInfoModel findUserAccountByAccountNo(String accountNo);
	
	/**
	 * 根据条件查询会员流水记录
	 * @FunName selectFlowByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月24日
	 */
	public GridDataModel selectFlowByConditions(PageObject po);
	
	/**
	 * 根据条件查询会员账户记录
	 * @FunName selectAccountByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月24日
	 */
	public GridDataModel selectAccountByConditions(PageObject po);
	
	/**
	 * 分页查询资产流水信息
	 * @FunName findListByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月30日
	 */
	public GridDataModel findListByConditions(PageObject po);

	/**
	 * 再次购买操作
	 * @FunName doBuyAgain
	 * @param password
	 * @param id
	 * @param buyNum
	 * @param type
	 * @param currentUserName
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	public boolean doBuyAgain(String password, Integer id, Integer buyNum,
			Integer type, String currentUserName) throws BusinessException;

	/**
	 * 分页查询系统收支信息列表
	 * @FunName selectInOutByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月9日
	 */
	public GridDataModel selectInOutByConditions(PageObject po);

	/**
	 * 分页查询系统收支详情信息列表
	 * @FunName selectInOutDetailByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月9日
	 */
	public GridDataModel selectInOutDetailByConditions(PageObject po);

	/**
	 * 分页获取账户累计流水信息列表
	 * @FunName findDayPointDetailByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月18日
	 */
	public GridDataModel findDayPointDetailByConditions(PageObject po);
	
}
