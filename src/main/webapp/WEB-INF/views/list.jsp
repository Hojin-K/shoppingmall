<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>list.jsp</h3>

<table width="500" border="1">
	<tr>
		<td>번호</td>
		<td>구매자</td>
		<td>제목</td>
		<td>내용</td>
		<td>판매자</td>
	</tr>
	
	<c:forEach items="${list }" var="dto">
		<tr>
			<td>${dto.board_id }</td>
			<td>${dto.item_code }</td>
			<td>
				<a href="content_view?bid=${dto.board_id }">${dto.board_title }</a> 
			</td>
			<td>${dto.board_content }</td>
			<td>${dto.recommend_id }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5"> <a href="write_view">글작성</a> </td>
	</tr>
	

</table>


</body>
</html>