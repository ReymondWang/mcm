<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 应用文件信息</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${rootPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/css/style.css">
    <link rel="stylesheet" href="${rootPath}/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/FortAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/fakeloader/fakeLoader.css">
    <link rel="stylesheet" href="${rootPath}/plugins/iCheck/flat/blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background:#ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>应用文件信息</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li><a href="##" onclick="loadSubUrl('${rootPath}/system/appfilemanage/list');">更新管理</a></li>
				<li class="active">应用文件信息</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<a href="${rootPath}/system/appfilemanage/list?osType=${curOsType}" class="btn btn-primary btn-block margin-bottom">返回更新管理</a>
					<div class="box box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">前端平台</h3>
							<div class="box-tools">
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="box-body no-padding">
							<ul class="nav nav-pills nav-stacked">
								<s:iterator var="item" value="osTypes">
								<li id="<s:property value="#item.dictItemCode"/>">
									<a href="${rootPath}/system/appfilemanage/list?osType=<s:property value="#item.dictItemCode" />">
										<s:property value="#item.dictItemValue" />
									</a>
								</li>
								</s:iterator>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">应用文件信息</h3>
						</div>
						<form id="formFile" action="${rootPath}/system/appfilemanage/save"
							class="form-horizontal" enctype="multipart/form-data" role="form"
							method="post">
							<div class="box-body">
								<input type="hidden" name="entity.id" value="${entity.id}">
								<div class="form-group">
									<label for="txtAppName" class="col-sm-2 control-label">应用名称</label>
									<div class="col-sm-9">
										<input type="text" name="entity.appName" class="form-control"
											id="txtAppName" value="${entity.appName}" placeholder="应用名称">
									</div>
								</div>
								<div class="form-group">
									<label for="txtVersionCode" class="col-sm-2 control-label">版本号</label>
									<div class="col-sm-9">
										<input type="text" name="entity.versionCode" class="form-control"
											id="txtVersionCode" value="${entity.versionCode}" placeholder="版本号">
									</div>
								</div>
								<div class="form-group">
									<label for="txtVersionDescription" class="col-sm-2 control-label">版本说明</label>
									<div class="col-sm-9">
										<textarea id="txtVersionDescription" name="entity.versionDescription" class="form-control" rows="5" placeholder="版本说明"><s:property value="entity.versionDescription" /></textarea>
									</div>
								</div>
								<div id="fileAndroid" class="form-group">
									<label for="txtTitleImgPath" class="col-sm-2 control-label">更新文件</label>
									<div class="col-sm-9">
										<input id="hdnFileName" type="hidden" name="selFileName">
										<div class="btn btn-default btn-file">
											<i class="fa fa-paperclip"></i> 选择
											<input id="btnAttachment" type="file" name="appFile" onchange="btnAttachment_OnChange();">
										</div>
										<span id="spanFileName"></span>
									</div>
								</div>
								<div id="fileIOS" class="form-group">
									<label for="txtAppFileUrl" class="col-sm-2 control-label">更新地址</label>
									<div class="col-sm-9">
										<input type="text" name="entity.fileName" class="form-control"
											id="txtAppFileUrl" value="${entity.fileName}" placeholder="更新地址">
									</div>
								</div>
								<div class="form-group">
									<label for="chkIsUsing" class="col-sm-2 control-label">是否启用</label>
									<div class="col-sm-9">
										<input id="chkIsUsing" type="checkbox" name="entity.isUsing" value="1" <s:if test="entity.isUsing == 1">checked</s:if>>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-danger" onclick="saveFileInfo();">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<s:if test="entity.id != 0">
										<button type="button" class="btn btn-danger" onclick="confirmDel();">&nbsp;&nbsp;删除&nbsp;&nbsp;</button>
										</s:if>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
	<script src="${rootPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="${rootPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${rootPath}/plugins/fastclick/fastclick.min.js"></script>
	<script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
	<script src="${rootPath}/plugins/iCheck/icheck.min.js"></script>
	<script src="${rootPath}/js/mcm.js"></script>
	<script>
		$(document).ready(function(){
			$("#${curOsType}").attr("class", "active");
			
			if ("${curOsTypeName}" == "安卓"){
				$("#fileAndroid").css("display", "block");
				$("#fileIOS").css("display", "none");
			} else {
				$("#fileAndroid").css("display", "none");
				$("#fileIOS").css("display", "block");
			}
			
			$('#chkIsUsing').iCheck({
				checkboxClass: 'icheckbox_flat-blue'
			});
			
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
		
		function btnAttachment_OnChange(){
			var fileFullName = $("#btnAttachment").val();
			var fileName = fileFullName.substring(fileFullName.lastIndexOf("\\") + 1);

			$("#spanFileName").text(fileName);
			$("#hdnFileName").val(fileName);
		}
		
		function saveFileInfo(){
			$("#formFile").submit();
		}
	</script>
</body>
</html>