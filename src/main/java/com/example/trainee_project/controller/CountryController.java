package com.example.trainee_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @GetMapping
    public List<CountryDetailResponse> getAllCountries() {

        String url = ("https://countriesnow.space/api/v0.1/countries");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CountryResponse> responseEntity = null;

        // responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
        // CountryResponse.class);
        responseEntity = restTemplate.getForEntity(url, CountryResponse.class);

        CountryResponse countryResponse = responseEntity.getBody();

        List<CountryDataResponse> countries = countryResponse.getData();

        List<CountryDetailResponse> countryDetailResponses = new ArrayList<>();

        if (countries != null && !countries.isEmpty()) {

            for (CountryDataResponse countryDetailResponse : countries) {

                CountryDetailResponse detailResponse = new CountryDetailResponse();
                detailResponse.setCountryCode(countryDetailResponse.getIso2());
                detailResponse.setName(countryDetailResponse.getCountry());

                countryDetailResponses.add(detailResponse);
            }
        }

        return countryDetailResponses;

    }

}
