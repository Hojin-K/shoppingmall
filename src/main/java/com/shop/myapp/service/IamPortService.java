package com.shop.myapp.service;

import com.shop.myapp.dto.Payment;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class IamPortService {

    public JSONObject parsingRestAttribute(ResponseEntity<String> restResponse) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject responseBody = (JSONObject) parser.parse(restResponse.getBody());
        // ResponseEntity 에 저장되어 있는 body(json 형태로 된 String)을 JSONObject 로 파싱
        // body 안에 json 형태의 String 으로 저장되어 있는 response(실제 저장 값들)를 JSONObject 로 다시 파싱
        return (JSONObject) responseBody.get("response");
    }

    public String getAccessToken(String impUid) throws ParseException {

        // map에 저장된 결제 정보를 꺼내 db에 저장되어있는 주문(order)과 비교하여
        // 민약 값이 맞으면 status 200 으로 보내고 값이 틀리면 status 400으로 보냄.
        log.info("고유 ID : {}", impUid);

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
        JSONObject responseAttributes = parsingRestAttribute(response);

        // key 와 secret key 를 통해 발급받은 access token 을 꺼내 String 으로 저장
        // 이후, import 에 요청할떄, http header 에 삽입하여 같이 보냄
        // access token 이 있어야 import 에 값을 요청할 수 있으며
        // access token 의 유효 시간이 30분이기때문에 일단 결제 요청시마다 계속 받는 형태로 구현했다.
        String accessToken = (String) responseAttributes.get("access_token");
        log.info("access_token : {}", accessToken);
        return accessToken;
    }

    public Payment getImpAttributes(String impUid, String accessToken) throws ParseException {
        // access token 을 전달할 httpHeaders 생성
        HttpHeaders headers = new HttpHeaders();
        // httpEntity 에 담아 header 전달
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        // HttpEntity 에 headers 를 삽입
        headers.add("Authorization", accessToken);
        // 구매자가 결제한 결제 번호의 정보를 요청 -> db에 저장된 결제 되어야하는 금액과 실제 결제된 금액을 대조
        String url = "https://api.iamport.kr/payments/" + impUid;
        // exchange()를 사용하면 postForEntity()에 비해 더 많은 정보를 전달할 수 있음
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        JSONObject responseAttributes = parsingRestAttribute(response);
        // access token 을 받을때와 마찬가지로 json 파싱
        // 해당 고유 번호로 결제된 금액 조회
        Payment payment = Payment.builder()
                .impUid((String) responseAttributes.get("imp_Uid"))
                .amount((Long) responseAttributes.get("amount"))
                .buyerName((String) responseAttributes.get("buyer_name"))
                .buyerTel((String) responseAttributes.get("buyer_tel"))
                .buyerPostCode((String) responseAttributes.get("buyer_postcode"))
                .buyerAddr((String) responseAttributes.get("buyer_addr"))
                .buyerEmail((String) responseAttributes.get("buyer_email"))
                .name((String) responseAttributes.get("name"))
                .status((String) responseAttributes.get("status"))
                .build();
        return payment;
    }
}
