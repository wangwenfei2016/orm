package com.xymn.connect.pinduoduo.entityexpress;

import java.util.Arrays;

/**
 * @Description 拼多多快递档案二级转换
 * @author aber
 */
public class PinduoduoExpressSecond {

	private ExpressInfo[] logistics_companies;

	public ExpressInfo[] getLogistics_companies() {
		return logistics_companies;
	}

	public void setLogistics_companies(ExpressInfo[] logistics_companies) {
		this.logistics_companies = logistics_companies;
	}

	@Override
	public String toString() {
		return "PinduoduoExpressSecond [logistics_companies=" + Arrays.toString(logistics_companies) + "]";
	}

}
