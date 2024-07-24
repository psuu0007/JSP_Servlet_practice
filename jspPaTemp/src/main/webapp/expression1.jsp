<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>expression1.jsp jsp전용 문법 배우기1</title>

<style type="text/css">
h1, p {
	border: 1px solid black;
}
</style>

<script type="text/javascript">
	
</script>

</head>
<%
	int n = 10;
	String greetStr = "벌써 아침이네";
	 
%>

<body>

	<h2>회원 목록</h2>
	<h1><%=greetStr %></h1>

	<p>
		<%=n + 20 %>
	</p>
</body>


</html>

