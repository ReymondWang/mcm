<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 桌面</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="plugins/FortAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="plugins/ionicons/css/ionicons.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background:#ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>用户桌面</h1>
			<ol class="breadcrumb">
				<li><a href="##"><i class="fa fa-home"></i> 用户桌面</a></li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
		桌面内容
		</section>
	</div>
	<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/app.min.js"></script>
    <script>
	    var parentFakeLoader = $(window.parent.document).find("#fakeLoader");
		if (parentFakeLoader){
			parentFakeLoader.fadeOut();
		}
    </script>
</body>
</html>
