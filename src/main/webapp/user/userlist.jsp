<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 桌面</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${rootPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/css/style.css">
    <link rel="stylesheet" href="${rootPath}/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/fakeloader/fakeLoader.css">
    <link rel="stylesheet" href="${rootPath}/plugins/iCheck/flat/blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background:#ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>用户管理</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li class="active">用户管理</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<a href="${rootPath}/user/direct?action=add" class="btn btn-primary btn-block margin-bottom">新增</a>
					<div class="box box-solid">
						<div class="box box-warning">
							<div class="box-header with-border">
								查询条件
							</div>
							<div class="box-body">
								<form id="searchForm" action="${rootPath}/user/list" role="form">
									<div class="form-group">
										<input type="text" name="user.userCode" value="${user.userCode}" class="form-control" placeholder="编号......">
									</div>
									<div class="form-group">
										<input type="text" name="user.userName" value="${user.userName}" class="form-control" placeholder="姓名......">
									</div>
									<div class="form-group">
										<input type="text" name="user.email" value="${user.email}" class="form-control" placeholder="邮箱......">
									</div>
									<div class="form-group">
										<input type="text" name="user.phone" value="${user.phone}" class="form-control" placeholder="手机......">
									</div>
								</form>
							</div>
							<div class="box-footer">
								<button type="submit" class="btn btn-primary pull-right" onclick="search();">查询</button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-header with-border">
							<button class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i></button>
							<button class="btn btn-default btn-sm" onclick="confirmDel();"><i class="fa fa-trash-o"></i></button>
							<button class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
							<form id="formAction" method="post">
							<input id="hdnDelIds" type="hidden" name="delIds" >
							</form>
						</div>
						<div class="box-body no-padding">
							<div class="table-responsive mailbox-messages">
								<table class="table table-hover table-striped" style="padding-left:10px; padding-right:10px;">
									<thead>
										<tr>
											<th>#</th>
											<th>头像</th>
											<th>编号</th>
											<th>姓名</th>
											<th>性别</th>
											<th>邮箱</th>
											<th>手机</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator var="item" value="#request.pageInfo.result">
										<tr>
											<td style="height:2rem; line-height:2rem;"><input type="checkbox" name="chkUser" value="<s:property value="#item.id" />"></td>
											<td style="height:2rem; line-height:2rem;"><img src="<s:if test="%{#item.headImgPath == null || #item.headImgPath == ''}">${rootPath}/images/default_avatar.png</s:if><s:else><s:property value="imageServer + #item.headImgPath" /></s:else>" class="img-circle" style="width:2rem;" alt="用户头像"></td>
											<td style="height:2rem; line-height:2rem;"><s:property value="#item.userCode" /></td>
											<td style="height:2rem; line-height:2rem;"><a href="${rootPath}/user/direct?action=info&id=<s:property value="#item.id" />"><s:property value="#item.userName" /></a></td>
											<td style="height:2rem; line-height:2rem;"><s:if test="%{#item.sex == 1}">男</s:if><s:else>女</s:else></td>
											<td style="height:2rem; line-height:2rem;"><s:property value="#item.email" /></td>
											<td style="height:2rem; line-height:2rem;"><s:property value="#item.phone" /></td>
										</tr>
									</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
						<div class="box-footer no-padding">
							<div class="mailbox-controls">
								<div class="pull-right">
									<s:property value="#request.pageInfo.startPos"/>-<s:property value="#request.pageInfo.endPos"/>/<s:property value="#request.pageInfo.totalCount"/>
									<div class="btn-group" style="margin-bottom:5px;">
										<button class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
										<button class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<div class="alert alert-danger alert-dismissable col-sm-4" style="display:none;">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		<h4><i class="icon fa fa-ban"></i> 错误</h4>
		<div id="MsgContent"></div>
	</div>
	<div class="alert alert-success alert-dismissable col-sm-4" style="display:none;">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		<h4><i class="icon fa fa-check"></i> 成功</h4>
		<div id="MsgContent"></div>
    </div>
    <div class="alert alert-info col-sm-4" style="display:none; background:white;">
    	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
    	<h4><i class="icon fa fa-info"></i> 确认</h4>
    	<div id="MsgContent" class="text-center">你是否确定要删除？</div>
    	<div class="text-center" style="margin-top:1rem;">
    		<button class="btn btn-defalut" onclick="cancelDel();">取消</button>
    		<button class="btn btn-danger" onclick="submitDel();">确定</button>
    	</div>
    </div>
	<script src="${rootPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="${rootPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${rootPath}/plugins/iCheck/icheck.min.js"></script>
	<script src="${rootPath}/plugins/fastclick/fastclick.min.js"></script>
	<script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
	<script src="${rootPath}/js/mcm.js"></script>
	<script>
		$(document).ready(function(){
			// 报错误消息
			var msgType = "${messageType}";
			var msg = "${message}";
			if (msg != ""){
				if (msgType == "1"){
					alertMcmMsg($(".alert-success"), msg);
				} else if (msgType == "2"){
					alertMcmMsg($(".alert-danger"), msg);
				}
			}
		});
	
		$('.mailbox-messages input[type="checkbox"]').iCheck({
			checkboxClass: 'icheckbox_flat-blue',
			radioClass: 'iradio_flat-blue'
		});
		$(".checkbox-toggle").click(function () {
			var clicks = $(this).data('clicks');
	        if (clicks) {
	        	$(".mailbox-messages input[type='checkbox']").iCheck("uncheck");
	        	$(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
	        } else {
	        	$(".mailbox-messages input[type='checkbox']").iCheck("check");
	        	$(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
	        }
	        $(this).data("clicks", !clicks);
	    });
		
		function modifyUser(id){
			loadModeView("user/direct?action=add&id=" + id);
		}
		
		function search(){
			var loader = $("#fakeLoader");
			if (loader){
				loader.fadeIn();
				$("#fakeLoader").fakeLoader({
					timeToHide: 1000000,
					bgColor: "rgba(0, 0, 0, 0.4)",
					spinner: "spinner2"
		        });
			}
			$("#searchForm").submit();
		}
		
		function confirmDel(){
			alertMcmConfirmMsg($(".alert-info"), "<s:text name='msg_del_confim'></s:text>")
		}
		
		function submitDel(){
			dismissMsg($(".alert-info"));
			var delIds = $("#hdnDelIds").val();
			$(".mailbox-messages input[type='checkbox']").each(function(){
				if ($(this).prop("checked")){
					delIds += $(this).val() + ",";
				} else {
					delIds.replace($(this).val() + ",", "");
				}
			});
			$("#hdnDelIds").val(delIds);
			if (delIds == ""){
				alertMcmMsg($(".alert-danger"), "<s:text name='msg_del_need_id'></s:text>");
			} else {
				$("#formAction").attr("action", "${rootPath}/user/del");
				$("#formAction").submit();
			}
		}
		
		function cancelDel(){
			dismissMsg($(".alert-info"));
		}
		
	</script>
</body>
</html>

