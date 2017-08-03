package com.daboo.finance.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.daboo.core.exception.BusinessException;
import com.daboo.core.exception.DrawCashException;
import com.daboo.core.util.CommonUtil;
import com.daboo.core.util.ConfigProperty;
import com.daboo.core.util.JSONUtils;
import com.daboo.core.util.Md5Util;
import com.daboo.finance.enums.MDrawLogStatus;
import com.daboo.finance.enums.MDrawStatus;
import com.daboo.finance.mapper.MDrawCashLogMapper;
import com.daboo.finance.mapper.MDrawMapper;
import com.daboo.finance.model.MDrawCashLogModel;
import com.daboo.finance.model.MDrawModel;
import com.daboo.finance.model.MDrawResultModel;
import com.daboo.finance.service.MDrawService;
import com.daboo.reward.task.MCoachAwardsTask;

/**
 * 提现定时任务
 * @author fengchao
 *
 */
@Repository("mDrawCashTask")
public class MDrawCashTask {

	private static final Logger LOG = LoggerFactory.getLogger(MCoachAwardsTask.class);
	@Autowired
	private MDrawMapper mDrawMapper;
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private MDrawCashLogMapper mDrawCashLogMapper;
	@Autowired
    private PlatformTransactionManager txManager ;
	@Autowired
	private MDrawService mDrawService;
	/**
	 * 
	  * @Description: 执行提现操作
	  * @return			status:状态
	  * @author FengChao
	 * @throws BusinessException 
	  * @date 2015年8月3日
	 */
	public void  executeDrawCash() throws DrawCashException{
		
		MDrawModel drwaModel = new MDrawModel();
		drwaModel.setStatus(MDrawStatus.AUDIT_SUCC);
		// 查询所有需要提现的记录
		List<MDrawModel> drawList = mDrawMapper.getListByModel(drwaModel);
		if(!drawList.isEmpty()){
			for(MDrawModel cashModel : drawList){
				try {
					//执行提现操作
					doDrawCash(cashModel);
				} catch (BusinessException e) {
				   	LOG.error(e.getMessage()+",此次提现的订单id为"+cashModel.getId());
				    	//throw new BusinessException(e.getMessage());
				    }
				}
		}
	}
	/**
	 * 
	  * @Description: 执行提现操作
	  * @return		  FxDrawCashModel 提现model
	  * @throws BusinessException
	  * @author FengChao
	  * @date 2015年8月3日
	 */
	public void  doDrawCash(final MDrawModel cashModel) throws BusinessException{
		

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				
		if(cashModel == null){
			throw new DrawCashException("提现对象为空");
		}else if(cashModel.getUserId() == null){
			throw new DrawCashException("提现对象用户id为空");
		}else if(StringUtils.isBlank(cashModel.getCardNo())){
			throw new DrawCashException("提现对象银行卡号为空");
		}else if(cashModel.getAmount() == null){
			throw new DrawCashException("提现对象金额为空");
		}else if(StringUtils.isBlank(cashModel.getBankName())){
			throw new DrawCashException("提现对象银行名称为空");
		}
		long t = 0;
		MDrawResultModel model = null;
		Boolean cashPass = null;
		Integer indexId  = null;
		MDrawCashLogModel logModel = null;
		try {
			//拼接一个时间格式的字符串
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String date = sdf.format(new Date());
			//拼接一个请求提现id(格式如123_20150806095226999_3456)
			 StringBuilder builder = new StringBuilder();
			 builder.append("bh_"+cashModel.getUserId()).append("_").append(date).append("_").append((int)(Math.random()*1000));
			//记录用户的提现操作,返回生成的提现日志id
			indexId = recordDrawCash(cashModel,builder.toString());
			//扣除用户账户的钱
			//	fxFinancialService.drawCash(cashModel.getUserId(), cashModel.getAmount(), cashModel.getId()+"", 3);
			//调用.net接口，进行提现操作
			try {
				t = System.currentTimeMillis();
				model  = drawCashByNet(cashModel,builder.toString());
			} catch (Exception e) {
				//如果抛出未知异常（可能网络中断）,默认当做发送提现请求成功
				cashPass = true;    
				LOG.info("调用.net提现接口失败，耗时："+String.valueOf(System.currentTimeMillis() - t));
                LOG.error("调用.net提现接口时异常",e);
			}
			//解析.net的返回结果
			if(model != null){
				LOG.info("调用.net提现接口成功，耗时："+String.valueOf(System.currentTimeMillis() - t)+",.net返回的code是:"+model.getCode());
                // 提现成功或者未知
				LOG.error(".net返回的code是:"+model.getCode());
				if(model.getCode().equals("0") || model.getCode().equals("-1")){
                    cashPass = true;                	
                }else if(model.getCode().equals("1")){
                	cashPass = false;
                }
			}
			//如果提现失败
			if(!cashPass){
				//提现失败,将用户已经扣的钱退还给用户，并且修改提现表和提现记录表的状态
				//调用提现失败方法 by mzz
				String errMsg  = null;
				if(StringUtils.isNotBlank(model.getFailureCause()))errMsg = ConfigProperty.getQuickProperty(model.getFailureCause());
				mDrawService.changeStatus(cashModel.getId(), MDrawStatus.DRAW_FAIL, errMsg, "system");
				logModel = new MDrawCashLogModel(indexId,MDrawLogStatus.FAILED_CASH,new Date());
				
			}else{
				String successMes = "提现已提交";
				mDrawService.changeStatus(cashModel.getId(), MDrawStatus.DRAW_SUBMIT, successMes, "system");
				logModel = new MDrawCashLogModel(indexId ,MDrawLogStatus.SUCCESS_SEND, new Date());
			}
			//记录此次提现成功还是失败
			if(logModel != null ) updateCashAndCashLog(logModel);
		} catch (DrawCashException e) {
			arg0.setRollbackOnly();
			//修改提现记录，标记发送请求失败
			updateModelByCashLogId(new MDrawCashLogModel(indexId,MDrawLogStatus.FAILED_SEND,new Date()));
			throw new DrawCashException(e.getCode(),"",e.getMessage());
		} catch (Exception e) {
			arg0.setRollbackOnly();
			LOG.error("提现失败,未知错误",e);
			//修改提现记录，标记发送请求失败
			updateModelByCashLogId(new MDrawCashLogModel(indexId,MDrawLogStatus.FAILED_SEND,new Date()));
			throw new DrawCashException("300","","提现失败,未知错误");
		}
	     }});
	}
	/**
	 * 
	  * @Description: 记录此次提现操作
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param cashModel FxDrawCashModel
	  * @author Feng Chao
	  * @date 2015-8-3
	 */
	public Integer recordDrawCash(final MDrawModel cashModel,final String sign)throws DrawCashException{
		
		int returnId = 0;
		TransactionTemplate txTemplate = new TransactionTemplate(txManager);                
		txTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		try {
		returnId = (Integer) txTemplate.execute(new TransactionCallback<Object>() {
		    public Object doInTransaction(TransactionStatus status) {
		    	int id = 0;
		    	MDrawCashLogModel drawLog = new MDrawCashLogModel(cashModel.getUserId(),sign,cashModel.getId(),cashModel.getArrivalAmount(),0,new Date(),new Date(),0);
		    	mDrawCashLogMapper.insertModelReturnId(drawLog);
		    	id = drawLog.getId();
		    	return id;		
				
		    }
		});
		} catch (Exception e) {
			LOG.error("插入提现记录时异常",e);
			throw new DrawCashException("201","","记录提现操作失败");
		}
		return returnId;
		
	}
	/**
	 * 
	  * @Description:调用.net方法，执行提现操作
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param cashModel
	  * @author Feng Chao
	  * @date 2015-8-3
	 */
	public MDrawResultModel drawCashByNet(MDrawModel cashModel,String requestId){
		
		 String sign = "";
		 String url = "";
		 String key = "";
		 MDrawResultModel model = null;
		 
		 //获取.net的密钥
		 key = ConfigProperty.getProperty("net_draw_key");
		 url = ConfigProperty.getProperty("net_draw_url");
		 //订单id+卡号+金额+密钥，然后进行md5加密
		 sign = Md5Util.makeMd5Sum((requestId+cashModel.getCardNo()+(double)cashModel.getArrivalAmount()/100.0+key).getBytes());
		 //获取需要传的参数
		 String jsonParam = getJsonParam(cashModel,sign.toUpperCase(),requestId);
		 //发送请求（调用.net）
		 String json = CommonUtil.sendRequestToNet(jsonParam , url);
		 LOG.info("调用.net提现接口返回的数据为:"+json);
		 String code = String.valueOf(JSON.parseObject(json).get("Code"));
		 if(code.equals("200")){
		   model =  new MDrawResultModel();
		   String data = String.valueOf(JSON.parseObject(json).get("Data"));
		   if(!data.equals("null") && StringUtils.isNotBlank(data)){
			   String result =  JSON.parseObject(data).getString("Result");
			   if(StringUtils.isNotBlank(result)){
				   model  = JSON.parseObject(result,MDrawResultModel.class);
			   }else{
				   LOG.error("调用.net提现接口时返回空数据");
				   throw new RuntimeException("返回200，获取数据为空");
			   }
		   }else{
			   LOG.error("调用.net提现接口时返回空数据");
			   throw new RuntimeException("返回200，获取数据为空");
		   }
		 }else{
			 LOG.error("调用.net提现接口时返回非200,错误信息为："+String.valueOf(JSON.parseObject(json).get("Message")));
			model =  new MDrawResultModel();
			model.setCode(-1+"");
			model.setAlertMsg(".net返回非200");
		 }
		 return model;
	}
	/**
	 * 
	  * @Description: 将所需要传的参数封装为json字符串
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param cashModel
	  * @param sign     密钥
	  * @param orderId  订单id
	  * @return
	  * @author Feng Chao
	  * @date 2015-8-4
	 */
	public String getJsonParam(MDrawModel cashModel,String sign,String orderId){
		
		String returnString = "";
		Map<String,String> map = new HashMap<String,String>();
		map.put("Sign",sign );
		map.put("UserID", cashModel.getUserId() + "");
		map.put("ProvinceCity",cashModel.getOpenBankCity());
		map.put("BankName", cashModel.getBankName());
		map.put("KaiHuHang", cashModel.getBankName());
		map.put("CreditName", cashModel.getCardOwnerName());
		map.put("BankCardNumber", cashModel.getCardNo());
		map.put("Amount", (double)cashModel.getArrivalAmount()/100.0 + "");
		map.put("Description", "提现");
		map.put("OrderID", orderId);
		//returnString = JSONUtils.mapToJson(map);
		returnString = JSONUtils.toJson(map);
		return returnString;
	}
	/**
	 * 
	  * @Description: 修改提现记录表
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param cashModel  提现对象
	  * @param logModel   提现记录对象
	  * @author Feng Chao
	  * @date 2015-8-4
	 */
	public void updateCashAndCashLog(MDrawCashLogModel logModel)throws DrawCashException{
		
		try {
			//修改提现记录表
			mDrawCashLogMapper.updateModelById(logModel);
		} catch (Exception e) {
			LOG.error("修改提现记录表时异常",e);
			throw new DrawCashException("203","","修改提现记录表时异常");
		}
	}
	/**
	 * 
	  * @Description: 修改提现记录表
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * @param logModel
	  * @author Feng Chao
	  * @date 2015-8-5
	 */
	public void updateModelByCashLogId(final MDrawCashLogModel logModel){
		
	    TransactionTemplate txTemplate = new TransactionTemplate(txManager);
	    txTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	    txTemplate.execute(new TransactionCallback<Object>() {
	        public Object doInTransaction(TransactionStatus status) {
	        	return mDrawCashLogMapper.updateModelById(logModel);
	        }});

		
	}
	
}
