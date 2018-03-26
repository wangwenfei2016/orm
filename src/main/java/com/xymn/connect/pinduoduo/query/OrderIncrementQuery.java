package com.xymn.connect.pinduoduo.query;

/**
 * @Description 订单增量查询接口
 * @author Aber
 * @Date 2018-03-16 12:12:25
 */
public class OrderIncrementQuery {

	/** String 必填 1:非抽奖订单 2：抽奖订单 */
	private String is_lucky_flag;

	/** String 必填 发货状态，1：待发货，2：已发货待签收，3：已签收 5：全部 */
	private String order_status;

	/**
	 * String 必填， 最后更新时间开始时间的时间戳， 指格林威治时间 1970 年 01 月 01 日 00 时 00 分 00 秒 (北京时间
	 * 1970 年 01 月 01 日 08 时 00 分 00 秒) 起至现在的总秒数
	 */
	private String start_updated_at;
	/**
	 * String 必填，最后更新时间结束时间的时间戳， 指格林威治时间 1970 年 01 月 01 日 00 时 00 分 00 秒 (北京时间
	 * 1970 年 01 月 01 日 08 时 00 分 00 秒) 起至现在的总秒数 PS：开始时间结束时间间距不超过 30 分钟
	 */
	private String end_updated_at;

	/** int 非必填 返回数量，默认 100。最大 100 */
	private Integer page_size = 100;

	/** int 非必填 返回页码 默认 1，页码从 1 开始 PS：当前采用分页返回，数量和页数会一起传，如果不传，则采用 默认值 */
	private Integer page = 1;

	/** String 必填 ,售后状态 1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功 5：全部 */
	private String refund_status;


	public String getIs_lucky_flag() {
		return is_lucky_flag;
	}

	public void setIs_lucky_flag(String is_lucky_flag) {
		this.is_lucky_flag = is_lucky_flag;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getStart_updated_at() {
		return start_updated_at;
	}

	public void setStart_updated_at(String start_updated_at) {
		this.start_updated_at = start_updated_at;
	}

	public String getEnd_updated_at() {
		return end_updated_at;
	}

	public void setEnd_updated_at(String end_updated_at) {
		this.end_updated_at = end_updated_at;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

}
