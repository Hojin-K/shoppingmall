<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/nstyle.css" />
</head>
<body>
<h3>write_view.jsp</h3>
<table>

<form action="write" method="get">

	<tr>
		<td class="left"> 제목 </td>
		<td> <input type="text" name="BOARD_TITLE" value="제목을 적으세요"></td>
	</tr>
	<tr>
		<td class="left"> 내용 </td>
		<td> <textarea name="BOARD_CONTENT"  rows="10">내용을 적으세요</textarea> </td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="입력" />
			<a href="list">목록</a>
		</td>
	</tr>

</form>
</table>



</body>
</html>