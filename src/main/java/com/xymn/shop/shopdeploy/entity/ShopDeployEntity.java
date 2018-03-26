package com.xymn.shop.shopdeploy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 店铺配置
 * @author onlineGenerator
 * @date 2018-03-01 15:12:41
 * @version V1.0   
 *
 */
@Entity
@Table(name = "sp_shop_deploy", schema = "")
@SuppressWarnings("serial")
public class ShopDeployEntity implements java.io.Serializable {
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
	/**店铺名称*/
    @Excel(name="店铺名称",width=15)
		
	private java.lang.String shopName;
	/**平台类型*/
    @Excel(name="平台类型",width=15)
		
	private java.lang.String platformSort;
	/**授权状态*/
    @Excel(name="授权状态",width=15)
		
	private java.lang.String impowerStatus;
	/**授权时限*/
    @Excel(name="授权时限",width=15,format = "yyyy-MM-dd")
		
	private java.util.Date impowerTimeLimit;
    /**用户id*/
    private String userId;
    /**获取code的state,其唯一性和shop_tokens中的state*/
    private String state;
	
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
	 *@return: java.lang.String  店铺名称
	 */
	@Column(name ="SHOP_NAME",nullable=true,length=100)
	public java.lang.String getShopName(){
		return this.shopName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺名称
	 */
	public void setShopName(java.lang.String shopName){
		this.shopName = shopName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  平台类型
	 */
	@Column(name ="PLATFORM_SORT",nullable=true,length=32)
	public java.lang.String getPlatformSort(){
		return this.platformSort;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  平台类型
	 */
	public void setPlatformSort(java.lang.String platformSort){
		this.platformSort = platformSort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授权状态
	 */
	@Column(name ="IMPOWER_STATUS",nullable=true,length=10)
	public java.lang.String getImpowerStatus(){
		return this.impowerStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授权状态
	 */
	public void setImpowerStatus(java.lang.String impowerStatus){
		this.impowerStatus = impowerStatus;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  授权时限
	 */
	@Column(name ="IMPOWER_TIME_LIMIT",nullable=true,length=32)
	public java.util.Date getImpowerTimeLimit(){
		return this.impowerTimeLimit;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  授权时限
	 */
	public void setImpowerTimeLimit(java.util.Date impowerTimeLimit){
		this.impowerTimeLimit = impowerTimeLimit;
	}
	
	@Column(name ="USER_ID",nullable=true,length=50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	@Column(name ="STATE",nullable=true,length=50)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
