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
<table>
	<form action="reply" method="post">
	<input type="hidden" name="bid" value="${reply_view.bid }" />
	<input type="hidden" name="bgroup" value="${reply_view.bgroup }" />
	<input type="hidden" name="bstep" value="${reply_view.bstep }" />
	<input type="hidden" name="bindent" value="${reply_view.bindent }" />
		<tr>
			<td class="left">번호</td>
			<td>${reply_view.bid }</td>
		</tr>
		<tr>
			<td class="left">히트</td>
			<td>${reply_view.bhit }</td>
		</tr>
		<tr>
			<td class="left">이름</td>
			<td>
				<input type="text" name="bName" value="" />
			</td>
		</tr>
		<tr>
			<td class="left">제목</td>
			<td>
				<input type="text" name="bTitle" value="${reply_view.btitle }" />
			</td>
		</tr>
		<tr>
			<td class="left">내용</td>
			<td>
				<textarea name="bContent"  rows="10">${reply_view.bcontent }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="reply" /> &nbsp;&nbsp;
				<a href="list">목록보기</a> &nbsp;&nbsp;
			</td>
		</tr>
	</form>

</table>

</body>
</html>