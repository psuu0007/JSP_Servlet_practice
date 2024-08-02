<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../include/incHead.jsp"/>
<jsp:include page="../include/incTop.jsp"/>
	
	<h2>회원 목록</h2>
	
	<div class='mt-4'>

		<table class="table table-bordered">
			<thead>
				<tr style='background: #f6f6f6;'>
					<th scope="col">번호</th>
					<th scope="col">회원명</th>
					<th scope="col">이메일</th>
					<th scope="col">가입일</th>
					<th scope="col">비고</th>
				</tr>
			</thead>
			<tbody >
			
			<c:forEach var="memberDto" items="${memberList}">
			
				<tr>
					<th scope="row">${memberDto.memberNo}</th>
					<td>${memberDto.memberName}</td>
					<td>${memberDto.email}</td>
					<td>${memberDto.createdDate}</td>
					<td>
						<button type="button" class="btn btn-outline-dark btn-sm" onclick='memberView(${memberDto.memberNo});'>정보</button>
						<button type="button" class="btn btn-outline-dark btn-sm" onclick='memberDelete(${memberDto.memberNo});'>삭제</button>
					</td>
				</tr>
			
			</c:forEach>
	
			</tbody>
		</table>
	
	</div>
	
	<div class='mt-4 text-center'>
		<button type="button" class="btn btn-outline-dark" onclick='memberAdd();'>신규 회원등록</button>
	</div>
	
	<form id='memberForm' action='delete' method='post' onsubmit='return false'>
		<input type='hidden' id='memberNo' name='memberNo' value=''>
		<input type='hidden' id='memberName' name='memberName' value=''>
	</form>
	
<jsp:include page="../include/incFooter.jsp"/>