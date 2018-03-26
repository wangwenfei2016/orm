package com.xymn.connect.kuaidiniao.entity;

public class TracesJson implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String AcceptTime;
	private String AcceptStation;
	private String Remark;

	public String getAcceptTime() {
		return AcceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		AcceptTime = acceptTime;
	}

	public String getAcceptStation() {
		return AcceptStation;
	}

	public void setAcceptStation(String acceptStation) {
		AcceptStation = acceptStation;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	@Override
	public String toString() {
		return "TracesJson {AcceptTime=" + AcceptTime + ", AcceptStation=" + AcceptStation + ", Remark=" + Remark + "}";
	}

	

}
