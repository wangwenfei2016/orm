<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="shopDeployList" checkbox="true" pagination="true" fitColumns="false" title="店铺配置" actionUrl="shopDeployController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="店铺名称"  field="shopName" align="center" query="true" queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="平台类型"  field="platformSort" align="center"  queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="授权状态"  field="impowerStatus"  align="center" queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="授权时限"  field="impowerTimeLimit" align="center" formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="用户id"  field="userId" align="center"  hidden="true" queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="state"  field="state" align="center" hidden="true" queryMode="single"  width="300"></t:dgCol>
<%--    <t:dgCol title="操作" field="opt" width="100"></t:dgCol> --%>
<%--    <t:dgDelOpt title="删除" url="shopDeployController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/> --%>
   <t:dgToolBar title="新增店铺" icon="icon-add" url="shopDeployController.do?goAdd" funname="add" width="650" height="300"></t:dgToolBar>
   <t:dgToolBar title="修改店铺" icon="icon-edit" url="shopDeployController.do?goUpdate" funname="update" width="650" height="300"></t:dgToolBar>
   <t:dgToolBar title="删除店铺"  icon="icon-remove" url="shopDeployController.do?doBatchDel" funname="deleteALLSelectre" width="650" height="300"></t:dgToolBar>
   <t:dgToolBar title="店铺授权" icon="icon-tip" url="shopDeployController.do?impowerShop" funname="impowerShop"></t:dgToolBar>
   <t:dgToolBar title="开启监控" icon="icon-tip" url="shopDeployController.do?doMonitoring" funname="doMonitoring"></t:dgToolBar>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="shopDeployController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/xymn/shop/shopdeploy/shopDeployList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
 /**
 *店铺订单监控
 *
 *
 */
 function doMonitoring(title,url,gname) {
	 	gridname=gname;
	     var ids = [];
	     var rows = $("#"+gname).datagrid('getSelections');
	     if(rows.length > 1){
	    	 tip("只能单独开启一个店铺,请选择一条进行监控 ");
	    	 return false;
	     }
	     if (rows.length > 0) {
	     	$.dialog.setting.zIndex = getzIndex(true);
	     	$.dialog.confirm('您确定要开启店铺:['+row[0].shopName+']的监控吗？', function(r) {
	 		   if (r) {
	 				
	 				$.ajax({
	 					url : url,
	 					type : 'post',
	 					data : {
	 						id : rows[0].id
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
	 		});
	 	} else {
	 		tip("请选择需要监控的店铺 ");
	 	}
	 }
	 
 
 /**
  * 多记录刪除請求
  * @param title
  * @param url
  * @param gname
  * @return
  */
 function deleteALLSelectre(title,url,gname) {
	 gridname=gname;
     var ids = [];
     var rows = $("#"+gname).datagrid('getSelections');
     if(rows.length > 1){
    	 tip("请选择一个店铺进行删除");
    	 return false;
     }
     if (rows.length > 0) {
     	$.dialog.setting.zIndex = getzIndex(true);
     	$.dialog.confirm('您确定要删除店铺名称['+rows[0].shopName+']吗？', function(r) {
 		   if (r) {
 				for ( var i = 0; i < rows.length; i++) {
 					ids.push(rows[i].id);
 				}
 				$.ajax({
 					url : url,
 					type : 'post',
 					data : {
 						ids : ids.join(',')
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
 		});
 	} else {
 		tip("请选择需要删除的店铺");
 	}
 }
 
 
 
 

 /**
  * 多店铺授权请求
  * @param title
  * @param url
  * @param gname
  * @return
  */
 function impowerShop(title,url,gname){
		gridname=gname;
	    var ids = [];
	    var rows = $("#"+gname).datagrid('getSelections');
	    if(rows.length>1){
	    	tip("请选择一个店铺进行授权");
	    	return false;
	    }
	    if (rows.length > 0) {
	    	$.dialog.setting.zIndex = getzIndex(true);
	    	$.dialog.confirm('你确定授权此店铺吗?', function(r) {
			   if (r) {
				   var url = "http://mms.pinduoduo.com/open.html?response_type=code&client_id=b5c1844ae5854845910a4d2adc36359c &redirect_uri=http://wldc.xymn.com/rest/pinduoduoOrder/callBack&state=";
				   url = url+rows[0].state;
				   createwindowforshop("授权",url,1500,700);
				}
			});
		} else {
			tip("请选择需要授权的店铺");
		}
 }
 
 function createwindowforshop(title, addurl,width,height) {
		width = width?width:700;
		height = height?height:400;
		if(width=="100%" || height=="100%"){
			width = window.top.document.body.offsetWidth;
			height =window.top.document.body.offsetHeight-100;
		}
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
		if(typeof(windowapi) == 'undefined'){
			$.dialog({
				content: 'url:'+addurl,
				lock : true,
				zIndex: getzIndex(),
				width:width,
				height:height,
				title:title,
				opacity : 0.3,
				cache:false,
			    cancelVal: '确定',
			    cancel: function(){
			    	reloadTable();
			    }
			});
		}else{

			/*W.*/$.dialog({//使用W，即为使用顶级页面作为openner，造成打开的次级窗口获取不到关联的主窗口
				content: 'url:'+addurl,
				lock : true,
				width:width,
				zIndex:getzIndex(),
				height:height,
				parent:windowapi,
				title:title,
				opacity : 0.3,
				cache:false,
			    cancelVal: '确定',
			    cancel: function(){
			    	reloadTable();
			    }
			});

		}
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
		
	}
 
 
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'shopDeployController.do?upload', "shopDeployList");
}

//导出
function ExportXls() {
	JeecgExcelExport("shopDeployController.do?exportXls","shopDeployList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("shopDeployController.do?exportXlsByT","shopDeployList");
}

 </script>