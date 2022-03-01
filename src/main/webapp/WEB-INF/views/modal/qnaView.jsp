<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
$(function () {
	  $(document).on("click", "[id^='replyBtn_']", function () {
		  let boardId = $(this).attr('id').split('_')[1];
		  $(this).hide();
		  result = '<div class="accordion-body" id="replyBody_'+boardId+'">'
		  result += '<form action="/qna/reply" method="post">'
		  result += '<textarea style="width: 100%; height: 50px;" class="form-control" aria-label="With textarea" name="boardReply" placeholder="답변을 입력해주세요."></textarea>'
		  result += '<div align="right">'
		  result += '<button type="submit" class="btn btn-primary" id="replySummitBtn" style="margin-top: 10px; margin-left: 10px;">저장</button>'
		  result +=	'<button type="button" class="btn btn-primary" id="replyCancleBtn_'+boardId+'" style="margin-top: 10px; margin-left: 10px;">취소</button>'
		  result += '<input type="hidden" name="boardId" value="'+boardId+'" />'
		  result += '<input type="hidden" name="itemCode" value="'+${itemCode }+'" />'
		  result +=	'</div></form></div>'
		  
		  $("#cBody_"+boardId).append(result);
		 
      });
	  
	  $(document).on("click", "[id^='replyCancleBtn_']", function () {
		  let boardId = $(this).attr('id').split('_')[1];
		  $("#replyBody_"+boardId).remove();
          $("#replyBtn_"+boardId).show();
      });
   });
</script>

	<div class="accordion" id="accordionList">
<c:forEach items="${qnaList }" var="qna">
	  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingOne">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${qna.boardId}" aria-expanded="true" aria-controls="collapse${qna.boardId}">
		        ${qna.boardTitle}<!--여기가 제목  --> <!-- 답글여부  -->
		      </button>
		    </h2>
		    <div id="collapse${qna.boardId}" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionList">
		      <div class="accordion-body" id="cBody_${qna.boardId }">
		        <!--질문자 id  <--><p align="left" id="bWriter">작성자 : ${qna.memberId}</p>
		        <!-- 여기가 질문 <--><p align="left" id="bContent">${qna.boardContent}</p>
		        <div align="right">
		        <c:if test="${qna.memberId eq sessionScope.member.memberId }">
			        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal">
					  수정
					</button>
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal">
					  삭제
					</button>
				</c:if>
				<c:if test="${sessionScope.member.memberLevel.getLast().equals('SELLER') and qna.boardReply == null}">
					<button type="button" class="btn btn-primary" id="replyBtn_${qna.boardId }" style="margin-top: 10px;">
					  답글달기
					</button>
				</c:if>
				</div>
		      </div>
				<c:if test="${qna.boardReply != null}">
	      <hr /> 
		      <div class="accordion-body">
		      <div align="left">
		        	${qna.boardReply }
		      </div>
		      </div>
	      </c:if>
	  </div>
	</div>
	      </c:forEach>
	    </div>
<!--밑으로 질문 달기 버튼 모달로  -->
<!-- Button trigger modal -->
<hr />
<c:if test="${sessionScope.member.memberLevel.getLast().equals('USER') }">
	<div align="right">
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#userWriteModal">
		  질문하기
		</button>
	</div>
</c:if>

<!-- 질문 Modal -->
<div class="modal fade" id="userWriteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">QNA</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="/qna/write" method="post">
	      <div class="modal-body">
	      		<div class="mb-3">
				  <h5 align="left">Title</h5>
				  <input type="text" class="form-control" id="bTitle" name="boardTitle" placeholder="제목을 입력해주세요.">
				</div>
	      		<div class="mb-3">
				  <h5 align="left">Content</h5>
				  <textarea style="width: 100%; height: 300px;" class="form-control" aria-label="With textarea" id="bContent" name="boardContent" placeholder="내용을 입력해주세요."></textarea>
				</div>
	      <div class="modal-footer">
	      		<input type="hidden" value="${itemCode }" name="itemCode"/>
		      <input type="submit" class="btn btn-primary" value="쓰기" />
	        <!-- <button type="button" class="btn btn-primary" >쓰기</button> -->
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	      </div>
	    </div>
      </form>
 	 </div>
	</div>
</div>