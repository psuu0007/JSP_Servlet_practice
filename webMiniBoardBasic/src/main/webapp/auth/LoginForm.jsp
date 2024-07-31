<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="../css/index.css" />
<link rel="stylesheet" href="../css/listPage.css" />
<style>

</style>

<script>

</script>
</head>
	
<body>
<jsp:include page="/Header.jsp" />

<h2>사용자 로그인</h2>
<form action="./login" method="post">
	<label>이메일</label> <input type="text" name="email" value="" placeholder="ex:hong@test.com" />
	<br/>
	<label>암호</label> <input type="password" name="password" value="" />
	<br/>
	<input type="submit" value="로그인" />
</form>
<jsp:include page="/Tail.jsp" />
</body>
</html>