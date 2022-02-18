<%--
  Created by IntelliJ IDEA.
  User: gimtaeyeon
  Date: 2022/02/18
  Time: 3:07 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="../bootStrap.jsp" flush="true"/>
    <title>Title</title>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-6 m-auto" style="height: 20rem">
    <img src="${item.itemImage}" style="width: auto; height: 100%; object-fit: cover;" alt="">
</div>
<div class="col-6 m-auto">
    <form action="">
        <div>
            <h3>${item.itemName}</h3>
            <div>
                <span class="aa-product-view-price"><fmt:formatNumber pattern="#,### 원"
                                                                      value="${item.itemPrice}"/></span>
            </div>
            <div>
                <a class="aa-add-to-cart-btn" href="#" onclick="">장바구니에 담기</a>&nbsp;
                <a class="aa-add-to-cart-btn" href="#">즉시구매</a>&nbsp;
                <a class="aa-add-to-cart-btn" href="#">취소</a>&nbsp;
            </div>
        </div>
    </form>
</div>
    <div class="row">

    </div>
</div>
</div>
</body>
</html>
