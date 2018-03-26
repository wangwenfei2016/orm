package com.xymn.connect.kuaidiniao.entity;

import java.util.Arrays;

public class CallBackJson implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String EBusinessID;
	private String Count;
	private String PushTime;
	private DataJson[] Data;

	public String getEBusinessID() {
		return EBusinessID;
	}

	public void setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
	}

	public String getCount() {
		return Count;
	}

	public void setCount(String count) {
		Count = count;
	}

	public String getPushTime() {
		return PushTime;
	}

	public void setPushTime(String pushTime) {
		PushTime = pushTime;
	}

	public DataJson[] getData() {
		return Data;
	}

	public void setData(DataJson[] data) {
		Data = data;
	}

	@Override
	public String toString() {
		return "CallBackJson [EBusinessID=" + EBusinessID + ", Count=" + Count + ", PushTime=" + PushTime + ", Data="
				+ Arrays.toString(Data) + "]";
	}

	
}
