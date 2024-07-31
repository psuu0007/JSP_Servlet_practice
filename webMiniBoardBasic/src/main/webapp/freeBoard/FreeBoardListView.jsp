<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/index.css" />
<link rel="stylesheet" href="../css/boardPage.css" />
<style>
</style>

<script>
	
</script>
</head>

<body>
	<jsp:include page="/Header.jsp" />

	<div id="main_content">
		<div class="boardTable">
			<div class="boardTable_header_row boardTable_row">
				<div class="row_content row_header">번호</div>
				<div class="row_content row_header">주제</div>
				<div class="row_content row_header">작성자(회원명)</div>
				<div class="row_content row_header">생성날짜</div>
				<div class="row_content row_header">수정날짜</div>
			</div>

			<c:forEach var="freeBoardDto" items="${freeBoardList}">
				<div class="boardTable_row borderTable_row_content">
					<div class="row_content">${freeBoardDto.freeBoardId}</div>
					<div class="row_content">
						<a class="row_content"
							href="${sessionScope.rootPath}/freeBoard/detail?boardId=${freeBoardDto.freeBoardId}">
							${freeBoardDto.freeBoardTitle}
						</a>
					</div>
					<div class="row_content">${freeBoardDto.freeBoardWriter}</div>
					<div class="row_content">${freeBoardDto.creDate}</div>
					<div class="row_content">${freeBoardDto.upDate}</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<jsp:include page="/Tail.jsp" />
</body>
</html>