<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户增加/编辑</title>
<t:base type="jquery,easyui,tools"></t:base>

</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tSUserController.do?saveUser" >
	<input id="id" name="id" type="hidden" value="${user.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="25%" nowrap>
                <label class="Validform_label">  <t:mutiLang langKey="用户名"/>:  </label>
            </td>
			<td class="value" width="85%">
                <c:if test="${user.id!=null }">  <input id="userName" class="inputxt" name="userName" readonly="readonly" validType="t_s_base_user,userName,id" value="${user.userName }" datatype="s2-10" />
                    <span class="Validform_checktip"> <t:mutiLang langKey="username.rang2to10"/></span> </c:if>
                <c:if test="${user.id==null }">
                    <input id="userName" class="inputxt" name="userName" validType="t_s_base_user,userName,id"  value="${user.userName }" datatype="s2-10" />
                    <span class="Validform_checktip"> 2-10位数字、字母、下划线字符</span>
                </c:if>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> <t:mutiLang langKey="姓名"/>: </label></td>
			<td class="value" width="10%">
                <input id="realName" class="inputxt" name="realName" value="${user.realName }" datatype="zh-cn" maxlength="4" minlength="2"/>
                <span class="Validform_checktip"><t:mutiLang langKey="fill.realname"/></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="common.phone"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="mobilePhone" value="${user.mobilePhone}" datatype="m" errormsg="手机号码不正确" ignore="ignore" maxlength="11"/>
                <span class="Validform_checktip">11位手机号码</span>
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

                <t:dictSelect id="billModel" field="billModel" typeGroupCode="billModel" hasLabel="false" defaultVal="${user.billModel==null?'0':(user.billModel)}" type="radio"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>
		<%--<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.role"/>: </label></td>
			<td class="value" nowrap>
                <input id="roleid" name="roleid" type="hidden" value="${id}"/>
                <input name="roleName" id="roleName" class="inputxt" value="${roleName }" readonly="readonly" datatype="*" />
                <t:choose hiddenName="roleid" hiddenid="id" textname="roleName" url="userController.do?roles" name="roleList" icon="icon-search" title="common.role.list" isclear="true" isInit="true"></t:choose>
                <span class="Validform_checktip"><t:mutiLang langKey="role.muti.select"/></span>
            </td>
		</tr>--%>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="Email"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="email" value="${user.email}"  validType="t_s_user,email,id" datatype="e" errormsg="邮箱格式不正确!" minlength="5" maxlength="20"/>
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
            <td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.address"/>: </label></td>
            <td class="value">
                <input class="inputxt" name="address" value="${user.address}"  datatype="*" minlength="5" maxlength="20" />
                <span class="Validform_checktip">5-20个字符</span>
            </td>
        </tr>
        <%--<tr>
            <td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.common.dev"/>: </label></td>
            <td class="value">

                <t:dictSelect id="devFlag" field="devFlag" typeGroupCode="dev_flag" hasLabel="false" defaultVal="${user.devFlag==null?'0':(user.devFlag)}" type="radio"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
        </tr>--%>
	</table>
</t:formvalid>
</body>