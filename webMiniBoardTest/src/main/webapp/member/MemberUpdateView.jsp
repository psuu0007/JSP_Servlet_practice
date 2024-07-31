<%@ page import="spms.dto.MemberDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" href="../css/listPage.css" />
<style>
</style>

<script>
function setAction(action) {
	document.getElementById('myForm').action = action;
}
function movePageMemberList() {
	location.href = "./list";
}
</script>
</head>

<jsp:useBean id="memberDto"
	scope="request"
	class="spms.dto.MemberDto"
/>


<body>
	<jsp:include page="/Header.jsp" />

	<h1>회원정보</h1>
	
	<form id='myForm' method='post'>
		번호: <input type='text' name='memberNo' value='<jsp:getProperty property="memberNo" name="memberDto"/>' readonly='readonly'/><br />
		이름: <input type='text' name='memberName' value='<jsp:getProperty property="memberName" name="memberDto"/>' /><br />
		비밀번호: <input type='text' name='memberPwd' value='<jsp:getProperty property="password" name="memberDto"/>' /><br />
		이메일: <input type='text' name='email' value='<jsp:getProperty property="email" name="memberDto"/>' /><br />
		가입일: <jsp:getProperty property="createdDate" name="memberDto"/><br />
		<input type='submit' value='정보 수정' onclick='setAction(\"./update\")' />
		<input type='submit' value='삭제' onclick='setAction(\"./delete\")' />
		<input type='button' value='취소' onclick='movePageMemberList();'/>
	</form>

	<jsp:include page="/Tail.jsp" />
</body>
</html>