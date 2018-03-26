package com.xymn.connect.pinduoduo.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.connect.pinduoduo.query.OrderIncrementQuery;

/**
 * @Description 拼多多调用api
 * @author aber
 * @Date 2018-03-12 09:30:25
 */

@Component
public class PinduoduoRequestApi {
	
	
	

	/***
	 * @Description  调用获取快递公司档案
	 * @return 返回json数据
	 * @throws Exception 
	 * @throws ParseException 
	 */
	public String doPinduoduoApiGetExpressInfo(ShopTokens shopToken)  {
		
		Map<String, Object> params = new HashMap<>();
		params.put("type", "pdd.logistics.companies.get");
		params.put("client_id", PinduoduoStaticParameter.CLIENT_ID);
		params.put("timestamp", new java.util.Date().getTime()+"");
		params.put("data_type", "JSON");
		params.put("client_secret", PinduoduoStaticParameter.CLIENT_SECRET);
		
		params.put("access_token", shopToken.getAccessToken());
		
		
		//发送秦明生成
		String sign = getSign(params);
		
		if(sign==null){
			return null;
		}
		
		params.put("sign", sign);
		
		return this.sendPost(PinduoduoStaticParameter.REQUEST_URI, params);
	}



	/**
	 * 获取初始化TOKEN
	 * @throws Exception
	 */
	public String initToken(ShopTokens shopToken)  {

		String result = null;

		Map<String, Object> params = new HashMap<>();
		params.put("client_id", PinduoduoStaticParameter.CLIENT_ID);
		params.put("client_secret", PinduoduoStaticParameter.CLIENT_SECRET);
		params.put("redirect_uri", PinduoduoStaticParameter.REDIRECT_URI);
		
		params.put("state", shopToken.getState());
		if(org.jeecgframework.core.util.StringUtil.isNotEmpty(shopToken.getCode())){
			params.put("code", shopToken.getCode());
		}else{
			return null;
		}
		params.put("grant_type", PinduoduoStaticParameter.GRANT_TYPE);
		
		try {
			result = this.sendPost(PinduoduoStaticParameter.GETTOKEN_URI, params);
		} catch (Exception e) {
			return null;
		}
		
		return result;
	}
	
	/**
	 * 刷新token
	 * @param shopToken
	 * @return
	 */
	public String refeshToken(ShopTokens shopToken) {
		
		String result = null;
		//参数
		Map<String, Object> params = new HashMap<>();
		params.put("client_id", PinduoduoStaticParameter.CLIENT_ID);
		params.put("client_secret", PinduoduoStaticParameter.CLIENT_SECRET);
		params.put("access_token", shopToken.getAccessToken());
		params.put("refresh_token", shopToken.getRefreshToken());
		params.put("grant_type", "refresh_token");
		
		params.put("state", shopToken.getState());
		result = this.sendPost(PinduoduoStaticParameter.GETTOKEN_URI, params);
		
		return result;
	}
	
	/***
	 * 获取拼多多店铺所有商品
	 * @param shopToken
	 * @return 返回json数据
	 */
	public String doPinduoduoApiGetProductListForShop(ShopTokens shopToken){
		
		
		return null;
	}
	
	
	/***
	 * @Description  调用增量接口获取拼多多店铺时间段内订单
	 * @param shopToken 店铺授权令牌
	 * @param query 查询条件
	 * @return 返回json数据
	 * @throws Exception 
	 * @throws ParseException 
	 */
	public String doPinduoduoApiGetIncrementOrderByTime(ShopTokens shopToken , OrderIncrementQuery query )  {
		
		Map<String, Object> params = new HashMap<>();
		params.put("type", "pdd.order.number.list.increment.get");
		params.put("client_id", PinduoduoStaticParameter.CLIENT_ID);
		params.put("timestamp", new java.util.Date().getTime()+"");
		params.put("data_type", "JSON");
		params.put("client_secret", PinduoduoStaticParameter.CLIENT_SECRET);
		
		params.put("access_token", shopToken.getAccessToken());
		
		params.put("is_lucky_flag", query.getIs_lucky_flag());
		params.put("start_updated_at", query.getStart_updated_at());
		params.put("end_updated_at", query.getEnd_updated_at());
		params.put("refund_status", query.getRefund_status());

		params.put("order_status", query.getOrder_status());
		params.put("page", query.getPage());
		params.put("page_size", query.getPage_size());
		
		//发送秦明生成
		String sign = getSign(params);
		
		if(sign==null){
			return null;
		}
		
		params.put("sign", sign);
		
		return this.sendPost(PinduoduoStaticParameter.REQUEST_URI, params);
	}
	
	
	
