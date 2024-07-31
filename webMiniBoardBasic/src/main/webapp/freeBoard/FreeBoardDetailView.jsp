<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/index.css" />
<link rel="stylesheet" href="../css/boardDetailPage.css" />
<style>
</style>

<script>
	
</script>
</head>

<body>
	<jsp:include page="/Header.jsp" />

	<div id="main_content">
		<table class="boardTable">
			<tr class="boardTableRow">
				<td width="100px" colspan="1" class="boardTableCol boardTable_bg_color">주제</td>
				<td colspan="3" class="boardTableCol boardTableCol_first">${requestScope.freeBoardDto.freeBoardTitle}</td>
			</tr>
			<tr class="boardTableRow">
				<td width="100px" class="boardTableCol boardTable_bg_color">작성자</td>
				<td width="100px" class="boardTableCol">${requestScope.freeBoardDto.freeBoardWriter}</td>
				<td width="100px" class="boardTableCol boardTable_bg_color">게시번호</td>
				<td width="100px" class="boardTableCol">${requestScope.freeBoardDto.freeBoardId}</td>
			</tr>
			<tr class="boardTableRow">
				<td class="boardTableCol boardTable_bg_color">email</td>
				<td class="boardTableCol">${requestScope.memberDto.email}</td>
				<td class="boardTableCol boardTable_bg_color">글 생성일</td>
				<td class="boardTableCol">${requestScope.freeBoardDto.koreaDate}</td>
			</tr>
			<tr class="boardTableRow">
				<td colspan="4" class="boardTableCol boardTable_Content">
					${requestScope.freeBoardDto.freeBoardContent}
				</td>
			</tr>
		</table>
		<div class="pageMove">
			<button class="btn pageMove_btn">이전 페이지</button>
			<button class="btn pageMove_btn">수정 페이지 이동</button>
		</div>
	</div>

	<jsp:include page="/Tail.jsp" />
</body>
</html>