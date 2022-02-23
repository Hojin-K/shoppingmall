<%--
  Created by IntelliJ IDEA.
  User: gimtaeyeon
  Date: 2022/02/21
  Time: 11:53 오전
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
        font-size: small
    }

    .itemLink {
        color: #333333;
    }

    .itemLink:hover {
        color: black;
    }

    .content {
        display: table;
    }

    .content span {
        display: table-cell;
        vertical-align: middle;
    }

</style>
<body>
<div class="container">
    <div class="row">
        <table class="table text-center">
            <thead>
            <tr>
                <th style="width: 8.33%">주문번호</th>
                <th style="width: 40%">상품</th>
                <th style="width: 16.66%">가격</th>
                <th style="width: 8.33%">갯수</th>
                <th style="width: 8.33%">주문 상태</th>
                <th style="width: 16.66%">주문관리</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${order.orderDetails}" var="orderDetail">
                <tr>
                    <td>${order.orderCode}</td>
                    <td class="content text-start">
                        <a class="itemLink" href="/item/${orderDetail.itemOption.item.itemCode}">
                            <div style="width:10%; height:100%; float:left;">
                                <img style="width: 100%;" src="${orderDetail.itemOption.item.itemImage}" alt="">
                            </div>
                            <div style="width:90%; height:100%; float:left;">
                                <b style="font-size: medium">${orderDetail.itemOption.item.itemName}</b>
                                <p>${orderDetail.itemOption.optionName}size</p>
                            </div>
                        </a>
                    </td>
                    <td>${orderDetail.itemOption.item.itemPrice}</td>
                    <td>
                        <span>${orderDetail.amount}</span>
                    </td>
                    <td>
                        ${orderDetail.postedStatus}
                    </td>
                    <td>
                        <button class="btn btn-sm btn-secondary" type="button">취소
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <br>
<div class="row">
<div class="col-6">
    <table class="table text-center">
        <tbody>
        <tr>
            <th>결제번호</th>
            <td>${order.impUid}</td>
        </tr>
            <tr>
                <th>결제일</th>
                <td>${order.paidAtToString()}</td>
            </th>
        <tr>
            <th>결제금액</th>
            <td>${order.totalPay}원</td>
        </tr>
        <tr>
            <th>환불금액</th>
            <td>${order.totalPay-order.change}원</td>
        </tr>
        </tbody>
    </table>
</div>
    <div class="col-6">
        <table class="table text-center">
            <tbody>
            <tr>
                <th>이름</th>
                <td>${order.buyerName}</td>
            </tr>
            <tr>
                <th>연락처</th>
                <td>${order.buyerTel}</td>
            </tr>
            <tr>
                <th>배송주소</th>
                <td>${order.buyerAddr}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>
