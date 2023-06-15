package com.group.travels.domain.travel;

import com.group.travels.api.IllegalOperationException;
import com.group.travels.api.travel.TravelRequest;
import com.group.travels.domain.country.Country;
import org.hibernate.query.Query;

import java.util.List;

public class TravelStorage {

    private final TravelRepository travelRepository;

    public TravelStorage(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public List<Travel> findAll() {
        return travelRepository.findAll();
    }

    public Travel findByID(Long id) {
        return travelRepository.findById(id)
                .orElseThrow(() -> new TravelNotFoundException(id));
    }

    public Travel create(TravelRequest details, Country country) {
        if(details.hasInvalidDates())
            throw new IllegalOperationException("Travel end date is before start date!!");

        Travel toSave = Travel.builder()
                .travelName(details.travelName())
                .travelStartDate(details.travelStartDate())
                .travelEndDate(details.travelEndDate())
                .price(details.price())
                .country(country)
                .maxNumberOfPlaces(details.maxNumberOfPlaces())
                .numberOfFreePlaces(details.maxNumberOfPlaces()).build();

        return travelRepository.save(toSave);
    }

    public Travel update(Long id, TravelRequest details, Country country) {
        Travel toUpdate = findByID(id);

        if(!toUpdate.canUpdateNumberOfMaxPlaces(details.maxNumberOfPlaces()))
            throw new IllegalOperationException("Cannot set number of max places to given value because of not enough free places");

        if(details.hasInvalidDates())
            throw new IllegalOperationException("Travel end date is before start date!!");

        toUpdate.setTravelName(details.travelName());
        toUpdate.setTravelStartDate(details.travelStartDate());
        toUpdate.setTravelEndDate(details.travelEndDate());
        toUpdate.setCountry(country);
        toUpdate.setPrice(details.price());
        toUpdate.setMaxNumberOfPlaces(details.maxNumberOfPlaces());

        int newNumberOfFreePlaces = details.maxNumberOfPlaces() - toUpdate.getMaxNumberOfPlaces() + toUpdate.getNumberOfFreePlaces();

        toUpdate.setNumberOfFreePlaces(newNumberOfFreePlaces);

        return travelRepository.save(toUpdate);
    }

    public void delete(Long id) {
        Travel travel = findByID(id);
        travelRepository.delete(travel);
    }

    public void reservePlaceOnTrip(Travel travel) {
        travel.setNumberOfFreePlaces(travel.getNumberOfFreePlaces() - 1);
        travelRepository.save(travel);
    }

    public void undoReservationOnTrip(Travel travel) {
        travel.setNumberOfFreePlaces(travel.getNumberOfFreePlaces() + 1);
        travelRepository.save(travel);
    }

    public List<Travel> searchByName(String query) {
        return travelRepository.findByTravelNameContainingIgnoreCase(query);
    }

    public List<Travel> filterTravels(List<Long> countries, String travelName, int minFreePlaces) {
        return travelRepository.filterTravels(countries, travelName, minFreePlaces);
    }

    public List<Travel> filterTravelsAllCountries(String travelName, int minFreePlaces) {
        return travelRepository.filterTravelsAllCountries(travelName, minFreePlaces);
    }
}
