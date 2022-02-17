package com.shop.myapp.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.shop.myapp.dao.BoardDao;
import com.shop.myapp.dto.BoardDto;

public class BListService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BListService-------");
		BoardDao dao=new BoardDao();//훳호출  BoardDao()의 생성자호출
		ArrayList<BoardDto> dtos=dao.list();
		
		//리턴받은 내용을 모델에 담기
		model.addAttribute("list",dtos);
		
	}
}
