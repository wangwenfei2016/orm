package com.xymn.connect.pinduoduo.entityexpress;


/**
 * @Description 拼多多快递档案一级转换
 * @author aber
 */
public class PinduoduoExpress {
	
	private PinduoduoExpressSecond logistics_companies_get_response;

	public PinduoduoExpressSecond getLogistics_companies_get_response() {
		return logistics_companies_get_response;
	}

	public void setLogistics_companies_get_response(PinduoduoExpressSecond logistics_companies_get_response) {
		this.logistics_companies_get_response = logistics_companies_get_response;
	}

	@Override
	public String toString() {
		return "PinduoduoExpress [logistics_companies_get_response=" + logistics_companies_get_response + "]";
	}

	
}
