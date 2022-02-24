package com.shop.myapp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.myapp.dto.Item;
import com.shop.myapp.dto.Pagination;
import com.shop.myapp.dto.Review;
import com.shop.myapp.repository.ItemRepository;
import com.shop.myapp.repository.ReviewRepository;


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

	    public int insertReview(Review review) {
	        reviewRepository.insertReview(review);
	       return reviewRepository.insertReview(review);
	    }

	    public int deleteReview(String review) {
	        return reviewRepository.deleteReview(review);
	    }

	    public int updateReview(Review review) {
	    	reviewRepository.updateReview(review);
	     return reviewRepository.updateReview(review);
	    }
	
	    public int getReviewListCnt() {
	    	return reviewRepository.getReviewListCnt();
	    }

}
