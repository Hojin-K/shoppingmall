<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style>
.review-list {
	margin-top: 20px;
	padding: 0;
	border-top: 1px solid #ddd;
}

.review-list li {
	list-style: none;
	padding: 20px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.review-list li:nth-child(2n) {
	background: #eee;
}

.review-list li dl dt .re-id {
	font-size: 15px;
}

.review-list li dl dt .re-model {
	margin-top: 8px;
	display: flex;
	align-items: center;
}

.review-list li dl dt .re-model img {
	width: 60px;
	margin-right: 10px;
}

.review-list li dl dt .star-box {
	padding: 0;
	margin: 0;
}

.star-box .star-sel {
	position: relative;
	display: inline-block;
	z-index: 20;
	opacity: 0.001;
	width: 30px;
	height: 30px;
	background-color: #fff;
	cursor: pointer;
	vertical-align: top;
	display: none;
}

.star-box .star-sel+label {
	position: relative;
	display: inline-block;
	margin-left: -4px;
	z-index: 10;
	width: 30px;
	height: 30px;
	background-image:
		url("data:image/svg+xml,%3C%3Fxml version='1.0' %3F%3E%3C!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'%3E%3Csvg style='enable-background:new 0 0 512 512;' version='1.1' viewBox='0 0 512 512' width='30px' height='30px' xml:space='preserve' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink'%3E%3Cpath d='M480,207H308.6L256,47.9L203.4,207H32l140.2,97.9L117.6,464L256,365.4L394.4,464l-54.7-159.1L480,207z M362.6,421.2 l-106.6-76l-106.6,76L192,298.7L84,224h131l41-123.3L297,224h131l-108,74.6L362.6,421.2z'/%3E%3C/svg%3E");
	cursor: pointer;
}

.star-box .star-sel:checked+label {
	background-image:
		url("data:image/svg+xml,%3C%3Fxml version='1.0' %3F%3E%3C!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'%3E%3Csvg style='enable-background:new 0 0 100 100; fill: %230d6efd;' version='1.1' viewBox='0 0 512 512' width='30px' height='30px' xml:space='preserve' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink'%3E%3Cpath d='M480,207H308.6L256,47.9L203.4,207H32l140.2,97.9L117.6,464L256,365.4L394.4,464l-54.7-159.1L480,207z'/%3E%3C/svg%3E");
}

.review-list li dl dd {
	padding-top: 20px;
	font-size: 14px;
}

.review-textarea textarea {
	width: 100%;
	height: 100px;
	padding: 20px;
}

.btn-box {
	display: flex;
	justify-content: flex-end;
}

.review-text {
	padding-bottom: 20px;
	line-height: 1.4;
}

.btn-box button {
	margin: 0 5px;
	padding: 5px 10px 4px 30px;
	line-height: 30px;
	color: #0d6efd;
	font-weight: 700;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.delete {
	background:
		url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' height='24' viewBox='0 0 48 48' width='24' fill='%230d6efd'%3E%3Cpath d='M0 0h48v48H0V0z' fill='none'/%3E%3Cpath d='M12 38c0 2.2 1.8 4 4 4h16c2.2 0 4-1.8 4-4V14H12v24zm4.93-14.24l2.83-2.83L24 25.17l4.24-4.24 2.83 2.83L26.83 28l4.24 4.24-2.83 2.83L24 30.83l-4.24 4.24-2.83-2.83L21.17 28l-4.24-4.24zM31 8l-2-2H19l-2 2h-7v4h28V8z'/%3E%3Cpath d='M0 0h48v48H0z' fill='none'/%3E%3C/svg%3E")
		no-repeat 4px 7px;
}
</style>
<script>

    //삭제	
	/* $(function () {
        // test();

        $(document).on("click", "#insert", function () {
            location.href = "/item/${review.reviewCode}/insert";
        });
        $(document).on("click", "#update", function () {
            location.href = "/item/${review.reviewCode}/update";
        });
        $(document).on("click", "#delete", function () {
            location.href = "/item/${review.reviewCode}/delete";
        });

        $('#myTab a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        }) */



	</script>


<body>
<c:if test="${not empty reviews}">

	<ul class="reviewlist" style="list-style:none;">
	<c:forEach items="${reviews }" var="review">
		<li style="border: 1px solid #cccccc; text-align: left;">
			<dl>
				<dt>
					<div style="margin-top: 15px">
					<span style="font-weight: normal">ID : </span><strong class="re-id">${review.memberId}</strong>
					</div>

				<div class="star-box" style="margin-top: 5px">
					<c:forEach begin="1" end="5" var="cnt">
						<c:choose>
						<c:when test="${review.reviewStar >= cnt}">
	                <input type="checkbox" name="star" id="star${cnt}" class="star-sel" title="${cnt}점" checked disabled>
	                <label for="star${cnt}"></label>
						</c:when>
							<c:otherwise>
	                <input type="checkbox" name="star" id="star${cnt}" class="star-sel" title="${cnt}점" disabled>
	                <label for="star${cnt}"></label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
	            </div>
	        </dt>
				<dd>
					<div class="reviewcontent">${review.reviewContent}</div>
					<div class="btn-box">
					<c:if test="${sessionScope.member.memberId == review.memberId || sessionScope.member.memberLevel.getLast() == 'SELLER'}">
						<button type="button" id="reviewDelete_${review.reviewCode}" class="delete">삭제</button>
					</c:if>
					</div>
				</dd>
			</dl>
		</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${empty reviews}">
	<h4>작성된 리뷰가 없습니다.</h4>
</c:if>
</body>
</html>