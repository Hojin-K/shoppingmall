<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
/*모달*/
	$(function() {
		function fnModuleInfo(str) {
			alert("dddddd");
			$('#MoaModal .modal-content').load("/admin/" + str + "/detail");
			alert("lkklklklkl");
			$('#MoaModal').modal();
		}
	});
</script>
<style>
	#memTable{
		overflow-x: scroll;
    	overflow-y: auto;
    	width: 100%;
	}
    .tableContent {
        display: table;
    }

    .tableContent span {
        display: table-cell;
        vertical-align: middle;
    }

</style>
<div id="memTable">

	<div class="modal fade" id="MoaModal" tabindex="-1" role="dialog"
		aria-labelledby="historyModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content"></div>
		</div>
	</div>


<table class="table text-center m-auto">
	<thead>
		<tr>
			<th style="width: auto;">ID</th>
			<th style="width: auto;">권한</th>
			<th style="width: auto;">이름</th>
			<th style="width: auto;">E-Mail</th>
			<th style="width: auto;">주소</th>
			<th style="width: auto;">TEL</th>
			<th style="width: auto;">생일</th>
			<th style="width: auto;">사업자번호</th>
			<th style="width: auto;">사업자명</th>
			<th style="width: auto;">사업자설명</th>
			<th style="width: auto;">탈퇴여부</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="i" value="${0}" scope="page" />
		<c:forEach items="${members }" var="member">
			<tr>
				<td><a href="" onclick="fnModuleInfo('${member.memberId }')">${member.memberId }</a></td>
				<td>${member.memberLevel }</td>
				<td>${member.memberName }</td>
				<td>${member.memberEmail }</td>
				<td>${member.memberAddress}</td>
				<td>${member.memberTel }</td>
				<td>${member.memberBirth }</td>
				<td>${member.businessRegistrationNo }</td>
				<td>${member.businessName }</td>
				<td>${member.businessInfo }</td>
				<td>${member.isDelete }</td>
			</tr>
			<c:set var="i" value="${i+1}" scope="page" />
		</c:forEach>
	</tbody>
</table>


</div>

