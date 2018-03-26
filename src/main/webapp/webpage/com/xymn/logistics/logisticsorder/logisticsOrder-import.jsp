<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>物流跟踪订单</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="jquery,tools"></t:base>
<link rel="stylesheet" href="online/template/ledefault/css/vendor.css">
<link rel="stylesheet" href="online/template/ledefault/css/bootstrap-theme.css">
<link rel="stylesheet" href="online/template/ledefault/css/bootstrap.css">
<link rel="stylesheet" href="online/template/ledefault/css/app.css">

<link rel="stylesheet" href="plug-in/Validform/css/metrole/style.css" type="text/css" />
<link rel="stylesheet" href="plug-in/Validform/css/metrole/tablefrom.css" type="text/css" />

<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
<script type="text/javascript" src="plug-in/easyui/locale/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/tools/syUtil.js"></script>
<script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="plug-in/tools/curdtools_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
<script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>

<script type="text/javascript">
	//编写自定义JS代码
</script>

<style type="text/css">
	select option {
    height: 40px;
    line-height: 40px;
    padding: 0px 10px;
    cursor: pointer;
}
</style>
</head>

<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="logisticsOrderController.do?doAdd" tiptype="1">
		<input type="hidden" id="btn_sub" class="btn_sub" />
		<input type="hidden" id="id" name="id" />
		<div class="tab-wrapper">
			<!-- tab -->
			<ul class="nav nav-tabs">
				<li role="presentation" class="active col-xs-3 text-center"><a href="javascript:void(0);">物流跟踪订单导入</a></li>
			</ul>
			<!-- tab内容 -->
			<div class="con-wrapper" id="con-wrapper1" style="display: block;">
				<div class="row form-wrapper">

					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>店铺名称:</b>
						</div>
						<div class="col-xs-6">
<!-- 							<input id="shopName" name="shopName" type="text" class="form-control" style="width: 92%" datatype="*" ignore="checked" /> -->
							<select  id="shopName" name="shopName" style="width: 300px;" datatype="*" ignore="checked" >
								<option value="">---请选择---</option>
								<c:forEach var="shop" items="${list }">
									<option value="${shop.id }">${shop.shopName }</option>
								</c:forEach>
							</select>
