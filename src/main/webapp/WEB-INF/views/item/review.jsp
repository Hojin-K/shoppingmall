<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
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
        font-size: 20px;
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
    .star-box .star-sel + label {
        position: relative;
        display: inline-block;
        margin-left: -4px;
        z-index: 10;
        width: 30px;
        height: 30px;
        background-image: url("data:image/svg+xml,%3C%3Fxml version='1.0' %3F%3E%3C!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'%3E%3Csvg style='enable-background:new 0 0 512 512;' version='1.1' viewBox='0 0 512 512' width='30px' height='30px' xml:space='preserve' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink'%3E%3Cpath d='M480,207H308.6L256,47.9L203.4,207H32l140.2,97.9L117.6,464L256,365.4L394.4,464l-54.7-159.1L480,207z M362.6,421.2 l-106.6-76l-106.6,76L192,298.7L84,224h131l41-123.3L297,224h131l-108,74.6L362.6,421.2z'/%3E%3C/svg%3E");
        cursor: pointer;
    }
    .star-box .star-sel:checked + label {
        background-image: url("data:image/svg+xml,%3C%3Fxml version='1.0' %3F%3E%3C!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'%3E%3Csvg style='enable-background:new 0 0 100 100; fill: %230d6efd;' version='1.1' viewBox='0 0 512 512' width='30px' height='30px' xml:space='preserve' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink'%3E%3Cpath d='M480,207H308.6L256,47.9L203.4,207H32l140.2,97.9L117.6,464L256,365.4L394.4,464l-54.7-159.1L480,207z'/%3E%3C/svg%3E");
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
    #insert {
        background: url("data:image/svg+xml,%3Csvg version='1.1' viewBox='0 0 32 32' height='24px' width='24px' xmlns='http://www.w3.org/2000/svg' xmlns:sketch='http://www.bohemiancoding.com/sketch/ns' xmlns:xlink='http://www.w3.org/1999/xlink'%3E%3Ctitle/%3E%3Cdesc/%3E%3Cdefs/%3E%3Cg fill='none' fill-rule='evenodd' id='Page-1' stroke='none' stroke-width='1'%3E%3Cg fill='%23157EFB' id='icon-135-pen-angled'%3E%3Cpath d='M23.1464466,12.0278086 L11.8535534,23.3207019 L11.8535534,23.3207019 L7.85355339,19.3207019 L19.1464466,8.02780864 L23.1464466,12.0278086 L23.1464466,12.0278086 Z M23.8535534,11.3207018 L25.5801067,9.59414849 C26.3642921,8.8099631 26.3661881,7.54044334 25.5897496,6.76400487 L24.4102504,5.58450561 C23.6313906,4.80564584 22.372781,4.80147421 21.5801067,5.59414851 L19.8535534,7.32070186 L23.8535534,11.3207018 L23.8535534,11.3207018 Z M11.1464466,24.0278086 L11,24.1742552 L6,25.1742552 L7,20.1742552 L7.14644661,20.0278086 L11.1464466,24.0278086 L11.1464466,24.0278086 Z' id='pen-angled'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E") no-repeat 4px 7px;
    }
    #update {
        background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' enable-background='new 0 0 64 64' style='fill: %230d6efd;' height='24px' id='Layer_1' version='1.1' viewBox='0 0 64 64' width='24px' xml:space='preserve'%3E%3Cg%3E%3Cpath d='M55.736,13.636l-4.368-4.362c-0.451-0.451-1.044-0.677-1.636-0.677c-0.592,0-1.184,0.225-1.635,0.676l-3.494,3.484 l7.639,7.626l3.494-3.483C56.639,15.998,56.639,14.535,55.736,13.636z'/%3E%3Cpolygon points='21.922,35.396 29.562,43.023 50.607,22.017 42.967,14.39 '/%3E%3Cpolygon points='20.273,37.028 18.642,46.28 27.913,44.654 '/%3E%3Cpath d='M41.393,50.403H12.587V21.597h20.329l5.01-5H10.82c-1.779,0-3.234,1.455-3.234,3.234v32.339 c0,1.779,1.455,3.234,3.234,3.234h32.339c1.779,0,3.234-1.455,3.234-3.234V29.049l-5,4.991V50.403z'/%3E%3C/g%3E%3C/svg%3E") no-repeat 4px 7px;
    }
    #delete {
        background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' height='24' viewBox='0 0 48 48' width='24' fill='%230d6efd'%3E%3Cpath d='M0 0h48v48H0V0z' fill='none'/%3E%3Cpath d='M12 38c0 2.2 1.8 4 4 4h16c2.2 0 4-1.8 4-4V14H12v24zm4.93-14.24l2.83-2.83L24 25.17l4.24-4.24 2.83 2.83L26.83 28l4.24 4.24-2.83 2.83L24 30.83l-4.24 4.24-2.83-2.83L21.17 28l-4.24-4.24zM31 8l-2-2H19l-2 2h-7v4h28V8z'/%3E%3Cpath d='M0 0h48v48H0z' fill='none'/%3E%3C/svg%3E") no-repeat 4px 7px;
    }
	</style>
	<script>
	
	  $(document).ready(function(){    
		  reviewList();
	    });      
	  
	  function updateReview(){        
	        location.href = "/review/updateReview";
	    }
	  //목록조회
	   function list(){
	 
	        $.ajax({    
	        
	           url      : "/review/reviewList",
	           data     : $("#boardForm").serialize(),
	           dataType : "JSON",
	           cache    : false,
	           async    : true,
	           type     : "GET",    
	           success  : function(obj) {
	                getBoardListCallback(obj);                
	            },           
	           error    : function(xhr, status, error) {}
	            
	         });
	    }
      
	/* $(function(){
	    init();
	    $('#reviewList').on('click',reviewList);
	})
	
	function init(){
    var reviewcode = ${review.reviewCode}
    sendData={"review":review}
    $.ajax({
        data : sendData,
        method :'GET',
        url: 'reviewList',
        success :output
    })
}
	
	function reviewDelete(){
	    var reviewcode = $(this).attr("data-del");
	    var sendData = {"reviewcode": reviewcode}
	    $.ajax({
	        method: 'POST',
	        url : 'reviewDelete',
	        data : sendData,
	        success: init
	    })
	}
} */
	
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

        
        //별점 마킹 모듈 프로토타입으로 생성(별점 부분)
       /*  function Rating(){};
        Rating.prototype.rate = 0;
        Rating.prototype.setRate = function(newrate){
            //별점 마킹 - 클릭한 별 이하 모든 별 체크 처리
            this.rate = newrate;
            let items = document.querySelectorAll('.star-sel');
            items.forEach(function(item, idx){
                if(idx < newrate){
                    item.checked = true;
                }else{
                    item.checked = false;
                }
            });
        }
        let rating = new Rating();//별점 인스턴스 생성
        //별점선택 이벤트 리스너
        document.querySelector('.star-box').addEventListener('click',function(e){
            let elem = e.target;
            if(elem.classList.contains('star-sel')){
                rating.setRate(parseInt(elem.value));
            }
        });
	}); */
	</script>


