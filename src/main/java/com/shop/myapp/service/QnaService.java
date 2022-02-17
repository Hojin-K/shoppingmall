package com.shop.myapp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.myapp.dto.Qna;
import com.shop.myapp.repository.QnaRepository;
import com.shop.myapp.vopage.QnaVO;


@Service
@Transactional
public class QnaService {

	
	private QnaRepository qnaRepository;

	public QnaService(@Autowired SqlSession sqlSession) {
		this.qnaRepository = sqlSession.getMapper(QnaRepository.class);
	}
	
	public List<Qna> getQnas(String qna){
		List<Qna> qnas = qnaRepository.findAll();
		return qnas;
	}
	
	public int createQna(Qna qna) {
		qnaRepository.insertQna(qna);
		return qnaRepository.insertQna(qna);		
	}
	
	public int deleteQna(String qna) {
		return qnaRepository.deleteQna(qna);
	}
	public int updateQna(Qna qna) {
		qnaRepository.updateQna(qna);
		return qnaRepository.updateQna(qna);		
	}
	
	
	

}
