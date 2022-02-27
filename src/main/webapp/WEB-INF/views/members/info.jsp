<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Insert title here</title>
<!--카카오맵  -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    $(function() {
    	levelChk();
        
        $('input[id=memberPwd]').click(function(){
        	if($('#memberPwd').attr('readonly') != null){
	        	$('#memberPwd').removeAttr('readonly');
        	}
    	});
    })
    window.onload = function() {
    document.getElementById("address_kakao").addEventListener("click",function() {
                        new daum.Postcode(
                                {
                                    oncomplete : function(data) {
                                        document.getElementById("address_kakao").value = data.address;
                                    }
                                }).open();
                    });
    }

    function levelChk() {

		if(${member.memberLevel.getLast() == 'SELLER'}){
			 let text = "<div class='mb-3' id='seller'>" 
			 	 text += "사업자명 <br />"
			 	 text += "<input class='form-control' type='text' name='businessName' value='${member.businessName}'/>"
                 text += " 사업자번호 <br />"
                 text += "<input class='form-control' type='text' name='businessRegistrationNo' value='${member.businessRegistrationNo}'/>"
                 text += "<input type='hidden' name='memberLevel' value='${memberLevel}' /></div>"
                 $("#inputDiv").append(text);
		}
	}
</script>
</head>
<body class="pt-5">
<div class ="container">
    <h2>정보수정</h2>
    <form name="frm" method="POST" action="/members/${memberId }/update">
        <div id="inputDiv">
            <div class="mb-3">
                ID <br />
               <input type="text" name="memberId" value="${memberId }" readonly/>
            </div>
            <div class="mb-3">
                이름 <br />
                <input class="form-control" type="text" name="memberName" value="${member.memberName }"/>
            </div>
            <div class="mb-3">
                이메일 <br />
                <input class="form-control" type="text" name="memberEmail" value="${member.memberEmail }"/> 
            </div>
            <div class="mb-3">
                비밀번호 <br />
                <input class="form-control" type="password" id="memberPwd" name="memberPwd" readonly />
            </div>
            <div class="mb-3">
                주소 <br />
                <input class="form-control" type="text" id="address_kakao" name="memberAddress" readonly value="${member.memberAddress }"/>
            </div>
            <div class="mb-3">
                상세주소 <br />
                <input class="form-control" type="text" name="memberDetailAddress" value="${member.memberDetailAddress }"/> 
            </div>
            <div class="mb-3">
                전화번호 <br />
                <input class="form-control" type="phone" name="memberTel" value="${member.memberTel }" /> 
            </div>
            <div class="mb-3">
                생년월일 <br />
                <input class="form-control" type="date" name="memberBirth" value="${member.memberBirth }"/>
            </div>
        </div>
        <button class="btn btn-primary btn-lg" type="submit">수정</button>
        <a class="btn btn-primary btn-lg" href="/" role="button">취소</a>
        <a class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#deleteChkModal" role="button">회원탈퇴</a>
    </form>
</div>
</body>
<!-- 회원 탈퇴 모달팝업 -->
<div class="modal fade" id="deleteChkModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel">회원탈퇴</h3>
        <!-- <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button> -->
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label" style="font-size: 20px;">
            	${memberId } 님 정말로 탈퇴하시겠습니까?
            </label>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <a class="btn btn-primary" href="/members/${memberId }/delete" role="button">탈퇴</a>
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
</html>