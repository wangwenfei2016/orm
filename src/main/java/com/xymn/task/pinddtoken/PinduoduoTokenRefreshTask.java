package com.xymn.task.pinddtoken;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.connect.pinduoduo.service.PinduoduoOrderService;

public class PinduoduoTokenRefreshTask implements Job{
	
	@Autowired
	private PinduoduoOrderService pinduoduoOrderService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		run();
	}
	
	//执行刷新token的timer task
	private void run() {
		//获取用户获取或刷新token令牌   
		List<ShopTokens> list = pinduoduoOrderService.findByProperty(ShopTokens.class, "success", "true");
		for (int i = 0; i < list.size(); i++) {
			pinduoduoOrderService.refeshAccessToken(list.get(i),1);
		}
	}

}