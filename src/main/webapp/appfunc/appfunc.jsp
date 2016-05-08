<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>头部轮播管理</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${rootPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/FortAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/fakeloader/fakeLoader.css">
    <link rel="stylesheet" href="${rootPath}/plugins/select2/select2.min.css">
    <link rel="stylesheet" href="${rootPath}/css/style.css">
    <link rel="stylesheet" href="${rootPath}/css/skins/_all-skins.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background:#ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>${partItem.value}</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>${fragmentItem.value}</li>
				<li class="active">${partItem.value}</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<a href="${rootPath}/appfunc/show?fragment=${fragment}&part=${part}" class="btn btn-primary btn-block margin-bottom">新增</a>
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
								<s:iterator var="item" value="appFuncList">
								<li id="<s:property value="#item.id"/>">
									<a href="${rootPath}/appfunc/show?id=<s:property value="#item.id" />&fragment=<s:property value="fragment" />&part=<s:property value="part" />">
										<s:property value="#item.appFuncName" />
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
							<h3 class="box-title">APP功能信息</h3>
						</div>
						<form id="formAppFunc" class="form-horizontal" enctype="multipart/form-data" role="form" method="post">
							<div class="box-body">
								<input type="hidden" name="appFunc.id" value="${appFunc.id}" >
								<input type="hidden" name="fragment" value="${fragment}" >
								<input type="hidden" name="part" value="${part}" >
								<div class="form-group">
									<label for="txtSystemCode" class="col-sm-2 control-label">所属模块</label>
									<div class="col-sm-9">
										<input type="text" class="form-control"
											id="txtFragment" value="${fragmentItem.value}" disabled placeholder="所属模块">
									</div>
								</div>
								<div class="form-group">
									<label for="txtPart" class="col-sm-2 control-label">所属分区</label>
									<div class="col-sm-9">
										<input type="text" class="form-control"
											id="txtPart" value="${partItem.value}" disabled placeholder="所属分区">
									</div>
								</div>
								<div class="form-group">
									<label for="txtSystemCode" class="col-sm-2 control-label">功能名称</label>
									<div class="col-sm-9">
										<input type="text" name="appFunc.appFuncName" class="form-control"
											id="txtAppFuncName" value="${appFunc.appFuncName}" placeholder="功能名称">
									</div>
								</div>
								<div class="form-group">
									<label for="txtTitleImgPath" class="col-sm-2 control-label">显示图片</label>
									<div class="col-sm-9">
										<input id="hdnFilePath" type="hidden" name="appFunc.titleImgPath" value="${appFunc.titleImgPath}">
										<input id="hdnFileName" type="hidden" name="imageFileName">
										<div class="btn btn-default btn-file">
											<i class="fa fa-paperclip"></i> 选择
											<input id="btnAttachment" type="file" name="image" onchange="btnAttachment_OnChange();">
										</div>
										<span id="spanFileName"></span>
										<span>请上传2:1的图片，最大可以上传5M。</span>
										<div style="margin-top:0.5rem">
											<img alt="显示图片" class='img-responsive' src="<s:if test="%{appFunc.titleImgPath == null || appFunc.titleImgPath == ''}"> ${rootPath}/images/default_image.png </s:if><s:else> <s:property value="imageServer + appFunc.titleImgPath" /> </s:else>">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="txtTitle" class="col-sm-2 control-label">显示标题</label>
									<div class="col-sm-9">
										<input type="text" name="appFunc.title" class="form-control"
											id="txtTitle" value="${appFunc.title}" placeholder="显示标题">
									</div>
								</div>
								<div class="form-group">
									<label for="txtFunctionType" class="col-sm-2 control-label">应用类型</label>
									<div class="col-sm-9">
										<select id="txtFunctionType" name="appFunc.functionType" class="form-control select2" style="width: 100%;" onchange="txtFunctionType_OnChange();">
											<s:iterator var="item" value="funcType">
												<option value='<s:property value="#item.code" />' <s:if test="#item.code == appFunc.functionType">selected="selected"</s:if> >
													<s:property value="#item.value" />
												</option>
											</s:iterator>
					                    </select>
									</div>
								</div>
								<div id="fgOuuterSystem" class="form-group" style="display:none;">
									<label for="txtOutterSystem" class="col-sm-2 control-label">外部系统</label>
									<div class="col-sm-9">
										<select id="txtOutterSystem" name="appFunc.outterSystem" class="form-control select2" style="width: 100%;">
											<s:iterator var="item" value="outterSystems">
												<option value='<s:property value="#item.systemCode" />' <s:if test="#item.systemCode == appFunc.outterSystem">selected="selected"</s:if> >
													<s:property value="#item.systemName" />
												</option>
											</s:iterator>
					                    </select>
									</div>
								</div>
								<div class="form-group">
									<label for="txtContentUrl" class="col-sm-2 control-label">应用地址</label>
									<div class="col-sm-9">
										<input type="text" name="appFunc.contentUrl" class="form-control"
											id="txtContentUrl" value="${appFunc.contentUrl}" placeholder="应用地址">
									</div>
								</div>
								<div class="form-group">
									<label for="txtContentUrl" class="col-sm-2 control-label">统计API</label>
									<div class="col-sm-9">
										<input type="text" name="appFunc.statUrl" class="form-control"
											id="txtContentUrl" value="${appFunc.statUrl}" placeholder="统计API">
									</div>
								</div>
								<div class="form-group">
									<label for="txtCallMethod" class="col-sm-2 control-label">调用方式</label>
									<div class="col-sm-9">
										<input type="text" name="appFunc.callMethod" class="form-control"
											id="txtCallMethod" value="${appFunc.callMethod}" placeholder="调用方式">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-danger" onclick="saveAppFunc();">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<s:if test="appFunc.id != 0">
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
	<script src="${rootPath}/plugins/iCheck/icheck.min.js"></script>
	<script src="${rootPath}/plugins/fastclick/fastclick.min.js"></script>
	<script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
	<script src="${rootPath}/plugins/select2/select2.full.min.js"></script>
	<script src="${rootPath}/js/mcm.js"></script>
	<script>
		$(document).ready(function(){
			// 设定当前选择的项目
			$("#${appFunc.id}").attr("class", "active");
			
			// 设定当前选中的外部系统样式
			$("#${outterSystem.id}").attr("class", "active");
			
			// 初始化下拉框
			$(".select2").select2();
			
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
		
		function saveAppFunc(){
			var name = $("#txtAppFuncName").val();
			var titleImg = $("#hdnFileName").val();
			var titleImgpath = $("#hdnFilePath").val();
			var contentUrl = $("#txtContentUrl").val();
			var funcType = $("#txtFunctionType").val();
			var outterSys = $("#txtOutterSystem").val();
			var hasError = false;
			
			if (name == ""){
				alertMcmMsg($(".alert-danger"), '<s:text name="msg_func_name_cannot_empty"></s:text>');
				hasError = true;
			}
			if (!hasError && titleImg == "" && titleImgpath == ""){
				alertMcmMsg($(".alert-danger"), '<s:text name="msg_func_title_img_cannot_empty"></s:text>');
				hasError = true;
			}
			if (!hasError && contentUrl == ""){
				alertMcmMsg($(".alert-danger"), '<s:text name="msg_func_url_cannot_empty"></s:text>');
				hasError = true;
			}
			if (!hasError && outterSys == "" && (funcType == 4 || funcType == 5 || funcType == 6)){
				alertMcmMsg($(".alert-danger"), '<s:text name="msg_func_outter_must_have_type"></s:text>');
				hasError = true;
			}
			
			if (!hasError){
				$("#formAppFunc").attr("action", "${rootPath}/appfunc/save")
				$("#formAppFunc").submit();
			}
		}
		
		function txtFunctionType_OnChange(){
			var funcType = $("#txtFunctionType").val();
			if (funcType == 4 || funcType == 5 || funcType == 6){
				$("#fgOuuterSystem").css("display", "block");
			} else {
				$("#fgOuuterSystem").css("display", "none");
			}
		}
		
		function btnAttachment_OnChange(){
			var fileFullName = $("#btnAttachment").val();
			var fileName = fileFullName.substring(fileFullName.lastIndexOf("\\") + 1);

			$("#spanFileName").text(fileName);
			$("#hdnFileName").val(fileName);
		}
		
		function confirmDel(){
			alertMcmConfirmMsg($(".alert-info"), "<s:text name='msg_del_confim'></s:text>")
		}
		
		function submitDel(){
			dismissMsg($(".alert-info"));
			$("#formAppFunc").attr("action", "${rootPath}/appfunc/del");
			$("#formAppFunc").submit();
		}
		
		function cancelDel(){
			dismissMsg($(".alert-info"));
		}
	</script>
</body>
</html>