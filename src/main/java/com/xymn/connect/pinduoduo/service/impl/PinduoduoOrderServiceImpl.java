package com.xymn.connect.pinduoduo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.sun.star.util.Date;
import com.xymn.connect.pinduoduo.api.PinduoduoRequestApi;
import com.xymn.connect.pinduoduo.entity.ErrorMsg;
import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.connect.pinduoduo.entityexpress.PinduoduoExpress;
import com.xymn.connect.pinduoduo.entityorderincrement.OrderIncrement;
import com.xymn.connect.pinduoduo.entityorderincrement.OrderIncrementFrist;
import com.xymn.connect.pinduoduo.query.OrderIncrementQuery;
import com.xymn.connect.pinduoduo.service.PinduoduoOrderService;
import com.xymn.logistics.logisticsorder.service.LogisticsOrderService;

import net.sf.json.JSONObject;

/**
 * @className PinduoduoOrderServiceImpl
 * @Description 拼多多相关业务接口实现类
 * @author aber
 * @Date 2018-03-15 05:06:00
 */
@Service("pinduoduoOrderService")
@Transactional
public class PinduoduoOrderServiceImpl extends CommonServiceImpl implements PinduoduoOrderService{
	
	@Autowired
	private PinduoduoRequestApi pinduoduoRequestApi;
	@Autowired
	private LogisticsOrderService logisticsOrderService;
	/**
	 * 刷新或获取token
	 */
	@Override
	public ShopTokens initAccessToken(ShopTokens shopToken) {
		
		if(shopToken.getCode()!=null){
			//status 0:没获取  1:获取过 
			String result = pinduoduoRequestApi.initToken(shopToken);
			if(result!=null){
				//json转化
				JSONObject obj = JSONObject.fromObject(result);
				if(obj!=null){
					shopToken.setAccessToken(obj.get("access_token")!=null?obj.get("access_token").toString():null);
					shopToken.setScope(obj.get("scope")!=null?obj.get("scope").toString():null);
					shopToken.setExpiresIn(obj.get("expires_in")!=null?obj.get("expires_in").toString():null);
					shopToken.setRefreshToken(obj.get("refresh_token")!=null?obj.get("refresh_token").toString():null);
					shopToken.setOwnerId(obj.get("owner_id")!=null?obj.get("owner_id").toString():null);
					shopToken.setOwnerName(obj.get("owner_name")!=null?obj.get("owner_name").toString():null);
					shopToken.setIsmonitor("false");
					shopToken.setSuccess("true");
				}else{
					Gson gson = new Gson();
					ErrorMsg error = gson.fromJson(result, ErrorMsg.class);
					if(error!=null){
						shopToken.setSuccess("false");
						shopToken.setReason(error.getError_response().getError_msg());
					}else{
						shopToken.setSuccess("false");
						shopToken.setReason("其他未知原因导致");
					}
				}
			}else{
				shopToken.setSuccess("false");
				shopToken.setReason("初始化失败,可能由于网络问题");
			}
		}else{
			shopToken.setSuccess("false");
			shopToken.setReason("code或state为空");
		}
		return shopToken;
		
	}
	
	
	/**
	 * 刷新token
	 */
	@Override
	public void refeshAccessToken(ShopTokens shopToken,int i) {
		
		//超过三次不刷新
		if(i<=3){
			String result = this.pinduoduoRequestApi.refeshToken(shopToken);
			if(StringUtils.isNotEmpty(result)){
				//json转化
				if(result.contains("scope")&&result.contains("refresh_token")){
					shopToken.setSuccess("true");
				}else{
					refeshAccessToken(shopToken ,i+1);
				}
			}else{
				refeshAccessToken(shopToken ,i+1);
			}
		}else{
			shopToken.setSuccess("false");
			this.saveOrUpdate(shopToken);
		}
	}
	
	
	/**
	 * 调用订单增量接口获取时间段内的订单
	 * @param shopTokens
	 */
	@Override
	public void getIncrementOrdersByTime(ShopTokens shopToken) {
		
		//当token有效是,当启动订单监控时获取同步订单
		if("true".equals(shopToken.getIsmonitor())&&"true".equals(shopToken.getSuccess())){
			OrderIncrementQuery query = new OrderIncrementQuery();
			
			/** String 必填 1:非抽奖订单 2：抽奖订单 */
			query.setIs_lucky_flag("1");
			/** String 必填 发货状态，1：待发货，2：已发货待签收，3：已签收 5：全部 */
			query.setOrder_status("2");
			
			long endTime = new java.util.Date().getTime()/1000;
			query.setStart_updated_at(endTime-1700+"");
			query.setEnd_updated_at(endTime+"");
			/** int 非必填 返回数量，默认 100。最大 100 */
			query.setPage_size(100);
			query.setPage(1);
			/** String 必填 ,售后状态 1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功 5：全部 */
			query.setRefund_status("5");
			
System.out.println("=================================更换PinduoduoRequestApi==impl=====================================================================");
			PinduoduoRequestApi impl = new PinduoduoRequestApi();
			
			String result = impl.doPinduoduoApiGetIncrementOrderByTime(shopToken, query);
			/**json转换*/
			Gson gson = new Gson();
			OrderIncrementFrist fromJson = gson.fromJson(result, OrderIncrementFrist.class);
			
			//订单增量数据
			List<OrderIncrement> list = new ArrayList<>();
			
			if(fromJson!=null){
				Integer total_count = fromJson.getOrder_sn_increment_get_response().getTotal_count();
				if(total_count > 100){
					//获取页数
					int page = total_count%100==0?total_count/100:total_count/100+1;
					list.addAll(Arrays.asList(fromJson.getOrder_sn_increment_get_response().getOrder_sn_list()));
					for (int i = 2; i <= page; i++) {
						result = impl.doPinduoduoApiGetIncrementOrderByTime(shopToken, query);
						/**json转换*/
						fromJson = gson.fromJson(result, OrderIncrementFrist.class);
						if(fromJson!=null){
							list.addAll(Arrays.asList(fromJson.getOrder_sn_increment_get_response().getOrder_sn_list()));
						}
					}
				}else{
					list.addAll(Arrays.asList(fromJson.getOrder_sn_increment_get_response().getOrder_sn_list()));
				}
			}
			
			//转换到系统类进行快递监控
			this.logisticsOrderService.conversionToOurSystem(list,shopToken);
			
		}
		
		
		
	}
	
	public static void main(String[] args) {
		
		PinduoduoRequestApi api = new PinduoduoRequestApi();
		ShopTokens shopTokens = new ShopTokens();
//		shopTokens.setCode("79ddc78e02ba40cb8ce53d365863a991a6a7ec71");
//		shopTokens.setState("1212");
//		String initToken = api.initToken(shopTokens);
//		System.out.println(initToken);
		
		shopTokens.setAccessToken("b99aa92d706a405da3da0605fe9321e4626e33b6");
		String result = api.doPinduoduoApiGetExpressInfo(shopTokens);
		System.out.println(result);
		Gson gson = new Gson();
		PinduoduoExpress fromJson = gson.fromJson(result, PinduoduoExpress.class);
//		System.out.println(fromJson);
	}

	/**
	 * 更新快递
	 */
	@Override
	public void doSyncExpress() {
		String hql = "from ShopTokens ke where ke.success = 'true' and ke.ismonitor = 'true'";
		List<ShopTokens> tokens = this.findHql(hql, null);
		if(tokens!=null&&tokens.size()>0){
			String result = this.pinduoduoRequestApi.doPinduoduoApiGetExpressInfo(tokens.get(0));
			
		}
	}
	
	


	

}