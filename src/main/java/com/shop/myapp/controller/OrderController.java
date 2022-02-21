package com.shop.myapp.controller;

import com.shop.myapp.dto.*;
import com.shop.myapp.service.OrderService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Cart> carts = orderService.getSelectCartByCartIds(formList.getCartCodes());
        model.addAttribute("carts",carts);
        return "order/orderForm";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Object> addOrder(@ModelAttribute FormList formList, @ModelAttribute Order order){
        Member member = (Member) session.getAttribute("member");
        order.setMemberId(member.getMemberId());
        Order responseOrder = orderService.insertOrder(order, formList.getCartCodes());

        return ResponseEntity.ok(responseOrder);
    }
    @PostMapping("/{orderCode}/validate")
    @ResponseBody
    public ResponseEntity<Object> validateTotalPay(@RequestParam("imp_uid") String impUid, @PathVariable String orderCode) throws ParseException {
        System.out.println("impUid = " + impUid);
        System.out.println("orderCode = " + orderCode);
        Payment payment = orderService.validateTotalPay(impUid, orderCode);
        if (payment == null){
        return ResponseEntity.status(402).build();
        }
            return ResponseEntity.ok(payment);
    }

    @PostMapping("/{orderCode}/cancel")
    @ResponseBody
    public ResponseEntity<Object> cancelOrder(@PathVariable String orderCode){
        orderService.cancelOrder(orderCode); // 리턴 받을 필요 없어보임(?)
        return ResponseEntity.ok().build();
    }

    @GetMapping("/myOrder")
    public String myOrder(Model model){
        Member member = (Member) session.getAttribute("member");
        String memberId = member.getMemberId();
        List<Order> orders = orderService.myOrder(memberId);
        model.addAttribute("orders",orders);
        return "/order/myOrder";
    };

}