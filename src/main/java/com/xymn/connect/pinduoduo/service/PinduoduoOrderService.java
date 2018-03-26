package com.xymn.connect.pinduoduo.service;

import org.jeecgframework.core.common.service.CommonService;

import com.xymn.connect.pinduoduo.entity.ShopTokens;


/**
 * @className PinduoduoOrderService
 * @Description 拼多多相关业务接口
 * @author aber
 * @Date 2018-03-15 05:06:00
 */
public interface PinduoduoOrderService extends CommonService{
	
	/**
	 * 初始化token
	 * @param shopTokens
	 */
	public ShopTokens initAccessToken(ShopTokens shopTokens);
	
	
	/**
	 * 调用订单增量接口获取时间段内的订单
	 * @param shopTokens
	 */
	public void getIncrementOrdersByTime(ShopTokens shopTokens);

	
	/**
	 * 刷新token
	 * @param shopTokens
	 */
	public void refeshAccessToken(ShopTokens shopTokens ,int i);

	/**
	 * 同步快递
	 */
	public void doSyncExpress();
	
	
	
	
	

}
