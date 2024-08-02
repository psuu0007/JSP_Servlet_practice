<%@page import="spms.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function moveMemberListPageFnc() {
	location.href = "${sessionScope.rootPath}/member/list";
}

function moveFreeBoardPageFnc() {
	location.href = "${sessionScope.rootPath}/freeBoard/list";
}
</script>
<div
	class="header">
	SPMS(Simple Project Management System)
	<button class="btn header_btn" onclick="moveMemberListPageFnc();">회원목록</button>
	<button class="btn header_btn" onclick="moveFreeBoardPageFnc();">자유게시판</button>
	<span style="float: right; text-align: right;">
		${sessionScope.memberDto.memberName}
		<!-- 공통단의 코드들은 가능한 절대 경로로 설정해야 어떤 구조에서든 동작 -->
		<a style="color: #86eb86;" href="${sessionScope.rootPath}/auth/logout">(로그아웃)</a>
	</span>
</div>
