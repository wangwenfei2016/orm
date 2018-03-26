package com.xymn.connect.pinduoduo.api;

public class PinduoduoStaticParameter {
	
	public static final String ACCESS_TOKEN = "d35a3aee10da47c3be19c0cf7d1bc4cfbdc7d198";
	public static final String CLIENT_ID = "b5c1844ae5854845910a4d2adc36359c";//创建时获取
	public static final String CLIENT_SECRET = "662157bb6f6d1b377f73819bef181113bf96764c";//创建时获取
	public static final String CODE = "9a0688cd93e34804b3e08eb4d66e3ee8e5bb219c";//创建时获取
	//授权类型 ，值为authorization_code
	public static final String GRANT_TYPE = "authorization_code";
	//回调地址
	public static final String REDIRECT_URI = "http://wldc.xymn.com/rest/pinduoduoOrder/callBack";
	//获取Token地址
	public static final String GETTOKEN_URI = "http://open-api.pinduoduo.com/oauth/token";
	//正式环境请求地址
	public static final String REQUEST_URI = "http://gw-api.pinduoduo.com/api/router";
	//状态
	public static final String STATUS = "1212";


}
