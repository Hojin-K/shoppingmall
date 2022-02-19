package com.shop.myapp.controller;

import com.shop.myapp.dto.Cart;
import com.shop.myapp.dto.FormList;
import com.shop.myapp.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final HttpSession session;
    public OrderController(OrderService orderService, HttpSession session) {
        this.orderService = orderService;
        this.session = session;
    }

    @PostMapping("/addForm")
    public String insertOrderForm(@ModelAttribute FormList formList, Model model){
        List<String> cartCodes = formList.getCartCodes();
        for (String cart : cartCodes){
            System.out.println(cart);
        }
        List<Cart> carts = orderService.getSelectCartByCartIds(formList.getCartCodes());
        model.addAttribute("carts",carts);
        return "order/orderForm";
    }
}
