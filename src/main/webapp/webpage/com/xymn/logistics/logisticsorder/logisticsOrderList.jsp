<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="logisticsOrderList" checkbox="true" pagination="true" onLoadSuccess="load" fitColumns="true" title="物流跟踪订单" actionUrl="logisticsOrderController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   
   <t:dgCol title="沉默时间(小时)"  field="silenceHour" query="true" extendParams="styler: function(value,row,index){if (value > ${remindHour} ){return 'background-color:#ffee00;color:red;';}},"  align="center" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="是否监控"  field="istail" dictionary="istail" align="center" queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="订单编号"  field="orderCode" formatterjs="orderDetail" align="center" queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="快递单号"  field="expressNumber" formatterjs="orderDetail" align="center" queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="快递公司"  field="expressCompany" align="center" queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="订单时间"  field="orderTime" align="center" hidden="true"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="付款时间"  field="paymentTime" align="center" hidden="true"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="发货时间"  field="deliveryTime" align="center"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="物流跟踪时间"  field="logisticsTailTime" align="center" formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="物流跟踪信息"  field="logisticsTailInfo" align="center" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="交易金额"  field="amount"  queryMode="single" align="center"  width="70"></t:dgCol>
   <t:dgCol title="收件人"  field="consignee"  queryMode="single" align="center" width="70"></t:dgCol>
   <t:dgCol title="省级"  field="province"  queryMode="single" align="center"  width="70"></t:dgCol>
   <t:dgCol title="市级"  field="city"  queryMode="single" align="center" width="70"></t:dgCol>
   <t:dgCol title="区级"  field="region"  queryMode="single" align="center" width="70"></t:dgCol>
   <t:dgCol title="承诺发货时间"  field="promiseDeliveryTime" align="center"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="single"  width="100"></t:dgCol>
   
   
   <t:dgCol title="手机"  field="phone"  queryMode="single" hidden="true" width="100"></t:dgCol>
   <t:dgCol title="街道"  field="street" hidden="true" queryMode="single"  width="255"></t:dgCol>
   <t:dgCol title="商品编号"  field="productCode" hidden="true" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="SKU编号"  field="skuCode" hidden="true" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="外部编号"  field="outsideCode" hidden="true" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="商品名称"  field="productName" hidden="true" queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="商品规格"  field="productSpecification" hidden="true" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="商品单价"  field="productUnitPrice" hidden="true" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="商品数量"  field="productNum" hidden="true" queryMode="single"  width="50"></t:dgCol>
<%--    <t:dgCol title="操作" field="opt" width="100"></t:dgCol> --%>
<%--    <t:dgDelOpt title="删除" url="logisticsOrderController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/> --%>
   <t:dgToolBar title="录入" icon="icon-add" url="logisticsOrderController.do?goAdd" width="900" height="480" funname="add"></t:dgToolBar>
   <t:dgToolBar title="导入订单" icon="icon-add" url="logisticsOrderController.do?importExcel" width="760" height="380" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="logisticsOrderController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="logisticsOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="logisticsOrderController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <t:authFilter/>
 <script src = "webpage/com/xymn/logistics/logisticsorder/logisticsOrderList.js"></script>		
 <script type="text/javascript">
 var remindHour = '${remindHour}';

 $(document).ready(function(){
	 
 });
 
 
 
function orderDetail(value, row, index){
	
	var url = "logisticsOrderController.do?toOrderDetail&id="+ row.id
	var detail = "createdetailwindow('查看','" + url + "',1000,700)"
	var str = "<a style='color:blue' href='#' onclick=\""+detail+"\">"
			+ value + "</a>"
	return str;
	
}
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'logisticsOrderController.do?upload', "logisticsOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("logisticsOrderController.do?exportXls","logisticsOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("logisticsOrderController.do?exportXlsByT","logisticsOrderList");
}

 </script>
 