package com.daboo.user.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daboo.business.mapper.MBusinessCenterMapper;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.util.CommonUtil;
import com.daboo.core.util.ConfigProperty;
import com.daboo.core.util.MD5Encoder;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.system.service.SysDictService;
import com.daboo.user.mapper.MUserInfoMapper;
import com.daboo.user.mapper.MUserRegMapper;
import com.daboo.user.model.MUserInfoModel;
import com.daboo.user.model.MUserRegModel;
@Service
@Transactional
public class MUserInfoServiceImpl implements MUserInfoService {
	
	Logger log = LoggerFactory.getLogger(MUserInfoServiceImpl.class);
	@Autowired
	MUserInfoMapper mUserInfoMapper;
	@Autowired
	MUserRegMapper mUserRegMapper;
	@Autowired
	MBusinessCenterMapper mBusinessCenterMapper;
	@Autowired
	SysDictService sysDictService;
	/**
	  * @Description: 分页获取用户消息
	  * @param page
	  * @return 
	  * @author 肖勇
	  * @date 2015-10-22
	 */
	public GridDataModel findUserInfoPaging(PageObject po){
		//分页消息
		RowBounds rowBounds=new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserInfoModel> rows = mUserInfoMapper.findUserInfoPaging(po.getCondition(), rowBounds);
		int total = mUserInfoMapper.findUserInfoCount(po.getCondition());
		return new GridDataModel(rows, total);
	}
	
