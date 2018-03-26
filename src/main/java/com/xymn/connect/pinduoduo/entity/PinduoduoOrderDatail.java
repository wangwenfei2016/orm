package com.xymn.connect.pinduoduo.entity;

public class PinduoduoOrderDatail {

	/** 订单编号 */
	private String order_sn;
	/** 成团时间 */
	private String confirm_time;
	/** 订单创建时间 */
	private String created_time;
	/** 国家 */
	private String country;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 区县 */
	private String town;
	/** 地址 */
	private String address;
	/** 收件人 */
	private String receiver_name;
	/** 收件人电话 */
	private String receiver_phone;
	/** 支付金额（元） */
	private String pay_amount;
	/** 商品金额 */
	private String goods_amount;
	/** 折扣金额 */
	private String discount_amount;
	/** 支付单号 */
	private String pay_no;
	/** 邮费 */
	private String postage;
	/** 支付方式 QQ，WEIXIN,ALIPAY */
	private String pay_type;
	/** 身份证号 */
	private String id_card_num;
	/** 身份证姓名 */
	private String id_card_name;
	/** 快递公司编号 */
	private String logistics_id;
	/** 快递编号 */
	private String tracking_number;
	/** 发货时间 */
	private String shipping_time;
	/** 备注 */
	private String remark;
	/** 是否抽奖订单 */
	private String is_lucky;
	/** 发货状态 1：待发货，2：已发货待签收， 3：已签收 */
	private String order_status;
	/** 承诺发货时间 */
	private String last_ship_time;
	/** 退款状态 1：无售后或售后关闭，2：售 后处理中，3：退款中，4： 退款成功 */
	private String refund_status;
	/** 商家折扣金额 */
	private String seller_discount;
	/** 团长免单优惠金额 */
	private String capital_free_discount;
	/** 平台折扣金额 */
	private String platform_discount;
	

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public String getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_phone() {
		return receiver_phone;
	}

	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}

	public String getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(String pay_amount) {
		this.pay_amount = pay_amount;
	}

	public String getGoods_amount() {
		return goods_amount;
	}

	public void setGoods_amount(String goods_amount) {
		this.goods_amount = goods_amount;
	}

	public String getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(String discount_amount) {
		this.discount_amount = discount_amount;
	}

	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}

	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getId_card_num() {
		return id_card_num;
	}

	public void setId_card_num(String id_card_num) {
		this.id_card_num = id_card_num;
	}

	public String getId_card_name() {
		return id_card_name;
	}

	public void setId_card_name(String id_card_name) {
		this.id_card_name = id_card_name;
	}

	public String getLogistics_id() {
		return logistics_id;
	}

	public void setLogistics_id(String logistics_id) {
		this.logistics_id = logistics_id;
	}

	public String getTracking_number() {
		return tracking_number;
	}

	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}

	public String getShipping_time() {
		return shipping_time;
	}

	public void setShipping_time(String shipping_time) {
		this.shipping_time = shipping_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getIs_lucky() {
		return is_lucky;
	}

	public void setIs_lucky(String is_lucky) {
		this.is_lucky = is_lucky;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getLast_ship_time() {
		return last_ship_time;
	}

	public void setLast_ship_time(String last_ship_time) {
		this.last_ship_time = last_ship_time;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

	public String getSeller_discount() {
		return seller_discount;
	}

	public void setSeller_discount(String seller_discount) {
		this.seller_discount = seller_discount;
	}

	public String getCapital_free_discount() {
		return capital_free_discount;
	}

	public void setCapital_free_discount(String capital_free_discount) {
		this.capital_free_discount = capital_free_discount;
	}

	public String getPlatform_discount() {
		return platform_discount;
	}

	public void setPlatform_discount(String platform_discount) {
		this.platform_discount = platform_discount;
	}

	

}
