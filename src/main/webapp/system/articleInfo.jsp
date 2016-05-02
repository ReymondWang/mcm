<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 文章详细</title>
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
			<h1>文章管理</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li class="active">文章管理</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<a href="${rootPath}/system/article/list?articleType=${articleType}" class="btn btn-primary btn-block margin-bottom">返回文章列表</a>
					<div class="box box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">文章类型</h3>
							<div class="box-tools">
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="box-body no-padding">
							<ul class="nav nav-pills nav-stacked">
								<s:iterator var="item" value="articleTypes">
								<li id="<s:property value="#item.dictItemCode"/>">
									<a href="${rootPath}/system/article/list?articleType=<s:property value="#item.dictItemCode" />">
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
							<h3 class="box-title">文章信息</h3>
						</div>
						<form id="formArticle" action="${rootPath}/system/article/save"
							class="form-horizontal" role="form" method="post">
							<div class="box-body">
								<input type="hidden" name="article.id" value="${article.id}">
								<input type="hidden" name="articleType" value="${articleType}">
								<div class="form-group">
									<label for="txtArticleCode" class="col-sm-2 control-label">编号</label>
									<div class="col-sm-9">
										<input type="text" name="article.articleCode" class="form-control"
											id="txtArticleCode" value="${article.articleCode}" placeholder="编号">
									</div>
								</div>
								<div class="form-group">
									<label for="txtArticleName" class="col-sm-2 control-label">名称</label>
									<div class="col-sm-9">
										<input type="text" name="article.articleName" class="form-control"
											id="txtArticleName" value="${article.articleName}" placeholder="名称">
									</div>
								</div>
								<div class="form-group">
								<label for="txtArticleName" class="col-sm-2 control-label">内容</label>
									<div class="col-sm-9">
										<textarea name="article.articleContent" class="summernote">${article.articleContent}</textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-danger" onclick="saveArticle();">
										&nbsp;&nbsp;保存&nbsp;&nbsp;
										</button>
										<s:if test="article.id != 0">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn btn-danger" onclick="delDictItem();">
											&nbsp;&nbsp;删除&nbsp;&nbsp;
											</button>
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
	<script src="${rootPath}/plugins/fastclick/fastclick.min.js"></script>
	<script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
	<script src="${rootPath}/plugins/summernote/summernote.js"></script>
	<script src="${rootPath}/plugins/summernote/lang/summernote-zh-CN.js"></script>
	<script src="${rootPath}/js/mcm.js"></script>
	<script>
		$(document).ready(function(){
			// 设定选中
			$("#${articleType}").attr("class", "active");
			
			// 设定编辑器
			$('.summernote').summernote({
				height: 300,
		        lang: 'zh-CN'
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
		
		function saveArticle(){
			/* alert($('.summernote').html()); */
			/* $("#hdnArticleContent").val($('.summernote').val()); */
			$("#formArticle").submit();
		}
	</script>
</body>
</html>