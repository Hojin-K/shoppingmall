package com.shop.myapp.controller;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/add")
    public String insertCart(HttpServletRequest request, @ModelAttribute Cart cartItem){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        cartItem.setMemberId(member.getMemberId());


    }
}
