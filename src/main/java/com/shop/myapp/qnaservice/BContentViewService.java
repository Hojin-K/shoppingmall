package com.shop.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.shop.myapp.dao.BoardDao;
import com.shop.myapp.dto.BoardDto;

public class BContentViewService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BContentViewService-------");
//		map으로 변환
		Map<String, Object> map=model.asMap();
//		map->request
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String BOARD_ID=request.getParameter("board_id");
		System.out.println(BOARD_ID);
		
//		System.out.println("김형철   :  "+BOARD_ID);
		
		BoardDao dao=new BoardDao();
		BoardDto dto=dao.contentView(BOARD_ID);
		
		model.addAttribute("content_view",dto);
	}
}
