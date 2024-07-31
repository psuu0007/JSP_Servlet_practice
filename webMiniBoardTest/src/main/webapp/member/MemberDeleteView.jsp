<%@ page import="spms.dto.MemberDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="../css/listPage.css" />
<style>
</style>

<script>
function movePageMemberList() {
	location.href = "./list";
}
</script>
</head>

<jsp:useBean id="memberList" scope="request"
	class="java.util.ArrayList"
	type="java.util.ArrayList<spms.dto.MemberDto>" />

<body>
	<jsp:include page="/Header.jsp" />

	<h1><%=request.getAttribute("memberName") %> 회원이 삭제되었습니다.</h1>
	<button onclick='movePageMemberList();'>list 페이지로 이동</button>

	<jsp:include page="/Tail.jsp" />
</body>
</html>