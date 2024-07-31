<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8">
<title>시스템 오류</title>
<style>

</style>

<script>
/* 	function pageMoveMemberListFnc() {
		location.href = './member/list';
	} */
</script>
</head>
<jsp:useBean id="memberErrorInfo" scope="request" class="spms.dto.MemberDto"></jsp:useBean>

<jsp:setProperty property="email" name="memberErrorInfo"/>

<body>
	<jsp:include page="/Header.jsp" />

	<h1>시스템 오류</h1>
	<pre>
		다시 한번 확인해주세요!
		지금 입력하신 주소의 페이지는 사라졌거나 다른 페이지로 변경되었습니다.
		주소를 다시 확인해주세요.
		만약 계속해서 이 문제가 발생된다면 
		시스템 운영팀(사내번호: 8282)에 연락하기 바랍니다.
	</pre>
	
	<div>
		<jsp:getProperty property="email" name="memberErrorInfo"/>
	</div>
	<!-- <button onclick="pageMoveMemberListFnc();">회원목록으로 이동</button> -->
	
	<jsp:include page="/Tail.jsp" />
</body>
</html>

