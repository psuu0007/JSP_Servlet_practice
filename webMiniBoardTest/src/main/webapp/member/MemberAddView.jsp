<%@ page import="spms.dto.MemberDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/listPage.css" />
<style>
</style>

<script>
function movePageMemberList() {
	location.href = "./list";
}
</script>
</head>

<body>
	<jsp:include page="/Header.jsp" />

	<h1>회원등록</h1>
	
	<form action='add' method='post'>
		이름: <input type='text' name='memberName' /><br />
		이메일: <input type='text' name='email' /><br />
		암호: <input type='password' name='password' /><br />
		<input type='submit' value='추가' />
		<input type='reset' value='취소' />
		<input type='button' value='돌아가기' onclick='movePageMemberList();'/>
	</form>
		

	<jsp:include page="/Tail.jsp" />
</body>
</html>