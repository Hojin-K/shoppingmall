<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
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
    <title>Title</title>
    <script type="text/javascript">
        $(document).ready(function (e) {

        });
    </script>
</head>
<body>
<table class="table text-center">
    <thead>
    <tr>
        <th style="width: 8.33%">선택</th>
        <th style="width: 50%">상품</th>
        <th style="width: 16.66%">가격</th>
        <th style="width: 8.33%">갯수</th>
        <th style="width: 8.33%">주문관리</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="i" value="${0}" scope="page"/>
    <c:forEach items="${orders}}" var="order">
        <tr>
            <td><input type="checkbox" name="cartCodes[${i}]" value="${order.orderCode}"></td>
            <td class="content text-start">
                <a class="itemLink" href="/item/${cart.itemOption.item.itemCode}">
                    <div style="width:10%; height:100%; float:left;">
                        <img style="width: 100%;" src="${cart.itemOption.item.itemImage}" alt="">
                    </div>
                    <div style="width:90%; height:100%; float:left;">
                        <b style="font-size: medium">${cart.itemOption.item.itemName}</b>
                        <p>${cart.itemOption.optionName}size</p>
                    </div>
                </a>
            </td>
            <td id="price_${cart.cartId}">${cart.itemOption.item.itemPrice}</td>
            <td >
                <button type="button" id="minus_${cart.cartId}">-</button>
                <span id="amount_${cart.cartId}">${cart.amount}</span>
                <button class="btn-default" type="button" id="plus_${cart.cartId}">+</button>
            </td>
            <td><button class="btn-default" type="button" id="deleteCart_${cart.cartId}">삭제하기</button></td>
        </tr>
        <c:set var="i" value="${i+1}" scope="page"/>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
