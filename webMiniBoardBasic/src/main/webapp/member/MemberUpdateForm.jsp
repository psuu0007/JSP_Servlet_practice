<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>회원상세 정보&수정</title>
<link rel="stylesheet" href="../css/index.css" />
<link rel="stylesheet" href="../css/listPage.css" />
<script type="text/javascript">
function movePageMemberListFnc() {
	location.href = "./list";
}

function movePageDeleteFnc(no) {
	const url = "./delete?memberNo=" + no
	location.href= url;
}
</script>
</head>

<jsp:useBean id="memberDto" scope="request" class="spms.dto.MemberDto"/>

<body>
	<jsp:include page="/Header.jsp" />

	<h1>회원정보</h1>
	<form action='./update' method='post'>
		번호: <input type='text' name='memberNo' 
					value='${requestScope.memberDto.memberNo}' readonly='readonly' 
				/><br />
			 
		이름: <input type='text' name='memberName' 
					value='${memberDto.memberName}' 
				/><br /> 
			
		이메일: <input type='text' name='email' 
						value='${memberDto.email}' 
					/><br /> 
			
		가입일: ${requestScope.memberDto.createdDate} <br /> 
		
		<input type='submit' value='정보 수정' /> 
		<input type='button' value='삭제' 
		onclick='movePageDeleteFnc(${memberDto.memberNo});' />
		<input type='button' value='취소' onclick='movePageMemberListFnc();' />
	</form>
	
	<jsp:include page="/Tail.jsp" />
</body>
</html>