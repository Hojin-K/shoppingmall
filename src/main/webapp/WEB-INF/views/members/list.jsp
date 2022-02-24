<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--카카오맵  -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script>
$(function() {
	$('#btnSearch').click(function () {
		let chkInfo = $('[name = chk_info]:checked').val();
		alert(chkInfo);
 		let condition = $('#condition').val();
 		$.ajax({
            url: '/members/list',
            method: 'POST',
            data: {
            	'chkInfo' : chkInfo,
            	'condition' : condition
            },
            dataType: 'json',
            success: function (data) {
            	$("#memberList").empty();
            	let view = "<tr><td>ID</td><td>이름</td></tr>";
	                $.each(data, function (key, member) {
	                    alert(member.memberId);
	                    let Id = member.memberId;
	                    let Name = member.memberName;
	                    
	                    view += "<tr><td>"+Id+"</td><td>"+Name+"</td></tr>"
	        			
	                });
        		$("#memberList").append(view);
            }
        })
	});
	
})
</script>
</head>
<body class="pt-5">
	<div class="container">
		<div>
			<h2>회원관리</h2>
			<input type="radio" name="chk_info" value="member_id" checked /> 
			<label for="아이디">아이디</label> 
			<input type="radio" name="chk_info" value="member_name" /> 
			<label for="이름">이름</label>
			<span style="margin-left: 30px;"><input id="condition" type="text" /></span>
			<span style="margin-left: 15px;"><input id="btnSearch" type="button" value="검색"/></span>
		</div>
		<table id="memberList" style="border: 1px;">
		</table>
		<div id="countryCode"></div>
	</div>
</body>
</html>