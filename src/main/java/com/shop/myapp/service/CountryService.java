package com.shop.myapp.service;

import com.shop.myapp.dto.Country;
import com.shop.myapp.repository.CountryRepository;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    private final SqlSession sqlSession;

    public CountryService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int getCountryFromAPI() throws ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser();
        String url = "https://api.odcloud.kr/api/15051105/v1/uddi:f6a08e41-a2fd-4acd-80e1-e6f14add3985?page=1&perPage=234&serviceKey=9TZRhsU%2BqHXm1ba1gLU92I1sO4BP0pHPNPz2tR4HOJvPzNdSaDlgIPjA%2FaExjI0ZFQ%2FhnZp9H%2F7OR20hJQiGGg%3D%3D";
        URI uri = new URI(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> countryAttributes = restTemplate.getForEntity(uri,String.class);
        JSONObject responseBody = (JSONObject) jsonParser.parse(countryAttributes.getBody());
        JSONArray datas = (JSONArray) responseBody.get("data");
        datas.remove(0);
            List<Country> countries = new ArrayList<>();
            for (Object data: datas) {
                String countryCode = (String) ((JSONObject) data).get("국가코드ISO_numeric");
                if (countryCode != null && !countryCode.equals("")){
                Country country = new Country();
                country.setCountryName((String) ((JSONObject) data).get("국가명"));
                    System.out.println(countryCode);
                country.setCountryCode(countryCode);
                countries.add(country);
                }
            }
        CountryRepository mapper = sqlSession.getMapper(CountryRepository.class);
        return mapper.insertCountries(countries);

    }

}
