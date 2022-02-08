package com.shop.myapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PaymentController {

    @GetMapping
    public String sendView(){
        return "payment/paymentView";
    }

    @PostMapping("/getPay_sendRequest")
    @ResponseBody
    public ResponseEntity<Object> getPayRequest(@RequestParam Map<String, Object> map){
        // map에 저장된 결제 정보를 꺼내 db에 저장되어있는 주문(order)과 비교하여
        // 민약 값이 맞으면 status 200 으로 보내고 값이 틀리면 status 400으로 보냄.
        log.info("결제 정보");
        String imp_uid = (String) map.get("imp_uid");
        log.info("고유 ID : {}",imp_uid);
        String merchant_uid = (String) map.get("merchant_uid");
        log.info("상점 거래 ID : {}",merchant_uid);
        String paid_amount = (String) map.get("paid_amount");
        log.info("결제 금액 : {}",paid_amount);
        String apply_num = (String) map.get("apply_num");
        log.info("카드 승인 번호 : {}",apply_num);

        // -- 검증 --

        return ResponseEntity.ok(map);
    }
}
