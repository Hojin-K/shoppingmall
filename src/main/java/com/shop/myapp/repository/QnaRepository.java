package com.shop.myapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.myapp.dto.Qna;

@Mapper
public interface QnaRepository {
	List<Qna> findAll(/* {페이징 처리 DTO}, {검색 필터 DTO}*/);
	Qna findByQnaCode(String qnaCode);
	List<Qna> findByItemCode(String itemCode);
	int insertQna(Qna qna);
	int deleteQna(String qna);
	int updateQna(Qna qna);
}