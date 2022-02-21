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
    .itemLink{
        color: #333333;
    }
    .itemLink:hover{
        color: black;
    }
    .content{
        display: table;
    }
    .content span{
        display: table-cell;
        vertical-align: middle;
    }

</style>

<script>
    $(function () {
        let total = 0;
        $("[name^='cartCodes']").change(function () {
            let value = this.value;
            let price = $('#price_' + value).text();
            let amount = $('#amount_' + value).text();
            if ($("[name^='cartCodes']").is(":checked")) {
                total += price * amount;
            } else {
                total -= price * amount;
            }
            $("#total").text(total);
        });

        $(document).on("click", "button[id^='deleteCart']", function () {
            let id = this.id;
            let str = id.split("_")[1];
            $.ajax({
                url: "/cart/" + str + "/delete",
                type: "POST",
                cache: false,
                data: {},
                success: function () {
                    location.reload();
                }
            });

        });

        $(document).on("click", "button[id^='plus']", function () {
            let id = this.id;
            let str = id.split("_")[1];
            $.ajax({
                url: "/cart/" + str + "/setAmount",
                type: "POST",
                cache: false,
                data: {"mathSign": "+"},
                success: function () {
                    location.reload();
                }
            });
        });
        $(document).on("click", "button[id^='minus']", function () {
            let id = this.id;
            let str = id.split("_")[1];
            $.ajax({
                url: "/cart/" + str + "/setAmount",
                type: "POST",
                cache: false,
                data: {"mathSign": "-"},
                success: function () {
                    location.reload();
                }
            });
        });
    });
</script>
<body>
<div class="container">
    <div class="row">
        <form action="/order/addForm" method="post">
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
        <c:forEach items="${carts}" var="cart">
                <tr>
                    <td><input type="checkbox" name="cartCodes[${i}]" value="${cart.cartId}"></td>
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
                        <button class="btn btn-secondary btn-sm" type="button" id="minus_${cart.cartId}">-</button>
                           <span id="amount_${cart.cartId}">${cart.amount}</span>
                        <button class="btn btn-secondary btn-sm" type="button" id="plus_${cart.cartId}">+</button>
                    </td>
                    <td><button class="btn btn-sm btn-secondary" type="button" id="deleteCart_${cart.cartId}">삭제하기</button></td>
                </tr>
            <c:set var="i" value="${i+1}" scope="page"/>
        </c:forEach>
                </tbody>
            </table>
            <div>
                <span>총 결제 금액 : </span><span id="total" style="font-size: xx-large">0</span>원
            <input class="btn btn-secondary pull-right" type="submit" value="결제하기">
            </div>
        </form>
    </div>
</div>
</body>
</html>
