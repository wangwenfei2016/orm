<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户信息</title>
	<t:base type="jquery,easyui,tools"></t:base>
	<script>

	</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tSUserController.do?savenewpwd" >
	<input id="id" name="id" type="hidden" value="${user.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="25%" nowrap>
				<label class="Validform_label">  <t:mutiLang langKey="用户名"/>:  </label>
			</td>
			<td class="value" width="85%">
				<c:if test="${user.id!=null }">
					<input id="userName" class="inputxt" name="userName" value="${user.userName }"  readonly="readonly"/>
				</c:if>
				<c:if test="${user.id==null }">
					<input id="userName" class="inputxt" name="userName"  value="${user.userName }"  readonly="readonly"/>

				</c:if>
			</td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> <t:mutiLang langKey="姓名"/>: </label></td>
			<td class="value" width="10%">
				<input id="realName" class="inputxt" name="realName" value="${user.realName }"  readonly="readonly"/>

			</td>
		</tr>
		<tr>
			<td align="right" width="10%"><span class="filedzt">原密码:</span></td>
			<td class="value"><input type="password" value="" name="password" class="inputxt"  value="${user.password}"  datatype="*6-18"/>
				<span class="Validform_checktip">原密码至少6个字符,最多18个字符！</span>
			</td>
		</tr>
		<tr>
			<td align="right"><span class="filedzt">新密码:</span></td>
			<td class="value"><input type="password" value="" name="newpassword" class="inputxt" plugin="passwordStrength" datatype="*6-18" errormsg="密码至少6个字符,最多18个字符！" /> <span
					class="Validform_checktip"> 密码至少6个字符,最多18个字符！ </span> <span class="passwordStrength" style="display: none;"> <b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span> </span></td>
		</tr>
		<tr>
			<td align="right"><span class="filedzt">确认新密码:</span></td>
			<td class="value"><input id="newpassword" type="password" recheck="newpassword" class="inputxt" datatype="*6-18" errormsg="两次输入的密码不一致！"> <span class="Validform_checktip"></span></td>
		</tr>
	</table>
</t:formvalid>
</body>