<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8" http-equiv="Refresh" content="5;url=./login">
<title>로그인 실패</title>
<link rel="stylesheet" href="../css/listPage.css" />
<style>

</style>

<script>

</script>
</head>
	
<body>
<h1>로그인 실패</h1>
	<pre>
		아이디(로그인 전용 아이디) 또는 비밀번호가 잘못 되었습니다.
		아이디와 비밀번호를 정확히 입력해 주세요.
		다시 시도하거나 비밀번호 찾기를 클릭하여 재설정하세요.
		
		잠시 후에 다시 로그인 화면으로 전환합니다.
	</pre>
	
	<div>
		<span>입력하신 이메일</span> <span><%=request.getParameter("email") %></span>
	</div>
	
	<button onclick="location.href='<%=request.getContextPath()%>/auth/login'">로그인 페이지로 이동</button>
</body>
</html>