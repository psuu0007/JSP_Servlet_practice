<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="memberDto"
	scope="session"
	class="spms.dto.MemberDto"
/>    
    
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light"><a href="${pageContext.request.contextPath}">webMiniBoard</a></div>
                <div class="list-group list-group-flush">
                <%
                if (session.getAttribute("ssoMemberNo") == null) {
                %>
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="${pageContext.request.contextPath}/auth/login">LogIn</a>
                <%
                } else {
                %>
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="${pageContext.request.contextPath}/auth/logout">LogOut</a>
                <%
                }
                %>	
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="${pageContext.request.contextPath}/member/list">Member</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="${pageContext.request.contextPath}/board/list">Board</a>
<!--                     <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Shortcuts</a> -->
<!--                     <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Overview</a> -->
<!--                     <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Events</a> -->
<!--                     <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Profile</a> -->
<!--                     <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Status</a> -->
                </div>
            </div>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-primary" id="sidebarToggle">Toggle Menu</button>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item mt-2">
                                
                <%
                if (session.getAttribute("ssoMemberNo") == null) {
                %>
                	<span class="badge rounded-pill text-bg-dark">회원 로그인을 하세요!</span>
                <%
                } else {
                %>
                	<span class="badge rounded-pill text-bg-info"><%=session.getAttribute("ssoMemberName")%>님 반갑습니다!</span>
                <%
                }
                %>	                                
                                
                                </li>
                                <!--<li class="nav-item"><a class="nav-link" href="#!">Link</a></li>-->
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="#!">Action</a>
                                        <a class="dropdown-item" href="#!">Another action</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#!">Something else here</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- Page content-->
                <div class="container-fluid" style='padding:30px;'>