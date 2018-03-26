package com.xymn.logistics.logisticsorder.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import com.xymn.connect.kuaidiniao.entity.TracesJson;
import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.connect.pinduoduo.entityorderincrement.OrderIncrement;
import com.xymn.logistics.logisticsorder.entity.LogisticsOrderEntity;
import com.xymn.logistics.logisticsorder.entity.LogisticsOrderProductEntity;
import com.xymn.shop.shopdeploy.entity.ShopDeployEntity;

public interface LogisticsOrderService extends CommonService{
	
 	public void delete(LogisticsOrderEntity entity) throws Exception;
 	
 	public Serializable save(LogisticsOrderEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LogisticsOrderEntity entity) throws Exception;
 	
 	
 	/**
 	 * 转化/excel数据
 	 * @param rows excel数据
 	 * @param needNumber 所在列标
 	 * @param shopName   店铺名称
 	 * @param list 数据承载
 	 * @param products 产品集合
 	 * @param j 数据转换状态标记
 	 * @return 
 	 */
	public Boolean convertBatchData(List<String[]> rows, Integer[] needNumber, String shopName, List<LogisticsOrderEntity> list, List<LogisticsOrderProductEntity> products, AjaxJson j);
	
	/**
	 * 创建表
	 * @param user
	 * @return
	 */
	public Boolean createMiddleTable(TSUser user);
	
	/**
	 * 保存excel数据
	 * @param list excel订单集合
	 * @param products  excel产品集合
	 * @param user 当前登录人
	 * @throws Exception 
	 */
	public void saveBatchData(List<LogisticsOrderEntity> list, List<LogisticsOrderProductEntity> products, TSUser user) throws Exception;
	
	/**
	 * 拼多多订单监控和去重复
	 * @param list
	 * @param shopToken 用户调用信息
	 */
	public void conversionToOurSystem(List<OrderIncrement> list, ShopTokens shopToken);
	
	/**
	 * 监控excel导入数据
	 * @param user
	 * @param shopName 店铺id
	 */
	public Integer doActionMonitoring(TSUser user, String shopName);
	
	
	/**
	 * 组装用户的查询数据
	 * @param logisticsOrder 封装条件
	 * @param dataGrid 装载条件
	 */
	public void queryOrderListByCondition(LogisticsOrderEntity logisticsOrder, DataGrid dataGrid);
	
	
	/**
	 * 获取当前用户的店铺
	 * @param user
	 */
	public List<ShopDeployEntity> getUserShopBySessionUser(TSUser user);
	
	/**
	 * 通过id获取动态表订单信息
	 * @param id 订单id
	 * @param user  用户
	 * @return
	 */
	public LogisticsOrderEntity getOrderById(String id, TSUser user);
	
	/**
	 * 通过订单id获取动态表订单产品详情
	 * @param id 订单id
	 * @param user 用户
	 * @return
	 */
	public List<LogisticsOrderProductEntity> getOrderProductDetail(String id, TSUser user);
	
	
	/**
	 * 通过快递单号获取动态表订单物流信息集合
	 * @param expressNumber 快递单号
	 * @param user 用户
	 * @return
	 */
	public List<TracesJson> getOrderTracesByExpressCode(String expressNumber, TSUser user);

 	
}
