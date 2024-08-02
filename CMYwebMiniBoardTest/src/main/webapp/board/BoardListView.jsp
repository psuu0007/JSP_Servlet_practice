<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../include/incHead.jsp"/>
<jsp:include page="../include/incTop.jsp"/>
	
	<h2>게시판 목록</h2>
	
	<div class='mt-4'>

		<table class="table table-bordered">
			<thead>
				<tr style='background: #f6f6f6;'>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">등록일</th>
					<th scope="col">수정일</th>
					<th scope="col">비고</th>
				</tr>
			</thead>
			<tbody >
			
			<c:forEach var="boardDto" items="${boardList}">
			
				<tr>
					<th scope="row">${boardDto.freeBoardId}</th>
					<td><a href='#' onclick='boardView(${boardDto.freeBoardId});'>${boardDto.freeBoardTitle}</a></td>
					<td>${boardDto.freeBoardWriter}</td>
					<td>${boardDto.createdDate}</td>
					<td>${boardDto.updateDate}</td>
					<td>
						<button type="button" class="btn btn-outline-dark btn-sm" onclick='boardDelete(${boardDto.freeBoardId});'>삭제</button>
					</td>
				</tr>
			
			</c:forEach>
	
			</tbody>
		</table>
	
	</div>
	
	<div class='mt-4 text-center'>
		<button type="button" class="btn btn-outline-dark" onclick='boardAdd();'>게시글 등록</button>
	</div>
	
	<form id='boardForm' name='boardForm' method='post' onsubmit='return false'>
		<input type='hidden' id='ssoMemberNo' name='ssoMemberNo' value='<%=session.getAttribute("ssoMemberNo")%>'>
	</form>
	
<jsp:include page="../include/incFooter.jsp"/>