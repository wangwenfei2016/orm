package com.xymn.logistics.logisticsorder.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 物流跟踪订单
 * @author onlineGenerator
 * @date 2018-03-01 15:40:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wl_logistics_order", schema = "")
@SuppressWarnings("serial")
public class LogisticsOrderEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	
    /**是否监控 0未监控 1监控*/
    @Excel(name="是否监控",width=15)
    private String istail;
    /**店铺*/
    @Excel(name="店铺",width=15)
    private String store;
    /**物流跟踪状态*/
    @Excel(name="物流跟踪状态",width=15)
    private String logisticsTailStatus;
	/**订单编号*/
    @Excel(name="订单编号",width=15)
	private java.lang.String orderCode;
	/**快递公司*/
    @Excel(name="快递公司",width=15)
	private java.lang.String expressCompany;
	/**快递单号*/
    @Excel(name="快递单号",width=15)
	private java.lang.String expressNumber;
	/**订单时间*/
    @Excel(name="订单时间",width=20,format = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date orderTime;
	/**付款时间*/
    @Excel(name="付款时间",width=20,format = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date paymentTime;
	/**发货时间*/
    @Excel(name="发货时间",width=20,format = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date deliveryTime;
	/**承诺发货时间*/
    @Excel(name="承诺发货时间",width=20,format = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date promiseDeliveryTime;
    /**交易金额*/
    @Excel(name="交易金额")
    private java.lang.Double amount;
	/**收件人*/
    @Excel(name="收件人",width=15)
	private java.lang.String consignee;
	/**手机*/
    @Excel(name="手机",width=15)
	private java.lang.String phone;
	/**省级*/
    @Excel(name="省级",width=15)
	private java.lang.String province;
	/**市级*/
    @Excel(name="市级",width=15)
	private java.lang.String city;
	/**区级*/
    @Excel(name="区级",width=15)
	private java.lang.String region;
	/**街道*/
    @Excel(name="街道",width=15)
	private java.lang.String street;
	
    /**沉默时间(小时)*/
    private Integer silenceHour;
    /**物流跟踪时间*/
    @Excel(name="物流跟踪时间",width=15)
    private Date logisticsTailTime;
    /**物流跟踪信息*/
    @Excel(name="物流跟踪信息",width=15)
    private String logisticsTailInfo;
    /**监控失败原因*/
    private String reason;
    
    
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单编号
	 */
	@Column(name ="ORDER_CODE",nullable=true,length=50)
	public java.lang.String getOrderCode(){
		return this.orderCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单编号
	 */
	public void setOrderCode(java.lang.String orderCode){
		this.orderCode = orderCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  快递公司
	 */
	@Column(name ="EXPRESS_COMPANY",nullable=true,length=100)
	public java.lang.String getExpressCompany(){
		return this.expressCompany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  快递公司
	 */
	public void setExpressCompany(java.lang.String expressCompany){
		this.expressCompany = expressCompany;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  快递单号
	 */
	@Column(name ="EXPRESS_NUMBER",nullable=true,length=50)
	public java.lang.String getExpressNumber(){
		return this.expressNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  快递单号
	 */
	public void setExpressNumber(java.lang.String expressNumber){
		this.expressNumber = expressNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单时间
	 */
	@Column(name ="ORDER_TIME",nullable=true,length=32)
	public java.util.Date getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单时间
	 */
	public void setOrderTime(java.util.Date orderTime){
		this.orderTime = orderTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  付款时间
	 */
	@Column(name ="PAYMENT_TIME",nullable=true,length=32)
	public java.util.Date getPaymentTime(){
		return this.paymentTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  付款时间
	 */
	public void setPaymentTime(java.util.Date paymentTime){
		this.paymentTime = paymentTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发货时间
	 */
	@Column(name ="DELIVERY_TIME",nullable=true,length=32)
	public java.util.Date getDeliveryTime(){
		return this.deliveryTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发货时间
	 */
	public void setDeliveryTime(java.util.Date deliveryTime){
		this.deliveryTime = deliveryTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  承诺发货时间
	 */
	@Column(name ="PROMISE_DELIVERY_TIME",nullable=true,length=32)
	public java.util.Date getPromiseDeliveryTime(){
		return this.promiseDeliveryTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  承诺发货时间
	 */
	public void setPromiseDeliveryTime(java.util.Date promiseDeliveryTime){
		this.promiseDeliveryTime = promiseDeliveryTime;
	}
	
	
	@Column(name ="AMOUNT",columnDefinition = "double(10,2) default '0.00'")
	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收件人
	 */
	@Column(name ="CONSIGNEE",nullable=true,length=100)
	public java.lang.String getConsignee(){
		return this.consignee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收件人
	 */
	public void setConsignee(java.lang.String consignee){
		this.consignee = consignee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机
	 */
	@Column(name ="PHONE",nullable=true,length=32)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省级
	 */
	@Column(name ="PROVINCE",nullable=true,length=50)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省级
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  市级
	 */
	@Column(name ="CITY",nullable=true,length=50)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  市级
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区级
	 */
	@Column(name ="REGION",nullable=true,length=50)
	public java.lang.String getRegion(){
		return this.region;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  区级
	 */
	public void setRegion(java.lang.String region){
		this.region = region;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  街道
	 */
	@Column(name ="STREET",nullable=true,length=255)
	public java.lang.String getStreet(){
		return this.street;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  街道
	 */
	public void setStreet(java.lang.String street){
		this.street = street;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  沉默小时(H)
	 */
	@Column(name ="SILENCE_HOUR",nullable=true,length=3)
	public Integer getSilenceHour() {
		return silenceHour;
	}
	
	/**
	 *方法: 设置java.lang.integer
	 *@param: java.lang.integer  沉默小时(H)
	 */
	public void setSilenceHour(Integer silenceHour) {
		this.silenceHour = silenceHour;
	}
	
	/**
	 *方法: 取得java.lang.Date
	 *@return: java.lang.Date  物流跟踪时间
	 */
	@Column(name ="LOGISTICS_TAIL_TIME",nullable=true,length=32)
	public Date getLogisticsTailTime() {
		return logisticsTailTime;
	}
	
	/**
	 *方法: 设置java.lang.Date
	 *@param: java.lang.Date  物流跟踪时间
	 */
	public void setLogisticsTailTime(Date logisticsTailTime) {
		this.logisticsTailTime = logisticsTailTime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流跟踪信息
	 */
	@Column(name ="LOGISTICS_TAIL_INFO",nullable=true,length=255)
	public String getLogisticsTailInfo() {
		return logisticsTailInfo;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String 物流跟踪时间
	 */
	public void setLogisticsTailInfo(String logisticsTailInfo) {
		this.logisticsTailInfo = logisticsTailInfo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否监控
	 */
	@Column(name ="istail",nullable=true,length=100)
	public String getIstail() {
		return istail;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否监控
	 */
	public void setIstail(String istail) {
		this.istail = istail;
	}
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  店铺
	 */
	@Column(name ="STORE",nullable=true,length=10)
	public String getStore() {
		return store;
	}
	

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺
	 */
	public void setStore(String store) {
		this.store = store;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跟踪状态
	 */
	@Column(name ="LOGISTICS_TAIL_STATUS",nullable=true,length=10)
	public String getLogisticsTailStatus() {
		return logisticsTailStatus;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跟踪状态
	 */
	public void setLogisticsTailStatus(String logisticsTailStatus) {
		this.logisticsTailStatus = logisticsTailStatus;
	}
	
	
	@Column(name ="REASON",nullable=true,length=10)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
