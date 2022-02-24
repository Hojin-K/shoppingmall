<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table width="500" border="1">
	<c:forEach items="${review }" var="dto">
		<tr>
			<td>${dto.reviewCode }</td>
			<td>${dto.itemCode }</td>
			<td>
	<a href="review?reviewcode=${dto.reviewcode }">${dto.reviewcontent }</a>
	</td>
		</tr>
	</c:forEach>	
		<tr>
			<td colspan="5"><a href="reviewwriteview"></a>
	</table>
	
	 <div id="paginationBox">
        <ul class="pagination justify-content-right" style="font-size: medium">
            <li class="page-reivew"><a class="page-link" style="border: none;" href="/review?page=1"> << </a>
            </li>
            <c:choose>
                <c:when test="${pagination.startPage != pagination.endPage}">
                    <c:forEach var="number" begin="${pagination.startPage}" end="${pagination.endPage}">
                        <c:if test="${number == pagination.page}">
                            <li class="page-item active selectedPage"><a href="/item?page=${number}" class="page-link"
                                                                         style="border: none">${number}</a></li>
                        </c:if>
                        <li class="page-item"><a href="/item?page=${number}" class="page-link"
                                                 style="border: none">${number}</a></li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <li class="page-item active selectedPage"><a href="/item?page=${pagination.startPage}"
                                                                 class="page-link"
                                                                 style="border: none">${pagination.startPage}</a></li>
                </c:otherwise>
            </c:choose>
            <li class="page-item"><a class="page-link" style="border: none" href="/review?page=${pagination.pageCnt}">
                >> </a></li>
        </ul>
    </div>




</body>
</html>