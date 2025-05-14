<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>批量重置密码</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入 Bootstrap -->
	<link rel="stylesheet" href="/css/bootstrap.min.css">
	<script src="/js/jquery-3.2.1.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
	<div class="row">
		<jsp:include page="menu.jsp"></jsp:include>
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 style="text-align: center;">批量重置密码</h1>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" onsubmit="return batchResetPasswords(event)">
						<div class="form-group">
							<label for="usernames" class="col-sm-2 control-label">账号(非管理员账号)</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows="5" id="usernames" placeholder="每行一个用户名" required></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">新密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password" placeholder="请输入新密码" required>
							</div>
						</div>
						<div class="form-group">
							<label for="confirmPassword" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="confirmPassword" placeholder="请再次输入密码" required>
							</div>
						</div>
						<div class="form-group" style="text-align: center">
							<button class="btn btn-primary" type="submit">提交</button>
							<button class="btn btn-default" type="reset">重置</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container" id="footer">
	<div class="row">
		<div class="col-md-12"></div>
	</div>
</div>
</body>
<script>
	function batchResetPasswords(event) {
		event.preventDefault(); // 阻止默认表单提交

		const usernames = document.getElementById("usernames").value.split("\n").map(s => s.trim()).filter(Boolean);
		const password = document.getElementById("password").value;
		const confirmPassword = document.getElementById("confirmPassword").value;

		// 校验密码
		if (!password || !confirmPassword) {
			alert("请输入密码！");
			return false;
		}
		if (password !== confirmPassword) {
			alert("两次输入的密码不一致！");
			return false;
		}

		// 校验是否填写了用户账号
		if (usernames.length === 0) {
			alert("请至少输入一个用户名！");
			return false;
		}

		// 构造批量请求
		let successCount = 0;
		let errorCount = 0;

		usernames.forEach(username => {
			console.log(`Processing user: ${username}`);
			$.ajax({
				url: "/admin/userPasswordRest", // 后端接口地址
				method: "POST",
				data: {
					username: username,
					password: password
				},
				success: function(response) {
					// 检查后端返回的状态
					if (response && response.status === "success") {
						console.log(`Success for user: ${username}`);
						successCount++;
					} else if (response && response.status === "error") {
						console.error(`Error for user: ${username}, Message: ${response.message}`);
						errorCount++;
					} else {
						console.error(`Unexpected response for user: ${username}, Response:`, response);
						errorCount++;
					}
					checkCompletion();
				},
				error: function(xhr, status, error) {
					console.error(`Error for user: ${username}, Status: ${status}, Error: ${error}`);
					errorCount++;
					checkCompletion();
				}
			});
		});

		// 检查所有请求是否完成
		function checkCompletion() {
			if (successCount + errorCount === usernames.length) {
				alert(`重置完成：成功 `);
			}
		}
	}
</script>
</html>