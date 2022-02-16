package com.shop.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.myapp.repository.QnaRepository;


@Service
@Transactional
public class QnaService {

	@Autowired
	private QnaRepository qnaRepository;
	
	
	

}
