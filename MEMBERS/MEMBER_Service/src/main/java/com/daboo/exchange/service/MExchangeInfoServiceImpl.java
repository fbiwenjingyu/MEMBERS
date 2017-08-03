package com.daboo.exchange.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daboo.core.exception.BusinessException;
import com.daboo.entity.page.GridDataModel;
import com.daboo.entity.page.PageObject;
import com.daboo.exchange.mapper.MExchangeInfoMapper;
import com.daboo.exchange.model.MExchangeInfoModel;
import com.daboo.reward.service.MemberRewardService;
import com.daboo.reward.service.MemberRewardServiceImpl;
import com.daboo.system.service.SysDictService;
/**
 * @category 云碟兑换信息管理
 * @author zhouRui
 */
@Service
public class MExchangeInfoServiceImpl implements MExchangeInfoService {
	@Autowired
	private MExchangeInfoMapper mExchangeInfoMapper;
	@Autowired
	private MemberRewardService memberRewardService;
	@Autowired
	private SysDictService sysDictService;
	/**
	 * @category 生成兑换记录
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public Integer insertExchangeInfo(MExchangeInfoModel mode, String operatorNo) throws BusinessException {
		try {
			int exNumber=mode.getExchangeNum();//兑换数量
			
			//这里需求根据类型调用不用的接口
			if(mode.getGoodType().equals(1)){//1：云蝶
				
				int exAmount=Integer.parseInt(sysDictService.getDetailValue("EXCHANGE_AMOUNT","EXCHANGE_AMOUNT"));
				int amount=exNumber*exAmount;//兑换所需的总金额
				
				//返回消费的博豆
				Integer beans=memberRewardService.saveAdvances(mode.getUserId(), amount, operatorNo);
				if(beans==null){
					throw new BusinessException("调用扣除预支博豆接口返回Null");
				}
				
				int overBeans=amount-beans.intValue();
				mode.setOverdraftBeans(overBeans);///消费的透支博豆
				mode.setBeans(beans);//博豆
			}else if(mode.getGoodType().equals(2)){//，2：安防套装',
				//兑换一台智家套装所需现金
				int cash=Integer.parseInt(sysDictService.getDetailValue("EXCHANGE_ZHIJIA","CASH"));
				//兑换一台智家套装所需积分
				int jifen=Integer.parseInt(sysDictService.getDetailValue("EXCHANGE_ZHIJIA","JIFEN"));
				
				int totalBeans=jifen*exNumber;//兑换所需的总积分
				//返回消费的博豆
				Integer beans=memberRewardService.exchangeSmartHome(mode.getUserId(), (long) (cash*exNumber*100), totalBeans, operatorNo);
				int overBeans=totalBeans-beans.intValue();
				mode.setOverdraftBeans(overBeans);///消费的透支博豆
				mode.setBeans(beans);//博豆
				mode.setCash(cash*exNumber*100);//消费现金
			}
			
			mExchangeInfoMapper.insertModel(mode);
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof BusinessException){
				throw (BusinessException)e;
			}else{
				throw new BusinessException("积分兑换物品操作异常");
			}
		}
		 return  mode.getId();
	}
	@Override
	public GridDataModel queryExchangeInfoByCondition(PageObject po) {
		int total = mExchangeInfoMapper.queryTotal(po.getCondition());
		List<MExchangeInfoModel> list = mExchangeInfoMapper.queryList(po.getCondition(), new RowBounds(po.getOffset(), po.getPageSize()));
		return  new GridDataModel(list,total);
	}
	@Override
	public int updateModelById(MExchangeInfoModel mode) throws BusinessException {
		
		if(mode.getState().equals(3)){//如果取消发货则返回消费的博豆
			//调用统一取消接口
			memberRewardService.cancelAdvances(mode.getId(), MemberRewardServiceImpl.SYSTEM_USER_NO);
			/*if(mode.getGoodType().equals(1)){//云蝶
				
			}else if(mode.getGoodType().equals(2)){//安防
				
			}*/
			
		}
		
		
		return mExchangeInfoMapper.updateModelById(mode);
	}

	/**
	   * 查询我的兑换云蝶记录列表
	   * @FunName queryMyExchangeList
	   * @param PageObject
	   * @return GridDataModel
	   * @author dingyang
	   * @Create Date 2015年12月2日
	   */
	  public GridDataModel queryMyExchangeList(PageObject po) {
		try {
			RowBounds rb=new RowBounds(po.getOffset(), po.getPageSize());
			int count=mExchangeInfoMapper.countByCondition(po.getCondition());
			List<MExchangeInfoModel> list = mExchangeInfoMapper.findModelsByCondition(po.getCondition(),rb);
			return new GridDataModel(list, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
