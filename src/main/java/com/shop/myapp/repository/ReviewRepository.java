package com.shop.myapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.myapp.dto.Review;

@Mapper
public interface ReviewRepository {
	List<Review> findAll(String reivewcode);
	Review findByReviewCode(String reviewCode);
	int insertReview(Review review);
	int deleteReview(String review);
	int updateReview(Review review);

}
