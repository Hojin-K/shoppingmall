package com.shop.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.myapp.dto.QnaBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QnaService {
	
	public List<QnaBoard> getList(String itemCode) {
		log.info("QNA List~");
		List<QnaBoard> qnaList = new ArrayList<QnaBoard>();
		
		return qnaList;
	}

}
