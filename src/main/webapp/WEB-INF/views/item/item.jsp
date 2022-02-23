<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>


        .nav-link {
            color: #cccccc;
        }

        .nav-link:hover {
            color: black;
        }
    </style>
    <script>
        $(function () {
            // test();

            $(document).on("click", "#backMain", function () {
                location.href = "/item/";
            });
            $(document).on("click", "#update", function () {
                location.href = "/item/${item.itemCode}/update";
            });

            $('#myTab a').click(function (e) {
                e.preventDefault()
                $(this).tab('show')
            })

            // function test (){
            //     $.ajax({
            //         url : "/test",
            //         method : "GET",
            //         success : function (data){
            //             alert(data);
            //             $("#review").html(data);
            //         }
            //     })
            // }

        });
    </script>

</head>
<body >
<div class="container">
    <div class="row">
        <div class="col-6 m-auto" style="height: 20rem; text-align: center">
            <img src="${item.itemImage}" style="width: auto; height: 100%; object-fit: cover;" alt="">
        </div>
        <div class="col-6 m-auto">
            <form action="/cart/add" method="post">
                <div>
                    <div>
                        <h3 style="display: inline-block">${item.itemName}</h3>
                        <button style="display: inline; margin-left: 5px; margin-bottom: 10px"  class="btn btn-sm btn-primary disabled">${item.country.countryName}</button>
                    </div>
                    <div>
                        <span class="aa-product-view-price"><fmt:formatNumber pattern="#,### 원"
                                                                              value="${item.itemPrice}"/></span>
                        <br>
                        <select name="optionCode" id="optionCode" class="form-select col-12" required>
                            <option value="" disabled selected hidden>옵션을 선택하여주세요.(필수)</option>
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
                    <br>
                    <div class="row text-center">
                        <input type="submit" class="btn btn-lg btn-dark col-8 m-auto" value="장바구니에 담기">
                        <button id="backMain" type="button" class="btn btn-lg btn-secondary col-3 m-auto">취소</button>
                    </div>
                        <c:if test="${sessionScope.member.memberId == item.memberId}">
                            <br>
                        <button id="update" type="button" class="btn btn-primary col-12 m-auto">수정하기</button>
                        </c:if>
                </div>
            </form>
        </div>
        <div class="row text-center my-2" style="text-align: center; width: 100%">
            <ul class="nav nav-tabs nav-justified" id="myTab">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#qwe">상세보기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#review">리뷰</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#zxc">QNA</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade show active text-center" id="qwe">
                    ${item.itemInfo}
                </div>
                <div class="tab-pane fade text-center" id="review">
                    <p>review</p>
                </div>
                <div class="tab-pane fade text-center" id="zxc">
                    <p>QNA</p>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
