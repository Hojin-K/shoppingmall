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
        $("[name^='cartCodes']").change(function(){
            let value = this.value;
            let price = $('#price_'+value).text();
            let amount = $('#amount_'+value).text();
        if ($("[name^='cartCodes']").is(":checked")){
            total += price*amount;
        } else {
            total -= price*amount;
        }
            $("#total").text(total);
        });

        $(document).on("click", "button[id^='deleteCart']", function () {
            let id = this.id;
            let str = id.split("_")[1];
            $.ajax({
                url:"/cart/"+str+"/delete",
                type : "GET",
                cache: false,
                success: function (){
                    location.href("/cart/myCart");
                }

            })
        });


    });
</script>
<body>
<div class="container">
    <div class="row">
        <form action="">
            <table class="table">
                <thead>
                <tr>
                    <th>선택</th>
                    <th>상품</th>
                    <th>가격</th>
                    <th>갯수</th>
                    <th>주문관리</th>
                </tr>
                </thead>
                <tbody>
        <c:forEach items="${carts}" var="cart">
            <c:set var="i" value="${0}" scope="page"/>
                <tr>
                    <td><input type="checkbox" name="cartCodes[${i}]" value="${cart.cartId}"></td>
                    <td class="content">
                        <div style="width:50px; height:50px; float:left;">
                        <img width="50px" height="50px" src="${cart.itemOption.item.itemImage}" alt="">
                        </div>
                        <div style="width:50px; height:50px; float:left;">
                            <b>${cart.itemOption.item.itemName}</b>
                            <p>${cart.itemOption.optionName}size</p>
                        </div>
                    </td>
                    <td id="price_${cart.cartId}">${cart.itemOption.item.itemPrice}</td>
                    <td id="amount_${cart.cartId}">${cart.amount}</td>
                    <td><button type="button" id="deleteCart_${cart.cartId}">삭제하기</button></td>
                </tr>
        </c:forEach>
                </tbody>
            </table>
                <span>총 결제 금액 : </span><span id="total" style="font-size: xx-large">0</span>원
        </form>
    </div>
</div>
</body>
</html>
