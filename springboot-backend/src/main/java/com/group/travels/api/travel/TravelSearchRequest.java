package com.group.travels.api.travel;

import java.util.List;

public record TravelSearchRequest(String travelName, List<Long> countryIDs, int minFreePlaces) {}
