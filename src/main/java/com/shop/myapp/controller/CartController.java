package com.shop.myapp.controller;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.Member;
import com.shop.myapp.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final HttpSession session;

    public CartController(CartService cartService, HttpSession session) {
        this.cartService = cartService;
        this.session = session;
    }

    @PostMapping("/add")
    public String insertCart( @ModelAttribute Cart cartItem) {
        Member member = (Member) session.getAttribute("member");
        System.out.println(cartItem.getOptionCode());
        cartItem.setMemberId(member.getMemberId());
        cartItem.setAmount(1);
        int result = cartService.insertCart(cartItem);
        if (result == 0) {
            throw new IllegalStateException("카트 저장 실패");
        }

        return "redirect:/cart/myCart";
    }
    @GetMapping("/myCart")
    public String myCart(Model model){
        Member member = (Member) session.getAttribute("member");
        List<Cart> carts = cartService.findCartDetailByMemberId(member.getMemberId());
        model.addAttribute("carts",carts);
        return "/cart/myCart";
    }

    @GetMapping("/{cartCode}/delete")
    @ResponseBody
    public ResponseEntity<Object> deleteCart(@PathVariable String cartCode){
        Member member = (Member) session.getAttribute("member");
        Cart cart = cartService.findByCartId(cartCode);
        if (cart.getMemberId().equals(member.getMemberId())){
        int result = cartService.deleteByCartId(cartCode);
        if (result>0){
            ResponseEntity.ok().build();
        } else {
            ResponseEntity.status(402).build();
        }
        }
        throw new IllegalStateException("사용자 정보 불일치");
    }

}
