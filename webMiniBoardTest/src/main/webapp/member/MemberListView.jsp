<%@ page import="spms.dto.MemberDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/listPage.css" />
<style>
</style>

<script>
	
</script>
</head>

<body>
	<jsp:include page="/Header.jsp" />

	<div id="main_content">
		<div class="title">
			<h1 class="title_name">회원 목록</h1>
			<p class="move_page">
				<button onclick="location.href='./add'" class="btn add_page_btn">신규
					회원 등록</button>
			</p>
		</div>

		<div class="title_list">
			<span>회원 번호</span> <span>회원 이름</span> <span>회원 이메일</span> <span>가입일</span>
			<span>회원 삭제 버튼</span>
		</div>

		<%
		ArrayList<MemberDto> memberList = (ArrayList<MemberDto>) request.getAttribute("memberList");

		for (MemberDto memberDto : memberList) {
		%>

		<div class="user_info">
			<span><%=memberDto.getMemberNo()%></span> <span> <a
				href='./update?memberNo=<%=memberDto.getMemberNo()%>'> <%=memberDto.getMemberName()%>
			</a>
			</span> <span><%=memberDto.getEmail()%></span> <span><%=memberDto.getCreatedDate()%></span>
			<span><a href='./delete?memberNo=<%=memberDto.getMemberNo()%>'>[삭제]</a></span>
		</div>

		<%
		} // for end
		%>
	</div>


	<jsp:include page="/Tail.jsp" />
</body>
</html>