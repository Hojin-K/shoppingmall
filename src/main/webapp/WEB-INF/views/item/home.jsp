<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<style>
		/* 캐러셀(이미지슬라이드) 이미지 크기변경 */
		.carousel-inner{
			width:100%;
			height:400px; /* 이미지 높이 변경 */
		}
		.carousel-item{
			width: 100%;
			height:100%;
		}
		.d-block {
			display:block;
			width: 100%;
			height: 100%;
		}
		.txt_line {
			width:100%;
			overflow:hidden;
			text-overflow:ellipsis;
			white-space:nowrap;
		}
        a{
            color: #333333;
            text-decoration: none;
        }
        a:hover{
            color: black;
            text-decoration: none;
        }

		.cont-box {
			display: grid;
			grid-template-rows: repeat(2, 1fr);
			grid-template-columns: repeat(3, 1fr);
		}

		.cont-box .item {
			margin: 10px;
			min-height: 200px;
			border: 1px solid #ddd;
		}
		.cont-box .item:nth-child(1) {
			grid-row-start: 1;
			grid-row-end: 3;
			grid-column-start: 1;
			grid-column-end: 2;
		}
		.cont-box .item:nth-child(2) {
			grid-row-start: 1;
			grid-row-end: 2;
			grid-column-start: 2;
			grid-column-end: 2;
		}
		.cont-box .item:nth-child(3) {
			grid-row-start: 1;
			grid-row-end: 2;
			grid-column-start: 3;
			grid-column-end: 4;
		}
		.cont-box .item:nth-child(4) {
			grid-row-start: 2;
			grid-row-end: 2;
			grid-column-start: 2;
			grid-column-end: 2;
		}
		.cont-box .item:nth-child(5) {
			grid-row-start: 2;
			grid-row-end: 2;
			grid-column-start: 3;
			grid-column-end: 4;
		}
		h2{
			text-align: center;
		}
	</style>
	<title>Home</title>
</head>
<body>
<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
	<div class="carousel-indicators">
		<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
		<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
	</div>
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/resources/banner1.png" class="d-block w-100" alt="...">
		</div>
		<div class="carousel-item">
			<img src="/resources/banner2.png" class="d-block w-100" alt="...">
		</div>
		<div class="carousel-item">
			<img src="/resources/banner3.png" class="d-block w-100" alt="...">
		</div>
	</div>
	<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		<span class="visually-hidden">Previous</span>
	</button>
	<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span>
		<span class="visually-hidden">Next</span>
	</button>
</div>


<div class="container">
	<br>
	<h2>따끈따끈 신상품</h2>
	<div class="cont-box">
		<div class="item"><img src="/resources/subBanner/subbanner1.png" style="width: 100%; height: 100%" alt=""></div>
		<c:forEach items="${newItems}" var="item">
			<div class="item">
				<a class="itemLink" href="/item/${item.itemCode}">
					<img src="${item.itemImage}"
						 style="border-bottom: 1px solid #eee; height: 300px; width: 100%" alt="">
					<p style="color: #0d6efd; font-size: small; margin-bottom: 0">${item.country.countryName}</p>
					<p class="txt_line" style="margin: 0; font-size: small"><c:out value="${item.itemName}"/></p>
					<span><c:out value="${item.itemPrice}원"/></span>
				</a>
			</div>
		</c:forEach>
	</div>
	<br>
	<h2>조회수 많은 상품</h2>
	<div class="cont-box">
		<div class="item"><img src="/resources/subBanner/subbanner2.png" style="width: 100%; height: 100%" alt=""></div>
		<c:forEach items="${hitItems}" var="item">
			<div class="item">
				<a class="itemLink" href="/item/${item.itemCode}">
					<img src="${item.itemImage}"
						 style="border-bottom: 1px solid #eee; height: 300px; width: 100%" alt="">
					<p style="color: #0d6efd; font-size: small; margin-bottom: 0">${item.country.countryName}</p>
					<p class="txt_line" style="margin: 0; font-size: small"><c:out value="${item.itemName}"/></p>
					<span><c:out value="${item.itemPrice}원"/></span>
				</a>
			</div>
		</c:forEach>
	</div>
	<br>
	<h2>많이 팔린 상품</h2>
	<div class="cont-box">
		<div class="item"><img src="/resources/subBanner/subbanner3.png" style="width: 100%; height: 100%" alt=""></div>
		<c:forEach items="${sellItems}" var="item">
			<div class="item">
				<a class="itemLink" href="/item/${item.itemCode}">
					<img src="${item.itemImage}"
						 style="border-bottom: 1px solid #eee; height: 300px; width: 100%" alt="">
					<p style="color: #0d6efd; font-size: small; margin-bottom: 0">${item.country.countryName}</p>
					<p class="txt_line" style="margin: 0; font-size: small"><c:out value="${item.itemName}"/></p>
					<span><c:out value="${item.itemPrice}원"/></span>
				</a>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>
