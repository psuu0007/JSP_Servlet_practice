<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/index.css" />
<link rel="stylesheet" href="../css/listPage.css" />
<style>
a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: underline;
	color: #86eb86;
}
</style>

<script>
	
</script>
</head>

<body>
	<jsp:include page="/Header.jsp" />

	<!-- <h1>회원 목록</h1> -->
	<p>
		<a href="./add">신규 회원 등록</a>
	</p>

	<c:forEach var="memberDto" items="${memberList}">
		${memberDto.memberNo},
		<a href='./update?memberNo=${memberDto.memberNo}'>
			${memberDto.memberName}
		</a>,
		${memberDto.email},
		${memberDto.createdDate},
		<a href='./delete?memberNo=${memberDto.memberNo}'>[삭제]</a>
		<br />
	</c:forEach>

	<jsp:include page="/Tail.jsp" />
</body>
</html>