<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>信息</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="${rootPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${rootPath}/css/style.css">
	<link rel="stylesheet" href="${rootPath}/css/skins/_all-skins.min.css">
	<link rel="stylesheet" href="${rootPath}/plugins/FortAwesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${rootPath}/plugins/ionicons/css/ionicons.min.css">
	<link rel="stylesheet" href="${rootPath}/plugins/fakeloader/fakeLoader.css">
	<link rel="stylesheet" href="${rootPath}/plugins/iCheck/flat/blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini"
	style="background: #ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>用户信息</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i
						class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li><a href="##" onclick="loadSubUrl('${rootPath}/user/list');">用户管理</a></li>
				<li class="active">用户信息</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<a href="${rootPath}/user/list"
						class="btn btn-primary btn-block margin-bottom">返回用户列表</a>
					<div class="box box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">快速导航</h3>
							<div class="box-tools">
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="box-body no-padding">
							<ul class="nav nav-pills nav-stacked">
								<s:iterator var="item" value="#request.pageInfo.result">
									<li id="<s:property value="#item.id"/>"><a
										href="${rootPath}/user/direct?action=info&id=<s:property value="#item.id" />">
											<div class="user-block">
												<img class="img-circle img-bordered-sm"
													src="<s:if test="%{#item.headImgPath == null || #item.headImgPath == ''}">${rootPath}/images/default_avatar.png</s:if><s:else><s:property value="imageServer + #item.headImgPath" /></s:else>"
													alt="用户头像"> <span class='username'><s:property
														value="#item.userName" /></span> <span class='description'><s:property
														value="#item.email" /></span>
											</div>
									</a></li>
								</s:iterator>
							</ul>
						</div>
						<div class="box-footer no-padding">
							<div class="mailbox-controls">
								<div class="pull-right">
									<s:property value="#request.pageInfo.startPos" />
									-
									<s:property value="#request.pageInfo.endPos" />
									/
									<s:property value="#request.pageInfo.totalCount" />
									<div class="btn-group" style="margin-bottom: 5px;">
										<button class="btn btn-default btn-sm">
											<i class="fa fa-chevron-left"></i>
										</button>
										<button class="btn btn-default btn-sm">
											<i class="fa fa-chevron-right"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">用户信息</h3>
						</div>
						<form id="formUser" action="${rootPath}/user/add"
							class="form-horizontal" enctype="multipart/form-data" role="form"
							method="post">
							<div class="box-body">
								<input type="hidden" name="user.id" value="${user.id}">
								<div class="form-group">
									<label for="txtUserCode" class="col-sm-2 control-label">头像</label>
									<div class="col-sm-9" style="margin-left: 1rem;">
										<div class="form-group">
											<input type="hidden" name="user.headImgPath" value="${user.headImgPath}"> 
											<div class="btn btn-default btn-file" style="margin-left: 1rem;">
												<i class="fa fa-paperclip"></i> 选择
												<input id="btnAttachment" type="file" name="image" onchange="btnAttachment_OnChange();">
											</div>
											<span id="spanFileName"></span> 
											<input id="hdnFileName" type="hidden" name="imageFileName"> 
											<span>最大可以上传5M的图片</span>
											<div style="margin-top:0.5rem; margin-left:1rem">
											<img src="<s:if test="user.headImgPath == null || user.headImgPath == ''">${rootPath}/images/default_avatar.png</s:if><s:else><s:property value="imageServer + user.headImgPath" /></s:else>"
												class="img-circle" style="width: 70px; height:70px;" alt="用户头像">
											</div>	
										</div>
									</div>	
								</div>
								<div class="form-group">
									<label for="txtUserCode" class="col-sm-2 control-label">编号</label>
									<div class="col-sm-9">
										<input type="text" name="user.userCode" class="form-control"
											id="txtUserCode" value="${user.userCode}" placeholder="编号">
									</div>
								</div>
								<div class="form-group">
									<label for="txtUserName" class="col-sm-2 control-label">姓名</label>
									<div class="col-sm-9">
										<input type="text" name="user.userName" class="form-control"
											id="txtUserName" value="${user.userName}" placeholder="姓名">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">性别</label>
									<div class="col-sm-9 radio">
										<label> <input type="radio" name="user.sex" value="1"
											<s:if test="user.sex == 1">checked</s:if>>男
										</label> <label> <input type="radio" name="user.sex" value="2"
											<s:if test="user.sex == 2">checked</s:if>>女
										</label>
									</div>
								</div>
								<div class="form-group">
									<label for="txtEmail" class="col-sm-2 control-label">邮箱</label>
									<div class="col-sm-9">
										<input type="email" name="user.email" class="form-control"
											id="txtEmail" value="${user.email}" placeholder="邮箱">
									</div>
								</div>
								<div class="form-group">
									<label for="txtPhone" class="col-sm-2 control-label">手机</label>
									<div class="col-sm-9">
										<input type="tel" name="user.phone" class="form-control"
											id="txtPhone" value="${user.phone}" placeholder="手机">
									</div>
								</div>
								<div class="form-group">
									<label for="txtAddress" class="col-sm-2 control-label">地址</label>
									<div class="col-sm-9">
										<input type="tel" name="user.address" class="form-control"
											id="txtAddress" value="${user.address}" placeholder="地址">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-danger"
											onclick="saveUser();">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
	<div class="alert alert-danger alert-dismissable col-sm-4"
		style="display: none;">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<h4>
			<i class="icon fa fa-ban"></i> 错误
		</h4>
		<div id="MsgContent"></div>
	</div>
	<div class="alert alert-success alert-dismissable col-sm-4"
		style="display: none;">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<h4>
			<i class="icon fa fa-check"></i> 成功
		</h4>
		<div id="MsgContent"></div>
	</div>
	<script src="${rootPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="${rootPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${rootPath}/plugins/iCheck/icheck.min.js"></script>
	<script src="${rootPath}/plugins/fastclick/fastclick.min.js"></script>
	<script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
	<script src="${rootPath}/js/mcm.js"></script>
	<script>
		$(document).ready(function() {
			$("#${user.id}").attr("class", "active");
			// 报错误消息
			var msgType = "${messageType}";
			var msg = "${message}";
			if (msg != "") {
				if (msgType == "1") {
					alertMcmMsg($(".alert-success"), msg);
				} else if (msgType == "2") {
					alertMcmMsg($(".alert-danger"), msg);
				}
			}
		});
		function saveUser() {
			var userCode = $("#txtUserCode").val();
			var userName = $("#txtUserName").val();
			var hasError = false;
			if (userCode == "") {
				alertMcmMsg($(".alert-danger"),
						'<s:text name="msg_usercode_cannot_empty"></s:text>');
				hasError = true;
			}
			if (!hasError && userName == "") {
				alertMcmMsg($(".alert-danger"),
						'<s:text name="msg_username_cannnot_empty"></s:text>');
				hasError = true;
			}
			if (!hasError) {
				$("#formUser").submit();
			}
		}
		function btnAttachment_OnChange() {
			var fileFullName = $("#btnAttachment").val();
			var fileName = fileFullName.substring(fileFullName
					.lastIndexOf("\\") + 1);

			$("#spanFileName").text(fileName);
			$("#hdnFileName").val(fileName);
		}
	</script>
</body>
</html>