<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>expressionTest2.jsp</title>

<style type="text/css">
h1, p {
	border: 1px solid black;
}
</style>

<script type="text/javascript">
	
</script>

</head>
<%
	
// 	form 태그에 팀원의 이름과 좋아하는 음악을 담는다
	
// 	서블릿에서 그 내용을 콘솔에 출력하시오
// 	팀원 이름: 		???
// 	팀원 취향 음악: 	???
// 	서블릿은 알아서 만든다
%>

<body>

	<form action="./backEnd" method="post">
		<label>책 이름</label>
		<input type="text" name="bookName">
		<label>호텔 번호</label>
		<input type="number" name="hotelNum">
		
		<input type="submit" value="백단에 데이터 전송">
	</form>
</body>


</html>

