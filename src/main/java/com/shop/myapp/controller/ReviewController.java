package com.shop.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.myapp.dto.Review;
import com.shop.myapp.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private final ReviewService reviewService;
		
	public ReviewController(ReviewService reviewService, HttpSession session) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("")
	public String getReviews(Model model) {
		List<Review> reviews = reviewService.getReviews();
		model.addAttribute("revies",reviews);
		return "review/reviews";
	}
	@GetMapping("/{reviewCode}")
	public String getReviewDetail(@PathVariable String reviewCode, Model model) {
		List<Review> review = reviewService.getReviews(reviewCode);
		model.addAttribute("review",review);
		
		return reviewCode;		
	}
	
	@GetMapping("/{reviewCode}/update")
	public String updateReview(@PathVariable String reviewCode, Review review, Model model) {
		Review review = reviewService.getReview(reviewCode);
		model.addAttribute("review",review);
		return "";
	}
	@PostMapping("/{reviewCode}/update")
	public String updateReview(@PathVariable String review, RedirectAttributes redirectAttributes) {
		
		reviewService.updateReview(review);
		redirectAttributes.addAttribute("reviewCode",reviewCode);
		return "redirect:/review/{reviewCode}";
	}
	
	@PostMapping("/{reviewCode}/delete")
	public String deleteReview(@PathVariable String reviewCode) {
		reviewService.deleteReview(reviewCode);
		return "redirect:/review";
	}
	
	

}
