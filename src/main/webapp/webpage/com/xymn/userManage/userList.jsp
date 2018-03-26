<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<title>用户列表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px">
		<div id="tSUserListtb" style="padding:3px; height: auto" class="datagrid-toolbar">
			<div name="searchColums" id="searchColums"><input id="isShowSearchId" type="hidden" value="false"><input
					id="_sqlbuilder" name="sqlbuilder" type="hidden"><input id="_complexSqlbuilder"
																			name="complexSqlbuilder" type="hidden">
				<form onkeydown="if(event.keyCode==13){tSUserListsearch();return false;}" id="tSUserListForm">
					<table>

						<tr>
							<td>
							<span style="display: -moz-inline-box; display: inline-block;">
								<span style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 100px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" title="用户名">
									用户名：
								</span>
								<input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text" name="userName" style="width: 120px" class="inuptxt">
							</span>
							</td>
							<td>
							<span style="display: -moz-inline-box; display: inline-block;">
								<span style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 90px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" title="手机">
									手机：
								</span>
								<input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text" name="mobilePhone" style="width: 120px" class="inuptxt">
							</span>
							</td>
							<td>
							<span style="display: -moz-inline-box; display: inline-block;">
								<span style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 100px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" title="姓名">
									姓名：
								</span>
								<input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text" name="realName" style="width: 120px" class="inuptxt">
							</span>
							</td>
							<td>
							<span style="display: -moz-inline-box; display: inline-block;">
								<span style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 100px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" title="公司名称">
									公司名称：
								</span>
								<input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text" name="company" style="width: 120px" class="inuptxt">
							</span>
							</td>
							<td>
								<span style="display: -moz-inline-box; display: inline-block;">
									<span style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 90px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" title="表状态"> 表状态： </span>
									<select name="tableStatus" width="100" style="width: 120px">
										<option value="">---请选择---</option>
										<option value="1">正常</option>
										<option value="0">停用</option>
									</select>
								</span>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div style="height: 30px;" class="datagrid-toolbar">
			<span style="float: left;">
				<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-add" onclick="add('新增用户','tSUserController.do?addorupdate','tSUserList',1200,700)" id="ab1"> 新增用户  </a>
				<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-edit" onclick="update('修改用户','tSUserController.do?addorupdate','tSUserList',1200,700)" id="ab2">修改用户</a>
				<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-add" onclick="update('充值续费','tSUserController.do?goRechargeOrRenewals','tSUserList',1200,700)" id="ab3"> 充值续费  </a>
				<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-edit" onclick="lockObj('禁用','tSUserController.do?lock&lockvalue=0','tSUserList',1200,700)" id="ab4">禁用</a>
				<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-remove" onclick="unlockObj('启用','tSUserController.do?lock&lockvalue=1','tSUserList',null,null)" id="ab5">启用</a>
				<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-redo" onclick="ExportXls()" id="ab6">导出</a>
			</span>
				<span style="float: right">
				<a href="#" class="easyui-linkbutton l-btn" iconcls="icon-search" onclick="tSUserListsearch()" id="ab8">查询 </a>
				<a href="#" class="easyui-linkbutton l-btn" iconcls="icon-reload" onclick="searchReset('tSUserList')" id="ab9"> 重置 </a>
			</span>
			</div>

		</div>
		<t:datagrid name="tSUserList" title="用户筛选" actionUrl="tSUserController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group" sortName="createDate,userName" sortOrder="asc,desc" checkbox="true">
			<t:dgCol title="序号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="用户状态" sortable="false" field="status" width="82" replace="common.active_1,common.inactive_0,super.admin_-1"></t:dgCol>
			<t:dgCol title="用户名" sortable="true" field="userName" query="true" width="55"></t:dgCol>
			<t:dgCol title="姓名" field="realName" query="true" width="66"></t:dgCol>
			<t:dgCol title="公司名称" field="company"  width="80" query="true"></t:dgCol>
			<t:dgCol title="手机" sortable="false" field="mobilePhone" query="true" width="70"></t:dgCol>
			<t:dgCol title="开通时间" field="createDate" formatter="yyyy-MM-dd hh:mm:ss" width="134"></t:dgCol>
			<t:dgCol title="上次登录时间" field="lastLoginTiime"   formatter="yyyy-MM-dd hh:mm:ss" width="134"></t:dgCol>
			<t:dgCol title="计费模式" field="billModel" dictionary="billModel" width="60" ></t:dgCol>
			<t:dgCol title="到期时间" field="endTime" formatter="yyyy-MM-dd hh:mm:ss" width="110"></t:dgCol>
			<t:dgCol title="余量" field="allowance" width="53"></t:dgCol>
			<t:dgCol title="表名称" field="tableName" width="110"></t:dgCol>
			<t:dgCol title="表状态" field="tableStatus" width="53" query="true"  replace="common.active_1,common.inactive_0,super.admin_-1"></t:dgCol>
			<t:dgCol title="common.operation" field="opt" width="140"></t:dgCol>
			<t:dgFunOpt funname="deleteDialog(id)" title="common.delete" urlclass="ace_button"  urlfont="fa-trash-o"></t:dgFunOpt>
			<t:dgFunOpt funname="updateStatus(id,tableStatus)" title="更改表状态" urlclass="ace_button"  urlfont="fa fa-edit"></t:dgFunOpt>
		</t:datagrid>
	</div>
</div>
<script>
    $(function() {
        var datagrid = $("#tSUserListtb");
        datagrid.find("div[name='searchColums']").find("form#tSUserListForm").append($("#realNameSearchColums div[name='searchColumsRealName']").html());
        $("#realNameSearchColums").html('');
        datagrid.find("div[name='searchColums']").find("form#tSUserListForm").append($("#tempSearchColums div[name='searchColums']").html());
        $("#tempSearchColums").html('');
        $(".datagrid-cell").css("font-weight","bold");
    });
</script>
<script type="text/javascript">
    function deleteDialog(id){
        var url = "tSUserController.do?deleteDialog&id=" + id
        createwindow("删除模式", url, 200, 100);
    }
    function updateStatus(id,tableStatus){
        var url = "tSUserController.do?updateStatus&id=" + id+"&tableStatus=" + tableStatus
        createwindow("确认信息", url, 100, 50);
    }
    function lockObj(title,url, id) {

        gridname=id;
        var rowsData = $('#'+id).datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
            return;
        }
        url += '&id='+rowsData[0].id;

        $.dialog.confirm('<t:mutiLang langKey="common.lock.user.tips"/>', function(){
            lockuploadify(url, '&id');
        }, function(){
        });
    }
    function unlockObj(title,url, id) {
        gridname=id;
        var rowsData = $('#'+id).datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
            return;
        }
        url += '&id='+rowsData[0].id;

        $.dialog.confirm('<t:mutiLang langKey="common.unlock.user.tips"/>', function(){
            lockuploadify(url, '&id');
        }, function(){
        });
    }


    function lockuploadify(url, id) {
        $.ajax({
            async : false,
            cache : false,
            type : 'POST',
            url : url,// 请求的action路径
            error : function() {// 请求失败处理函数

            },
            success : function(data) {
                var d = $.parseJSON(data);
                if (d.success) {
                    var msg = d.msg;
                    tip(msg);
                    reloadTable();
                }
            }
        });
    }
</script>

<script type="text/javascript">
    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'tSUserController.do?upload', "tSUserList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("tSUserController.do?exportXls", "tSUserList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("tSUserController.do?exportXlsByT", "tSUserList");
    }
</script>