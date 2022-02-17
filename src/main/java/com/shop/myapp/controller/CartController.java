package com.shop.myapp.controller;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.Member;
import com.shop.myapp.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public String insertCart(HttpServletRequest request, @ModelAttribute Cart cartItem) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        cartItem.setMemberId(member.getMemberId());
        int result = cartService.insertCart(cartItem);
        if (result == 0) {
            throw new IllegalStateException("카트 저장 실패");
        }
        return "Mycart";
    }

}
