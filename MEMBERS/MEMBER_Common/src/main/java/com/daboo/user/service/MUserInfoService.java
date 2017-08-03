package com.daboo.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.remoting.service.annotation.RemoteService;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.model.MUserRegModel;
/*
 * 会员相关服务
 */
@RemoteService
public interface MUserInfoService {
	

	/**
	  * @Description: 分页获取用户信息
	  * @param page
	  * @return
	  * @author 肖勇
	  * @date 2015-10-22
	 */
	public GridDataModel findUserInfoPaging(PageObject po);
	
	/**
	  * @Description: 冻结激活账号
	  * @param id
	  * @param type
	  * @throws BusinessException
	  * @author 肖勇
	  * @date 2015-10-26
	 */
	public void frozen(Integer id, Integer type) throws BusinessException;
	
	/**
	  * @Description: 新增会员
	  * @param model 会员model
	  * @throws BusinessException
	  * @author 肖勇
	  * @date 2015-10-27
	 */
	public void addMember(MUserInfoModel model) throws BusinessException;
	
	/**
	  * @Description: 检查会员是否存在
	  * @param accountNo 会员编号
	  * @return
	  * @throws Exception
	  * @author 肖勇
	  * @date 2015-10-27
	 */
	public boolean checkMember(String accountNo) throws Exception;
	
	/**
	  * @Description: 检查会员是否存在|商务中心
	  * @param code 会员编号
	  * @param type 1 会员  2商务中心
	  * @return
	  * @throws Exception
	  * @author 肖勇
	  * @date 2015-10-27
	 */
	public Integer checkRecommend(String code, int type) throws Exception;
	
	/**
	  * @Description: 会员是否已激活
	  * @param accountNo 会员编号
	  * @return
	  * @throws Exception
	  * @author 朱冲
	  * @date 2015-10-27
	 */
	public boolean checkRecommend(String accountNo) throws Exception;
	
	/**
	  * @Description: 根据Id获取对象
	  * @param id 用户Id
	  * @return
	  * @throws Exception
	  * @author 肖勇
	  * @date 2015-10-28
	 */
	public MUserInfoModel findModelById(Integer id);
	
	/**
	  * @Description: 修改会员信息
	  * @param model 用户model
	  * @throws Exception
	  * @author 肖勇
	  * @date 2015-10-28
	 */
	public void updateMember(MUserInfoModel model);
	
	/**
	  * @Description: 用户登录
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param accountNo
	  * @return
	  * @author 肖勇
	  * @date 2015-10-29
	 */
	public MUserInfoModel userLoginIn(String accountNo);
	
	
	public MUserInfoModel findUserByAccountNo(String accountNo);
	
	public int updateUserInfoById(MUserInfoModel model);

	/**
	  * @Description: 分页获取团队人员 后台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	public GridDataModel findTeam(PageObject po);

	/**
	  * @Description: 分页获取未开通人员 后台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	public GridDataModel findUnOpenUser(PageObject po);
	
	/**
	  * @Description: 分页获取已开通人员 前台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	public GridDataModel findOpenUserForVip(PageObject po);

	/**
	  * @Description: 分页获取未开通人员 前台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	public GridDataModel findUnOpenUserForVip(PageObject po);
	
	/**
	  * @Description: 分页获取团队信息 前台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	public GridDataModel findTeamMember(PageObject po);
	
	public int deleteUnOpenUser(Integer id) throws BusinessException;
	
	public MUserInfoModel findUserById(Integer id);
	
	/**
	 * 
	  * @Description: 根据会员编号查询会员
	  * @param memberNo
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public Map<String, Object> findUserRelaByMemberNo(String memberNo);
	
	/**
	 * 
	  * @Description: 根据会员ID查询会员
	  * @param memberId
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public Map<String, Object> findUserRelaById(Integer memberId);
	
	/**
	 * 
	  * @Description: 根据PID查询会员
	  * @param po
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年12月1日
	 */
	public GridDataModel findUserRelaByPidPage(PageObject po) throws BusinessException;
	
	/**
	 * 
	  * @Description: 根据PID查询会员
	  * @param pid
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public List<Map<String, Object>> findUserRelaByPid(Integer pid);
	
	/**
	  * @Description: 获取详细的用户信息
	  * @param id
	  * @return
	  * @author 肖勇
	  * @date 2015-10-30
	 */
	public MUserInfoModel getFullModel(Integer id);
	
	/**
	  * @Description: 修改登录或支付密码
	  * @return type 0 登录   1支付
	  * @return 0 失败  1成功  2支付密码和登录密码不能相同  3.原密码不正确，请检查
	  * @author 肖勇
	  * @date 2015-11-2
	 */
	public Integer updatePwd(Integer id, String oldPwd, String pwd, int type);
	
	/**
	  * @Description: 注册用户
	  * @return type 0 登录   1支付
	  * @return 0
	  * @author 肖勇
	  * @date 2015-11-2
	 */
	public void addMemberReg(MUserRegModel model) throws BusinessException;
	
	/**
	 * 分页获取会员购买记录方法
	 * @FunName findBuyListByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	public Object findBuyListByConditions(PageObject po);
	

	public MUserRegModel getUserRegModel(Integer id);
}
