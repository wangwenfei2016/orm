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
</head>

<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="logisticsOrderController.do?doUpdate" tiptype="1">
		<input type="hidden" id="btn_sub" class="btn_sub" />
		<input type="hidden" name="id" value='${logisticsOrderPage.id}'>


		<div class="tab-wrapper">
			<!-- tab -->
			<ul class="nav nav-tabs">
				<li role="presentation" class="active">
					<a href="javascript:void(0);">物流跟踪订单</a>
				</li>
			</ul>
			<!-- tab内容 -->
			<div class="con-wrapper" id="con-wrapper1" style="display: block;">
				<div class="row form-wrapper">
					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>订单编号：</b>
						</div>
						<div class="col-xs-3">
							<input id="orderCode" name="orderCode" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.orderCode}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">订单编号</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>快递公司：</b>
						</div>
						<div class="col-xs-3">
							<input id="expressCompany" name="expressCompany" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.expressCompany}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">快递公司</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>快递单号：</b>
						</div>
						<div class="col-xs-3">
							<input id="expressNumber" name="expressNumber" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.expressNumber}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">快递单号</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>订单时间：</b>
						</div>
						<div class="col-xs-3">
							<input id="orderTime" name="orderTime" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ignore="ignore" value='<fmt:formatDate value='${logisticsOrderPage.orderTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">订单时间</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>付款时间：</b>
						</div>
						<div class="col-xs-3">
							<input id="paymentTime" name="paymentTime" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ignore="ignore" value='<fmt:formatDate value='${logisticsOrderPage.paymentTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">付款时间</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>发货时间：</b>
						</div>
						<div class="col-xs-3">
							<input id="deliveryTime" name="deliveryTime" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ignore="ignore" value='<fmt:formatDate value='${logisticsOrderPage.deliveryTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">发货时间</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>承诺发货时间：</b>
						</div>
						<div class="col-xs-3">
							<input id="promiseDeliveryTime" name="promiseDeliveryTime" type="text" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ignore="ignore" value='<fmt:formatDate value='${logisticsOrderPage.promiseDeliveryTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">承诺发货时间</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>收件人：</b>
						</div>
						<div class="col-xs-3">
							<input id="consignee" name="consignee" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.consignee}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">收件人</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>手机：</b>
						</div>
						<div class="col-xs-3">
							<input id="phone" name="phone" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.phone}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">手机</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>省级：</b>
						</div>
						<div class="col-xs-3">
							<input id="province" name="province" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.province}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">省级</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>市级：</b>
						</div>
						<div class="col-xs-3">
							<input id="city" name="city" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.city}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">市级</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>区级：</b>
						</div>
						<div class="col-xs-3">
							<input id="region" name="region" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.region}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">区级</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>街道：</b>
						</div>
						<div class="col-xs-3">
							<input id="street" name="street" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.street}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">街道</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>商品编号：</b>
						</div>
						<div class="col-xs-3">
							<input id="productCode" name="productCode" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.productCode}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">商品编号</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>SKU编号：</b>
						</div>
						<div class="col-xs-3">
							<input id="skuCode" name="skuCode" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.skuCode}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">SKU编号</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>外部编号：</b>
						</div>
						<div class="col-xs-3">
							<input id="outsideCode" name="outsideCode" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.outsideCode}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">外部编号</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>商品名称：</b>
						</div>
						<div class="col-xs-3">
							<input id="productName" name="productName" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.productName}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">商品名称</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>商品规格：</b>
						</div>
						<div class="col-xs-3">
							<input id="productSpecification" name="productSpecification" type="text" class="form-control" ignore="ignore" value='${logisticsOrderPage.productSpecification}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">商品规格</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>商品单价：</b>
						</div>
						<div class="col-xs-3">
							<input id="productUnitPrice" name="productUnitPrice" type="text" class="form-control" datatype="/^(-?\d+)(\.\d+)?$/" ignore="ignore" value='${logisticsOrderPage.productUnitPrice}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">商品单价</label>
						</div>
					</div>


					<div class="row show-grid">
						<div class="col-xs-3 text-center">
							<b>商品数量：</b>
						</div>
						<div class="col-xs-3">
							<input id="productNum" name="productNum" type="text" class="form-control" datatype="n" ignore="ignore" value='${logisticsOrderPage.productNum}' />
							<span class="Validform_checktip" style="float: left; height: 0px;"></span>
							<label class="Validform_label" style="display: none">商品数量</label>
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
	</script>
</body>
<script src="webpage/com/xymn/logistics/logisticsorder/logisticsOrder.js"></script>
</html>