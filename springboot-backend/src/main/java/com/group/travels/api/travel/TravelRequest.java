package com.group.travels.api.travel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record TravelRequest(@NotBlank String travelName,
                            LocalDateTime travelStartDate,
                            LocalDateTime travelEndDate,
                            @Positive Integer maxNumberOfPlaces,
                            @PositiveOrZero Double price,
                            @Positive Long countryID) {

    public boolean hasInvalidDates() {
        return travelEndDate.isBefore(travelStartDate) || travelStartDate.isBefore(LocalDateTime.now());
    }
}
