package com.shop.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.myapp.dto.MemberSession;
import com.shop.myapp.dto.QnaBoard;
import com.shop.myapp.service.QnaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/qna")
public class QnaController {

	private final QnaService qnaService;

	public QnaController(QnaService qnaService) {
		this.qnaService = qnaService;
	}
	
	@GetMapping("/{itemCode}/list")
	public ModelAndView getList(@PathVariable String itemCode){
		/*, @RequestParam(required = false, defaultValue = "1") int page*/
		List<QnaBoard> qnaList = qnaService.getList(itemCode);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("modal/qnaView");
		mv.addObject("qnaList", qnaList);
		
		return mv;
	}
	
	@PostMapping("/{itemCode}/qnaWrite")
	public String write(HttpServletRequest request) {
		MemberSession mSession = (MemberSession)request.getSession().getAttribute("member");
		
		return "redirect:/qna/"+"/list";
	}

}
