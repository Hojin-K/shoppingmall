package com.shop.myapp.controller;

import com.shop.myapp.dto.Country;
import com.shop.myapp.dto.Member;
import com.shop.myapp.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/country")
@Slf4j
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/getCountry")
    public String get() throws ParseException, URISyntaxException {
        int result = countryService.getCountryFromAPI();
        log.info("들어간 값(234) : {}",result);
        return "";
    }

    @ResponseBody
    @GetMapping("")
    public ResponseEntity<Object> getCountries(){
        List<Country> countries = countryService.getCountries();
        return ResponseEntity.ok(countries);
    }

}
