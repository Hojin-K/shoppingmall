
<html>
<head>
	<style>
		/* 캐러셀(이미지슬라이드) 이미지 크기변경 */
		.carousel-inner{
			width:100%;
			height:300px; /* 이미지 높이 변경 */
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
	</style>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<!-- <a href="itemlistView">itemlistView</a> -->
<a href="members/join">가입</a>
<a href="members/login">로그인</a>

<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
	<div class="carousel-indicators">
		<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
		<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
	</div>
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/resources/test.JPG" class="d-block w-100" alt="...">
		</div>
		<div class="carousel-item">
			<img src="/resources/test2.png" class="d-block w-100" alt="...">
		</div>
		<div class="carousel-item">
			<img src="/resources/test.JPG" class="d-block w-100" alt="...">
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

</body>
</html>
