<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../include/incHead.jsp" />
<jsp:include page="../include/incTop.jsp" />

<jsp:useBean id="memberDto"
	scope="request"
	class="spms.dto.MemberDto"
/>

	<h2>회원 정보</h2>
	
	<div class='mt-4'>
	
		<form action='update' method='post' onsubmit='return updateCheck()'>
		<input type='hidden' id='memberNo' name='memberNo' value='${memberDto.memberNo}'>
			<table class='table table-bordered'>
				<tr>
					<td style='width: 10%; background: #f3f3f3'>번호</td>
					<td>${memberDto.memberNo}</td>
				</tr>			
				<tr>
					<td style='width: 10%; background: #f3f3f3'>이름</td>
					<td><input type='text' id='memberName' name='memberName'
						class='form-control' value='${memberDto.memberName}'></td>
				</tr>
				<tr>
					<td style='background: #f3f3f3'>이메일</td>
					<td><input type='text' id='email' name='email'
						class='form-control' value='${memberDto.email}'></td>
				</tr>								
				<tr>
					<td style='background: #f3f3f3'>가입일</td>
					<td>${requestScope.memberDto.createdDate}</td>
				</tr>
			</table>
			<div class='mt-4 text-center'>
				<input type='submit' value='적용하기' class='btn btn-outline-dark'>&nbsp;&nbsp;
				<input type='button' value='돌아가기' onclick='memberList();' class='btn btn-outline-dark'>
			</div>
		</form>
	
	</div>
	


<jsp:include page="../include/incFooter.jsp" />