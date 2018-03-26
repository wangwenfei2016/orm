<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户信息</title>
	<t:base type="jquery,easyui,tools,autocomplete"></t:base>
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


        function afterTips() {
			tip("修改基本信息成功")
		}

	</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="false" usePlugin="password" layout="table"  action="tSUserController.do?saveUser" callback="afterTips" >
	<input id="id" name="id" type="hidden" value="${user.id }"/>
	<input id="orgIds" name="orgIds" type="hidden" value="${orgIds}"/>
	<input id="roleid" name="roleid" type="hidden" value="${roleid}"/>
	<div class="panel-header" style="width: 100%;"><div class="panel-title messager-title">基本信息</div><div class="panel-tool messager-tool"></div></div>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable" id="baseInfo" name="baseInfo">
		<tr>
			<td align="right" width="25%" nowrap>
				<label class="Validform_label"><t:mutiLang langKey="用户名"/>: </label>
			</td>
			<td class="value" width="85%">
				<c:if test="${user.id!=null }"> ${user.userName } </c:if>
				<c:if test="${user.id==null }">
					<input id="userName" class="inputxt" name="userName" validType="t_s_base_user,userName,id" value="${user.userName }" datatype="s2-10" />
					<span class="Validform_checktip"> </span>
				</c:if>
			</td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"><span style="color: red">*</span><t:mutiLang langKey="姓名"/>: </label></td>
			<td class="value" width="10%">
				<input id="realName" class="inputxt" name="realName" value="${user.realName }" datatype="zh-cn" maxlength="4" minlength="2"/>
				<span class="Validform_checktip">2-4位汉字</span>
			</td>
		</tr>

		<tr>
			<td align="right" nowrap><label class="Validform_label"> <span style="color: red">*</span> <t:mutiLang langKey="common.phone"/>: </label></td>
			<td class="value">
				<input class="inputxt" name="mobilePhone" value="${user.mobilePhone}" datatype="m" errormsg="手机号码不正确" ignore="ignore" maxlength="11"/>
				<span class="Validform_checktip">11位数字号码</span>

			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="公司名称"/>: </label></td>
			<td class="value">
				<input id="company" name="company" type="text"  class="inputxt" datatype="*" value="${user.company}" maxlength="25" minlength="6"/>
				<span class="Validform_checktip">6-25个字符</span>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="计费模式"/>: </label></td>
			<td class="value">
				<t:dictSelect id="billModel" field="billModel" typeGroupCode="billModel" hasLabel="false" defaultVal="${user.billModel==null?'0':(user.billModel)}" type="radio" readonly="readonly"></t:dictSelect>
				<%--<span class="Validform_checktip"></span>--%>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="Email"/>: </label></td>
			<td class="value">
				<input class="inputxt" name="email" value="${user.email}"  validType="t_s_user,email,id" datatype="e" errormsg="邮箱格式不正确!"  minlength="5" maxlength="20"/>
				<span class="Validform_checktip">5-20个字符</span>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="QQ"/>: </label></td>
			<td class="value">
					<input class="inputxt" name="qqNumber" value="${user.qqNumber}"  validType="t_s_user,qq_number,id" datatype="qq" errormsg="QQ格式不正确!" minlength="4" maxlength="10"/>
					<span class="Validform_checktip">4-10个数字</span>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="联系地址"/>: </label></td>
			<td class="value">
				<input class="inputxt" name="address" value="${user.address}"  datatype="*" minlength="5" maxlength="20" />
				<span class="Validform_checktip">5-20个字符</span>
			</td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="余量"/>: </label></td>
			<td class="value">
						<input class="inputxt" name="allowance" value="${user.allowance}" readonly="readonly"/>
			</td>

		</tr>
		<tr>
			<td class="value" align="center" colspan="2">
				<input class="btn" type="submit" value="保存" style="height:30px;background-color:#1a9182;width:100px !important;border-radius:5px">
				<input class="btn" type="button" value="修改密码" style="height:30px;background-color:#1a9182;width:100px !important;border-radius:5px" onclick="add('<t:mutiLang langKey="common.change.password"/>','tSUserController.do?changepassword','',750,350)">
			</td>

		</tr>
	</table>
</t:formvalid>
</body>