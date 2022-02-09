package com.shop.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.myapp.vopage.SearchVO;

/*private String item_image;
private String item_name;
private String item_cost;
private int item_code;*/

@Controller
public class ItemlistController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/itemlistView")
	public String itemlist(HttpServletRequest request,SearchVO searchVO, Model model) {
		System.out.println("=========pass by itemlist");
		
		//search기능
		String item_name="";
		String[] itemname=request.getParameterValues("searchType");
		if(itemname!=null) {
			for(int i=0;i<itemname.length;i++);
		}
		//ch값 변수에 저장(아이템명만으로 검색하는 페이지)
		if(itemname!=null) {
			for (String val : itemname) {
				if(val.equals("item_name")) {
					model.addAttribute("item_name","true"); //검색유지를 위해 넣음
					item_name="item_name";
				}
			}
		}
		
		String searchKeyword=request.getParameter("sk");
		if(searchKeyword==null) 
			searchKeyword="";
		System.out.println("searchKeyword : "+searchKeyword);
		
		//paging
		String strPage=request.getParameter("page");
		System.out.println("strPage1 : "+strPage);
		if(strPage==null)
			strPage="1";
		System.out.println("strPage2 : "+strPage);
				
		//totalpage 계산
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		return "itemlistView";
		
	}
	

}
