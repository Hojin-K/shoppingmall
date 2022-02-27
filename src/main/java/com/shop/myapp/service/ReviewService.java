package com.shop.myapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.Item;
import com.shop.myapp.dto.Pagination;
import com.shop.myapp.dto.Review;
import com.shop.myapp.repository.ItemRepository;
import com.shop.myapp.repository.ReviewRepository;

import lombok.Builder;


@Service
@Transactional
public class ReviewService {
	
	private ReviewRepository reviewRepository;
	
	public ReviewService(@Autowired SqlSession sqlSession) {
		this.reviewRepository = sqlSession.getMapper(ReviewRepository.class);
				
	}
	
	public Review getReview(String reviewCode) {
		Review review = reviewRepository.findByReviewCode(reviewCode);
		return review;		
	}
	
	
	 public List<Review> getReviews(Pagination pagination) {
	        return reviewRepository.findAll(pagination);
	    }
	
	/*public List<Review> reviewList(){
		List<Review> reviews = new ArrayList<>();
		reviews = reviewRepository.getReivewList();
		return reviews;
	}*/

	    public int insertReview(Review review) {
	       return reviewRepository.insertReview(review);
	    }

	    public int deleteReview(String review) {
	        return reviewRepository.deleteReview(review);
	    }

	    public int updateReview(String reviewCode, String reviewContent) {
	     return reviewRepository.updateReview(reviewCode, reviewContent);
	    }
	
	    public int getReviewListCnt() {
	    	return reviewRepository.getReviewListCnt();
	    }
	    
	    public Review findByReviewId(String memberId){
	        return reviewRepository.findByReviewId(memberId);

	    }

}
