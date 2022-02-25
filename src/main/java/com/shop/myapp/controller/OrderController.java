package com.shop.myapp.controller;

import com.shop.myapp.dto.*;
import com.shop.myapp.service.OrderService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String insertOrderForm(@ModelAttribute FormList formList, Model model) {
        List<Cart> carts = orderService.getSelectCartByCartIds(formList.getCartCodes());
        model.addAttribute("carts", carts);
        return "order/orderForm";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Object> addOrder(@ModelAttribute FormList formList, @ModelAttribute Order order) {
        Member member = (Member) session.getAttribute("member");
        order.setMemberId(member.getMemberId());
        try {
        Order responseOrder = orderService.insertOrder(order, formList.getCartCodes());

        System.out.println(order.getOrderCode());
        for (OrderDetail orderDetail : responseOrder.getOrderDetails()) {
            orderDetail.setOrder(null);
        }
        System.out.println(order.getTotalPay());
        return ResponseEntity.ok(responseOrder);
        } catch (Exception e){
            e.printStackTrace(); return ResponseEntity.status(402).build();
        }
    }

    @PostMapping("/{orderCode}/validate")
    @ResponseBody
    public ResponseEntity<Object> validateTotalPay(@RequestParam("imp_uid") String impUid, @PathVariable String orderCode) throws ParseException {
        System.out.println("impUid = " + impUid);
        System.out.println("orderCode = " + orderCode);
        Payment payment = orderService.validateTotalPay(impUid, orderCode);
        if (payment == null) {
            return ResponseEntity.status(402).build();
        }
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/{orderCode}/cancel")
    @ResponseBody
    public ResponseEntity<Object> cancelOrder(@PathVariable String orderCode) {
        orderService.cancelOrder(orderCode); // 리턴 받을 필요 없어보임(?)
        return ResponseEntity.ok().build();
    }

    @GetMapping("/myOrder")
    public String myOrder(Model model) {
        Member member = (Member) session.getAttribute("member");
        String memberId = member.getMemberId();
        List<Order> orders = orderService.myOrder(memberId);
        model.addAttribute("orders", orders);
        return "/order/myOrder";
    }

    ;

    @GetMapping("/{orderCode}")
    public String findByOrderCode(@PathVariable String orderCode, Model model) {
        Member member = (Member) session.getAttribute("member");
        String memberId = member.getMemberId();
        Order order = orderService.findByOrderCode(orderCode);
        if (order.getMemberId().equals(memberId)) {
            model.addAttribute("order", order);
            return "/order/order";
        }
        throw new IllegalStateException("아이디 불일치(접근 권한 없음)");
    }


}
