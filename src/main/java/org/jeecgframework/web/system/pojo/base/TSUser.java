package org.jeecgframework.web.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 系统用户表
 *  @author  张代浩
 */
@Entity
@Table(name = "t_s_user")
@PrimaryKeyJoinColumn(name = "id")
public class TSUser extends TSBaseUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String signatureFile;// 签名文件

	@Excel(name = "手机" ,width = 20)
	private String mobilePhone;// 手机
	@Excel(name = "办公电话",width = 20)
	private String officePhone;// 办公电话
	@Excel(name = "邮箱",width = 25)

	private String email;// 邮箱
	/**创建时间*/
	private java.util.Date createDate;
	/**创建人ID*/
	private java.lang.String createBy;
	/**创建人名称*/
	private java.lang.String createName;
	/**修改时间*/
	private java.util.Date updateDate;
	/**修改人*/
	private java.lang.String updateBy;
	/**修改人名称*/
	private java.lang.String updateName;
	/**头像*/
	private java.lang.String portrait;
	/**开发权限标志*/
	private java.lang.String devFlag;
	private String userType;//用户类型  1:系统用户 \2接口用户
	private String personType;//人员类型
	private String sex;//性别
	private String empNo;//工号
	private String citizenNo;//身份证号
	private String fax;//传真
	private String address;//联系地址
	private String post;//邮编
	private String memo;//备注

	private String  billModel;//计费模式
	private String  qqNumber; //qq帐号
	private Integer  allowance;//余量
	private Date endTime;//到期日期
	/**公司名称*/
	private java.lang.String company;
	/**上次登录时间*/
	@Excel(name="上次登录时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date lastLoginTiime;
	/**表状态*/
	@Excel(name="表状态",width=15)
	private java.lang.Integer tableStatus;
	/**表名称*/
	@Excel(name="表名称",width=15)
	private java.lang.String tableName;

	
	@Column(name = "dev_flag", length = 2)
	public String getDevFlag() {
		return devFlag;
	}

	public void setDevFlag(String devFlag) {
		this.devFlag = devFlag;
	}
	@Column(name = "signatureFile", length = 100)
	public String getSignatureFile() {
		return this.signatureFile;
	}

	public void setSignatureFile(String signatureFile) {
		this.signatureFile = signatureFile;
	}

	@Column(name = "mobilePhone", length = 30)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "officePhone", length = 20)
	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="create_date",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人ID
	 */
	@Column(name ="create_by",nullable=true,length=32)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人ID
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="create_name",nullable=true,length=32)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="update_date",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人ID
	 */
	@Column(name ="update_by",nullable=true,length=32)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人ID
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="update_name",nullable=true,length=32)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	@Column(name = "portrait", length = 100)
	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	
	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Column(name = "person_type")
	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "emp_no")
	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	@Column(name = "citizen_no")
	public String getCitizenNo() {
		return citizenNo;
	}

	public void setCitizenNo(String citizenNo) {
		this.citizenNo = citizenNo;
	}

	@Column(name = "fax")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "post")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "bill_model")
	public String getBillModel() {
		return billModel;
	}

	public void setBillModel(String billModel) {
		this.billModel = billModel;
	}
	@Column(name = "qq_number")
	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	@Column(name = "allowance")
	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司名称
	 */

	@Column(name ="company",nullable=true,length=32)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上次登录时间
	 */

	@Column(name ="last_login_tiime",nullable=true,length=32)
	public java.util.Date getLastLoginTiime(){
		return this.lastLoginTiime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上次登录时间
	 */
	public void setLastLoginTiime(java.util.Date lastLoginTiime){
		this.lastLoginTiime = lastLoginTiime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  表状态
	 */

	@Column(name ="TABLE_STATUS",nullable=true,length=32)
	public java.lang.Integer getTableStatus(){
		return this.tableStatus;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  表状态
	 */
	public void setTableStatus(java.lang.Integer tableStatus){
		this.tableStatus = tableStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  表名称
	 */

	@Column(name ="TABLE_NAME",nullable=true,length=32)
	public java.lang.String getTableName(){
		return this.tableName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  表名称
	 */
	public void setTableName(java.lang.String tableName){
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "TSUser{" +
				"signatureFile='" + signatureFile + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", officePhone='" + officePhone + '\'' +
				", email='" + email + '\'' +
				", createDate=" + createDate +
				", createBy='" + createBy + '\'' +
				", createName='" + createName + '\'' +
				", updateDate=" + updateDate +
				", updateBy='" + updateBy + '\'' +
				", updateName='" + updateName + '\'' +
				", portrait='" + portrait + '\'' +
				", devFlag='" + devFlag + '\'' +
				", userType='" + userType + '\'' +
				", personType='" + personType + '\'' +
				", sex='" + sex + '\'' +
				", empNo='" + empNo + '\'' +
				", citizenNo='" + citizenNo + '\'' +
				", fax='" + fax + '\'' +
				", address='" + address + '\'' +
				", post='" + post + '\'' +
				", memo='" + memo + '\'' +
				", billModel='" + billModel + '\'' +
				", qqNumber='" + qqNumber + '\'' +
				", allowance=" + allowance +
				", endTime=" + endTime +
				", company='" + company + '\'' +
				", lastLoginTiime=" + lastLoginTiime +
				", tableStatus=" + tableStatus +
				", tableName='" + tableName + '\'' +
				'}';
	}
}