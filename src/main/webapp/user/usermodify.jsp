<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 主体内容 -->
<section class="content">
	<div id="modeView" class="col-md-6">
	<div class="box box-default">
		<div class="box-header with-border">
			<h3 class="box-title">用户修改</h3>
			<div class="box-tools pull-right">
				<button class="btn btn-box-tool" data-widget="remove" onclick="closeModeView();"><i class="fa fa-remove"></i></button>
			</div>
        </div>
        <form id="formUser" action="user/add" class="form-horizontal" role="form" method="post">
        	<div class="box-body">
        		<div class="form-group">
					<label for="txtUserCode" class="col-sm-2 control-label">编号</label>
					<div class="col-sm-9">
						<input type="text" name="user.userCode" class="form-control" id="txtUserCode" placeholder="编号">
					</div>
				</div>
				<div class="form-group">
					<label for="txtUserName" class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-9">
						<input type="text" name="user.userName" class="form-control" id="txtUserName" placeholder="姓名">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-9 radio">
						<label>
							<input type="radio" name="user.sex" value="0" checked>男
                        </label>
                        <label>
							<input type="radio" name="user.sex" value="1" checked>女
                        </label>
                    </div>
				</div>
				<div class="form-group">
					<label for="txtEmail" class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-9">
						<input type="email" name="user.email" class="form-control" id="txtEmail" placeholder="邮箱">
					</div>
				</div>
				<div class="form-group">
					<label for="txtPhone" class="col-sm-2 control-label">手机</label>
					<div class="col-sm-9">
						<input type="tel" name="user.phone" class="form-control" id="txtPhone" placeholder="手机">
					</div>
				</div>
				<div class="form-group">
					<label for="txtAddress" class="col-sm-2 control-label">地址</label>
					<div class="col-sm-9">
						<input type="tel" name="user.address" class="form-control" id="txtAddress" placeholder="地址">
					</div>
				</div>
			</div>
        </form>
		<div class="box-footer">
              <button type="button" class="btn btn-default" onclick="closeModeView();">取消</button>
              <button type="button" class="btn btn-primary pull-right" onclick="saveUser();">保存</button>
		</div>
	</div>
	</div>
</section>
<script>
	function saveUser(){
		$("#formUser").submit();
	}
</script>