package com.daboo.finance.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import com.alibaba.fastjson.JSON;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.exception.DrawCashException;
import com.daboo.core.util.CommonUtil;
import com.daboo.core.util.ConfigProperty;
import com.daboo.finance.enums.MDrawLogStatus;
import com.daboo.finance.enums.MDrawStatus;
import com.daboo.finance.mapper.MDrawCashLogMapper;
import com.daboo.finance.model.MDrawCashLogModel;
import com.daboo.finance.model.MDrawModel;
import com.daboo.finance.model.MDrawResultModel;
import com.daboo.finance.service.MDrawService;
/**
 * 定时任务，验证提现是成功还是失败
 * @author fengchao
 *
 */
@Repository("mVerifyDrawCashSuccessTask")
public class MVerifyDrawCashSuccessTask {
	
	private static final Logger LOG = LoggerFactory.getLogger(MVerifyDrawCashSuccessTask.class);
	@Autowired
	private MDrawCashLogMapper mDrawCashLogMapper;
	@Autowired
    private PlatformTransactionManager txManager ;
	@Autowired
	private MDrawService mDrawService;
	/**
	 * 
	  * @Description: 查询提现是否成功
	  * @return			status:状态
	  * @throws BusinessException
	  * @author FengChao
	  * @date 2015年8月3日
	 */
	public void selectDrawCashSuccess(){
		
		//查询提现记录表中已成功发送提现请求的记录
		List<MDrawCashLogModel> logList = mDrawCashLogMapper.selectCashList(MDrawLogStatus.SUCCESS_SEND);
		if(!logList.isEmpty()){
           for(MDrawCashLogModel logModel : logList){
        	   try {
					//执行查询提现结果的操作
        		   verifyDrawCashSuccess(logModel.getCashId(),logModel.getOrderId(),logModel.getId(),logModel.getUserId(),logModel.getAmount());
				}catch (DrawCashException e) {
				   LOG.error(e.getMessage()+",此次提现的订单id为:"+logModel.getOrderId());
				}
			}
        }
	
	}
	/**
	 * 
	  * @Description:查询提现是否成功
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param cashModel
	  * @author Feng Chao
	  * @date 2015-8-3
	 */
	public void verifyDrawCashSuccess(String requestId,Integer orderId,Integer logId,Integer userId,Integer amount)throws DrawCashException{
		
		MDrawResultModel model = null;
		long t = 0;
		boolean cashSuccess = true;
		MDrawCashLogModel logModel = null;
		try {
			try {
				t = System.currentTimeMillis();
				model = selectDrawCashSuccessByNet(requestId);
			} catch (Exception e) {
				LOG.info("调用.net查询提现接口失败，耗时："+String.valueOf(System.currentTimeMillis() - t));
				LOG.error("调用.net查询提现接口时异常",e);
			}
			if(model != null){
				LOG.error("调用.net接口查询是否提现成功时返回:"+model.getCode()+",查询id是:"+requestId);
				if(model.getCode().equals("-1") || model.getCode().equals("112") || model.getCode().equals("114")){
					cashSuccess = false;
			    //提现成功
				}else if(model.getCode().equals("111")){
					//修改提现表和记录表的状态
					String successMsg = "提现成功";
					mDrawService.changeStatus(orderId, MDrawStatus.DRAW_SUCC, successMsg, "system");
					logModel = new MDrawCashLogModel(logId,MDrawLogStatus.SUCCESS_CASH,new Date());
				}
			}else{
				LOG.error("调用.net接口查询是否提现成功时发生异常，查询失败");
			}
			//如果查询结果是提现失败，需要把钱退还给用户
			if(!cashSuccess){
				//将钱退还给用户
				String errMsg = StringUtils.isNotBlank(model.getFailureCause()) == true ? model.getFailureCause() + " | " 
						+ model.getAlertMsg() :model.getAlertMsg();		
				if(model.getCode().equals("-1")){
					mDrawService.changeStatus(orderId, MDrawStatus.DRAW_PAUS, errMsg, "system");
				}else{
					mDrawService.changeStatus(orderId, MDrawStatus.DRAW_FAIL, errMsg, "system");
				}
				logModel = new MDrawCashLogModel(logId,MDrawLogStatus.FAILED_CASH,new Date());
			}
			//执行修改提现表和提现记录表操作
			if(logModel != null )updateCashAndCashLogByOrderId(logModel);
		} catch (BusinessException e) {
			LOG.error("查询订单id为:"+orderId+"，提现请求id为:"+requestId+"的任务失败",e);
			throw new DrawCashException("查询提现失败");
		}
	}
	/**
	 * 
	  * @Description:调用.net方法，查询是否提现成功
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param cashModel
	  * @author Feng Chao
	  * @date 2015-8-3
	 */
	public MDrawResultModel selectDrawCashSuccessByNet(String orderId){
		
		 String url = "";
		 MDrawResultModel model = null;
		 url = ConfigProperty.getProperty("net_drawSuccess_url");
		 //发送请求（调用.net）
		 String json = CommonUtil.sendRequestToNet("{\"orderID\":\""+orderId+"\"}", url);
		 String code = String.valueOf(JSON.parseObject(json).get("Code"));
		 if(code.equals("200")){
		   model =  new MDrawResultModel();
		   String data = String.valueOf(JSON.parseObject(json).get("Data"));
		   if(!data.equals("null") && StringUtils.isNotBlank(data)){
			   String result =  JSON.parseObject(data).getString("Result");
			   if(StringUtils.isNotBlank(result)){
				   model  = JSON.parseObject(result,MDrawResultModel.class);
			   }else{
				   LOG.error("调用.net查询提现是否成功时返回空数据");
				   throw new RuntimeException("返回200，获取数据为空");
			   }
		   }else{
			   LOG.error("调用.net查询提现是否成功时返回空数据");
			   throw new RuntimeException("返回200，获取数据为空");
		   }
		 }else{
			 LOG.error("调用.net查询提现是否成功接口时返回非200");
		 }
		 return model;
	}

	/**
	 * 
	  * @Description: 同时修改提现表和提现记录表
	  * TODO 1. 记录表用订单id来修改
	  * @param cashModel  提现对象
	  * @param logModel   提现记录对象
	  * @author Feng Chao
	  * @date 2015-8-4
	 */
	public void updateCashAndCashLogByOrderId(MDrawCashLogModel logModel)throws DrawCashException{
		
		try {
			//修改提现记录表
			mDrawCashLogMapper.updateModelByOrderId(logModel);
		} catch (Exception e) {
			LOG.error("同时修改提现表和提现记录表时异常",e);
			throw new DrawCashException("203","","同时修改提现表和提现记录表时异常");
		}
	}
}
