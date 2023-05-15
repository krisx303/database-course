package com.group.travels.api.country;

import jakarta.validation.constraints.NotBlank;

public record CountryRequest(@NotBlank String countryName) {}