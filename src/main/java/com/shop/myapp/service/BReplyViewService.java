package com.shop.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.shop.myapp.dao.BoardDao;
import com.shop.myapp.dto.BoardDto;

public class BReplyViewService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BReplyViewService-------");
//		map으로 변환
		Map<String, Object> map=model.asMap();
//		map->request
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String bid=request.getParameter("bid");
		
		
		
		BoardDao dao=new BoardDao();
		BoardDto dto=dao.replyView(bid);
		
		
		model.addAttribute("reply_view",dto);
	}
}
