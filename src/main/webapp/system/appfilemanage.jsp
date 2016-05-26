<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 更新管理</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${rootPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/css/style.css">
    <link rel="stylesheet" href="${rootPath}/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/FortAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/fakeloader/fakeLoader.css">
    <link rel="stylesheet" href="${rootPath}/plugins/summernote/summernote.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background:#ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>更新管理</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li class="active">更新管理</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<a href="${rootPath}/system/appfilemanage/info?osType=${curOsType}" class="btn btn-primary btn-block margin-bottom">新增</a>
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
						<div class="box-body no-padding">
							<div class="table-responsive mailbox-messages">
								<table class="table table-hover table-striped" style="padding-left:10px; padding-right:10px;">
									<thead>
										<tr>
											<th>应用名称</th>
											<th>版本名称</th>
											<th>更新说明</th>
											<th>是否启用</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator var="item" value="#request.pageInfo.result">
										<tr>
											<td>
												<a href="${rootPath}/system/appfilemanage/info?osType=<s:property value="curOsType" />&id=<s:property value="#item.id" />">
												<s:property value="#item.appName" />
												</a>
											</td>
											<td>
												<s:property value="#item.versionName" />
											</td>
											<td>
												<s:property value="#item.versionDescription" />
											</td>
											<td>
												<s:if test="#item.isUsing == 1">启用</s:if><s:else>停用</s:else>
											</td>
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
										<button class="btn btn-default btn-sm" onclick="prePage();"><i class="fa fa-chevron-left"></i></button>
										<button class="btn btn-default btn-sm" onclick="nextPage();"><i class="fa fa-chevron-right"></i></button>
									</div>
								</div>
							</div>
							<input id="hdnCurrentPageNo" type="hidden" name="currentPageNo" />
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<script src="${rootPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="${rootPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${rootPath}/plugins/fastclick/fastclick.min.js"></script>
	<script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
	<script src="${rootPath}/plugins/summernote/summernote.js"></script>
	<script src="${rootPath}/plugins/summernote/lang/summernote-zh-CN.js"></script>
	<script src="${rootPath}/js/mcm.js"></script>
	<script>
		$(document).ready(function(){
			$("#${curOsType}").attr("class", "active");
			
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
		
		function prePage(){
			if (<s:property value="pageInfo.pageNo" /> == 1){
				alertMcmMsg($(".alert-danger"), "<s:text name='msg_first_page'></s:text>");
			} else {
				$("#hdnCurrentPageNo").val(<s:property value="pageInfo.pageNo" /> - 1);
				search();
			}
		}
		
		function nextPage(){
			if (<s:property value="pageInfo.totalCount" /> == <s:property value="pageInfo.endPos" />){
				alertMcmMsg($(".alert-danger"), "<s:text name='msg_last_page'></s:text>");
			} else {
				$("#hdnCurrentPageNo").val(<s:property value="pageInfo.pageNo" /> + 1);
				search();
			}
		}
	</script>
</body>
</html>