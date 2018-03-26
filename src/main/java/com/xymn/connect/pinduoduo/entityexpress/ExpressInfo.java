package com.xymn.connect.pinduoduo.entityexpress;


/**
 * @Description 拼多多快递档案三级转换
 * @author aber
 */
public class ExpressInfo {

	private Integer id;
	private String logistics_company;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogistics_company() {
		return logistics_company;
	}

	public void setLogistics_company(String logistics_company) {
		this.logistics_company = logistics_company;
	}

	@Override
	public String toString() {
		return "ExpressInfo [id=" + id + ", logistics_company=" + logistics_company + "]";
	}
	

}
