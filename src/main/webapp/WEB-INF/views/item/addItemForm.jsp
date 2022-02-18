<%--
  Created by IntelliJ IDEA.
  User: gimtaeyeon
  Date: 2022/02/18
  Time: 3:23 오후
  To change this template use File | Settings | File Templates.
--%>
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
    <script type="text/javascript" src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
    <script type="text/javascript">
        $(document).ready(function (e) {
            let optionSetting = 0;
            getCountries();
            CKEDITOR.replace("content", {
                uploadUrl: "/file/img/dropUpload"
            }); // 편집기

            function getCountries() {
                let result = '';
                $.ajax({
                    url: '/country',
                    method: 'GET',
                    success: function (data) {
                        $.each(data, function (key, value) {
                            let countryCode = value.countryCode;
                            let countryName = value.countryName;
                            result += "<option value='" + countryCode + "'>" + countryName + "</option>"
                        });
                        $("#countryCode").html(result);
                    }
                })
            }

            function getOptionForm() {
                let result = "<div style='display: inline-block' name='option" + optionSetting + "' id='option" + optionSetting + "'>"
                result += '<p>옵션</p>';
                result += "사이즈<input type='number' name='itemOptions[" + optionSetting + "].optionName'/> <br>"
                result += "보유 수량<input type='number' name='itemOptions[" + optionSetting + "].optionStock'/> <br>"
                result += "변동액<input type='number' name='itemOptions[" + optionSetting + "].optionPriceUd'/> <br>"
                result += "<button type='button' name='deleteOption'>삭제</button>"
                result += "</div>"
                $("#options").append(result);
                optionSetting += 1;
            }

            $("#createOption").click(function () {
                getOptionForm();
            });

            $(document).on("click","[name='deleteOption']",function() {
                let optionDiv = $(this).closest("div");
                optionDiv.remove();
            });
        });
    </script>
</head>
<body class="pt-5">
<div class="container">

    <form action="/item/add" method="post" enctype="multipart/form-data" id="addForm">
        <div class="mb-3">
            <label for="itemName">이름</label>
            <input class="form-control" type="text" name="itemName" id="itemName" placeholder="상품 이름을 입력해주세요." required>
        </div>
        <div class="mb-3">
            <button type="button" id="createOption">옵션 생성하기</button>
            <div class="mb-3" id="options">

            </div>
        </div>
        <div class="mb-3">
            <label for="countryCode">국가코드</label>
            <select name="countryCode" id="countryCode" required>
            </select>
        </div>
        <div class="mb-3">
            <label for="itemPrice">가격</label>
            <input class="form-control" type="number" name="itemPrice" id="itemPrice" placeholder="가격을 입력해주세요.(한화기준)"
                   required>
        </div>
        <div class="mb-3">
            <label for="file">상품 이미지</label>
            <input type="file" name="file" id="file" required>
        </div>
        <div class="mb-3">
            <label for="content">상세 보기</label>
            <textarea name="itemInfo" id="content" style="width:100%;" required></textarea>
        </div>
        <input type="submit" value="생성하기">
    </form>
</div>
</body>
</html>
