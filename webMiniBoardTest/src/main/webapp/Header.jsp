<%@page import="spms.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberDto sessionMemberDto = (MemberDto) session.getAttribute("memberDto");
%>

<div class="header">
	SPMS(Simple Project Management System)
</div>
