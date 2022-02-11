package com.shop.myapp.controller;

import com.shop.myapp.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;

@Controller
@Slf4j
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping("/country")
    public String get() throws ParseException, URISyntaxException {
        int result = countryService.getCountryFromAPI();
        log.info("들어간 값(234) : {}",result);
        return "";
    }
}