<body>

<ul class="review-list">
	<li>
	    <dl>
	        <dt>
	            <strong class="re-id">id</strong>
	            <div class="re-model">
	                <img src="sample01.jpg" alt="">
	                <span>nikey-mkdojs-sjdks</span>
	            </div>
	            <div class="star-box">
	                <input type="checkbox" name="star" id="star1" value="1" class="star-sel" title="1점">
	                <label for="star1"></label>
	                <input type="checkbox" name="star" id="star2" value="2" class="star-sel" title="2점">
	                <label for="star2"></label>
	                <input type="checkbox" name="star" id="star3" value="3" class="star-sel" title="3점" >
	                <label for="star3"></label>
	                <input type="checkbox" name="star" id="star4" value="4" class="star-sel" title="4점">
	                <label for="star4"></label>
	                <input type="checkbox" name="star" id="star5" value="5" class="star-sel" title="5점">
	                <label for="star5"></label>
	            </div>
	        </dt>
	        <dd>
	            <div class="review-textarea">
	                <textarea name="" id="" cols="30" rows="10"></textarea>
	            </div>
	            <div class="review-text">
	                리뷰 리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰
	               
	            </div>
	            <div class="btn-box">
	                <button type="button" id="insert">리뷰쓰기</button>
	                <button type="button" id="update">수정</button>
	                <button type="button" id="delete">삭제</button>
	            </div>
	        </dd>
	    </dl>
	</li>
	<li>
	    <dl>
	        <dt>
	            <strong class="re-id">id</strong>
	            <div class="re-model">
	                <img src="sample01.jpg" alt="">
	                <span>nikey-mkdojs-sjdks</span>
	            </div>
	            <div class="star-box">
	                <input type="checkbox" name="star" id="star1" value="1" class="star-sel" title="1점">
	                <label for="star1"></label>
	                <input type="checkbox" name="star" id="star2" value="2" class="star-sel" title="2점">
	                <label for="star2"></label>
	                <input type="checkbox" name="star" id="star3" value="3" class="star-sel" title="3점" >
	                <label for="star3"></label>
	                <input type="checkbox" name="star" id="star4" value="4" class="star-sel" title="4점">
	                <label for="star4"></label>
	                <input type="checkbox" name="star" id="star5" value="5" class="star-sel" title="5점">
	                <label for="star5"></label>
	            </div>
	        </dt>
	        <dd>
	            <div class="review-textarea">
	                <textarea name="" id="" cols="30" rows="10"></textarea>
	            </div>
	            <div class="review-text">
	                리뷰 리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰
	            </div>
	            <div class="btn-box">
	                <button type="button" id="insert">리뷰쓰기</button>
	                <button type="button" id="update">수정</button>
	                <button type="button" id="delete">삭제</button>
	            </div>
	        </dd>
	    </dl>
	</li>
	<li>
	    <dl>
	        <dt>
	            <strong class="re-id">id</strong>
	            <div class="re-model">
	                <img src="sample01.jpg" alt="">
	                <span>nikey-mkdojs-sjdks</span>
	            </div>
	            <div class="star-box">
	                <input type="checkbox" name="star" id="star1" value="1" class="star-sel" title="1점">
	                <label for="star1"></label>
	                <input type="checkbox" name="star" id="star2" value="2" class="star-sel" title="2점">
	                <label for="star2"></label>
	                <input type="checkbox" name="star" id="star3" value="3" class="star-sel" title="3점" >
	                <label for="star3"></label>
	                <input type="checkbox" name="star" id="star4" value="4" class="star-sel" title="4점">
	                <label for="star4"></label>
	                <input type="checkbox" name="star" id="star5" value="5" class="star-sel" title="5점">
	                <label for="star5"></label>
	            </div>
	        </dt>
	        <dd>
	            <div class="review-textarea">
	                <textarea name="" id="" cols="30" rows="10"></textarea>
	            </div>
	            <div class="review-text">
	                리뷰 리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰
	              
	            </div>
	            <div class="btn-box">
	                <button type="button" id="insert">리뷰쓰기</button>
	                <button type="button" id="update">수정</button>
	                <button type="button" id="delete">삭제</button>
	            </div>
	        </dd>
	    </dl>
	</li>
</ul>
</body>
</html>