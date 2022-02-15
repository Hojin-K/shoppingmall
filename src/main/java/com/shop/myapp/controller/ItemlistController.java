package com.shop.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.myapp.dao.IDao;
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
			for(int i=0;i<itemname.length;i++){
			System.out.println("itemname : "+itemname[i]);
			}
		}
			
			String searchKeyword=request.getParameter("searchKeyword");
			if(searchKeyword==null) {
				searchKeyword="";
			}
			System.out.println("searchKeyword : "+searchKeyword);			
			
		//ch값 변수에 저장(아이템명만으로 검색하는 페이지)
		if(itemname!=null) {
			for (String val : itemname) {
				if(val.equals("itemname")) {
					model.addAttribute("itemname","true"); //검색유지를 위해 넣음
					item_name="itemname";
				}
			}
		}
				
		//paging
		String strPage=request.getParameter("page");
		System.out.println("strPage1 : "+strPage);
		if(strPage==null)
			strPage="1";
		System.out.println("strPage2 : "+strPage);
				
		//totalpage 계산
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		IDao dao=sqlSession.getMapper(IDao.class);
		
		int total=0;
		if (itemname.equals("itemname")) {
			total=dao.selectBoardTotCount1(searchKeyword);
		}else if(itemname.equals("")) {
			total=dao.selectBoardTotCount0(searchKeyword);
		}
		
		searchVO.pageCalculate(total);
		
		/*System.out.println("Totrow : "+total); // 글의 총 갯수
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());*/
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();

		if (itemname.equals("itemname")) {
			model.addAttribute("item_name",dao.itemList(rowStart,rowEnd,searchKeyword,"1"));
		} else if (itemname.equals("")) {
			model.addAttribute("item_name",dao.itemList(rowStart,rowEnd,searchKeyword,"0"));
		}
	
		model.addAttribute("totRowCnt",total); 
		model.addAttribute("searchVO",searchVO);
	
		
		
		return "itemlistView";
		
	}
	
	
	

}
