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
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
    />
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

    /* 검색창*/
    #custom-search-input {
        margin: 10px 0 0;
        padding: 0;
    }

    #custom-search-input .search-query {
        padding-right: 3px;
        padding-right: 4px \9;
        padding-left: 3px;
        padding-left: 4px \9;

        margin-bottom: 0;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }

    #custom-search-input button {
        border: 0;
        background: none;
        padding: 2px 5px;
        margin-top: 10px;
        position: relative;
        left: -28px;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
        color: black;
    }

    .search-query:focus + button {
        z-index: 3;
    }
    .txt_line {
        width:100%;
        overflow:hidden;
        text-overflow:ellipsis;
        white-space:nowrap;
    }

</style>
<body>
<div class="container">
    <form action="/item/search" method="GET">
        <div id="custom-search-input" class="col-6">
            <div class="input-group col-md-12">
                <input type="text" name="q" class="search-query form-control rounded-pill" placeholder="Search"/>
                <input type="hidden" name="page" value="${pagination.page}">
                <span class="input-group-btn">
                                    <button class="btn btn-danger" type="submit">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </span>
            </div>
        </div>
    </form>

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
                            <h5 class="card-title txt_line" ><c:out value="${item.itemName}"/></h5>
                            <h5 class="card-subtitle"><c:out value="${item.memberId}"/></h5>
                            <br>
                            <span class="card-text mt-5"><c:out value="${item.itemPrice}원"/></span>
                            <button style="display: inline; margin-left: 5px; margin-bottom: 10px"  class="btn btn-sm btn-primary disabled">${item.country.countryName}</button>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
    <div style="clear: both"></div>

    <div id="paginationBox">
        <ul class="pagination justify-content-center" style="font-size: medium">
            <li class="page-item"><a class="page-link" style="border: none;" href="/item?page=1"> << </a>
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
            <li class="page-item"><a class="page-link" style="border: none" href="/item?page=${pagination.pageCnt}">
                >> </a></li>
        </ul>
    </div>
    <!-- Gallery Item 1 -->
</div>
</body>
</html>
