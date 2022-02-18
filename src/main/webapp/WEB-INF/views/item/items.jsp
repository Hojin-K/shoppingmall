<%--
  Created by IntelliJ IDEA.
  User: gimtaeyeon
  Date: 2022/02/18
  Time: 11:54 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../bootStrap.jsp" flush="true"/>
</head>
<style>
    p,h5{font-size: small}

</style>
<body>
<div class="container">
    <div class="row">
            <c:forEach items="${items}" var="item">
                <div class="card" style="width: 10rem;" >
                    <a class="card-link" href="/item/${item.itemCode}">
                    <img class="card-img-top"  style="width: 100%; height: auto;" src="/resources${item.itemImage}" alt=""/>
                    <div class="card-body">
                        <p class="card-text"><c:out value="${item.memberId}"/></p>
                        <h5 class="card-title"><c:out value="${item.itemName}"/></h5>
                        <p class="card-text"><c:out value="${item.itemPrice}원"/></p>
                        <c:if test="${item.itemStock == 0}">
                            <span class="card-text" style="color: red;">&nbsp;&nbsp;품절</span>
                        </c:if>
                    </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
