<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>expression2.jsp jsp전용 문법 배우기2</title>

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
	
	request.setAttribute("n", n);
	request.setAttribute("greetStr", greetStr);
	
	
%>

<body>

	<h2>request 객체 다루기</h2>
	<h1><%=greetStr %></h1>

	<p>
		<%=n + 20 %>
	</p>
	
	<form action="./back" method="post">
	
	</form>
</body>


</html>

