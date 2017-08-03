package com.daboo.finance.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.daboo.core.util.CommonUtil;
import com.daboo.core.util.ConfigProperty;
import com.daboo.core.util.SendMail;
import com.daboo.finance.model.MDrawResultModel;

@Repository("healthyChcekTask")
public class HealthyChcekTask {
	
	private static final Logger logger = LoggerFactory.getLogger(HealthyChcekTask.class);
	public void execute(){
		
		try {
			 String url = "";
			 MDrawResultModel model = null;
			 url = ConfigProperty.getProperty("net_drawSuccess_url");
			 //发送请求（调用.net）
			 String json = CommonUtil.sendRequestToNet("{\"orderID\":\"bhtest\"}", url);
			 System.out.println(json);
		} catch (Exception e) {
			logger.error("健康检查时发生异常",e);
			SendMail.sendAlarmNotify();
		}
	}
}
