package com.group.travels.api.country;

import com.group.travels.domain.country.Country;

public record CountryResponse(Long id, String countryName){
    public CountryResponse(Country src) {
        this(src.getId(), src.getCountryName());
    }
}