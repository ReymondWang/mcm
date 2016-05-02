<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 登录</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="plugins/FortAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="plugins/fakeloader/fakeLoader.css">
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition login-page">
  	<div id="fakeLoader"></div>
    <div class="login-box">
      <div class="login-logo">移动管理平台</div>
      <div class="login-box-body">
        <p class="login-box-msg">欢迎您使用移动管理平台，请登录！</p>
        <form id="formLogin" action="login" method="post">
          <div class="form-group has-feedback">
            <input id="txtLoginId" type="email" name="loginId" class="form-control" value="${loginId}" placeholder="用户编号/电子邮箱/手机号">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input id="txtPassword" type="password" name="password" class="form-control" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <a href="#">忘记密码 ？</a>
            </div>
            <div class="col-xs-4">
              <button type="button" class="btn btn-primary btn-block btn-flat" onclick="login();">登录</button>
            </div>
          </div>
        </form>
      </div>
      <div id="divErrorMsg" class="alert bg-red" style="margin-top:10px; display:none;">
      </div>
    </div>
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="plugins/fakeloader/fakeLoader.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		var msg = '${message}';
    		if (msg && msg != ""){
    			showMessage(msg);
    		}
    	});
    
    	function login(){
    		var hasError = false;
    		
    		if ($("#txtLoginId").val() == ""){
    			showMessage('<s:text name="msg_must_has_login_id" />');
    			hasError = true;
    		}
    		
    		if (!hasError){
    			$("#fakeLoader").fakeLoader({
                    bgColor:"#2ecc71",
                    spinner:"spinner1"
                });
        		$("#formLogin").submit();
    		}
    	}
    	
    	function showMessage(msg){
    		$("#divErrorMsg").html(msg);
    		$("#divErrorMsg").fadeIn();
    		setTimeout(function(){
    			$("#divErrorMsg").fadeOut();
    		}, 3000);
    	}
    </script>
  </body>
</html>