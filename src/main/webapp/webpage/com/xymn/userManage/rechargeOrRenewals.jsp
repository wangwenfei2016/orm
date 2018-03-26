<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户充值续费</title>
	<t:base type="jquery,easyui,tools"></t:base>
	<script src="plug-in/date/dateParser.js"></script>
	<script>
        function openDepartmentSelect() {
            $.dialog.setting.zIndex = getzIndex();
            var orgIds = $("#orgIds").val();

            $.dialog({content: 'url:departController.do?departSelect&orgIds='+orgIds, zIndex: getzIndex(), title: '组织机构列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
                    {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackDepartmentSelect, focus: true},
                    {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
                ]}).zindex();

        }

        function callbackDepartmentSelect() {
            var iframe = this.iframe.contentWindow;
            var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
            var nodes = treeObj.getCheckedNodes(true);
            if(nodes.length>0){
                var ids='',names='';
                for(i=0;i<nodes.length;i++){
                    var node = nodes[i];
                    ids += node.id+',';
                    names += node.name+',';
                }
                $('#departname').val(names);
                $('#departname').blur();
                $('#orgIds').val(ids);
            }
        }

        function callbackClean(){
            $('#departname').val('');
            $('#orgIds').val('');
        }

        function setOrgIds() {}
        $(function(){
            $("#departname").prev().hide();
        });
		//实时计算充值数量、充值后数量
		function calculationUnitResult(){
			var rechargeMoney=$("#rechargeAmount").val();//充值金额
			var rechargeAmount=parseInt($("#rechargeAmount").val());
			//alert("rechargeAmount==="+rechargeAmount);
			var unitPrice=parseInt($("#unitPrice").val());
			//alert("unitPrice="+unitPrice);
            //设置充值数量
            $("#rechargeQuantity").val(rechargeAmount/unitPrice);
			//设置充值后数量
			$("#rechargeAfterQuantity").val(parseInt($("#allowance").val())+parseInt($("#rechargeQuantity").val()));

		}
		//实时计算 续费到期时间
		function calculationDayResult(){
            var d=$("#endTime").val();
            var addDay=$("#rechargeDay").val();
            var d1=new Date(d);
            var datePlused = d1.addDays(addDay).format('yyyy-MM-dd hh:mm:ss');
            console.log(datePlused);
            $("#rechargeEndTime").val(datePlused);
		}
        $(document).ready(function(){


        });
	</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tSUserController.do?saveRechargeOrRenewals">
	<input id="id" name="id" type="hidden" value="${user.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="25%" nowrap>
				<label class="Validform_label">  <t:mutiLang langKey="用户名"/>:  </label>
			</td>
			<td class="value" width="85%">
				<c:if test="${user.id!=null }"> <input id="userName" class="inputxt" name="userName"  value="${user.userName }" readonly="readonly" /> </c:if>
				<c:if test="${user.id==null }">
					<input id="userName" class="inputxt" name="userName"  value="${user.userName }" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> <t:mutiLang langKey="姓名"/>: </label></td>
			<td class="value" width="10%">
				<input id="realName" class="inputxt" name="realName" value="${user.realName }"   readonly="readonly"/>
			</td>
		</tr>

		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="common.phone"/>: </label></td>
			<td class="value">
				<input class="inputxt" name="mobilePhone" value="${user.mobilePhone}"  readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="公司名称"/>: </label></td>
			<td class="value">
				<input id="company" name="company" type="text"  class="inputxt"  value="${user.company}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="计费模式"/>: </label></td>
			<td class="value">
				<c:if test="${user.billModel=='0'}"><input type="radio" name="billModel" checked="checked" value="${user.billModel}" id="runti" readonly="readonly">按单</c:if>
				<c:if test="${user.billModel=='1'}"><input type="radio" name="billModel" checked="checked" value="${user.billModel}" id="rday" readonly="readonly">按天</c:if>
			</td>
		</tr>
		<c:if test="${user.billModel=='0'}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable" id="units">
			<tbody><tr>
				<td align="right" width="25%" nowrap="">
					<label class="Validform_label">  余量:  </label>
				</td>
				<td class="value" width="85%">
					<input id="allowance" class="inputxt" name="allowance" value="${user.allowance}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td align="right" width="10%" nowrap=""><label class="Validform_label"> 充值金额: </label></td>
				<td class="value" width="10%">
					<input id="rechargeAmount" class="inputxt" name="rechargeAmount" value="" datatype="d"  >
					<span class="Validform_checktip"></span>
				</td>
			</tr>

			<tr>
				<td align="right" nowrap=""><label class="Validform_label">  单价: </label></td>
				<td class="value">
					<input id="unitPrice" class="inputxt" name="unitPrice" value="" datatype="d" onchange="calculationUnitResult()" >
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 充值数量: </label></td>
				<td class="value">
					<input id="rechargeQuantity" name="rechargeQuantity" type="text"  readonly="readonly" class="inputxt"  value="">
				</td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 充值后数量: </label></td>
				<td class="value">
					<input id="rechargeAfterQuantity" name="rechargeAfterQuantity" type="text"  readonly="readonly" class="inputxt"  value="">
				</td>
			</tr>
			</tbody>
		</table>
		</c:if>
		<c:if test="${user.billModel=='1'}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable" id="day">
			<tbody><tr>
				<td align="right" width="25%" nowrap="">
					<label class="Validform_label">  到期时间:  </label>
				</td>
				<td class="value" width="85%">
					<input  id="endTime" class="inputxt" name="endTime" value="<fmt:formatDate value="${user.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly" >
				<%--	<span class="Validform_checktip"></span>--%>
				</td>
			</tr>
			<tr>
				<td align="right" width="10%" nowrap=""><label class="Validform_label"> 续费金额: </label></td>
				<td class="value" width="10%">
					<input  id="rachargeMoney" class="inputxt" name="rachargeMoney" value="" datatype="d">

				<%--	<span class="Validform_checktip"></span>--%>
				</td>
			</tr>

			<tr>
				<td align="right" nowrap=""><label class="Validform_label">  续费天数: </label></td>
				<td class="value">
					<input id="rechargeDay" class="inputxt" name="rechargeDay" value="" datatype="n"  onchange="calculationDayResult()">
					<%--<span class="Validform_checktip"></span>--%>
				</td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 续费到期时间: </label></td>
				<td class="value">
					<input  id="rechargeEndTime" name="rechargeEndTime" type="text" class="inputxt"  readonly="readonly" >
				<%--	<span class="Validform_checktip"></span>--%>
				</td>
			</tr>

			</tbody>
		</table>
		</c:if>
	</table>
</t:formvalid>
</body>