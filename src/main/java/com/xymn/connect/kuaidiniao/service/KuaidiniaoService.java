package com.xymn.connect.kuaidiniao.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import com.xymn.connect.kuaidiniao.entity.SendBackEntity;
import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.logistics.logisticsorder.entity.LogisticsOrderEntity;

public interface KuaidiniaoService extends CommonService{
	
	/***
	 * 发布单条订阅
	 * @param sendBackEntity 监控订单
	 * @throws Exception
	 * @return 返回是否成功
	 */
	public Boolean publishSubscribeSingle(SendBackEntity sendBackEntity) throws Exception;
	
	/***
	 * 发布订阅集合
	 * @param list 监控订单集合
	 * @throws Exception
	 * @return 返回成功订阅数量
	 */
//	public Integer publishSubscribeBatch(List<SendBackEntity> list,String tableName) ;
	
	
	/**
	 * 回调函数数据处理
	 * @param data 数据集
	 * @param map 回传报文
	 * @throws Exception
	 */
	public void doCallBackDataService(String data, Map<String, Object> map)throws Exception;
	
	/***
	 * 发布订阅集合
	 * @param shopToken 
	 * @param list 监控订单集合
	 * @throws Exception
	 * @return 返回成功订阅数量
	 */
	public void publishSubscribeBatch(List<LogisticsOrderEntity> orders, TSUser user, ShopTokens shopToken);
	
	
	/***
	 * 发布订阅集合
	 * @param list 监控订单集合
	 * @throws Exception
	 * @return 返回成功订阅数量
	 */
	public void publishSubscribeBatchExcel(List<LogisticsOrderEntity> orders, TSUser user, String shopName);
	
}
