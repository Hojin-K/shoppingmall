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
<h2>reply_view.jsp</h2>
	<form action="reply" method="post">
	<input type="hidden" name="BOARD_ID" value="${reply_view.BOARD_ID }" />
	<input type="hidden" name="ITEM_CODE" value="${reply_view.ITEM_CODE }" />
	<input type="hidden" name="MEMBER_ID" value="${reply_view.MEMBER_ID }" />
<table>
		<tr>
			<td class="left">번호</td>
			<td>${reply_view.BOARD_ID }</td>
		</tr>
		<tr>
			<td class="left">구매자</td>
			<td>${reply_view.ITEM_CODE }</td>
		</tr>
		<tr>
			<td class="left">제목</td>
			<td>
				<input type="text" name="BOARD_TITLE" value="${reply_view.BOARD_TITLE }" />
			</td>
		</tr>
		<tr>
			<td class="left">내용</td>
			<td>
				<textarea name="BOARD_CONTENT"  rows="10">${reply_view.BOARD_CONTENT }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="reply" /> &nbsp;&nbsp;
				<a href="list">목록보기</a> &nbsp;&nbsp;
			</td>
		</tr>

</table>
	</form>

</body>
</html>