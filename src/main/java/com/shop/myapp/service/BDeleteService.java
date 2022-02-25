package com.shop.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.shop.myapp.dao.BoardDao;

public class BDeleteService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BDeleteService-------");
//		map으로 변환
		Map<String, Object> map=model.asMap();
//		map->request
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String BOARD_ID=request.getParameter("board_id");
		
		
		//System.out.println("bName   :  "+bName);
		
		BoardDao dao=new BoardDao();
		dao.delete(BOARD_ID);
	}
}
