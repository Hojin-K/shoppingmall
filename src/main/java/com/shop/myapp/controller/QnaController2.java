package com.shop.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*@RestController
@RequestMapping("/qna")
//이게 더 낫다!
public class QnaController2 {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	//게시글 목록
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() throws Exception{
		
		List<QnaVO> list = qnaMapper.qnaList();
		sevice.vo
		
		return new ModelAndView("qnaList","list",list);
	}
	
	//게시글 작성 페이지(GET)
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView writeForm() throws Exception{
		
		return new ModelAndView("qnaWrite");
	}
	
	//게시글 작성(POST)
	 @RequestMapping(value="/add",method=RequestMethod.POST)
	    public String write(@ModelAttribute("QnaVo") QnaVo qna) throws Exception{
	 
	        qnaMapper.boardInsert(qna);
	        
	        return "redirect:/qna";
	    }
	 
	//게시글 상세
	    @RequestMapping(value="/{board_id}",method=RequestMethod.GET)
	    public ModelAndView view(@PathVariable("board_id") int board_id) throws Exception{
	        
	    	QnaVo board = qnaMapper.qnaView(board_id);
	        
	        return new ModelAndView("qnaView","board",board);
	    }
	    
	  //게시글 수정 페이지(GET)
	    @RequestMapping(value="/post/{board_id}", method=RequestMethod.GET)
	    public String updateForm(@PathVariable("board_id") int board_id,Model model) throws Exception{
	        
	    	QnaVo board = qnaMapper.qnaView(board_id);
	        model.addAttributes("board",board);
	        return "qndUpdate";
	    }

	    //게시글 수정(PATCH)
	    @RequestMapping(value="/post/{board_id}", method=RequestMethod.PATCH)
	    public String update(@ModelAttribute("BoardVO")BoardVO board,@PathVariable("board_id") int board_id) throws Exception{
	        
	    	qnaMapper.qnaUpdate(board);
	        
	        return "redirect:/qna"+board_id;
	    }
	    
	    //게시글 삭제(DELETE)
	    @RequestMapping(value="/post/{board_id}", method=RequestMethod.DELETE)
	    public String delete(@PathVariable("board_id") int board_id) throws Exception{
	        
	    	qnaMapper.boardDelete(board_id);
	        
	        return "redirect:/qna";
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
		
		System.out.println("Totrow : "+total); // 글의 총 갯수
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();

		if (itemname.equals("itemname")) {
			model.addAttribute("item_name",dao.itemList(rowStart,rowEnd,searchKeyword,"1"));
		} else if (itemname.equals("")) {
			model.addAttribute("item_name",dao.itemList(rowStart,rowEnd,searchKeyword,"0"));
		}
	
		model.addAttribute("totRowCnt",total); 
		model.addAttribute("searchVO",searchVO);
	
		
		
		return "qnalistView";
		



	

}
*/