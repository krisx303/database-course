package com.group.travels.api.travel;

import com.group.travels.domain.travel.Travel;

import java.time.LocalDateTime;

public record TravelResponse(Long id,
                             String travelName,
                             LocalDateTime travelStartDate,
                             LocalDateTime travelEndDate,
                             Integer maxNumberOfPlaces,
                             Integer numberOfFreePlaces,
                             Double price,
                             Long countryID) {

    public TravelResponse(Travel travel){
        this(travel.getId(),
                travel.getTravelName(),
                travel.getTravelStartDate(),
                travel.getTravelEndDate(),
                travel.getMaxNumberOfPlaces(),
                travel.getNumberOfFreePlaces(),
                travel.getPrice(),
                travel.getCountry().getId());
    }
}
