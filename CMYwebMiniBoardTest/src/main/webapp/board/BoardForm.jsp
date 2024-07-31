<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../include/incHead.jsp" />
<jsp:include page="../include/incTop.jsp" />

	<h2>게시글 등록</h2>
	
	<div class='mt-4'>
	
		<form action='add' method='post' onsubmit='return addBoardCheck()'>
			<table class='table table-bordered'>
				<tr>
					<td style='width: 10%; background: #f3f3f3'>제목</td>
					<td><input type='text' id='freeBoardTitle' name='freeBoardTitle' class='form-control'></td>
				</tr>
				<tr>
					<td style='background: #f3f3f3'>내용</td>
					<td><textarea id='freeBoardContent' name='freeBoardContent' rows='5' class='form-control'></textarea></td>
				</tr>
			</table>
			<div class='mt-4 text-center'>
				<input type='submit' value='등록하기' class='btn btn-outline-dark'>&nbsp;&nbsp;
				<input type='button' value='돌아가기' onclick='boardList();' class='btn btn-outline-dark'>
			</div>
		</form>
	
	</div>

<jsp:include page="../include/incFooter.jsp" />