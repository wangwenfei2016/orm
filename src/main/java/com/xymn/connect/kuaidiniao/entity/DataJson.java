package com.xymn.connect.kuaidiniao.entity;

import java.util.Arrays;

public class DataJson implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String EBusinessID;
	private String OrderCode;
	private String ShipperCode;
	private String LogisticCode;
	private String Success;
	private String Reason;
	private String State;
	private String CallBack;
	private TracesJson[] Traces;

	public String getEBusinessID() {
		return EBusinessID;
	}

	public void setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
	}

	public String getOrderCode() {
		return OrderCode;
	}

	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}

	public String getShipperCode() {
		return ShipperCode;
	}

	public void setShipperCode(String shipperCode) {
		ShipperCode = shipperCode;
	}

	public String getLogisticCode() {
		return LogisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
	}

	public String getSuccess() {
		return Success;
	}

	public void setSuccess(String success) {
		Success = success;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCallBack() {
		return CallBack;
	}

	public void setCallBack(String callBack) {
		CallBack = callBack;
	}

	public TracesJson[] getTraces() {
		return Traces;
	}

	public void setTraces(TracesJson[] traces) {
		Traces = traces;
	}

	@Override
	public String toString() {
		return "DataJson [EBusinessID=" + EBusinessID + ", OrderCode=" + OrderCode + ", ShipperCode=" + ShipperCode
				+ ", LogisticCode=" + LogisticCode + ", Success=" + Success + ", Reason=" + Reason + ", State=" + State
				+ ", CallBack=" + CallBack + ", Traces=" + Arrays.toString(Traces) + "]";
	}


}
