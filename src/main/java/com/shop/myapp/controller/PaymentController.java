package com.shop.myapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.myapp.dto.ImPortAttributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PaymentController {

    @GetMapping
    public String sendView(){
        return "payment/paymentView";
    }

    @PostMapping(value = "/getPay_sendRequest")
    @ResponseBody
    public ResponseEntity<Object> getPayRequest(@ModelAttribute ImPortAttributes imPortAttributes) throws JsonProcessingException {
        // map에 저장된 결제 정보를 꺼내 db에 저장되어있는 주문(order)과 비교하여
        // 민약 값이 맞으면 status 200 으로 보내고 값이 틀리면 status 400으로 보냄.
        RestTemplate template = new RestTemplate();
        String url = "https://api.iamport.kr/users/getToken";
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("imp_key","8245163167318128");
        map.add("imp_secret","41123179b382fed758afa59a01b11043b4f72a3d6ce176d91234dd508082e34968a885c53cb1ef89");
                ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> response = template.postForEntity(url,map,String.class);
        Map<String,String> responseAttributes = mapper.readValue(response.getBody(),Map.class);
        String access_token = responseAttributes.get("access_token");
        log.info("access_token : {}",access_token);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        headers.add("Authorization", "bearer " + access_token);
        url = "https://api.iamport.kr/payments/"+imPortAttributes.getImp_uid();
        response = template.exchange(url, HttpMethod.GET,entity,String.class);
        responseAttributes = mapper.readValue(response.getBody(),Map.class);
        String imp_uid = (String) responseAttributes.get("imp_uid");
        log.info("imp_uid : {}",imp_uid);
        if (imp_uid.equals(imPortAttributes.getImp_uid())){
            return ResponseEntity.ok().build();
        }

        log.info(response.getBody());
        log.info("결제 정보");
        log.info("고유 ID : {}",imPortAttributes.getImp_uid());
        log.info("상점 거래 ID : {}",imPortAttributes.getMerchant_uid());
        log.info("결제 금액 : {}",imPortAttributes.getPaid_amount());
        log.info("카드 승인 번호 : {}",imPortAttributes.getApply_num());

        // -- 검증 --

        return ResponseEntity.status(400).build();
    }

    @GetMapping("/token")
    @ResponseBody
    public String getToken() throws JsonProcessingException {
        // String으로 받은 json 데이터를 변환하는 과정 필요
        // 결제는 문제 없이 진행 됌.
        RestTemplate template = new RestTemplate();
        String url = "https://api.iamport.kr/users/getToken";
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("imp_key","8245163167318128");
        map.add("imp_secret","41123179b382fed758afa59a01b11043b4f72a3d6ce176d91234dd508082e34968a885c53cb1ef89");
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> response = template.postForEntity(url,map,String.class);
        log.info(response.getBody());
        Map<String, String> readValue = mapper.readValue(response.getBody(), Map.class);
        log.info("readValue : {}",readValue.get("response"));
        Map<String, String> responseAttributes = mapper.readValue(readValue.get("response"),Map.class);
        String access_token = responseAttributes.get("access_token");
        log.info("access_token : {}",access_token);
            return "";

    }
}
