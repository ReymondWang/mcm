<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 桌面</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="../plugins/fakeloader/fakeLoader.css">
    <link rel="stylesheet" href="../plugins/iCheck/flat/blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background:#ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>用户管理</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('../desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li class="active">用户管理</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="box box-primary">
				<div class="box-header with-border">
					<button class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i></button>
					<div class="btn-group">
						<button class="btn btn-default btn-sm" onclick="addUser();"><i class="fa fa-pencil-square-o"></i></button>
						<button class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i></button>
					</div>
					<button class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
					<div class="pull-right">
						<div class="has-feedback">
							<input type="text" class="form-control input-sm" placeholder="请输入查询条件......">
							<span class="glyphicon glyphicon-search form-control-feedback"></span>
						</div>
					</div>
				</div>
				<div class="box-body no-padding">
					<div class="table-responsive mailbox-messages">
						<table class="table table-hover table-striped" style="padding-left:10px; padding-right:10px;">
							<thead>
								<tr>
									<th>#</th>
									<th>编号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>邮箱</th>
									<th>手机</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="checkbox"></td>
									<td>wangyn</td>
									<td><a href="read-mail.html">王亚南</a></td>
									<td>男</td>
									<td>zisetianguang@sina.com</td>
									<td>13764275155</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="box-footer no-padding">
					<div class="mailbox-controls">
						<div class="pull-right">
							1-50/200
							<div class="btn-group" style="margin-bottom:5px;">
								<button class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
								<button class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<script src="../plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
	<script src="../plugins/iCheck/icheck.min.js"></script>
	<script src="../plugins/fastclick/fastclick.min.js"></script>
	<script src="../plugins/fakeloader/fakeLoader.js"></script>
	<script src="../js/mcm.js"></script>
	<script>
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
		
		function addUser(){
			loadModeView("user/direct?action=add");
		}
	</script>
</body>
</html>

