<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../include/incHead.jsp" />
<jsp:include page="../include/incTop.jsp" />

	<h2>회원 등록</h2>
	
	<div class='mt-4'>
	
		<form action='add' method='post' onsubmit='return addCheck()'>
			<table class='table table-bordered'>
				<tr>
					<td style='width: 10%; background: #f3f3f3'>이름</td>
					<td><input type='text' id='memberName' name='memberName'
						class='form-control'></td>
				</tr>
				<tr>
					<td style='background: #f3f3f3'>이메일</td>
					<td><input type='text' id='email' name='email'
						class='form-control'></td>
				</tr>
				<tr>
					<td style='background: #f3f3f3'>암호</td>
					<td><input type='password' id='password' name='password'
						class='form-control'></td>
				</tr>
			</table>
			<div class='mt-4 text-center'>
				<input type='submit' value='등록하기' class='btn btn-outline-dark'>&nbsp;&nbsp;
				<input type='reset' value='돌아가기' onclick='memberList();' class='btn btn-outline-dark'>
			</div>
		</form>
	
	</div>
	


<jsp:include page="../include/incFooter.jsp" />