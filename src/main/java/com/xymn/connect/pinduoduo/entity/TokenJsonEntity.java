package com.xymn.connect.pinduoduo.entity;

/***
 * @Class TokenEntity
 * @author aber
 * @Description 获取token json 转换类
 * @Date 2018-03-15 05:06:36
 */
public class TokenJsonEntity {

	/** 接口调用集合 */
	private String scope;
	/** 令牌token */
	private String access_token;
	/** 令牌剩余秒数 */
	private String expires_in;
	/** 刷新token值 */
	private String refresh_token;
	/** 拼多多店铺id */
	private String owner_id;
	/** 店铺名称 */
	private String owner_name;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

}
