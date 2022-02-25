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
    <script>
        $(function () {
            $(document).on("click", "[name='refund']", function () {
                let orderDetailCode = $(this).attr('id');
                alert(orderDetailCode);
                fnModuleInfo(orderDetailCode);
            });

            function fnModuleInfo(orderDetailCode) {
                $("#refundModal .modal-content").load("/orderDetail/" + orderDetailCode + "/cancel");
            }
        })
    </script>
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

    .tableContent {
        display: table;
    }

    .tableContent span {
        display: table-cell;
        vertical-align: middle;
    }

</style>
<body class="pt-5">
<div class="container">
    <div class="accordion" id="accordionExample">
        <c:set var="i" value="${0}"/>
        <c:forEach items="${order.orderDetails}" var="orderDetail">
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading${i}">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">
                        <table>
                            <tr>
                                <td>${order.orderCode}</td>
                                <td class="tableContent text-start">
                                        <div style="width:10%; height:100%; float:left;">
                                            <img style="width: 100%;" src="${orderDetail.itemOption.item.itemImage}"
                                                 alt="">
                                        </div>
                                        <div style="width:90%; height:100%; float:left;">
                                            <b style="font-size: medium">${orderDetail.itemOption.item.itemName}</b>
                                            <p>${orderDetail.itemOption.optionName}size</p>
                                        </div>
                                </td>
                                <td style="width: 16.66%">${orderDetail.itemOption.item.itemPrice}원</td>
                                <td style="width: 8.33%">
                                    <span>${orderDetail.amount}개</span>
                                </td>
                                <td style="width: 8.33%">
                                        ${orderDetail.postedStatus}
                                </td>
                            </tr>
                        </table>
                    </button>
                </h2>
                <div id="collapse${i}" class="accordion-collapse collapse" aria-labelledby="heading${i}"
                     data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <c:if test="${orderDetail.postedStatus != 'refund'}">
                            <button id="${orderDetail.orderDetailCode}" data-bs-toggle="modal"
                                    data-bs-target="#refundModal" name="refund"
                                    class="btn btn-sm btn-secondary"
                                    type="button">환불하기
                            </button>
                        </c:if>
                        <strong>This is the first item's accordion body.</strong> It is shown by default, until the...
                    </div>
                </div>
            </div>
            <c:set var="i" value="${i+1}"/>
        </c:forEach>
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
                    <td>${order.change}원</td>
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
<div class="modal fade" id="refundModal" tabindex="-1" role="dialog" aria-labelledby="historyModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
        </div>
    </div>
</div>
</div>
</body>
</html>
