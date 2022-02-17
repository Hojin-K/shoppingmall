package com.shop.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.shop.myapp.dao.BoardDao;

public class BModifyService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BModifyService-------");
//		map으로 변환
		Map<String, Object> map=model.asMap();
//		map->request
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String bid=request.getParameter("bid");
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("bTitle");
		String bContent=request.getParameter("bContent");
		
		
		BoardDao dao=new BoardDao();
		dao.modify(bid,bName,bTitle,bContent);
	}
}