	/**
	 * 测试API
	 * @throws Exception
	 */
	public String doPinduoduoApi() throws Exception {

		String result = null;

//		Map<String, Object> params = new HashMap<>();
//		params.put("type", "pdd.order.information.get");//例:pdd.order.number.list.get
//		params.put("client_id", PinduoduoStaticParameter.CLIENT_ID);
//		params.put("access_token", PinduoduoStaticParameter.ACCESS_TOKEN);
//		params.put("timestamp", new java.util.Date().getTime()+"");
//		params.put("data_type", "JSON");
//		params.put("grant_type", PinduoduoStaticParameter.GRANT_TYPE);
//		params.put("client_secret", PinduoduoStaticParameter.CLIENT_SECRET);
		
		
//		params.put("order_status", "5");
//		params.put("page", "1");
//		params.put("page_size", "100");
//		params.put("is_onsale", "1");
		
//		params.put("order_sn", "180315-269470478081848");//订单详情
		
		
//		params.put("is_lucky_flag", "1");
//		params.put("start_updated_at", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-03-15 15:17:00").getTime()/1000);
//		params.put("end_updated_at", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-03-15 15:27:00").getTime()/1000);
//		params.put("refund_status", "5");
		
		
		
		//测试签名
		Map<String, Object> params = new HashMap<>();
//		params.put("type", "pdd.order.number.list.get");//例:pdd.order.number.list.get
		params.put("client_id", PinduoduoStaticParameter.CLIENT_ID);
		params.put("access_token", PinduoduoStaticParameter.ACCESS_TOKEN);
		params.put("client_secret", PinduoduoStaticParameter.CLIENT_SECRET);
		params.put("state", PinduoduoStaticParameter.STATUS);
		params.put("code", "code");
		params.put("refresh_token", "0d4c35c3c6ec42d4a32c87f1b72ffd55c1ec8f1d");
		params.put("grant_type", "refresh_token");
		
		//获取签名
//		String sign = getSign(params);
		
//		System.out.println(sign);
		
//		params.put("sign", sign);
		
		result = this.sendPost(PinduoduoStaticParameter.GETTOKEN_URI, params);
//		result = this.sendPost(PinduoduoStaticParameter.REQUEST_URI, params);
		
		return result;
		
	}
	
	
	
	
	
	
	/**
	 * 获取签名
	 * @param params 参数集合
	 * @return sign
	 * @throws Exception
	 */
	private String getSign(Map<String, Object> params) {
		
		String sign = null;
		// 第一步：检查参数是否已经排序
	    String[] keys = params.keySet().toArray(new String[0]);
	    Arrays.sort(keys);
	    
	    // 第二步：把所有参数名和参数值串在一起
	    StringBuilder query = new StringBuilder();
	    for (int i = 0; i < keys.length; i++) {
	    	query.append(keys[i]+params.get(keys[i]));
		}
	    
	    sign = PinduoduoStaticParameter.CLIENT_SECRET+query.toString()+PinduoduoStaticParameter.CLIENT_SECRET;
	    
	    String md5Str = null;
	    try {
	    	md5Str = this.MD5(sign, "UTF-8");
		} catch (Exception e) {
			md5Str = null;
		}
		
	    return md5Str;
	}
	
	 /**
     * 向指定 URL 发送POST方法的请求     
     * @param url 发送请求的 URL    
     * @param params 请求的参数集合     
     * @return 远程资源的响应结果
     */
	private  String sendPost(String url, Map<String, Object> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;        
        StringBuilder result = new StringBuilder(); 
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数            
            Gson gson = new Gson();
            out.write(gson.toJson(params));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {            
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

	/**
     * MD5加密
     * @param str 内容       
     * @param charset 编码方式
	 * @throws Exception 
     */
	private  String MD5(String str, String charset) throws Exception {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(str.getBytes(charset));
	    byte[] result = md.digest();
	    StringBuffer sb = new StringBuffer(32);
	    for (int i = 0; i < result.length; i++) {
	        int val = result[i] & 0xff;
	        if (val <= 0xf) {
	            sb.append("0");
	        }
	        sb.append(Integer.toHexString(val));
	    }
	    return sb.toString().toUpperCase();
	}


}
