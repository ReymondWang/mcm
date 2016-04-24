<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>外部系统管理</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${rootPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
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
			<h1>外部系统管理</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li class="active">外部系统管理</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<a href="${rootPath}/system/show" class="btn btn-primary btn-block margin-bottom">新增</a>
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
								<s:iterator var="item" value="#request.pageInfo.result">
								<li id="<s:property value="#item.id"/>">
									<a href="${rootPath}/system/show?id=<s:property value="#item.id" />">
										<s:property value="#item.systemName" />
									</a>
								</li>
								</s:iterator>
							</ul>
						</div>
						<div class="box-footer no-padding">
							<div class="mailbox-controls">
								<div class="pull-right">
									<s:property value="#request.pageInfo.startPos" />
									-
									<s:property value="#request.pageInfo.endPos" />
									/
									<s:property value="#request.pageInfo.totalCount" />
									<div class="btn-group" style="margin-bottom: 5px;">
										<button class="btn btn-default btn-sm">
											<i class="fa fa-chevron-left"></i>
										</button>
										<button class="btn btn-default btn-sm">
											<i class="fa fa-chevron-right"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">外部系统信息</h3>
						</div>
						<form id="formSystem" class="form-horizontal" role="form" method="post">
							<div class="box-body">
								<input type="hidden" name="outterSystem.id" value="${outterSystem.id}" >
								<div class="form-group">
									<label for="txtSystemCode" class="col-sm-2 control-label">外部系统编号</label>
									<div class="col-sm-9">
										<input type="text" name="outterSystem.systemCode" class="form-control"
											id="txtSystemCode" value="${outterSystem.systemCode}" placeholder="外部系统编号">
									</div>
								</div>
								<div class="form-group">
									<label for="txtSystemName" class="col-sm-2 control-label">外部系统名称</label>
									<div class="col-sm-9">
										<input type="text" name="outterSystem.systemName" class="form-control"
											id="txtSystemName" value="${outterSystem.systemName}" placeholder="外部系统名称">
									</div>
								</div>
								<div class="form-group">
									<label for="txtSystemType" class="col-sm-2 control-label">外部系统类型</label>
									<div class="col-sm-9">
										<select id="txtSystemType" name="outterSystem.systemType" class="form-control select2" placeholder="外部系统类型" style="width: 100%;">
											<s:iterator var="item" value="outterSystemTypes">
												<option value='<s:property value="#item.dictItemCode" />' <s:if test="#item.dictItemCode == outterSystem.systemType">selected="selected"</s:if> >
													<s:property value="#item.dictItemValue" />
												</option>
											</s:iterator>
					                    </select>
									</div>
								</div>
								<div class="form-group">
									<label for="txtSystemDescription" class="col-sm-2 control-label">外部系统描述</label>
									<div class="col-sm-9">
										<textarea id="txtSystemDescription" name="outterSystem.systemDescription" class="form-control" rows="5" placeholder="外部系统描述"><s:property value="outterSystem.systemDescription" /></textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-danger" onclick="saveOutterSystem();">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<s:if test="outterSystem.id != 0">
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
		
		function saveOutterSystem(){
			var systemCode = $("#txtSystemCode").val();
			var systemName = $("#txtSystemName").val();
			var systemType = $("#txtSystemType").val();
			var hasError = false;
			if (systemCode == "") {
				alertMcmMsg($(".alert-danger"),'<s:text name="msg_systemcode_cannnot_empty"></s:text>');
				hasError = true;
			}
			if (!hasError && systemName == "") {
				alertMcmMsg($(".alert-danger"),'<s:text name="msg_systemname_cannot_empty"></s:text>');
				hasError = true;
			}
			if (!hasError && systemType == "") {
				alertMcmMsg($(".alert-danger"),'<s:text name="msg_systemtype_cannot_empty"></s:text>');
				hasError = true;
			}
			if (!hasError) {
				$("#formSystem").attr("action", "${rootPath}/system/save");
				$("#formSystem").submit();
			}
		}
		
		function confirmDel(){
			alertMcmConfirmMsg($(".alert-info"), "<s:text name='msg_del_confim'></s:text>")
		}
		
		function submitDel(){
			dismissMsg($(".alert-info"));
			$("#formSystem").attr("action", "${rootPath}/system/del");
			$("#formSystem").submit();
		}
		
		function cancelDel(){
			dismissMsg($(".alert-info"));
		}
	</script>
</body>
</html>