package com.example.trainee_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Finder {

    public static final okhttp3.MediaType JSON = okhttp3.MediaType.parse("application/json; charset=utf-8");
    public static final ObjectMapper map = new ObjectMapper();

    public String getCountryFlagByName(String iso2) {
        try {

            // making a POST request to the API to get the flag url using okhttp3

            String url = ("https://countriesnow.space/api/v0.1/countries/flag/images");

            CountryFlagRequest countryFlagRequest = new CountryFlagRequest();
            countryFlagRequest.setIso2(iso2);

            OkHttpClient client = new OkHttpClient();
            String json = map.writeValueAsString(countryFlagRequest);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder().url(url).post(body).build();

            Response responseEntity = client.newCall(request).execute();

            CountryFlagResponse countryFlagResponse = map.readValue(responseEntity.body().string(),
                    CountryFlagResponse.class);

            CountryFlagDataResponse flag = countryFlagResponse.getData();
            return flag.getFlag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CountryDetailsResponse findCountryDetailsByName(String countryName) {
        try {

            // making a POST request to the API to get the capital using okhttp3
            String url = ("https://countriesnow.space/api/v0.1/countries/capital");

            CountryCapitalRequest countryCapitalRequest = new CountryCapitalRequest();
            countryCapitalRequest.setCountry(countryName);

            OkHttpClient client = new OkHttpClient();
            String json = map.writeValueAsString(countryCapitalRequest);
            // testing the json request
            System.out.println(json);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder().url(url).post(body).build();

            Response responseEntity = client.newCall(request).execute();

            CountryCapitalResponse countryCapitalResponse = map.readValue(responseEntity.body().string(),
                    CountryCapitalResponse.class);

            CountryCapitalDataResponse countryCapitalDataResponse = countryCapitalResponse.getData();
            // getting the other information from the API and passing it to the method below

            String name = countryCapitalDataResponse.getName();
            String capital = countryCapitalDataResponse.getCapital();
            String iso2 = countryCapitalDataResponse.getIso2();
            String flag = getCountryFlagByName(iso2);
            CountryDetailsResponse countryDetailsResponse = new CountryDetailsResponse();
            countryDetailsResponse.setCapital(capital);
            countryDetailsResponse.setCountryCode(iso2);
            countryDetailsResponse.setFlagFileUrl(flag);
            countryDetailsResponse.setName(name);

            return countryDetailsResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
