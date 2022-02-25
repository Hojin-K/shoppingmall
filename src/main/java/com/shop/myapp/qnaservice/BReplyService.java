package com.shop.myapp.qnaservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.shop.myapp.dao.BoardDao;

public class BReplyService implements BServiceInf{

	@Override
	public void execute(Model model) {
		System.out.println("BReplyService-------");
//		map으로 변환
		Map<String, Object> map=model.asMap();
//		map->request
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String BOARD_ID=request.getParameter("BOARD_ID");
		String ITEM_CODE=request.getParameter("ITEM_CODE");
		String MEMBER_ID=request.getParameter("MEMBER_ID");
		String BOARD_TITLE=request.getParameter("BOARD_TITLE");
		String BOARD_CONTENT=request.getParameter("BOARD_CONTENT");
		String RECOMMEND_ID=request.getParameter("RECOMMEND_ID");
		
		BoardDao dao=new BoardDao();
		dao.reply(BOARD_ID,ITEM_CODE,MEMBER_ID,
				BOARD_TITLE,BOARD_CONTENT,RECOMMEND_ID);
		
	}
}
