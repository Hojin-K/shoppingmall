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
    <style>
        .back-to-top {
            cursor: pointer;
            position: fixed;
            bottom: 10px;
            right: 10px;
            display: none;
        }
    </style>
    <script>
        $(function () {
            $(window).scroll(function () {
                if ($(this).scrollTop() > 250) {
                    $('#back-to-top').fadeIn();
                    $('#back-to-top').css('left');
                } else {
                    $('#back-to-top').fadeOut();
                }
            });
            $("#back-to-top").click(function () {
                $('html, body').animate({scrollTop: 0}, 1500);
                return false;
            });

            $(document).on("click","#backMain",function() {
                location.href="/item/getItem";
            });

        });
    </script>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-6 m-auto" style="height: 20rem">
            <img src="${item.itemImage}" style="width: auto; height: 100%; object-fit: cover;" alt="">
        </div>
        <div class="col-6 m-auto">
            <form action="/cart/add" method="post">
                <div>
                    <h3>${item.itemName}</h3>
                    <div>
                        <span class="aa-product-view-price"><fmt:formatNumber pattern="#,### 원"
                                                                              value="${item.itemPrice}"/></span>
                        <br>
                        <label for="optionCode">사이즈</label>
                        <select name="optionCode" id="optionCode">
                            <c:forEach items="${item.itemOptions}" var="itemOption">
                                <c:if test="${itemOption.optionStock == 0}">
                                    <option value="${itemOption.optionCode}" disabled>${itemOption.optionName}(품절)
                                    </option>
                                </c:if>
                                <c:if test="${itemOption.optionStock > 0}">
                                    <option value="${itemOption.optionCode}">${itemOption.optionName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <br>
                        <input type="submit" class="btn btn-dark" value="장바구니에 담기">
                        <button id="backMain" type="button" class="btn btn-secondary">취소</button>
                    </div>
                </div>
            </form>
        </div>
        <hr style="border: 2px solid #cccccc">
        <div class="row text-center">
            <h2>상세보기</h2>
            ${item.itemInfo}
        </div>
    </div>
    <a id="back-to-top" href="#" class="btn btn-secondary btn-sm back-to-top" role="button"
       title="Click to return on the top page" data-toggle="tooltip" data-placement="left">TOP<span
            class="glyphicon glyphicon-chevron-up"></span></a>
</div>


</body>
</html>
