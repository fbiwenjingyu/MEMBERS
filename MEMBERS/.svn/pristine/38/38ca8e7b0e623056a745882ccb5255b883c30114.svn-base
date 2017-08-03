package com.daboo.reward.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.MD5Encoder;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.reward.mapper.MDayPointDetailMapper;
import com.daboo.reward.mapper.MDayReportMapper;
import com.daboo.reward.mapper.MPointTaskMapper;
import com.daboo.reward.mapper.MUserAccountInoutMapper;
import com.daboo.reward.model.MDayPointDetailModel;
import com.daboo.reward.model.MDayReportModel;
import com.daboo.reward.model.MUserAccountInoutModel;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.model.MUserInfoModel;

@Service
public class MUserAccountServiceImpl implements MUserAccountService {
	
	Logger log=LoggerFactory.getLogger(MUserAccountServiceImpl.class);

	@Autowired
	private MUserInfoMapper mUserInfoMapper;
	
	@Autowired
	private MUserAccountInoutMapper accountInoutMapper;
	
	@Autowired 
	private MPointTaskMapper pointTaskMapper;
	
	@Autowired
	private MemberRewardService memberRewardService;
	
	@Autowired
	private MDayReportMapper dayReportMapper;
	
	@Autowired
	private MDayPointDetailMapper dayPointDetailMapper;
	
	/**
	 * 根据账户查询用户资产信息
	 * @FunName findUserAccountByAccountNo
	 * @param accountNo 用户账户
	 * @return balance:余额; shoppingScore:购物积分; generalizeScore:推广积分
	 * @author dingyang
	 * @Create Date 2015年10月30日
	 */
	public MUserInfoModel findUserAccountByAccountNo(String accountNo) {
		log.info("查询账户为"+accountNo+"的资产信息");
		MUserInfoModel model=null;
		try {
			model=mUserInfoMapper.findUserAccountByAccountNo(accountNo);
			if(model!=null){
				return model;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询账户为"+accountNo+"的资产信息发生异常",e);
		}
		return null;
	}
	
	/**
	 * 分页查询资产流水信息
	 * @FunName findListByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年10月30日
	 */
	public GridDataModel findListByConditions(PageObject po) {
		
		RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserAccountInoutModel> rows=accountInoutMapper.findListByConditions(po.getCondition(),rb);
		int count=accountInoutMapper.countListByConditions(po.getCondition());
		
		return new GridDataModel(rows, count);
	}
	
	
	/**
	 * 根据条件查询会员流水记录
	 * @FunName selectFlowByConditions
	 * @param PageObject po
	 * @return GridDataModel
	 * @author dingyang
	 * @Create Date 2015年11月24日
	 */
	public GridDataModel selectFlowByConditions(PageObject po) {
		try {
			RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
			List<Map<String, Object>> rows=accountInoutMapper.selectFlowByConditions(po.getCondition(),rb);
			int count=accountInoutMapper.countFlowByConditions(po.getCondition());
			return new GridDataModel(rows, count);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据条件查询会员流水记录异常",e);
		}
		return null;
	}
	
	
	/**
	 * 根据条件查询会员账户记录
	 * @FunName selectAccountByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月24日
	 */
	public GridDataModel selectAccountByConditions(PageObject po) {
		try {
			RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
			List<Map<String, Object>> rows=accountInoutMapper.selectAccountByConditions(po.getCondition(),rb);
			int count=accountInoutMapper.countAccountByConditions(po.getCondition());
			return new GridDataModel(rows, count);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据条件查询会员账户记录异常",e);
		}
		return null;
	}
	
	/**
	 * 分页查询系统收支信息列表
	 * @FunName selectInOutByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月9日
	 */
	public GridDataModel selectInOutByConditions(PageObject po) {
		
		try {
			RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
			List<MDayReportModel> rows=dayReportMapper.selectInOutByConditions(po.getCondition(),rb);
			int count=dayReportMapper.countInOutByConditions(po.getCondition());
			return new GridDataModel(rows, count);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据条件查询系统收支信息异常",e);
		}
		return null;
	}
	
	/**
	 * 分页查询系统收支详情信息列表
	 * @FunName selectInOutDetailByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月9日
	 */
	public GridDataModel selectInOutDetailByConditions(PageObject po) {
		try {
			RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
			List<MDayPointDetailModel> rows=dayPointDetailMapper.findByConditions(po.getCondition(),rb);
			int count=dayPointDetailMapper.countByConditions(po.getCondition());
			return new GridDataModel(rows, count);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据条件查询系统收支信息异常",e);
		}
		return null;
	}
	
	/**
	 * 分页获取账户累计流水信息列表
	 * @FunName findDayPointDetailByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年12月18日
	 */
	public GridDataModel findDayPointDetailByConditions(PageObject po) {
		try {
			RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
			List<MDayPointDetailModel> rows=dayPointDetailMapper.findDayPointDetailByUserId(po.getCondition(),rb);
			int count=dayPointDetailMapper.countDayPointDetailByUserId(po.getCondition());
			return new GridDataModel(rows, count);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页获取账户累计流水信息列表异常",e);
		}
		return null;
	}
	
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
	@Transactional(rollbackFor=BusinessException.class)
	public boolean doBuyAgain(String password, Integer id, Integer buyNum,Integer type, String currentUserName) throws BusinessException {
		log.info("执行再次购买操作");
		
		String ckpwd=checkPayPwd(id, password);
		if(!"ok".equals(ckpwd)){//支付密码校验
			throw new BusinessException(ckpwd);
		}
		
		try {
			memberRewardService.userBuyAgain(id, buyNum, type, currentUserName);
		} catch (BusinessException e) {
			e.printStackTrace();
			log.error("执行再次购买操作异常",e);
			if(e instanceof BusinessException){
				throw new BusinessException(e.getMessage());
			}else {
				throw new BusinessException("再次购买操作接口异常");
			}
		}
		return true;
	}
	
	/**
	 * 检查用户密码正确性
	 * @FunName checkPayPwd
	 * @param userId
	 * @param pwd
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月3日
	 */
	public String checkPayPwd(int userId,String pwd){
		MUserInfoModel user=mUserInfoMapper.getObjectById(userId);
		if(user==null){
			return "用户不存在";
		}else if(user.getPayPassword()==null){
			return "密码为空";
		}else if(!user.getPayPassword().equals(MD5Encoder.encode(pwd))){
			return "密码错误";
		}else{
			return "ok";
		}
	}


}
