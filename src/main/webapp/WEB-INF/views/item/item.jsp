<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <style>
        .back-to-top {
            cursor: pointer;
            position: fixed;
            bottom: 10px;
            right: 10px;
            display: none;
        }

        .nav-link {
            color: #cccccc;
        }

        .nav-link:hover {
            color: black;
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

            $(document).on("click", "#backMain", function () {
                location.href = "/item/getItem";
            });

            $('#myTab a').click(function (e) {
                e.preventDefault()
                $(this).tab('show')
            })

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
                        <span >${item.country.countryName}</span>
                        <br>
                        <select name="optionCode" id="optionCode" class="form-select col-12">
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
                </div>
            </form>
        </div>
        <div class="row text-center my-2" style="text-align: center; width: 100%">
            <ul class="nav nav-tabs nav-justified" id="myTab">
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#qwe">상세보기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#asd">리뷰</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#zxc">QNA</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade show active text-center" id="qwe">
                    ${item.itemInfo}
                </div>
                <div class="tab-pane fade text-center" id="asd">
                    <p>review</p>
                </div>
                <div class="tab-pane fade text-center" id="zxc">
                    <p>QNA</p>
                </div>
            </div>
        </div>
    </div>
    <a id="back-to-top" href="#" class="btn btn-secondary btn-sm back-to-top" role="button"
       title="Click to return on the top page" data-toggle="tooltip" data-placement="left">TOP<span
            class="glyphicon glyphicon-chevron-up"></span></a>
</div>


</body>
</html>
