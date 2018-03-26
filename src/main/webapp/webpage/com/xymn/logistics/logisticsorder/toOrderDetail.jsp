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
		
		<div class="tab-wrapper">
			<!-- tab -->
			<ul class="nav nav-tabs">
				<li role="presentation" class="active">
					<a href="javascript:void(0);">订单详情</a>
				</li>
			</ul>
			<!-- tab内容 -->
			<table class="table" style="background-color: #ffffff">
				<tr>
					<td class="text-center">
						<label>
							订单编号:
						</label>
					</td>
					<td class="text-left">
						<span>${orderEntity.orderCode }</span>
					</td>
					<td class="text-center">
						<label>
							订单时间:
						</label>
					</td>
					<td class="text-left">
						<span><fmt:formatDate value="${orderEntity.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</td>
					<td class="text-center">
						<label>
							付款时间:
						</label>
					</td>
					<td class="text-left">
						<span><fmt:formatDate value="${orderEntity.paymentTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</td>
					<td class="text-center">
						<label>
							店铺名称:
						</label>
					</td>
					<td class="text-left">
						<span>${orderEntity.store }</span>
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<label>
							快递单号:
						</label>
					</td>
					<td class="text-left">
						<span>${orderEntity.expressNumber }</span>
					</td>
					<td class="text-center">
						<label>
							快递公司:
						</label>
					</td>
					<td class="text-left">
						<span>${orderEntity.expressCompany }</span>
					</td>
					<td class="text-center">
						<label>
							发货时间:
						</label>
					</td>
					<td class="text-left">
						<span><fmt:formatDate value="${orderEntity.paymentTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</td>
					<td class="text-center">
						<label>
							预计发货时间:
						</label>
					</td>
					<td class="text-left">
						<span><fmt:formatDate value="${orderEntity.deliveryTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<label>
							收货人:
						</label>
					</td>
					<td class="text-left">
						<span>${orderEntity.consignee }</span>
					</td>
					<td class="text-center">
						<label>
							手机:
						</label>
					</td>
					<td class="text-left">
						<span>${orderEntity.phone }</span>
					</td>
					<td class="text-center">
						<label>
							交易金额:
						</label>
					</td>
					<td class="text-left" colspan="4">
						<span>${orderEntity.amount }</span>
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<label>
							收货地址:
						</label>
					</td>
					<td class="text-left" colspan="7">
						<span>${orderEntity.province }-${orderEntity.city }-${orderEntity.region }-${orderEntity.street }</span>
					</td>
				</tr>
			</table>
			<div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
		</div>
		
		
		<div class="tab-wrapper">
			<!-- tab -->
			<ul class="nav nav-tabs">
				<li role="presentation" class="active">
					<a href="javascript:void(0);">商品详情</a>
				</li>
			</ul>
			<!-- tab内容 -->
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<th class="text-center">商品编号</th>
					<th class="text-center">SKU编号</th>
					<th class="text-center">外部编号</th>
					<th class="text-center">商品名称</th>
					<th class="text-center">商品规格</th>
					<th class="text-center">商品单价</th>
					<th class="text-center">数量</th>
				</tr>
				<c:forEach var="pro" items="${productEntities}">
					<tr>
						<td class="text-center info">${pro.productCode }</td>
						<td class="text-center info">${pro.skuCode }</td>
						<td class="text-center info">${pro.outsideCode }</td>
						<td class="text-center info">${pro.productName }</td>
						<td class="text-center info">${pro.productSpecification }</td>
						<td class="text-center info">${pro.productUnitPrice }</td>
						<td class="text-center info">${pro.productNum }</td>
					</tr>
				</c:forEach>
			</table>
			<div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
		</div>
		
		
		<div class="tab-wrapper">
			<!-- tab -->
			<ul class="nav nav-tabs">
				<li role="presentation" class="active">
					<a href="javascript:void(0);">物流详情</a>
				</li>
			</ul>
			<!-- tab内容 -->
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<th class="col-md-2 text-center">时间</th>
					<th class="col-md-10 text-center">跟踪信息</th>
				</tr>
				<c:forEach var="tra" items="${traces}">
				<tr>
					<td class="col-md-2 info">${tra.acceptTime }</td>
					<td class="col-md-10 info">${tra.acceptStation }</td>
				</tr>
				</c:forEach>
			</table>
			<div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
		</div>

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