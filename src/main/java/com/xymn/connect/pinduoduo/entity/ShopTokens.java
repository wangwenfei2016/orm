package com.xymn.connect.pinduoduo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "wl_shop_tokens", schema = "")
public class ShopTokens implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	/** 用户id */
	private String userId;
	/** 初次获取token的必须参数code */
	private String code;
	/** 令牌:access_token */
	private String accessToken;
	/** 可以刷新access_token令牌的 taken */
	private String refreshToken;
	/** token是否有效  值:true or false */
	private String success;
	/** 权限接口值 */
	private String scope;
	/** 调用接口剩余秒数 */
	private String expiresIn;
	/** 拼多多店铺id */
	private String ownerId;
	/** 拼多多店铺名称 */
	private String ownerName;
	/**拼多多获取token和店铺模块的店铺对应*/
	private String state;
	/**错误原因*/
	private String reason;
	/**是否启动监听 true:启动 , false:停止*/
	private String ismonitor;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USER_ID", nullable = true, length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "CODE", nullable = true, length = 100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ACCESS_TOKEN", nullable = true, length = 100)
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(name = "REFESH_TOKEN", nullable = true, length = 100)
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}


	@Column(name = "SUCCESS", nullable = true, length = 10)
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	@Column(name = "SCOPE", nullable = true, length = 255)
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@Column(name = "EXPIRES_IN", nullable = true, length = 10)
	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	@Column(name = "OWNER_ID", nullable = true, length = 10)
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	@Column(name = "OWNER_NAME", nullable = true, length = 10)
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column(name = "STATE", nullable = true, length = 100)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "REASON", nullable = true, length = 100)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name = "ISMONITOR", nullable = true, length = 20)
	public String getIsmonitor() {
		return ismonitor;
	}

	public void setIsmonitor(String ismonitor) {
		this.ismonitor = ismonitor;
	}
	
	

}
