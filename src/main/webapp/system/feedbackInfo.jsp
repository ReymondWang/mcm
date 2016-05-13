<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 反馈信息</title>
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
					<a href="${rootPath}/system/feedback/list" class="btn btn-primary btn-block margin-bottom">返回反馈列表</a>
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
						<div class="box-header with-border">
							<h3 class="box-title">反馈信息</h3>
						</div>
						<div class="box-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label">内容</label>
									<div class="col-sm-9">
										<textarea class="form-control"  name="feedback.content" rows="6">${feedback.content}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">提交人</label>
									<div class="col-sm-9">
										<input type="text" name="feedback.systemUser.userName" class="form-control"  value="${feedback.systemUser.userName}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">提交日期</label>
									<div class="col-sm-9">
										<input type="text" name="feedback.inputTime" class="form-control"  value="<s:date name="feedback.inputTime" format="yyyy-MM-dd" />">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">系统截图</label>
									<div class="col-sm-9">
									<s:if test="%{feedback.imagePath == null || feedback.imagePath == ''}"></s:if>
									<s:else>
										<img alt="显示图片" class='img-responsive' src="<s:property value="imageServer + feedback.imagePath" />">
									</s:else>
									</div>
								</div>
							</form>
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
	</script>
</body>
</html>