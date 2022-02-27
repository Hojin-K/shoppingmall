package com.shop.myapp.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.myapp.dto.MemberSession;
import com.shop.myapp.dto.OrderDetail;
import com.shop.myapp.service.OrderDetailService;

@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final HttpSession session;

    public OrderDetailController(OrderDetailService orderDetailService, HttpSession session) {
        this.orderDetailService = orderDetailService;
        this.session = session;
    }

    @GetMapping("/{orderDetailCode}/cancel")
    public String orderDetailCancelForm(@PathVariable String orderDetailCode, Model model) {
        OrderDetail orderDetail = orderDetailService.findByOrderDetailCode(orderDetailCode);
        System.out.println(orderDetail.getItemOption().getItem().getItemPrice());
        System.out.println(orderDetail.getAmount());
        model.addAttribute("orderDetail", orderDetail);
        return "/modal/orderDetailCancelModal";
    }

    @ResponseBody
    @PostMapping("/{orderDetailCode}/cancel")
    public ResponseEntity<Object> orderDetailCancel(@PathVariable String orderDetailCode){
        try {
        OrderDetail orderDetail = orderDetailService.findByOrderDetailCode(orderDetailCode);
        MemberSession member = (MemberSession) session.getAttribute("member");
        if (orderDetail.getOrder().getMemberId().equals(member.getMemberId())){
            if (orderDetailService.orderCancelService(orderDetail)){
            return ResponseEntity.ok().build();
            }else {
                throw  new IllegalStateException("환불 실패");
            }
        }
        throw  new IllegalStateException("권한 없음");
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(405).build();
    }

    @ResponseBody
    @PostMapping("/{orderDetailCode}/update")
    public ResponseEntity<Object> setOrderDetailPostedStatus(@PathVariable String orderDetailCode,String postedStatus){
        int result = orderDetailService.updatePostedStatusByOrderDetailCode(orderDetailCode, postedStatus);
        if (result == 0){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok().build();
    }
}
