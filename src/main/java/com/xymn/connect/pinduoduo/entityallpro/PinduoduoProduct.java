package com.xymn.connect.pinduoduo.entityallpro;

import com.xymn.connect.pinduoduo.entity.PinduoduoSku;

/**
 * @Description获取所有店铺商品的json转换类 第三级转换
 * @author aber
 * @Date 2018-03-15 06:32:32
 */
public class PinduoduoProduct {

	/** 拼多多商品id */
	private String goods_id;
	/** 拼多多商品名称 */
	private String goods_name;
	/** 商品图片 */
	private String image_url;
	/** 是否有多个sku 0:没有 1:有 */
	private String is_more_sku;
	private String goods_quantity;
	private String is_onsale;
	/** sku数组 */
	private PinduoduoSku[] sku_list;

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getIs_more_sku() {
		return is_more_sku;
	}

	public void setIs_more_sku(String is_more_sku) {
		this.is_more_sku = is_more_sku;
	}

	public String getGoods_quantity() {
		return goods_quantity;
	}

	public void setGoods_quantity(String goods_quantity) {
		this.goods_quantity = goods_quantity;
	}

	public String getIs_onsale() {
		return is_onsale;
	}

	public void setIs_onsale(String is_onsale) {
		this.is_onsale = is_onsale;
	}

	public PinduoduoSku[] getSku_list() {
		return sku_list;
	}

	public void setSku_list(PinduoduoSku[] sku_list) {
		this.sku_list = sku_list;
	}

}
