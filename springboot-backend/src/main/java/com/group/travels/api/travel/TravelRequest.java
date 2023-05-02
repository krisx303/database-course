package com.group.travels.api.travel;

import java.time.LocalDateTime;

public record TravelRequest(String travelName,
                            LocalDateTime travelDate,
                            Integer maxNumberOfPlaces,
                            Integer price) {
}
