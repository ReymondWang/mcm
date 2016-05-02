<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移动管理平台 | 字典详细</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${rootPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/css/style.css">
    <link rel="stylesheet" href="${rootPath}/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/FortAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${rootPath}/plugins/fakeloader/fakeLoader.css">
    <link rel="stylesheet" href="${rootPath}/plugins/iCheck/flat/blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background:#ecf0f5;">
	<div>
		<div id="fakeLoader"></div>
		<!-- 顶部导航 -->
		<section class="content-header">
			<h1>字典管理</h1>
			<ol class="breadcrumb">
				<li><a href="##" onclick="loadSubUrl('${rootPath}/desktop');"><i class="fa fa-home"></i> 用户桌面</a></li>
				<li>系统管理</li>
				<li class="active">字典管理</li>
			</ol>
		</section>
		<!-- 主体内容 -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<a href="${rootPath}/dict/list?dictNameCode=${dictNameCode}" class="btn btn-primary btn-block margin-bottom">返回字典列表</a>
					<div class="box box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">字典类型</h3>
							<div class="box-tools">
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="box-body no-padding">
							<ul class="nav nav-pills nav-stacked">
								<s:iterator var="item" value="dictNames">
								<li id="<s:property value="#item.dictNameCode"/>">
									<a href="${rootPath}/dict/list?dictNameCode=<s:property value="#item.dictNameCode" />">
										<s:property value="#item.dictNameValue" />
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
							<h3 class="box-title">字典项信息</h3>
						</div>
						<form id="formDictItem" action="${rootPath}/dict/save"
							class="form-horizontal" role="form" method="post">
							<div class="box-body">
								<input type="hidden" name="dictItem.id" value="${dictItem.id}">
								<input type="hidden" name="dictNameCode" value="${dictNameCode}" >
								<input type="hidden" name="dictItemCode" value="${dictItemCode}" >
								<div class="form-group">
									<label for="txtDictItemCode" class="col-sm-2 control-label">编号</label>
									<div class="col-sm-9">
										<input type="text" name="dictItem.dictItemCode" class="form-control"
											id="txtDictItemCode" value="${dictItem.dictItemCode}" placeholder="编号">
									</div>
								</div>
								<div class="form-group">
									<label for="txtDictItemName" class="col-sm-2 control-label">名称</label>
									<div class="col-sm-9">
										<input type="text" name="dictItem.dictItemValue" class="form-control"
											id="txtDictItemName" value="${dictItem.dictItemValue}" placeholder="名称">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-danger" onclick="saveDictItem();">
										&nbsp;&nbsp;保存&nbsp;&nbsp;
										</button>
										<s:if test="dictItem.id != 0">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn btn-danger">
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
	<script src="${rootPath}/plugins/iCheck/icheck.min.js"></script>
	<script src="${rootPath}/plugins/fastclick/fastclick.min.js"></script>
	<script src="${rootPath}/plugins/fakeloader/fakeLoader.js"></script>
	<script src="${rootPath}/js/mcm.js"></script>
	<script>
		$(document).ready(function(){
			// 设定选中
			$("#${dictNameCode}").attr("class", "active");
			
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
		
		function saveDictItem(){
			var code = $("#txtDictItemCode").val();
			var name = $("#txtDictItemName").val();
			var hasError = false;
			if (code == ""){
				alertMcmMsg(".alert-danger", '<s:text name="msg_dict_item_code_empty" />')
				hasError = true;
			}
			if (!hasError && name==""){
				alertMcmMsg(".alert-danger", '<s:text name="msg_dict_item_name_empty" />')
				hasError = true;
			}
			
			if (!hasError){
				$("#formDictItem").submit();
			}
		}
	</script>
</body>
</html>