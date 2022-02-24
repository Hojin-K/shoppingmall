<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/nstyle.css" />
</head>
<body>
	<h3>리뷰작성하기</h3>

	<table>

		<form action="write" method="get">
		
		<tr>
		<td class="left"> 간단 리뷰 </td>
		<td> <textarea name="reviewContent"  rows="10">제품리뷰를 간략히 적어주세요</textarea> </td>
	</tr>
	
		</form>
	</table>


</body>
</html>