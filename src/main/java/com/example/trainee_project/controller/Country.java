package com.example.trainee_project.controller;

import java.util.List;

public class Country {

    private List<CountryDetailResponse> countries;

    public List<CountryDetailResponse> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDetailResponse> countries) {
        this.countries = countries;
    }
}
