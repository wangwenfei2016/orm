package com.xymn.connect.kuaidiniao.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.xymn.connect.kuaidiniao.api.KdniaoSubscribeAPI;
import com.xymn.connect.kuaidiniao.entity.CallBackJson;
import com.xymn.connect.kuaidiniao.entity.DataJson;
import com.xymn.connect.kuaidiniao.entity.KuaidiniaoCallBackEntity;
import com.xymn.connect.kuaidiniao.entity.SendBackEntity;
import com.xymn.connect.kuaidiniao.entity.TracesJson;
import com.xymn.connect.kuaidiniao.service.KuaidiniaoService;
import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.logistics.logisticsorder.entity.LogisticsOrderEntity;
import com.xymn.shop.shopdeploy.entity.ShopDeployEntity;

import net.sf.json.JSONObject;

@Service("kuaidiniaoService")
@Transactional
public class KuaidiniaoServiceImpl extends CommonServiceImpl implements KuaidiniaoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/***
	 * 发布单条订阅
	 * 
	 * @param sendBackEntity
	 *            监控订单
	 * @throws Exception
	 * @return 返回是否成功
	 */
	@Override
	public Boolean publishSubscribeSingle(SendBackEntity sendBackEntity) throws Exception {

		List<SendBackEntity> list = new ArrayList<>();

		String requestData = null;
		KdniaoSubscribeAPI api = new KdniaoSubscribeAPI();
		boolean result = true;

		if (sendBackEntity.getOrderCode() != null) {
			requestData = "{'OrderCode': '" + sendBackEntity.getOrderCode() + "','ShipperCode': '"
					+ sendBackEntity.getShipperCode() + "','LogisticCode': '" + sendBackEntity.getLogisticCode() + "'}";
		} else {
			requestData = "{'OrderCode': '','ShipperCode': '" + sendBackEntity.getShipperCode() + "','LogisticCode': '"
					+ sendBackEntity.getLogisticCode() + "'}";
		}

		/** 参数1008代表发送订阅信息,快递鸟根据状态码判断请求类型 */
		String backJson = api.orderTracesSubByJson(requestData, "1008");
		JSONObject resultJson = JSONObject.fromObject(backJson);
		if ("true".equals(resultJson.get("Success").toString())) {
			sendBackEntity.setUpdateTime(resultJson.get("UpdateTime").toString());
			sendBackEntity.setSuccess(resultJson.get("Success").toString());
		} else {
			sendBackEntity.setUpdateTime(resultJson.get("UpdateTime").toString());
			sendBackEntity.setSuccess(resultJson.get("Success").toString());
			sendBackEntity.setReason(resultJson.get("Reason") != null ? resultJson.get("Reason").toString() : null);
			result = false;
		}

		list.add(sendBackEntity);

		this.saveSendBackDataForKuaidiniao(list, "");

		return result;
	}

	/***
	 * 发布订阅集合
	 * @param string 
	 * 
	 * @param list
	 *            监控订单集合
	 * @throws Exception
	 * @return 返回成功订阅数量
	 */
	// @Override
	// public Integer publishSubscribeBatch(List<SendBackEntity> list,String
	// tableName) {
	//
	// int successNum = 0;
	// String requestData = null;
	// KdniaoSubscribeAPI api = new KdniaoSubscribeAPI();
	// for (int i = 0; i < list.size(); i++) {
	// /**ShipperCode:快递编码(官方文档对应),OrderCode:订单编号,LogisticCode:快递单号*/
	// SendBackEntity sendBackEntity = list.get(i);
	// if(sendBackEntity.getOrderCode()!=null){
	// requestData = "{'OrderCode':
	// '"+sendBackEntity.getOrderCode()+"','ShipperCode':
	// '"+sendBackEntity.getShipperCode()+"','LogisticCode':
	// '"+sendBackEntity.getLogisticCode()+"','CallBack':'"+sendBackEntity.getCallBack()+"'}";
	// }else{
	// requestData = "{'OrderCode': '','ShipperCode':
	// '"+sendBackEntity.getShipperCode()+"','LogisticCode':
	// '"+sendBackEntity.getLogisticCode()+"','CallBack':'"+sendBackEntity.getCallBack()+"'}";
	// }
	//
	// /**参数1008代表发送订阅信息,快递鸟根据状态码判断请求类型*/
	// try {
	// String result = api.orderTracesSubByJson(requestData,"1008");
	// if(result!=null){
	// JSONObject resultJson = JSONObject.fromObject(result);
	// if("true".equals(resultJson.get("Success").toString())){
	// sendBackEntity.setUpdateTime(resultJson.get("UpdateTime").toString());
	// sendBackEntity.setSuccess(resultJson.get("Success").toString());
	// successNum++;
	// }else{
	// sendBackEntity.setUpdateTime(resultJson.get("UpdateTime").toString());
	// sendBackEntity.setSuccess(resultJson.get("Success").toString());
	// sendBackEntity.setReason(resultJson.get("Reason")!=null?resultJson.get("Reason").toString():null);
	// }
	// }
	// } catch (Exception e) {
	// sendBackEntity.setUpdateTime(new SimpleDateFormat().format(new Date()));
	// sendBackEntity.setSuccess("false");
	// sendBackEntity.setReason("发送出错");
	// }
	//
	// }
	//
	// //保存数据
	// this.saveSendBackDataForKuaidiniao(list, tableName);
	//
	// return successNum;
	// }

	public Map<String, String> getExpressCode(String platformtype) {

		Map<String, String> map = new HashMap<>();

		String sql = "SELECT express_code , kuaidiniao_code FROM express_info where platformType = '"+platformtype+"' ";
		List<Map<String, Object>> list = this.findForJdbc(sql, null);
		for (Map<String, Object> map2 : list) {
			if (map2.get("express_code") != null && map2.get("kuaidiniao_code") != null) {
				map.put(map2.get("express_code").toString(), map2.get("kuaidiniao_code").toString());
			}
		}

		return map;
	}

	@Override
	public void publishSubscribeBatch(List<LogisticsOrderEntity> orders, TSUser user, ShopTokens shopToken) {
		
		ShopDeployEntity shop = this.findUniqueByProperty(ShopDeployEntity.class, "state", shopToken.getState());
		
		TSUser userInfo = this.get(TSUser.class, user.getId());
		// 账户余额
		int allowance = userInfo.getAllowance();

		int sendNum = 0;

		String requestData = null;
		String sendTableName = "wl_send_back_" + user.getUserName();

		// 获取所有的快递编码 key是id value是code
		Map<String, String> map = this.getExpressCode(shop.getPlatformSort());
		//获取
		Map<String, String> expressNameMap = this.getExpressNameMap(shop.getPlatformSort());

		List<SendBackEntity> list = new ArrayList<>();
		KdniaoSubscribeAPI api = new KdniaoSubscribeAPI();
		if (allowance > 0) {
			for (int i = 0; i < orders.size(); i++) {

				LogisticsOrderEntity orderEntity = orders.get(i);

				if (StringUtil.isNotEmpty(user.getId())
						&& StringUtil.isNotEmpty(map.get(orderEntity.getExpressCompany()))
						&& StringUtil.isNotEmpty(orderEntity.getExpressNumber())) {

					/** ShipperCode:快递编码(官方文档对应),OrderCode:订单编号,LogisticCode:快递单号 */
					SendBackEntity sendBackEntity = new SendBackEntity();
					sendBackEntity.setCallBack(user.getId());
					sendBackEntity.setOrderCode(orderEntity.getOrderCode());
					sendBackEntity.setShipperCode(map.get(orderEntity.getExpressCompany()));
					sendBackEntity.setLogisticCode(orderEntity.getExpressNumber());
					
					//设置快递公司名称
					orderEntity.setExpressCompany(expressNameMap.get(orderEntity.getExpressCompany()));
					
					if (sendBackEntity.getOrderCode() != null) {
						requestData = "{'OrderCode': '" + sendBackEntity.getOrderCode() + "','ShipperCode': '"
								+ sendBackEntity.getShipperCode() + "','LogisticCode': '"
								+ sendBackEntity.getLogisticCode() + "','CallBack':'" + sendBackEntity.getCallBack()
								+ "'}";
					} else {
						requestData = "{'OrderCode': '','ShipperCode': '" + sendBackEntity.getShipperCode()
								+ "','LogisticCode': '" + sendBackEntity.getLogisticCode() + "','CallBack':'"
								+ sendBackEntity.getCallBack() + "'}";
					}

					/** 参数1008代表发送订阅信息,快递鸟根据状态码判断请求类型 */
					try {
						String result = api.orderTracesSubByJson(requestData, "1008");
						if (result != null) {
							JSONObject resultJson = JSONObject.fromObject(result);
							if ("true".equals(resultJson.get("Success").toString())) {
								sendBackEntity.setUpdateTime(resultJson.get("UpdateTime").toString());
								sendBackEntity.setSuccess(resultJson.get("Success").toString());
								orderEntity.setIstail("1");
								sendNum++;
							} else {
								sendBackEntity.setUpdateTime(resultJson.get("UpdateTime").toString());
								sendBackEntity.setSuccess(resultJson.get("Success").toString());
								sendBackEntity.setReason(
										resultJson.get("Reason") != null ? resultJson.get("Reason").toString() : null);
								orderEntity.setIstail("0");
							}
						}
					} catch (Exception e) {
						sendBackEntity.setUpdateTime(new SimpleDateFormat().format(new Date()));
						sendBackEntity.setSuccess("false");
						sendBackEntity.setReason("发送出错");
						orderEntity.setIstail("0");
					}

					list.add(sendBackEntity);
				}else{
					orderEntity.setIstail("0");
				}

			}

			user.setAllowance(allowance - sendNum);

		} else{
			for (LogisticsOrderEntity order : orders) {
				// 未监控
				order.setIstail("0");
			}
		}

		// 保存数据
		if (list.size() > 0) {
			this.saveSendBackDataForKuaidiniao(list, sendTableName);
		}

	}

	private Map<String, String> getExpressNameMap(String platformSort) {
		Map<String, String> map = new HashMap<>();

		String sql = "SELECT express_code , express_name FROM express_info where platformType = '"+platformSort+"' ";
		List<Map<String, Object>> list = this.findForJdbc(sql, null);
		for (Map<String, Object> map2 : list) {
			if (map2.get("express_code") != null && map2.get("express_name") != null) {
				map.put(map2.get("express_code").toString(), map2.get("express_name").toString());
			}
		}

		return map;
	}

	/**
	 * 保存批量发送信息数据
	 * 
	 * @param list
	 *            数据集合
	 * @param tableName
	 *            表名
	 */
	private void saveSendBackDataForKuaidiniao(List<SendBackEntity> list, String tableName) {

		final List<SendBackEntity> tempBpplist = list;
		String sql = "INSERT INTO " + tableName + " VALUES(REPLACE(uuid(), '-', ''),?, ?,?,?,?,?,?,?);";
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				ps.setString(1, tempBpplist.get(i).getLogisticCode());
				ps.setString(2, tempBpplist.get(i).getShipperCode());
				ps.setString(3, tempBpplist.get(i).getOrderCode());
				ps.setString(4, tempBpplist.get(i).getUpdateTime());
				ps.setString(5, tempBpplist.get(i).getEbusinessId());
				ps.setString(6, tempBpplist.get(i).getSuccess());
				ps.setString(7, tempBpplist.get(i).getReason());
				ps.setString(8, tempBpplist.get(i).getCallBack());

			}

			@Override
			public int getBatchSize() {

				return tempBpplist.size();
			}

		});
	}

	/**
	 * 回调函数数据处理
	 * 
	 * @param data
	 * @throws Exception
	 */
	@Override
	public void doCallBackDataService(String json, Map<String, Object> map) throws Exception {


		Gson gson = new Gson();
		CallBackJson backJson = gson.fromJson(json, CallBackJson.class);
		// 设置回执报文
		map.put("EBusinessID", backJson.getEBusinessID());
		DataJson[] data = backJson.getData();
		for (int i = 0; i < data.length; i++) {
			
			if("true".equals(data[i].getSuccess())){
				
				TracesJson[] traces = backJson.getData()[i].getTraces();
				if (traces != null) {
					String tracesString = gson.toJson(traces);
					
					Object[] orderparameter = new Object[]{
							UUID.randomUUID().toString().replaceAll("-", ""),
							backJson.getPushTime(),
							data[i].getEBusinessID(),
							data[i].getOrderCode(),
							data[i].getShipperCode(),
							data[i].getLogisticCode(),
							data[i].getSuccess(),
							data[i].getReason(),
							data[i].getState(),
							data[i].getCallBack(),
							tracesString,
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
					};
					
					//根据callback返回的用户id,获取用户表名,进行相关更新
					TSUser user = this.get(TSUser.class, data[i].getCallBack());
					String tableName = "wl_kuaidiniao_call_back_"+user.getUserName();
					String sql = "INSERT INTO "+tableName+"(id,push_time,ebusiness_id,order_code,shipper_code,logistic_code,success,reason,state,call_back,traces,refresh_time) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
					
					jdbcTemplate.update(sql, orderparameter);
					
					
					Date pushTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(traces[traces.length-1].getAcceptTime());
					Date nowDate = new Date();
					long hours = (nowDate.getTime() - pushTime.getTime()) / (1000 * 60 * 60);
					
					String orderTable = "wl_logistics_order_"+user.getUserName();
					sql = "UPDATE "+orderTable+" wl SET wl.silence_hour =  ? , wl.logistics_tail_time = ? ,wl.logistics_tail_info = ? WHERE wl.express_number = ? ";
					jdbcTemplate.update(sql, new Object[] {hours ,pushTime, traces[traces.length-1].toString(),data[i].getLogisticCode()});
					
					
				}
				
			}
			
		}

		// 参数1:集合,参数2:表名

	}

	/**
	 * 保存快递鸟返回数据列
	 * 
	 * @param list
	 *            数据集合
	 * @param tableName
	 *            表名
	 */
	private void saveCallBackDataForKuaidiniao(List<KuaidiniaoCallBackEntity> list,String tableName) {

		final List<KuaidiniaoCallBackEntity> tempBpplist = list;
		String sql = "INSERT INTO " + tableName + " VALUES(REPLACE(uuid(), '-', ''),?,?,?,?,?,?,?,?,?,?,?);";
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				ps.setString(1, tempBpplist.get(i).getPushTime());
				ps.setString(2, tempBpplist.get(i).getEbusinessId());
				ps.setString(3, tempBpplist.get(i).getOrderCode());
				ps.setString(4, tempBpplist.get(i).getShipperCode());
				ps.setString(5, tempBpplist.get(i).getLogisticCode());
				ps.setString(6, tempBpplist.get(i).getSuccess());
				ps.setString(7, tempBpplist.get(i).getReason());
				ps.setString(8, tempBpplist.get(i).getState());
				ps.setString(9, tempBpplist.get(i).getCallBack());
				ps.setString(10, tempBpplist.get(i).getTraces());
				ps.setString(11, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}

			@Override
			public int getBatchSize() {

				return tempBpplist.size();
			}

		});
	}
	
	
	/**
	 * 发送excel导入的订单订阅
	 */
	@Override
	public void publishSubscribeBatchExcel(List<LogisticsOrderEntity> orders, TSUser user, String shopName) {
		TSUser userInfo = this.get(TSUser.class, user.getId());
		// 账户余额
		int allowance = userInfo.getAllowance();

		int sendNum = 0;

		String requestData = null;
		String sendTableName = "wl_send_back_" + user.getUserName();

		// 获取所有的快递编码 key是id value是code
		Map<String, String> map = this.getExpressCodeExcel(shopName);

		List<SendBackEntity> list = new ArrayList<>();
		KdniaoSubscribeAPI api = new KdniaoSubscribeAPI();
		if (allowance > 0) {
			for (int i = 0; i < orders.size(); i++) {

				LogisticsOrderEntity orderEntity = orders.get(i);

				if (StringUtil.isNotEmpty(user.getId())
						&& StringUtil.isNotEmpty(map.get(orderEntity.getExpressCompany()))
						&& StringUtil.isNotEmpty(orderEntity.getExpressNumber())) {

					/** ShipperCode:快递编码(官方文档对应),OrderCode:订单编号,LogisticCode:快递单号 */
					SendBackEntity sendBackEntity = new SendBackEntity();
					sendBackEntity.setCallBack(user.getId());
					sendBackEntity.setOrderCode(orderEntity.getOrderCode());
					sendBackEntity.setLogisticCode(orderEntity.getExpressNumber());
					sendBackEntity.setShipperCode(map.get(orderEntity.getExpressCompany()));

					if (sendBackEntity.getOrderCode() != null) {
						requestData = "{'OrderCode': '" + sendBackEntity.getOrderCode() + "','ShipperCode': '"
								+ sendBackEntity.getShipperCode() + "','LogisticCode': '"
								+ sendBackEntity.getLogisticCode() + "','CallBack':'" + sendBackEntity.getCallBack()
								+ "'}";
					} else {
						requestData = "{'OrderCode': '','ShipperCode': '" + sendBackEntity.getShipperCode()
								+ "','LogisticCode': '" + sendBackEntity.getLogisticCode() + "','CallBack':'"
								+ sendBackEntity.getCallBack() + "'}";
					}

					/** 参数1008代表发送订阅信息,快递鸟根据状态码判断请求类型 */
					try {
						String result = api.orderTracesSubByJson(requestData, "1008");
						if (result != null) {
							JSONObject resultJson = JSONObject.fromObject(result);
							if ("true".equals(resultJson.get("Success").toString())) {
								sendBackEntity.setUpdateTime(resultJson.get("UpdateTime").toString());
								sendBackEntity.setSuccess(resultJson.get("Success").toString());
								orderEntity.setIstail("1");
								sendNum++;
							} else {
								sendBackEntity.setUpdateTime(resultJson.get("UpdateTime").toString());
								sendBackEntity.setSuccess(resultJson.get("Success").toString());
								sendBackEntity.setReason(
										resultJson.get("Reason") != null ? resultJson.get("Reason").toString() : null);
								orderEntity.setIstail("0");
								orderEntity.setReason("请求出错");
							}
						}
					} catch (Exception e) {
						sendBackEntity.setUpdateTime(new SimpleDateFormat().format(new Date()));
						sendBackEntity.setSuccess("false");
						sendBackEntity.setReason("网络异常");
						orderEntity.setIstail("0");
						orderEntity.setReason("网络异常");
					}

					list.add(sendBackEntity);
				}else{
					orderEntity.setIstail("0");
					orderEntity.setReason("快递公司,快递单号不能为空");
				}

			}

			user.setAllowance(allowance - sendNum);

		} else{
			for (LogisticsOrderEntity order : orders) {
				// 未监控
				order.setIstail("0");
				order.setReason("账户余额不足");
			}
		}

		// 保存数据
		if (list.size() > 0) {
			this.saveSendBackDataForKuaidiniao(list, sendTableName);
		}

		
	}

	private Map<String, String> getExpressCodeExcel(String shopId) {
		//获取店铺
		ShopDeployEntity shopDeployEntity = this.get(ShopDeployEntity.class, shopId);
		Map<String, String> map = new HashMap<>();

		String sql = "SELECT express_name , kuaidiniao_code FROM express_info WHERE platform_type = '"+shopDeployEntity.getPlatformSort()+"' ";
		List<Map<String, Object>> list = this.findForJdbc(sql, null);
		for (Map<String, Object> map2 : list) {
			if (map2.get("express_name") != null && map2.get("kuaidiniao_code") != null) {
				map.put(map2.get("express_code").toString(), map2.get("kuaidiniao_code").toString());
			}
		}

		return map;
	}

}
