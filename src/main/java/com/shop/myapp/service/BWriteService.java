package com.shop.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.shop.myapp.dao.BoardDao;

public class BWriteService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BWriteService-------");
//		map으로 변환
		Map<String, Object> map=model.asMap();
//		map->request
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String BOARD_TITLE=request.getParameter("BOARD_TITLE");
		String BOARD_CONTENT=request.getParameter("BOARD_CONTENT");
		System.out.println("김형철"+BOARD_TITLE);
		BoardDao dao=new BoardDao();
		dao.write(BOARD_TITLE,BOARD_CONTENT);
	}
}
