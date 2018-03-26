<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="expressInfoList" checkbox="true" pagination="true" fitColumns="false" title="快递档案" actionUrl="expressInfoController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="快递公司名称"  field="expressName" align="center" query="true" queryMode="single"  width="400"></t:dgCol>
   <t:dgCol title="快递公司编码"  field="expressCode" align="center" query="true" queryMode="single"  width="400"></t:dgCol>
   <t:dgCol title="快递鸟编码"  field="kuaidiniaoCode" align="center" query="true" queryMode="single"  width="400"></t:dgCol>
   <t:dgCol title="平台类别"  field="platformType" dictionary="plattype" align="center" query="true" queryMode="single"  width="400"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="expressInfoController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="expressInfoController.do?goAdd" funname="add" width="650" height="300"></t:dgToolBar>
   <t:dgToolBar title="同步" icon="icon-add" url="expressInfoController.do?doSyncExpress" funname="doSyncExpress" width="650" height="300"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="expressInfoController.do?goUpdate" funname="update" width="650" height="300"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="expressInfoController.do?doBatchDel" funname="deleteALLSelect" ></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="expressInfoController.do?goUpdate" funname="detail" width="650" height="300"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/xymn/basic/expressinfo/expressInfoList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
 function doSyncExpress(title,url,gname){
	 
	 $.ajax({
			url : url,
			type : 'post',
			data : {
			},
			cache : false,
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					reloadTable();
					$("#"+gname).datagrid('unselectAll');
					ids='';
				}
			}
		});
	 
 }
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'expressInfoController.do?upload', "expressInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("expressInfoController.do?exportXls","expressInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("expressInfoController.do?exportXlsByT","expressInfoList");
}

 </script>