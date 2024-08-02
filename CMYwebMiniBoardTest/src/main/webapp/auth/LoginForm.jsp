<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String setLogin = request.getParameter("setLogin");
%>

<jsp:include page="../include/incHead.jsp" />
<jsp:include page="../include/incTop.jsp" />

	<h2>회원 로그인</h2>
	
	<div class='mt-4'>
	
		<form action='./login' method='post' onsubmit='return loginCheck()'>
		<table class='table table-bordered'>
			<tr>
				<td style='width: 10%; background: #f3f3f3'>이메일</td>
				<td><input type='text' id='loginEmail' name='loginEmail' class='form-control' placeholder='이메일을 입력하세요!'></td>
			</tr>
			<tr>
				<td style='width: 10%; background: #f3f3f3'>비밀번호</td>
				<td><input type='password' id='loginPass' name='loginPass' class='form-control' placeholder='비밀번호를 입력하세요!'></td>
			</tr>
		</table>
		<div class='mt-4 text-center'>
			<input type='submit' value='로그인' class='btn btn-outline-dark'>
		</div>
		</form>
	
	</div>
	
<form method='post' onSubmit='return false;'>
	<input type='hidden' id='setLogin' name='setLogin' value='<%=setLogin%>'>
</form>
	
<script type="text/javascript">

	window.onload = function() {
		
		let setLogin = document.getElementById('setLogin').value;
		if (setLogin == 'fail') {
			alert('로그인 정보를 정확히 입력하세요!');
		}
	}

</script>

<jsp:include page="../include/incFooter.jsp" />