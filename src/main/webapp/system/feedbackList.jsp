<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 反馈列表</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${rootPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/css/style.css">
    <link rel="stylesheet" href="${rootPath}/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/FortAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="${rootPath}/plugins/fakeloader/fakeLoader.css">
    <link rel="stylesheet" href="${rootPath}/plugins/iCheck/flat/blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background:#ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>意见反馈</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li class="active">意见反馈</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<div class="box box-solid">
						<div class="box box-warning">
							<div class="box-header with-border">
								查询条件
							</div>
							<div class="box-body">
								<form id="searchForm" action="${rootPath}/system/feedback/list" role="form">
									<div class="form-group">
										<input type="text" name="content" value="${content}" class="form-control" placeholder="反馈内容">
									</div>
									<div class="form-group">
										<div class="input-group">
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</div>
											<input id="startDate" type="text" name="startDate" value="${startDate}" class="form-control pull-right" placeholder="开始日期">
										</div>
									</div>
									<div class="form-group">
										<div class="input-group">
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</div>
											<input id="endDate" type="text" name="endDate" value="${endDate}" class="form-control pull-right" placeholder="结束日期">
										</div>
									</div>
									<input id="hdnCurrentPageNo" type="hidden" name="currentPageNo" />
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
						<div class="box-body no-padding">
							<div class="table-responsive mailbox-messages">
								<table class="table table-hover table-striped" style="padding-left:10px; padding-right:10px;">
									<thead>
										<tr>
											<th>操作</th>
											<th>内容</th>
											<th>反馈者</th>
											<th>反馈日期</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator var="item" value="#request.pageInfo.result">
										<tr>
											<td><a href="${rootPath}/system/feedback/show?id=<s:property value="#item.id" />">查看</a></td>
											<td><s:property value="#item.content" /></td>
											<td><s:property value="#item.inputUser.userName" /></td>
											<td><s:date name="#item.inputTime" format="yyyy-MM-dd" /></td>
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
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<script src="${rootPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="${rootPath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${rootPath}/plugins/iCheck/icheck.min.js"></script>
	<script src="${rootPath}/plugins/fastclick/fastclick.min.js"></script>
	<script src="${rootPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="${rootPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
	<script src="${rootPath}/js/mcm.js"></script>
	<script>
		$(document).ready(function(){
			$("#startDate").datepicker({format:"yyyy-mm-dd", language:"zh-CN", todayHighlight:"true"});
			$("#endDate").datepicker({format:"yyyy-mm-dd", language:"zh-CN", todayHighlight:"true"});
		})
		
		function search(){
			$("#searchForm").submit();
		}
	</script>
</body>
</html>