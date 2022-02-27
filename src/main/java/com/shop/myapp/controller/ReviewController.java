package com.shop.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.myapp.dto.Member;
import com.shop.myapp.dto.Pagination;
import com.shop.myapp.dto.Review;
import com.shop.myapp.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private final ReviewService reviewService;
	private final HttpSession session;
		
	public ReviewController(ReviewService reviewService, HttpSession session) {
		this.reviewService = reviewService;
		this.session = session;
	}

	/*@GetMapping("/list")
	public ResponseEntity<Object> getReviews(@RequestParam(required = false, defaultValue = "1") int page) {
		//@RequestParam 페이지 넘버를 선택하지 않은 페이지 첫접속과 같은 경우에 1페이지로 자동으로 돌려주는 부분
		Pagination pagination = new Pagination();
		int reviewListCnt = reviewService.getReviewListCnt();
		pagination.pageInfo(page,reviewListCnt);
		List<Review> reviews = reviewService.getReviews(pagination);
		ResponseEntity.status(300).build();
		return ResponseEntity.ok(reviews); // 성공 했을 때, 성공했다는 신호와 함께 요청 값을 같이 보내줌.
	}*/
	
	@ResponseBody
	@GetMapping("/list")
	public ModelAndView getReviews(@RequestParam(required = false, defaultValue = "1") int page) {
		//@RequestParam 페이지 넘버를 선택하지 않은 페이지 첫접속과 같은 경우에 1페이지로 자동으로 돌려주는 부분
		Pagination pagination = new Pagination();
		int reviewListCnt = reviewService.getReviewListCnt();
		pagination.pageInfo(page,reviewListCnt);
		List<Review> reviews = reviewService.getReviews(pagination);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("modal/review");
		mv.addObject("reviews", reviews);
		
		return mv; // 성공 했을 때, 성공했다는 신호와 함께 요청 값을 같이 보내줌.
	}
	
	@PostMapping("/add") //reviewCode가 sql에서 자동 생성되기 때문에 {reviewCode}를 붙이지 않는다.
	public ResponseEntity<Object> insertReview(@ModelAttribute Review review){
		Member member=(Member)session.getAttribute("member");
		review.setMemberId(member.getMemberId());
		reviewService.insertReview(review);
		return ResponseEntity.ok().build();
	}
	
	/*@GetMapping("/list")
	public ResponseEntity<Object> reviewList(){
		List<Review> reviews = reviewService.reviewList();
		return ResponseEntity.ok(reviews);
	}
	*/
	@ResponseBody
	@PostMapping("/{reviewCode}/delete")
	public ResponseEntity<Object> deleteReview(@PathVariable String reviewCode) {
		//로그인 아이디 비교
		Member member = (Member) session.getAttribute("member");
		String memberId = member.getMemberId();
		Review review = reviewService.findByReviewId(memberId);
		// ajax에서 statusCode로 체크
		if(review.getMemberId().equals(memberId)) {
			reviewService.deleteReview(reviewCode);
//			if(result == 0) {
//				삭제 실패
//				return ResponseEntity.status(405).build();
//			}
			// 삭제 성공
			return ResponseEntity.ok().build();
		} 
		// 회원 아이디 불일치
		return ResponseEntity.status(400).build();
	}
	
	/*	@GetMapping("/{reviewCode}") // 나중에 삭제 가능성 있음.
	public String getReviewDetail(@PathVariable String reviewCode, Model model) {
		Review review = reviewService.getReview(reviewCode);
		model.addAttribute("review",review);
		
		return "review/review";		
	}
	 */
	
	/*// 평점 옵션
	Map ratingOptions = new HashMap();
	ratingOptions.put(0, "☆☆☆☆☆");
	ratingOptions.put(1, "★☆☆☆☆");
	ratingOptions.put(2, "★★☆☆☆");
	ratingOptions.put(3, "★★★☆☆");
	ratingOptions.put(4, "★★★★☆");
	ratingOptions.put(5, "★★★★★");
	model.addAttribute("ratingOptions", ratingOptions);
	*/
	

}
