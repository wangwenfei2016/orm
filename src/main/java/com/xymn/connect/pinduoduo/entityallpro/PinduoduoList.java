package com.xymn.connect.pinduoduo.entityallpro;


/**
 * @Description获取所有店铺商品的json转换类 第二级转换
 * @author aber
 * @Date 2018-03-15 06:32:32
 */
public class PinduoduoList {
	
	//总数
	private String total_count;
	//商品集合
	private PinduoduoProduct[] goods_list;

	public String getTotal_count() {
		return total_count;
	}

	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}

	public PinduoduoProduct[] getGoods_list() {
		return goods_list;
	}

	public void setGoods_list(PinduoduoProduct[] goods_list) {
		this.goods_list = goods_list;
	}

}
