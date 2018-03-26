package com.xymn.connect.kuaidiniao.entity;

/**
 * 发送请求和返回消息实体类
 * 
 * @author aber
 *
 */
public class SendBackEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	/** 快递单号 */
	private String logisticCode;
	/** 快递编码 */
	private String shipperCode;
	/** 订单编号 */
	private String orderCode;
	/** 传送指定参数 特指用户id */
	private String callBack;
	/** 修改时间 */
	private String updateTime;
	/** 账户id */
	private String ebusinessId;
	/** 状态 */
	private String success;
	/** 失败原因 */
	private String reason;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}

	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getEbusinessId() {
		return ebusinessId;
	}

	public void setEbusinessId(String ebusinessId) {
		this.ebusinessId = ebusinessId;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}

	@Override
	public String toString() {
		return "SendBackEntity [id=" + id + ", logisticCode=" + logisticCode + ", shipperCode=" + shipperCode
				+ ", orderCode=" + orderCode + ", updateTime=" + updateTime + ", ebusinessId=" + ebusinessId
				+ ", success=" + success + ", reason=" + reason + "]";
	}

}
