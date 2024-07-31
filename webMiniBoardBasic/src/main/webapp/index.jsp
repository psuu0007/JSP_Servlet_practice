<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/index.css" />
<style>

</style>

<script>
	function pageMoveLoginFnc() {
		location.href = './auth/login';
	}
</script>
</head>
	
<body>
<jsp:include page="/Header.jsp" />

	<div class="main_conteint">
		<h1 class="main_conteint__title">Hello JSP&amp;Servlet</h1>
		<p class="main_conteint__content">환영</p>
	</div>
	<div class="move">
		<button class="btn move_login__btn" onclick="pageMoveLoginFnc();">로그인</button>
	</div>
	
	<jsp:include page="/Tail.jsp" />
</body>
</html>

