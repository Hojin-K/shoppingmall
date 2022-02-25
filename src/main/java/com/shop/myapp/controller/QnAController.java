package com.shop.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.myapp.service.BContentViewService;
import com.shop.myapp.service.BDeleteService;
import com.shop.myapp.service.BListService;
import com.shop.myapp.service.BModifyService;
import com.shop.myapp.service.BReplyService;
import com.shop.myapp.service.BReplyViewService;
import com.shop.myapp.service.BServiceInf;
import com.shop.myapp.service.BWriteService;

@Controller
public class QnAController {
	BServiceInf commandInf;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("=========pass by list()=============");
//		db접속 데이터 가져오기
		commandInf=new BListService();
		commandInf.execute(model);
		return "list";
	}

	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("=========pass by write_view()=============");
		
		return "write_view";
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("=========pass by write()=============");
		//db에 글쓰기진행
		model.addAttribute("request",request);
		commandInf=new BWriteService();
		commandInf.execute(model);
		
		return "redirect:list";
	}
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("=========pass by content_view()=============");
		
		model.addAttribute("request",request);
		commandInf=new BContentViewService();
		commandInf.execute(model);
		
		return "content_view";
	}
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request, Model model) {
		System.out.println("=========pass by content_updateform()=============");
		model.addAttribute("request",request);
		commandInf=new BContentViewService();
		commandInf.execute(model);
		
		return "content_update";
	}
//	@RequestMapping(method=RequestMethod.POST,value="/modify")
//	public String modify(HttpServletRequest request, Model model) {
//		System.out.println("=========pass by modify()=============");
//		model.addAttribute("request",request);
//		commandInf=new BModifyService();
//		commandInf.execute(model);
//		
//		return "redirect:list";
//	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("=========pass by delete()=============");
		model.addAttribute("request",request);
		commandInf=new BDeleteService();
		commandInf.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("=========pass by reply_view()=============");
		model.addAttribute("request",request);
		commandInf=new BReplyViewService();
		commandInf.execute(model);
		
		return "reply_view";
	}
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("=========pass by reply()=============");
		model.addAttribute("request",request);
		commandInf=new BReplyService();
		commandInf.execute(model);
		
		return "redirect:list";
	}
}
