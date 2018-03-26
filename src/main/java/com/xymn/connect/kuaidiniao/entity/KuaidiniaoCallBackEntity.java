package com.xymn.connect.kuaidiniao.entity;

import java.util.Date;

public class KuaidiniaoCallBackEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	/** 快递鸟反馈时间 */
	private String pushTime;
	/** 快递鸟用户电商ID */
	private String ebusinessId;
	/** 订单编号 */
	private String orderCode;
	/** 快递鸟对应的快递公司编码 */
	private String shipperCode;
	/** 快递单号 */
	private String logisticCode;
	/** 快递鸟返回状态 成功与否：true,false */
	private String success;
	/** 原因 */
	private String reason;
	/** 快递鸟状态 物流状态: 0-无轨迹，1-已揽收，2-在途中，3-签收,4-问题件 */
	private String state;
	/** 订阅接口的Bk值 */
	private String callBack;
	/** 快递鸟跟踪时时信息 */
	private String traces;
	/** 自定义系统调用时间 */
	private Date refreshTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public String getEbusinessId() {
		return ebusinessId;
	}

	public void setEbusinessId(String ebusinessId) {
		this.ebusinessId = ebusinessId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}

	public String getTraces() {
		return traces;
	}

	public void setTraces(String traces) {
		this.traces = traces;
	}

	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

}
