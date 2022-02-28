package com.shop.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		mv.addObject("itemCode", itemCode);
		
		return mv;
	}
	
	@PostMapping("/write")
	public String write(QnaBoard qna, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		log.info("qna write start");
		MemberSession mSession = (MemberSession)request.getSession().getAttribute("member");
		qna.setMemberId(mSession.getMemberId());
		int result = qnaService.insertWrite(qna);
		log.info("insert success : ("+result+")");
		redirectAttributes.addAttribute("itemCode", qna.getItemCode());
		return "redirect:/item/{itemCode}";
	}
	
	@PostMapping("/reply")
	public String reply(QnaBoard qna, RedirectAttributes redirectAttributes) {
		log.info("reply process start");
		System.out.println("보드아이디 체크 -->"+qna.getBoardId());
		System.out.println("리플 체크 -->"+qna.getBoardReply());
		System.out.println("아이템 체크 -->"+qna.getItemCode());
		int result = qnaService.reply(qna);
		log.info("insert success : ("+result+")");
		redirectAttributes.addAttribute("itemCode", qna.getItemCode());
		return "redirect:/item/{itemCode}";
	}

}
