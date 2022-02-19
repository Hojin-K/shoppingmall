<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
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
<body class="pt-5">
<div class="container">

    <form action="/order/insertOrder" method="post">
        <div class="mb-3">
            <label for="buyerName">받는 이</label>
            <input class="form-control" type="text" name="buyerName" id="buyerName" placeholder="받는 이 이름을 입력해주세요."
                   value="${sessionScope.member.memberName}" required>
        </div>
        <div class="mb-3">
            <label for="buyerTel">전화번호</label>
            <input class="form-control" type="tel" name="buyerTel" id="buyerTel" value="${sessionScope.member.memberTel}" required>
        </div>
        <div class="mb-3">
            <label for="buyerEmail">이메일</label>
            <input class="form-control" type="email" name="buyerEmail" id="buyerEmail" placeholder="이메일을 입력해주세요." required>
        </div>
        <div class="mb-3">
            <label for="buyerPostCode">주소 번호</label>
            <input class="form-control" type="text" name="buyerPostCode" id="buyerPostCode" placeholder="주소 번호를 입력해주세요."
                   required>
        </div>
        <div class="mb-3">
            <%--  api 사용 필요    --%>
            <label for="buyerAddr">주소</label>
            <input class="form-control" type="text" name="buyerAddr" id="buyerAddr" placeholder="주소를 입력해주세요."
                   value="${sessionScope.member.memberAddress}" required>
        </div>

        <table class="table text-center">
            <thead>
            <tr>
                <th style="width: 50%">상품</th>
                <th style="width: 16.66%">가격</th>
                <th style="width: 8.33%">갯수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${carts}" var="cart">
                <c:set var="total" value="${total+cart.itemOption.item.itemPrice}" scope="page"/>
                <tr>
                    <td class="content text-start">
                        <div style="width:10%; height:100%; float:left;">
                            <img style="width: 100%;" src="${cart.itemOption.item.itemImage}" alt="">
                        </div>
                        <div style="width:90%; height:100%; float:left;">
                            <b style="font-size: medium">${cart.itemOption.item.itemName}</b>
                            <p>${cart.itemOption.optionName}size</p>
                        </div>
                    </td>
                    <td id="price_${cart.cartId}">${cart.itemOption.item.itemPrice}</td>
                    <td id="amount_${cart.cartId}">${cart.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <span>총 결제 금액 : </span><span id="total" style="font-size: xx-large">
        ${pageScope.total}
    </span>원
    </form>
</div>
</body>
</html>
