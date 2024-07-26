<%@page import="spms.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="memberDto" scope="session" class="spms.dto.MemberDto"/>

<div class="header">
	SPMS(Simple Project Management System)
	<span style="float: right;">
	<jsp:getProperty property="memberName" name="memberDto"/>
		<!-- 공통단의 코드들은 가능한 절대 경로로 설정해야 어떤 구조에서든 동작 -->
		<a style="color: #86eb86;" href="<%=request.getContextPath()%>/auth/logout">(로그아웃)</a>
	</span>
</div>
