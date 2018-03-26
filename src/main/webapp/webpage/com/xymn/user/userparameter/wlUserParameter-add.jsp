<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户参数</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wlUserParameterController.do?doAddUserTime" >
					<input id="id" name="id" type="hidden" value="${wlUserParameterPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							提醒时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="remindHour" name="remindHour" onchange="remindHouronchange();" type="text" style="width: 150px" datatype="/^([1-9]\d*|[0]{1,1})$/" class="inputxt"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<span >注:X小时未获取到最新物流信息</span>
							<label class="Validform_label" style="display: none;">提醒时间</label>
						</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/userparameter/wlUserParameter.js"></script>		
<script type="text/javascript">
function remindHouronchange(){
	var remindHour = $("#remindHour").val();
	if($.trim(remindHour).length==0){
 	  tip("请输入限制时间");
      return false;
	}
	
	var reg = /^([1-9]\d*|[0]{1,1})$/;
	
	if(reg.test(remindHour)){
		
		if(parseInt(remindHour)!=0){
			$("#formobj").submit();
		}else{
			alertTipTop("限制时间不能为0");
		    return false;
		}
		
	}else{
		alertTipTop("请填写正确的限制时间");
	    return false;
	}
	
	
	
};
</script>