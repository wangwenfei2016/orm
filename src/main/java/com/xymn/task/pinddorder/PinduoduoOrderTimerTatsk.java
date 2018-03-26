package com.xymn.task.pinddorder;

import java.util.List;

import org.jeecgframework.web.system.pojo.base.TSUser;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.connect.pinduoduo.service.PinduoduoOrderService;

/**
 * 
 * @ClassName:PinduoduoOrderTimerTatsk
 * @Description: 定时刷新监控店铺的订单
 * @author aber
 * @date 2018-03-15 下午5:06:34
 * 
 */

@Service("orderTimerTatsk")
public class PinduoduoOrderTimerTatsk implements Job {
	
	
	@Autowired
	private PinduoduoOrderService pinduoduoOrderService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		run();
	}

	public void run() {
		
		//获取用户获取或刷新token令牌
		List<ShopTokens> list = pinduoduoOrderService.getList(ShopTokens.class);
		//循环调取不同店铺的订单
		for (int i = 0; i < list.size(); i++) {
			//调用订单增量接口获取时间段内的增量订单
			pinduoduoOrderService.getIncrementOrdersByTime(list.get(i));
		}
		
	}

}
