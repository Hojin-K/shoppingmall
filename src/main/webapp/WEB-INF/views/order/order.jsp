<%--
  Created by IntelliJ IDEA.
  User: gimtaeyeon
  Date: 2022/02/21
  Time: 11:53 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Title</title>
<script>
	$(function() {
		$(document).on("click", "[name='refund']", function() {
			let orderDetailCode = $(this).attr('id');
			alert(orderDetailCode);
			fnModuleInfo(orderDetailCode);
		});

		function fnModuleInfo(orderDetailCode) {
			$("#refundModal .modal-content").load(
					"/orderDetail/" + orderDetailCode + "/cancel");
		}

		function Rating() {
		}
		;
		Rating.prototype.rate = 0;
		Rating.prototype.setRate = function(newrate) {
			//별점 마킹 - 클릭한 별 이하 모든 별 체크 처리
			this.rate = newrate;
			let items = document.querySelectorAll('.star-sel');
			items.forEach(function(item, idx) {
				if (idx < newrate) {
					item.checked = true;
				} else {
					item.checked = false;
				}
			});
		}
		let rating = new Rating();//별점 인스턴스 생성
		//별점선택 이벤트 리스너
		document.querySelector('.star-box').addEventListener('click',
				function(e) {
					let elem = e.target;
					if (elem.classList.contains('star-sel')) {
						rating.setRate(parseInt(elem.value));
					}
				});
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
			<c:set var="i" value="${0}" />
			<c:forEach items="${order.orderDetails}" var="orderDetail">
				<div class="accordion-item">
					<h2 class="accordion-header" id="heading${i}">
						<button class="accordion-button" type="button"
							data-bs-toggle="collapse" data-bs-target="#collapse${i}"
							aria-expanded="true" aria-controls="collapse${i}">
							<div style="width: 8.33%">${order.orderCode}</div>
							<div class="tableContent text-start"
								style="width: 30%; margin-left: 50px">
								<div style="width: 10%; height: 100%; float: left;">
									<img style="width: 100%;"
										src="${orderDetail.itemOption.item.itemImage}" alt="">
								</div>
								<div style="width: 90%; height: 100%; float: left;">
									<b style="font-size: medium">${orderDetail.itemOption.item.itemName}</b>
									<p>${orderDetail.itemOption.optionName}size</p>
								</div>
							</div>
							<div style="width: 16.66%">${orderDetail.itemOption.item.itemPrice}원</div>
							<div style="width: 8.33%">
								<span>${orderDetail.amount}개</span>
							</div>
							<div style="width: 8.33%">${orderDetail.postedStatus}</div>
						</button>
					</h2>
					<div id="collapse${i}" class="accordion-collapse collapse"
						aria-labelledby="heading${i}" data-bs-parent="#accordionExample">
						<div class="accordion-body">
							<c:if test="${orderDetail.postedStatus == 'Done'} }">
								<div>
									<div class="review-textarea">
										<form action="/review/add">
											<div class="star-box">
												<input type="checkbox" name="reviewStar" id="star1" value="1"
													class="star-sel" title="1점"> <label for="star1"></label>
												<input type="checkbox" name="reviewStar" id="star2" value="2"
													class="star-sel" title="2점"> <label for="star2"></label>
												<input type="checkbox" name="reviewStar" id="star3" value="3"
													class="star-sel" title="3점"> <label for="star3"></label>
												<input type="checkbox" name="reviewStar" id="star4" value="4"
													class="star-sel" title="4점"> <label for="star4"></label>
												<input type="checkbox" name="reviewStar" id="star5" value="5"
													class="star-sel" title="5점"> <label for="star5"></label>
											</div>
											<label for="reviewContent">리뷰작성하기</label> <input
												class="form-control" type="text" name="reviewContent"
												id="reviewContent"> <input type="hidden"
												name="itemCode"
												value="${orderDetail.itemOption.item.itemCode}" /> <input
												type="submit" value="작성하기" />
										</form>
									</div>
								</div>
							</c:if>
							<c:if test="${orderDetail.postedStatus != 'Refund'}">
								<button id="${orderDetail.orderDetailCode}"
									data-bs-toggle="modal" data-bs-target="#refundModal"
									name="refund" class="btn btn-sm btn-secondary" type="button">환불하기
								</button>
							</c:if>
						</div>
					</div>
				</div>
				<c:set var="i" value="${i+1}" />
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
		<div class="modal fade" id="refundModal" tabindex="-1" role="dialog"
			aria-labelledby="historyModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-xl">
				<div class="modal-content"></div>
			</div>
		</div>
	</div>
</body>
</html>