	/**
	  * @Description: 冻结激活账号
	  * @param id
	  * @param type
	  * @throws BusinessException
	  * @author 肖勇
	  * @date 2015-10-26
	 */
	public void frozen(final Integer id, Integer type) throws BusinessException{
		try{
			MUserInfoModel model = new MUserInfoModel();
			model.setId(id);
			model.setType(type);
			mUserInfoMapper.updateModelById(model);
			model = mUserInfoMapper.getObjectById(id);
			if(type == 3){
				mBusinessCenterMapper.activeBCByAccountNo(model.getAccountNo());
			}else if(type == 2){
				mBusinessCenterMapper.freezeBCByAccountNo(model.getAccountNo());
			}
			
			//调用net注销接口
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					TreeMap<String,String> t = new TreeMap<String,String>();
					t.put("appkey", ConfigProperty.getProperty("donet.appkey"));
					t.put("thirdId", id.toString());
					try {
						CommonUtil.doPost(ConfigProperty.getProperty("donet.url")+"LogOut", t, 3000, 15000, null, ConfigProperty.getProperty("donet.secretKey"));
					} catch (IOException e) {
						log.error("冻结/解冻-调用net注销异常",e);
					}
				}
			}).start();
			
			
		}catch(Exception e){
			log.error("冻结/激活账号异常", e);
			throw new BusinessException("冻结/激活账号异常");
		}
	}
	
	
	/**
	  * @Description: 新增会员
	  * @param model 会员model
	  * @throws BusinessException
	  * @author 肖勇
	  * @date 2015-10-27
	 */
	public void addMember(MUserInfoModel model) throws BusinessException{
		try{
			model.setPassword(MD5Encoder.encode(model.getPassword()));
			model.setPayPassword(MD5Encoder.encode(model.getPayPassword()));
			model.setCreateTime(new Date());
			model.setUpdateTime(new Date());
			model.setIsDeleted(0); 
			//推荐人
			model.setCopperCardNum(0);
			model.setSilverCardNum(0);
			model.setType(0); //未激活的会员
			this.mUserInfoMapper.insertModel(model);
		}catch(Exception e){
			log.error("新增会员异常", e);
			throw new BusinessException("新增会员异常");
		}
	}
	
	/**
	  * @Description: 检查会员是否存在
	  * @param accountNo 会员编号
	  * @return 存在 true  不存在false
	  * @throws Exception
	  * @author 肖勇
	  * @date 2015-10-27
	 */
	public boolean checkMember(String accountNo) throws Exception{
		try{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("accountNo", accountNo);
			Integer num = mUserInfoMapper.countByCondition(paramMap);
			if(num > 0){
				return true;
			}else{
				TreeMap<String,String> t = new TreeMap<String,String>();
				t.put("appkey", ConfigProperty.getProperty("donet.appkey"));
				t.put("userName", accountNo);
				try {
					 String result = CommonUtil.doPost(ConfigProperty.getProperty("donet.url")+"IsUserExists", t, 3000, 15000, null, ConfigProperty.getProperty("donet.secretKey"));
					 JSONObject obj = JSON.parseObject(result);
					 if(obj.getInteger("value") == 1){
						 return true;
					 }
				} catch (IOException e) {
					log.error("调用.net 检查会员异常",e);
				}
			}
			return false;
		}catch(Exception e){
			log.error("检查会员是否存在异常", e);
			throw new BusinessException("检查会员是否存在异常");
		}
	}
	
	/**
	  * @Description: 检查推荐人是否已激活
	  * @param accountNo 会员编号
	  * @return 存在 true  不存在false
	  * @throws Exception
	  * @author zhuchong
	  * @date 2015-10-27
	 */
	public boolean checkRecommend(String recommendNo) throws Exception{
		try{
			Integer num = mUserInfoMapper.checkRecommendCount(recommendNo);
			if(num > 0){
				return true;
			}
			return false;
		}catch(Exception e){
			log.error("检查推荐人是否已激活异常", e);
			throw new BusinessException("检查推荐人是否已激活异常");
		}
	}
	
	/**
	  * @Description: 根据Id获取对象
	  * @param id 用户Id
	  * @return
	  * @throws Exception
	  * @author 肖勇
	  * @date 2015-10-28
	 */
	public MUserInfoModel findModelById(Integer id){
		
		return mUserInfoMapper.getObjectById(id);
	}
	
	
	/**
	  * @Description: 修改会员信息
	  * @param model 用户model
	  * @throws Exception
	  * @author 肖勇
	  * @date 2015-10-28
	 */
	public void updateMember(MUserInfoModel model){
		if(StringUtils.isNotBlank(model.getPassword())){
			model.setPassword(MD5Encoder.encode(model.getPassword()));
			final String id = model.getId().toString();
			//调用net注销接口
			new Thread(new Runnable() {
				@Override
				public void run() {
					TreeMap<String,String> t = new TreeMap<String,String>();
					t.put("appkey", ConfigProperty.getProperty("donet.appkey"));
					t.put("thirdId", id);
					try {
						CommonUtil.doPost(ConfigProperty.getProperty("donet.url")+"LogOut", t, 3000, 15000, null, ConfigProperty.getProperty("donet.secretKey"));
					} catch (IOException e) {
						log.error("修改密码-调用net注销异常",e);
					}
				}
			}).start();
		}
		if(StringUtils.isNotBlank(model.getPayPassword())){
			model.setPayPassword(MD5Encoder.encode(model.getPayPassword()));
		}
		this.mUserInfoMapper.updateModelById(model);
		if(StringUtils.isBlank(model.getArea())){
			this.mUserInfoMapper.updateAreaById(model.getId());
		}
	}


	/**
	  * @Description: 分页获取团队人员 后台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	@Override
	public GridDataModel findTeam(PageObject po) {
		RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserInfoModel> rows = mUserInfoMapper.findTeamByConditions(
				po.getCondition(), rowBounds);
		int total = mUserInfoMapper.findTeamCountByConditions(po.getCondition());
		return new GridDataModel(rows, total);
	}
	
	/**
	  * @Description: 分页获取未开通人员 后台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	@Override
	public GridDataModel findUnOpenUser(PageObject po) {
		RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserRegModel> rows = mUserInfoMapper.findUnOpenByConditions(po.getCondition(), rowBounds);
		int total = mUserInfoMapper.findUnOpenCountByConditions(po.getCondition());
		return new GridDataModel(rows, total);
	}
	
	/**
	 * 通过账号查询用户信息
	 */
	public MUserInfoModel findUserByAccountNo(String accountNo) {
		if (StringUtils.isNotBlank(accountNo)) {
			MUserInfoModel model = new MUserInfoModel();
			model.setAccountNo(accountNo);
			model.setIsDeleted(0);
			return mUserInfoMapper.getObjectByModel(model);
		}
		return null;
	}

	@Override
	public int updateUserInfoById(MUserInfoModel model) {
		return mUserInfoMapper.updateModelById(model);
	}

	/**
	 * 删除未激活用户
	 */
	@Override
	public int deleteUnOpenUser(Integer id) throws BusinessException {
		return mUserRegMapper.deleteById(id);
	}

	@Override
	public MUserInfoModel findUserById(Integer id) {
		return mUserInfoMapper.getObjectById(id);
	}

	/**
	 * 
	  * @Description: 根据会员编号查询会员
	  * @param memberNo
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public Map<String, Object> findUserRelaByMemberNo(String memberNo){
		return mUserInfoMapper.findUserRelaByMemberNo(memberNo);
	}
	
	/**
	 * 
	  * @Description: 根据会员ID查询会员
	  * @param memberId
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public Map<String, Object> findUserRelaById(Integer memberId){
		return mUserInfoMapper.findUserRelaById(memberId);
	}
	
	/**
	 * 
	  * @Description: 根据PID查询会员
	  * @param po
	  * @return
	  * @throws BusinessException
	  * @author 马正正
	  * @date 2015年12月1日
	 */
	public GridDataModel findUserRelaByPidPage(PageObject po) throws BusinessException {
		try{
			RowBounds rowBounds=new RowBounds(po.getOffset(), po.getPageSize());
			
			List<Map<String, Object>> rows=mUserInfoMapper.findUserRelaByPidPage(po.getCondition(), rowBounds);
			int total=mUserInfoMapper.findUserRelaByPidCount(po.getCondition());
			
			return new GridDataModel(rows, total);
		} catch(Exception e){
			log.error("查询提现单异常",e);
			throw new BusinessException("查询提现单异常");
		}
	}
	
	/**
	 * 
	  * @Description: 根据PID查询会员
	  * @param pid
	  * @return
	  * @author 马正正
	  * @date 2015年10月29日
	 */
	public List<Map<String, Object>> findUserRelaByPid(Integer pid){
		return mUserInfoMapper.findUserRelaByPid(pid);
	}
	
	/**
	  * @Description: 用户登录
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param accountNo 账号
	  * @return
	  * @author 肖勇
	  * @date 2015-10-29
	 */
	public MUserInfoModel userLoginIn(String accountNo){
		MUserInfoModel model = new MUserInfoModel();
		model.setAccountNo(accountNo);
		return this.mUserInfoMapper.getObjectByModel(model);
	}
	
	/**
	  * @Description: 获取详细的用户信息
	  * @param id
	  * @return
	  * @author 肖勇
	  * @date 2015-10-30
	 */
	public MUserInfoModel getFullModel(Integer id){
		return this.mUserInfoMapper.getFullModel(id);
	}
	
	/**
	  * @Description: 修改登录或支付密码
	  * @return type 0 登录   1支付
	  * @return  1成功  2支付密码和登录密码不能相同  3.原密码不正确，请检查
	  * @author 肖勇
	  * @date 2015-11-2
	 */
	public Integer updatePwd(final Integer id, String oldPwd, String pwd, int type){
		
		MUserInfoModel model = this.mUserInfoMapper.getObjectById(id);
		oldPwd = MD5Encoder.encode(oldPwd);
		if(type == 0){
			if(model.getPassword().equals(oldPwd)){
				//修改密码
				pwd = MD5Encoder.encode(pwd);
				model = new MUserInfoModel();
				model.setId(id);
				model.setPassword(pwd);
				this.mUserInfoMapper.updateModelById(model);
				//调用net注销接口
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						TreeMap<String,String> t = new TreeMap<String,String>();
						t.put("appkey", ConfigProperty.getProperty("donet.appkey"));
						t.put("thirdId", id.toString());
						try {
							CommonUtil.doPost(ConfigProperty.getProperty("donet.url")+"LogOut", t, 3000, 15000, null, ConfigProperty.getProperty("donet.secretKey"));
						} catch (IOException e) {
							log.error("冻结/解冻-调用net注销异常",e);
						}
					}
				}).start();
				return 1;
			}else{
				return 3;
			}
		}else{
			if(model.getPayPassword().equals(oldPwd)){
				//修改支付密码
				model.setPayPassword(pwd);
				model = new MUserInfoModel();
				model.setId(id);
				pwd = MD5Encoder.encode(pwd);
				model.setPayPassword(pwd);
				this.mUserInfoMapper.updateModelById(model);
				return 1;
			}else{
				return 3;
			}
		}
	}

	/**
	  * @Description: 分页获取已开通人员 前台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	@Override
	public GridDataModel findOpenUserForVip(PageObject po) {
		RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserInfoModel> rows = mUserInfoMapper.findOpenByConditionsForVip(
				po.getCondition(), rowBounds);
		int total = mUserInfoMapper.findOpenCountByConditionsForVip(po.getCondition());
		return new GridDataModel(rows, total);
	}

	/**
	  * @Description: 分页获取未开通人员 前台
	  * @param page
	  * @return
	  * @author 朱冲
	  * @date 2015-10-22
	 */
	@Override
	public GridDataModel findUnOpenUserForVip(PageObject po) {
		RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserRegModel> rows = mUserInfoMapper.findUnOpenByConditionsForVip(po.getCondition(), rowBounds);
		int total = mUserInfoMapper.findUnOpenCountByConditionsForVip(po.getCondition());
		return new GridDataModel(rows, total);
	}

	@Override
	public GridDataModel findTeamMember(PageObject po) {
		RowBounds rowBounds = new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserRegModel> rows = mUserInfoMapper.findTeamMemberByConditionsForVip(
				po.getCondition(), rowBounds);
		int total = mUserInfoMapper.findTeamMemberCountByConditionsForVip(po.getCondition());
		return new GridDataModel(rows, total);
	}
	
	/**
	  * @Description: 检查会员是否存在|商务中心
	  * @param code 会员编号
	  * @param type 1 会员  2商务中心
	  * @return
	  * @throws Exception
	  * @author 肖勇
	  * @date 2015-10-27
	 */
	public Integer checkRecommend(String code, int type) throws Exception{
		try{
			MUserInfoModel model = new MUserInfoModel();
			model.setAccountNo(code);
			model = mUserInfoMapper.getObjectByModel(model);
			if(model != null){
				if(type == 1){
					if(model.getType() == 1 || model.getType() == 3){
						return model.getId();
					}
				}else{
					if(model.getType() == 3){
						return model.getId();
					}
				}
			}
		}catch(Exception e){
			log.error("检查会员是否存在|商务中心异常", e);
		}
		//不存在
		return -2;
	}
	
	/**
	  * @Description: 推荐会员
	  * @param model 会员model
	  * @throws BusinessException
	  * @author 肖勇
	  * @date 2015-11-25
	 */
	public void addMemberReg(MUserRegModel model) throws BusinessException{
		try{
			//去掉空格
			model.setAccountNo(model.getAccountNo().trim());
			if(model.getRegNum() == null || model.getRegNum() < 1){
				model.setRegNum(1);
			}
			String rechargeAmountValue = sysDictService.getDetailValue("ACTIVATION_USER_AMOUNT","ACTIVATION_USER_AMOUNT");
			model.setPassword(MD5Encoder.encode(model.getPassword()));
			model.setPayPassword(MD5Encoder.encode(model.getPayPassword()));
			model.setCreateTime(new Date()); 
			model.setLevel(0);
			model.setRegisterMoney(Integer.parseInt(rechargeAmountValue)*model.getRegNum());
			this.mUserRegMapper.insertModel(model);
		}catch(Exception e){
			log.error("新增会员异常", e);
			throw new BusinessException("新增会员异常");
		}
	}
	
	/**
	 * 分页获取会员购买记录方法
	 * @FunName findBuyListByConditions
	 * @param po
	 * @return
	 * @author dingyang
	 * @Create Date 2015年11月25日
	 */
	public GridDataModel findBuyListByConditions(PageObject po) {
		
		RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
		List<MUserInfoModel> rows=mUserInfoMapper.findBuyListByConditions(po.getCondition(),rb);
		int count=mUserInfoMapper.countBuyListByConditions(po.getCondition());
		
		return new GridDataModel(rows, count);
	}

	@Override
	public MUserRegModel getUserRegModel(Integer id) {
		return mUserRegMapper.getObjectById(id);
	}
}
