<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
   <div id="salesanalysisInfoEntityListtb" style="padding: 3px; height: auto" class="datagrid-toolbar">
    <div name="searchColums" id="searchColums">
     <input id="isShowSearchId" type="hidden" value="false">
     <input id="_sqlbuilder" name="sqlbuilder" type="hidden">
     <form onkeydown="if(event.keyCode==13){salesanalysisInfoEntityListsearch();return false;}" id="pcProductListForm">

      <table>


       <tr>

        <td>
							<span style="display: -moz-inline-box; display: inline-block;">
								<span style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 90px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" title="来料日期">
									时间：
								</span>
								<input type="text" name="createDate_begin" style="width: 120px" class="Wdate" onclick="WdatePicker()">
								<span style="display: -moz-inline-box; display: inline-block; width: 8px; text-align: right;">
									至
								</span>
								<input type="text" name="createDate_end" style="width: 120px" class="Wdate" onclick="WdatePicker()">
							</span>
        </td>
        <td>
							<span style="display: -moz-inline-box; display: inline-block;">
								<span style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 100px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" title="来料质检单号">
									用户名：
								</span>
								<input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text" name="userName" style="width: 120px" class="inuptxt">
							</span>
        </td>

       </tr>
      </table>
     </form>
    </div>
    <div style="height: 30px;" class="datagrid-toolbar">
				<span style="float: left;">
                 <a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-putout" onclick="ExportXls('导出','null','salesanalysisInfoEntityList',null,null)" id="">
						<span>导出</span>
					</a>
				</span>
     <span style="float: right">
					<a href="#" class="easyui-linkbutton l-btn" iconcls="icon-search" onclick="salesanalysisInfoEntityListsearch()" id="">
						<span>查询</span>
					</a>
					<a href="#" class="easyui-linkbutton l-btn" iconcls="icon-reload" onclick="searchReset('salesanalysisInfoEntityList')" id="">
						<span>重置</span>
					</a>
				</span>
    </div>
   </div>

   <t:datagrid name="salesanalysisInfoEntityList" checkbox="true" pagination="true" fitColumns="true" title="销售信息表" actionUrl="salesanalysisInfoEntityController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm:ss"     queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作员"  field="createName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="rechargeType"  queryMode="single" dictionary="recharge"  width="120"></t:dgCol>
   <t:dgCol title="交易金额"  field="tradingAmount"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="userName"  queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="realName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公司名称"  field="company"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机"  field="mobilePhone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
    <t:dgDelOpt title="删除" url="salesanalysisInfoEntityController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/xymn/sa/salesanalysisInfoEntityList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
     $(".datagrid-sort-icon").css("font-weight","bold");
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'salesanalysisInfoEntityController.do?upload', "salesanalysisInfoEntityList");
}

//导出
function ExportXls() {
	JeecgExcelExport("salesanalysisInfoEntityController.do?exportXls","salesanalysisInfoEntityList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("salesanalysisInfoEntityController.do?exportXlsByT","salesanalysisInfoEntityList");
}

 </script>