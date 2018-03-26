package com.xymn.sa.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 销售信息表
 * @author onlineGenerator
 * @date 2018-03-02 15:06:24
 * @version V1.0   
 *mobile_phone
 */
@Entity
@Table(name = "t_sa_salesAnalysis_info", schema = "")
@SuppressWarnings("serial")
public class SalesanalysisInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	@Excel(name="创建人名称",width=15)
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd hh:mm:ss")
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
	/**充值类型*/
	@Excel(name="充值类型",width=15,dicCode="recharge")
	private java.lang.Integer rechargeType;
	/**交易金额*/
	@Excel(name="交易金额",width=15)
	private java.lang.Double tradingAmount;
	/**用户名*/
	@Excel(name="用户名",width=15)
	private java.lang.String userName;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private java.lang.String realName;
	/**公司名称*/
	@Excel(name="公司名称",width=15)
	private java.lang.String company;
	/**手机*/
	@Excel(name="手机",width=15)
	private java.lang.String mobilePhone;
	/**充值单价*/
	@Excel(name="充值单价",width=15)
	private java.lang.Double payUnitPrice;
	/**充值数量*/
	@Excel(name="充值数量",width=15)
	private java.lang.Integer payNumber;
	/**续费天数*/
	@Excel(name="续费天数",width=15)
	private java.lang.Integer rechargeDay;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  充值类型
	 */

	@Column(name ="RECHARGE_TYPE",nullable=true,length=32)
	public java.lang.Integer getRechargeType(){
		return this.rechargeType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  充值类型
	 */
	public void setRechargeType(java.lang.Integer rechargeType){
		this.rechargeType = rechargeType;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  交易金额
	 */

	@Column(name ="TRADING_AMOUNT",nullable=true,length=32)
	public java.lang.Double getTradingAmount(){
		return this.tradingAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  交易金额
	 */
	public void setTradingAmount(java.lang.Double tradingAmount){
		this.tradingAmount = tradingAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户名
	 */

	@Column(name ="USER_NAME",nullable=true,length=32)
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名
	 */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */

	@Column(name ="REAL_NAME",nullable=true,length=32)
	public java.lang.String getRealName(){
		return this.realName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setRealName(java.lang.String realName){
		this.realName = realName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司名称
	 */

	@Column(name ="COMPANY",nullable=true,length=32)
	public java.lang.String getCompany(){
		return this.company;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司名称
	 */
	public void setCompany(java.lang.String company){
		this.company = company;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机
	 */

	@Column(name ="MOBILE_PHONE",nullable=true,length=32)
	public java.lang.String getMobilePhone(){
		return this.mobilePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机
	 */
	public void setMobilePhone(java.lang.String mobilePhone){
		this.mobilePhone = mobilePhone;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  充值单价
	 */

	@Column(name ="PAY_UNIT_PRICE",nullable=true,length=32)
	public java.lang.Double getPayUnitPrice(){
		return this.payUnitPrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  充值单价
	 */
	public void setPayUnitPrice(java.lang.Double payUnitPrice){
		this.payUnitPrice = payUnitPrice;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  充值数量
	 */

	@Column(name ="PAY_NUMBER",nullable=true,length=32)
	public java.lang.Integer getPayNumber(){
		return this.payNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  充值数量
	 */
	public void setPayNumber(java.lang.Integer payNumber){
		this.payNumber = payNumber;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  续费天数
	 */

	@Column(name ="RECHARGE_DAY",nullable=true,length=32)
	public java.lang.Integer getRechargeDay(){
		return this.rechargeDay;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  续费天数
	 */
	public void setRechargeDay(java.lang.Integer rechargeDay){
		this.rechargeDay = rechargeDay;
	}
}
