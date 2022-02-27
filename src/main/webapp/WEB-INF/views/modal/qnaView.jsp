<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        Accordion Item #1<!--여기가 제목  --> <!-- 답글여부  -->
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <!--질문자 id  -->
        <!-- 여기가 질문 --><strong>This is the first item's accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
        <div align="right">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal">
		  수정
		</button>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal">
		  삭제
		</button>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#replyModal">
		  답글달기
		</button>
		</div>
      </div>
      <hr /> 
      <div class="accordion-body">
        <!-- 여기가 답변 --><strong>This is the first item's accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
       <div align="right">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal">
		  수정
		</button>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal">
		  삭제
		</button>
		</div>
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingTwo">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
        Accordion Item #2
      </button>
    </h2>
    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>This is the second item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingThree">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
        Accordion Item #3
      </button>
    </h2>
    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
      </div>
    </div>
  </div>
</div>
<!--밑으로 질문 달기 버튼 모달로  -->
<!-- Button trigger modal -->
<div align="right">
	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#userWriteModal">
	  질문하기
	</button>
</div>

<!-- 질문 Modal -->
<div class="modal fade" id="userWriteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">QNA</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      		<div class="mb-3">
			  <h5 align="left">Title</h5>
			  <input type="text" class="form-control" id="bTitle" name="boardTitle" placeholder="제목을 입력해주세요.">
			</div>
      		<div class="mb-3">
			  <h5 align="left">Content</h5>
			  <input type="text" class="form-control" id="bContent" name="boardContent" placeholder="내용을 입력해주세요.">
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">쓰기</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
