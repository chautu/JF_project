<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<title>Đăng nhập</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="application/x-javascript">
		 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
	</script>
	<!-- bootstrap-css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<!-- //bootstrap-css -->
	<!-- Custom CSS -->
	<link href="${pageContext.request.contextPath}/bootstrap/css/style.css" rel='stylesheet' type='text/css' />
	<link href="${pageContext.request.contextPath}/bootstrap/css/style-responsive.css" rel="stylesheet" />
	
	<!-- font-awesome icons -->
	<link rel="stylesheet" href="css/font.css" type="text/css" />
	<link href="${pageContext.request.contextPath}/bootstrap/css/font-awesome.css" rel="stylesheet">
	<!-- //font-awesome icons -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery2.0.3.min.js"></script>
</head>
<body>
	<div class="log-w3">
		<div class="w3layouts-main">
			<h2>Đăng nhập</h2>
			<form action="${pageContext.request.contextPath}/login" method="post">
				<input type="text" class="ggg" name="username" placeholder="Tài khoản"/> 
				<input type="password" class="ggg" name="password" placeholder="Mật khẩu" required="">
				<div class="clearfix"></div>
				<input type="submit" value="Đăng nhập" name="submit">
				
				<p style="color: red; text-align: center">${msgErr}</p>
			</form>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/scripts.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.slimscroll.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.nicescroll.js"></script>
	
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.scrollTo.js"></script>
</body>
</html>