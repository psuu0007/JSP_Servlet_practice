<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<jsp:include page="../include/incHead.jsp" />
<jsp:include page="../include/incTop.jsp" />

<jsp:useBean id="boardDto"
	scope="request"
	class="spms.dto.BoardDto"
/>

	<h2>게시글 상세 정보</h2>
	
	<div class='mt-4'>
	
		<table class='table table-bordered'>
			<tr>
				<td style='background: #f3f3f3'>제목</td>
				<td colspan='3'>${boardDto.freeBoardTitle}</td>
			</tr>
			<tr>
				<td style='width: 10%; background: #f3f3f3'>작성자</td>
				<td style='width: 40%;'>${boardDto.freeBoardWriter}</td>
				<td style='width: 10%; background: #f3f3f3'>게시글 번호</td>
				<td style='width: 40%;'>${boardDto.freeBoardId}</td>
			</tr>
			<tr>
				<td style='width: 10%; background: #f3f3f3'>이메일</td>
				<td style='width: 40%;'>${boardDto.email}</td>
				<td style='width: 10%; background: #f3f3f3'>작성일</td>
				<td style='width: 40%;'>
					<!--<fmt:formatDate  value="${boardDto.createdDate}" pattern="yyyy년MM월dd일 hh:mm"/>-->
					${boardDto.createdDateN}
				</td>
			</tr>
			<tr>
				<td colspan='4' style='height: 200px;'>${boardDto.freeBoardContent}</td>
			</tr>
		</table>
		<div class='mt-4 text-center'>
			<input type='button' value='이전 페이지' onclick='boardList();' class='btn btn-outline-dark'>&nbsp;&nbsp;
			<input type='button' value='수정페이지 이동' onclick='boardList();' class='btn btn-outline-dark'>
		</div>
	
	</div>

<jsp:include page="../include/incFooter.jsp" />