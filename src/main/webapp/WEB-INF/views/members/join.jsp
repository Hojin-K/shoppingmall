<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--카카오맵  -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
          crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script>
	$(function() {
		$("[name='chk_info']").change(
			function() {								if (this.value === "판매자") {
					let text = "<div class='mb-3' id='seller'> 사업자명 <br /> <input class='form-control' type='text' name='businessName' />"
					text += " 사업자번호 <br /> <input class='form-control' type='text' name='businessRegistrationNo' /></div>"
					$("#inputDiv").append(text);
				} else {
					$("#seller").remove();
				}
			});
	})
	window.onload = function() {
	document.getElementById("address_kakao")
			.addEventListener(
					"click",
					function() {
						new daum.Postcode(
								{
									oncomplete : function(data) {
										document.getElementById("address_kakao").value = data.address;
									}
								}).open();
					});
	}
	</script>

</head>
<body class="pt-5">
<div class ="container">

	<h2>회원가입</h2>
	<form name="frm" onsubmit="setMemberAddress()" method="POST"
		action="/members/join">
		<div id="inputDiv">
			<input type="radio" name="chk_info" value="일반회원" checked /> 
			<label for="일반회원">일반회원</label> 
			<input type="radio" name="chk_info" value="판매자" /> 
			<label for="판매자">판매자</label> <br />
			<div class="mb-3">
				ID <br />
				<input class="form-control" type="text" name="memberId" />
			</div>
			<div class="mb-3">
				이름 <br />
				<input class="form-control" type="text" name="memberName" />
			</div>
			<div class="mb-3">
				이메일 <br />
				<input class="form-control" type="text" name="memberEmail" /> 
			</div>
			<div class="mb-3">
				비밀번호 <br />
				<input class="form-control" type="password" name="memberPwd" /> 
			</div>
			<div class="mb-3">
				주소 <br />
				<input class="form-control" type="text" id="address_kakao" name="address" readonly />
			</div>
			<div class="mb-3">
				상세주소 <br />
				<input class="form-control" type="text" name="detailAddress" /> 
			</div>
			<div class="mb-3">
				전화번호 <br />
				<input class="form-control" type="phone" name="memberTel" /> 
			</div>
			<div class="mb-3">
				생년월일 <br />
				<input class="form-control" type="date" name="memberBirth" />
			</div>
		</div>
		<input class="form-control" type="submit" value="가입하기">
	</form>
	</div>
</body>
</html>