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
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
</head>
<style>
    p, h5 {
        font-size: small;
        margin: 0;
        padding: 0;
    }

    a {
        color: black;
        text-decoration-line: none;
    }

    .itemImg {
        overflow: hidden;
    }

    .itemImg img {
        transition: all 0.3s linear;
    }

    .itemImg img:hover {
        transform: scale(1.2);
    }

    .itemLink:hover {
        color: black;
    }

    .cards-box {
        display: flex;
        /*justify-content: space-between;*/
        margin: 65px 70px 0;
        flex-wrap: wrap;
        float: left;
    }

</style>
<body>
<div class="container">

    <div class="card-colums">
        <div id="card-box" class="cards-box">
<c:forEach items="${items}" var="item">
    <a class="itemLink" href="/item/${item.itemCode}">
            <div class="card" style="width: 14rem; margin-bottom:15px; margin-left: 10px;">
                <div class="itemImg">
                <img class="card-img-top" src="${item.itemImage}"
                     style="border-bottom: 1px solid #eee; height: 200px;" alt="">
                </div>
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${item.itemName}"/></h5>
                    <h5 class="card-subtitle"><c:out value="${item.memberId}"/></h5>
                    <span class="card-text mt-3"><c:out value="${item.itemPrice}"/></span> &nbsp;&nbsp;&nbsp;<span class="card-text">${item.country.countryName}</span>
                </div>
            </div>
    </a>
</c:forEach>
        </div>
    </div>
    <div style="clear: both"></div>

    <div id="paginationBox">
        <ul class="pagination justify-content-center" style="font-size: medium">
            <li class="page-item"><a class="page-link" style="border: none;" href="/item?page=1" > << </a>
            </li>
                <c:choose>
                    <c:when test="${pagination.startPage != pagination.endPage}">
            <c:forEach var="number" begin="${pagination.startPage}" end="${pagination.endPage}">
                <c:if test="${number == pagination.page}">
                    <li class="page-item active selectedPage"><a href="/item?page=${number}" class="page-link" style="border: none">${number}</a></li>
                </c:if>
                <li class="page-item"><a href="/item?page=${number}" class="page-link" style="border: none">${number}</a></li>
            </c:forEach>
                    </c:when>
                    <c:otherwise>
                            <li class="page-item active selectedPage"><a href="/item?page=${pagination.startPage}" class="page-link" style="border: none">${pagination.startPage}</a></li>
                    </c:otherwise>
                </c:choose>
            <li class="page-item"><a class="page-link" style="border: none" href="/item?page=${pagination.pageCnt}"> >> </a></li>
        </ul>
    </div>
    <!-- Gallery Item 1 -->
</div>
</body>
</html>
