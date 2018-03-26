package com.xymn.logistics.logisticsorder.entity;

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
@Table(name = "wl_logistics_order_product", schema = "")
@SuppressWarnings("serial")
public class LogisticsOrderProductEntity  implements java.io.Serializable {
	
	
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
	/**订单主表id*/
    @Excel(name="主表id",width=15)
	private java.lang.String orderId;
	/**订单编号*/
    @Excel(name="订单编号",width=15)
	private java.lang.String orderCode;
	/**商品编号*/
    @Excel(name="商品编号",width=15)
	private java.lang.String productCode;
	/**SKU编号*/
    @Excel(name="SKU编号",width=15)
	private java.lang.String skuCode;
	/**外部编号*/
    @Excel(name="外部编号",width=15)
	private java.lang.String outsideCode;
	/**商品名称*/
    @Excel(name="商品名称",width=15)
	private java.lang.String productName;
	/**商品规格*/
    @Excel(name="商品规格",width=15)
	private java.lang.String productSpecification;
	/**商品单价*/
    @Excel(name="商品单价",width=15)
	private java.lang.Double productUnitPrice;
	/**商品数量*/
    @Excel(name="商品数量",width=15)
	private java.lang.Integer productNum;
	
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
	 *@return: java.lang.String  订单主表id
	 */
	@Column(name ="ORDER_ID",nullable=true,length=36)
	public java.lang.String getOrderId() {
		return orderId;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单主表id
	 */
	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
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
	 *@return: java.lang.String  商品编号
	 */
	@Column(name ="PRODUCT_CODE",nullable=true,length=50)
	public java.lang.String getProductCode(){
		return this.productCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品编号
	 */
	public void setProductCode(java.lang.String productCode){
		this.productCode = productCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  SKU编号
	 */
	@Column(name ="SKU_CODE",nullable=true,length=50)
	public java.lang.String getSkuCode(){
		return this.skuCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  SKU编号
	 */
	public void setSkuCode(java.lang.String skuCode){
		this.skuCode = skuCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外部编号
	 */
	@Column(name ="OUTSIDE_CODE",nullable=true,length=50)
	public java.lang.String getOutsideCode(){
		return this.outsideCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外部编号
	 */
	public void setOutsideCode(java.lang.String outsideCode){
		this.outsideCode = outsideCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品名称
	 */
	@Column(name ="PRODUCT_NAME",nullable=true,length=100)
	public java.lang.String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品名称
	 */
	public void setProductName(java.lang.String productName){
		this.productName = productName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品规格
	 */
	@Column(name ="PRODUCT_SPECIFICATION",nullable=true,length=50)
	public java.lang.String getProductSpecification(){
		return this.productSpecification;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品规格
	 */
	public void setProductSpecification(java.lang.String productSpecification){
		this.productSpecification = productSpecification;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  商品单价
	 */
	@Column(name ="PRODUCT_UNIT_PRICE",nullable=true,length=30)
	public java.lang.Double getProductUnitPrice(){
		return this.productUnitPrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  商品单价
	 */
	public void setProductUnitPrice(java.lang.Double productUnitPrice){
		this.productUnitPrice = productUnitPrice;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  商品数量
	 */
	@Column(name ="PRODUCT_NUM",nullable=true,length=11)
	public java.lang.Integer getProductNum(){
		return this.productNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  商品数量
	 */
	public void setProductNum(java.lang.Integer productNum){
		this.productNum = productNum;
	}
	

	
	
}