<%-- 						   	<t:dictSelect field="shopName" dictField="id" dictTable="sp_shop_deploy" dictText="shop_name" extendJson="{style:'width:300px'}"></t:dictSelect> --%>
						    <span class="Validform_checktip" style="float: left; height: 0px;"></span> 
						    <label class="Validform_label" style="display: none">店铺名称</label>
						</div>
					</div>
					

					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>选择文件:</b>
						</div>
						<div class="col-xs-6">
							<input id="orderFile" name="orderFile" type="file"  accept=".xlsx" class="form-control col-xs-9" datatype="*" ignore="ignore" />
							<input id="load" name="load" type="button" style="width: 80px;margin-left: 10px;border-radius:5px;height: 30px;line-height: 20px;background-color: #1ab394;" value="加载" >
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">选择文件</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>Sheet页: </b>
						</div>
						<div class="col-xs-6">
							<select id="sheetName" name="sheetName" style="width: 340px;">
								<option value="">---请选择---</option>
							</select>
							<span class="Validform_checktip" style="float: left; height: 0px;"></span> 
							<label class="Validform_label" style="display: none">Sheet页</label>
						</div>
					</div>
					
					
					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>操作 : </b>
						</div>
						<div class="col-xs-6">
							<input type="button" class="btn"   onclick="saveExcelData();" value="执行导入" style="height:30px;width:100px !important;border-radius:5px;background-color: #1AB394;">
						</div>
					</div>
					
					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>温馨提示 : </b>
						</div>
						<div class="col-xs-6">
							<span id="" style="color: green;">快递公司名称【申小通】不匹配,日期格式支持 例:2018-01-01或2018-01-01 01:01:01或2018/01/01或2018/01/01 01:01:01</span>
						</div>
					</div>
					
					<div class="row show-grid">
						<div class="col-xs-3 text-center" style="height: 60px;">
							<b>提示信息 : </b>
						</div>
						<div class="col-xs-6" style="height: 60px;">
							<span id="message" style="color: red;"></span>
						</div>
					</div>

					<div class="row" id="sub_tr" style="display: none;">
						<div class="col-xs-12 layout-header">
							<div class="col-xs-6"></div>
							<div class="col-xs-6">
								<button type="button" onclick="neibuClick();" class="btn btn-default">提交</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
		</div>
	</t:formvalid>

	<script type="text/javascript">
		$(function() {
			//查看模式情况下,删除和上传附件功能禁止使用
			if (location.href.indexOf("load=detail") != -1) {
				$(".jeecgDetail").hide();
			}

			if (location.href.indexOf("mode=read") != -1) {
				//查看模式控件禁用
				$("#formobj").find(":input").attr("disabled", "disabled");
			}
			if (location.href.indexOf("mode=onbutton") != -1) {
				//其他模式显示提交按钮
				$("#sub_tr").show();
			}
		});

		var neibuClickFlag = false;
		function neibuClick() {
			neibuClickFlag = true;
			$('#btn_sub').trigger('click');
		}
		
		$("#load").on('click',function(){
			var orderFile = $("#orderFile").val();
			if(orderFile.lastIndexOf(".xlsx")==-1){
				alertTip("文件格式不对,支持xlsx格式");
				return false;
			}
			var formData = new FormData($( "#formobj" )[0]);
			layer.msg('亲，正在努力解析中......o(*￣︶￣*)o', {
				id:31321,			
	          icon: 16
	          ,time: 0
	          ,shade:[0.08, '#B0C4DE']
	      	});
	         
		     $.ajax({
		          url: 'logisticsOrderController.do?resolvingExcel',  
		          type: 'POST',  
		          data: formData,
		          async: true,
		          cache: false,
		          contentType: false,
		          processData: false,
		          success: function (data) {
		        	  var d = $.parseJSON(data);
						if(d.success) {
							$("#sheetName").empty(); 
							$("#sheetName").append("<option value=\"\">---请选择---</option>");
							for(var i=0;i<d.obj.length;i++){
								$("#sheetName").append("<option value=\""+d.obj[i]+"\">"+d.obj[i]+"</option>");
							}
							
							$("#message").text(d.msg);
						}else{
							$("#message").text(d.msg);
						}
				     layer.close(layer.index);
		          },  
		          error: function (returndata) { 
		        	  layer.close(layer.index); 
		          }  
		     });
		})
		
		
		
		
		
	 	function saveExcelData(){
	 		
	 		var shopName = $("#shopName").val();
	 		if(shopName == ''){
				alertTipTop("店铺名称不能为空");
				return false;
			}
	 		
	 		var orderFile = $("#orderFile").val();
			if(orderFile.lastIndexOf("xlsx") == -1){
				alertTipTop("文件格式不正确");
				return false;
			}
	 		var sheetName = $("#sheetName").val();
			if(sheetName == ''){
				alertTipTop("请选择要导入的sheet页");
				return false;
			}
			
			layer.msg('亲，正在努力导入中......o(*￣︶￣*)o', {
				id:31321,			
	          icon: 16
	          ,time: 0
	          ,shade:[0.08, '#B0C4DE']
	      	});
	     var formData = new FormData($( "#formobj" )[0]);  
	     $.ajax({  
	          url: 'logisticsOrderController.do?saveExcelData',
	          type: 'POST',  
	          data: formData,
	          async: true,
	          cache: false,
	          contentType: false,
	          processData: false,
	          success: function (data) {
	        	  var d = $.parseJSON(data);
	        	  $("#message").text(d.msg);
	        	  layer.close(layer.index); 
	          },
	          error: function (returndata) { 
	        	  layer.close(layer.index); 
	          }  
	     });
	 	}
	</script>
</body>
<script src="webpage/com/xymn/logistics/logisticsorder/logisticsOrder.js"></script>
</html>