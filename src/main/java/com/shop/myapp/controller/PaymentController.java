package com.shop.myapp.controller;

import com.shop.myapp.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public String sendView() {
        return "payment/paymentView";
    }

    // 서비스에 넘길 내용 정하기
    @PostMapping(value = "/getPay_sendRequest")
    @ResponseBody
    public ResponseEntity<Object> getPayRequest(@RequestParam("imp_uid") String impUid) throws ParseException {

        String accessToken = paymentService.getAccessToken(impUid);

        String amount = paymentService.getImpAttributes(impUid, accessToken);
        // db에 저장된 결제 금액과 비교
        if (amount.equals("{db에 저장된 결제 금액}")) {
            // true 일 경우, status 200으로 response
            log.info("paid done.");
            return ResponseEntity.ok().build();
        }
        // false 일 경우, status 400 으로 response
        log.info("paid fail");
        return ResponseEntity.status(400).build();
    }
}
