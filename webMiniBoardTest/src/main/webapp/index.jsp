<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type = "text/css">

</style>

<script>
	function pageMoveMemberListFnc() {
		location.href = './member/list';
	}
</script>

</head>


<body>
	<h1>Hello JSP&amp;Servlet</h1>
	<p>환영</p>
	<button onclick="pageMoveMemberListFnc();">회원목록으로 이동</button>
</body>
</html>