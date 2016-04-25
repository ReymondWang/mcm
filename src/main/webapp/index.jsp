<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
    	<!-- 头部工具栏 -->
    	<header class="main-header">
    		<a href="index2.html" class="logo">
    			<span class="logo-mini">MCM</span>
    			<span class="logo-lg">移动管理平台</span>
    		</a>
    		<nav class="navbar navbar-static-top" role="navigation">
    			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
    				<span class="sr-only">Toggle navigation</span>
    			</a>
    			<div class="navbar-custom-menu">
    				<ul class="nav navbar-nav">
    					<!-- 消息 -->
    					<li class="dropdown messages-menu">
    						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
    							<i class="fa fa-envelope-o"></i>
    							<span class="label label-success">1</span>
    						</a>
    						<ul class="dropdown-menu">
    							<li class="header">你有1条新的消息</li>
    							<li>
    								<ul class="menu">
    									<li><!-- start message -->
				                        <a href="#">
				                          <div class="pull-left">
				                            <img src="images/user2-160x160.jpg" class="img-circle" alt="User Image">
				                          </div>
				                          <h4>
				                            Support Team
				                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
				                          </h4>
				                          <p>Why not buy a new awesome theme?</p>
				                        </a>
				                      </li>
    								</ul>
    							</li>
    							<li class="footer"><a href="#">查看全部</a></li>
    						</ul>
    					</li>
    					<!-- 提醒 -->
    					<li class="dropdown notifications-menu">
    						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
    							<i class="fa fa-bell-o"></i>
    							<span class="label label-warning">1</span>
    						</a>
    						<ul class="dropdown-menu">
    							<li class="header">你有1条提醒</li>
    							<li>
    								<ul class="menu">
    									<li>
    										<a href="#">
    										<i class="fa fa-users text-aqua"></i> 5 new members joined today
    										</a>
    									</li>
    								</ul>
    							</li>
    							<li class="footer"><a href="#">查看全部</a></li>
    						</ul>
    					</li>
    					<li class="dropdown tasks-menu">
    						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
    							<i class="fa fa-flag-o"></i>
    							<span class="label label-danger">1</span>
    						</a>
    						<ul class="dropdown-menu">
    							<li class="header">你有1个任务</li>
    							<li>
    								<ul class="menu">
    									<li>
    										<a href="#">
    											<h3>Design some buttons<small class="pull-right">20%</small></h3>
    											<div class="progress xs">
    												<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
    													<span class="sr-only">20% Complete</span>
    												</div>
    											</div>
    										</a>
    									</li>
    								</ul>
    							</li>
    							<li class="footer">
    								<a href="#">查看全部</a>
    							</li>
    						</ul>
    					</li>
    					<li class="dropdown user user-menu">
    						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
    							<img src="images/avatar5.png" class="user-image" alt="User Image">
    							<span class="hidden-xs">王亚南</span>
    						</a>
    						<ul class="dropdown-menu">
    							<li class="user-header">
	    							<img src="images/avatar5.png" class="img-circle" alt="User Image">
	    							<p>
	    								王亚南 - Web Developer
	    								<small>Member since Nov. 2012</small>
	    							</p>
    							</li>
    							<li class="user-body">
    								<div class="col-xs-4 text-center">
    									<a href="#">Followers</a>
    								</div>
    								<div class="col-xs-4 text-center">
    									<a href="#">Sales</a>
    								</div>
    								<div class="col-xs-4 text-center">
    									<a href="#">Friends</a>
    								</div>
    							</li>
    							<li class="user-footer">
    								<div class="pull-left">
    									<a href="#" class="btn btn-default btn-flat">Profile</a>
    								</div>
    								<div class="pull-right">
    									<a href="#" class="btn btn-default btn-flat">Sign out</a>
    								</div>
    							</li>
    						</ul>
    					</li>
    				</ul>
    			</div>
    		</nav>
    	</header>
    	<!-- 左侧导航栏 -->
    	<aside class="main-sidebar">
    		<section class="sidebar">
    			<ul class="sidebar-menu">
    				<li class="treeview">
    					<a href="#">
    						<i class="fa fa-laptop"></i> <span>系统管理</span> <i class="fa fa-angle-left pull-right"></i>
    					</a>
    					<ul class="treeview-menu">
    						<li><a href="##" onclick="loadUrl('user/list', this);">用户管理</a></li>
    						<li><a href="##" onclick="loadUrl('user/list', this);">角色管理</a></li>
    						<li><a href="##" onclick="loadUrl('system/show', this);">外部系统管理</a></li>
    					</ul>
    				</li>
    				<li class="treeview">
    					<a href="#">
    						<i class="fa fa-dashboard"></i> <span>首页管理</span> <i class="fa fa-angle-left pull-right"></i>
    					</a>
    					<ul class="treeview-menu">
    						<li><a href="##" onclick="loadUrl('hometopadv/show', this);">头部轮播</a></li>
    						<li><a href="##" onclick="loadUrl('user/list', this);">功能按钮</a></li>
    						<li><a href="##" onclick="loadUrl('user/list', this);">通知内容</a></li>
    					</ul>
    				</li>
    				<li class="treeview">
    					<a href="#">
    						<i class="fa fa-th"></i> <span>办公管理</span> <i class="fa fa-angle-left pull-right"></i>
    					</a>
    					<ul class="treeview-menu">
    						<li><a href="##" onclick="loadUrl('user/list', this);">功能注册</a></li>
    						<li><a href="##" onclick="loadUrl('user/list', this);">流程表单</a></li>
    					</ul>
    				</li>
    				<li class="treeview">
    					<a href="#">
    						<i class="fa fa-book"></i> <span>对外接口</span> <i class="fa fa-angle-left pull-right"></i>
    					</a>
    					<ul class="treeview-menu">
    						<li><a href="##" onclick="loadUrl('user/list', this);">接口文档</a></li>
    						<li><a href="##" onclick="loadUrl('user/list', this);">接口测试</a></li>
    					</ul>
    				</li>
    			</ul>
    		</section>
    	</aside>
    	
    	<div class="content-wrapper">
    		<div id="fakeLoader"></div>
    		<iframe id="content" style="border:none; width:100%;"></iframe>
    	</div>
    	<!-- <footer class="main-footer">
    		<div class="pull-right hidden-xs"><b>Version</b> 1.0.0</div>
    		<strong>Copyright &copy; 2014-2015 PurpleLight</strong> All rights reserved.
      	</footer> -->
    </div>
    <script src="${rootPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="${rootPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${rootPath}/js/app.min.js"></script>
    <script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
    <script type="text/javascript">
    	var posTop = $("#content").position().top;
    	var posLeft = $("#content").position().left;
    	var posWidth = $(window).width() - posLeft;
    	var posHeight = $(window).height();
    
    	function loadUrl(loadUrl, obj){
    		if (obj){
    			makeActive($(obj).parent());
    			makeActive($(obj).parents(".treeview"));
    		}
    		
    		$("#fakeLoader").fadeIn();
    		$("#fakeLoader").fakeLoader({
				timeToHide: 1000000,
				bgColor: "rgba(0, 0, 0, 0.4)",
				top: posTop,
				left: posLeft,
				width: posWidth,
				height: posHeight,
				spinner: "spinner2"
            });
    		$("#content").attr("src", loadUrl);
    	}
    	
    	function makeActive(obj){
    		if (obj){
    			if (!obj.hasClass("active")){
    				obj.addClass("active");
    			}
    			
    			var prev = obj.prev();
    			while(prev.length > 0){
    				makeUnActive(prev);
    				prev = prev.prev();
    			}
    			
    			var next = obj.next();
    			while(next.length > 0){
    				makeUnActive(next);
    				next = next.next();
    			}
    		}
    	}
    	
    	function makeUnActive(obj){
    		if (obj){
    			obj.removeClass("active");
    			obj.find("li").each(function(index, element){
    				$(element).removeClass("active");
    			});
    		}
    	}
    	
    	$(document).ready(function(){
    		var height = $(".content-wrapper").height() - 5;
    		$("#content").css("height", height);
    		loadUrl("desktop");
    	});
    </script>
  </body>
</html>
