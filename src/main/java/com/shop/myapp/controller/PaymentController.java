package com.shop.myapp.controller;

import com.shop.myapp.dto.IamPortAttributes;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

    @GetMapping
    public String sendView() {
        return "payment/paymentView";
    }

    // 서비스에 넘길 내용 정하기
    @PostMapping(value = "/getPay_sendRequest")
    @ResponseBody
    public ResponseEntity<Object> getPayRequest(@ModelAttribute IamPortAttributes iamPortAttributes) throws ParseException {
        // map에 저장된 결제 정보를 꺼내 db에 저장되어있는 주문(order)과 비교하여
        // 민약 값이 맞으면 status 200 으로 보내고 값이 틀리면 status 400으로 보냄.
        log.info("고유 ID : {}", iamPortAttributes.getImp_uid());
        // 외부 api에서 받은 json 형태의 String을 JSONObject로 파싱하기 위해 JSONParser 선언
        JSONParser parser = new JSONParser();
        // Spring에서 외부 api로 http 요청을 보내기 위해 RestTemplate 사용
        RestTemplate template = new RestTemplate();
        // 호출할 url
        String url = "https://api.iamport.kr/users/getToken";
        // http body 에 담을 값을 Map 에 저장
        // linked 를 사용하면 값들의 순서를 보장할 수 있음
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // import key
        map.add("imp_key", "키 번호");
        // import secret key
        map.add("imp_secret", "시크릿 번호");
        // RestTemplate 를 사용하여 url 에 request 요청 -> 이후 response 를 ResponseEntity 에 저장
        ResponseEntity<String> response = template.postForEntity(url, map, String.class);
        // ResponseEntity 에 저장되어 있는 body(json 형태로 된 String)을 JSONObject 로 파싱
        JSONObject body = (JSONObject) parser.parse(response.getBody());
        // body 안에 json 형태의 String 으로 저장되어 있는 response(실제 저장 값들)를 JSONObject 로 다시 파싱
        JSONObject responseAttributes = (JSONObject) body.get("response");
        // key 와 secret key 를 통해 발급받은 access token 을 꺼내 String 으로 저장
        // 이후, import 에 요청할떄, http header 에 삽입하여 같이 보냄
        // access token 이 있어야 import 에 값을 요청할 수 있으며
        // access token 의 유효 시간이 30분이기때문에 일단 결제 요청시마다 계속 받는 형태로 구현했다.
        String accessToken = (String) responseAttributes.get("access_token");
        log.info("access_token : {}", accessToken);

        // access token 을 전달할 httpHeaders 생성
        HttpHeaders headers = new HttpHeaders();
        // httpEntity 에 담아 header 전달
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        // HttpEntity 에 headers 를 삽입
        headers.add("Authorization", accessToken);
        // 구매자가 결제한 결제 번호의 정보를 요청 -> db에 저장된 결제 되어야하는 금액과 실제 결제된 금액을 대조
        url = "https://api.iamport.kr/payments/" + iamPortAttributes.getImp_uid();
        // exchange()를 사용하면 postForEntity()에 비해 더 많은 정보를 전달할 수 있음
        response = template.exchange(url, HttpMethod.GET, entity, String.class);
        // access token 을 받을때와 마찬가지로 json 파싱
        body = (JSONObject) parser.parse(response.getBody());
        log.info("body : {}",body);
        responseAttributes = (JSONObject) body.get("response");
        log.info("responseAttributes : {}",responseAttributes);
        // 해당 고유 번호로 결제된 금액 조회
        log.info("paid_amount : {}",iamPortAttributes.getPaid_amount());
        log.info("amount : {}", responseAttributes.get("amount"));
        String amount =""+ responseAttributes.get("amount");
        // db에 저장된 결제 금액과 비교
        if (amount.equals(iamPortAttributes.getPaid_amount())) {
            // true 일 경우, status 200으로 response
            log.info("paid done.");
            return ResponseEntity.ok().build();
        }
        // false 일 경우, status 400 으로 response
        log.info("paid fail");
        return ResponseEntity.status(400).build();
    }
}
