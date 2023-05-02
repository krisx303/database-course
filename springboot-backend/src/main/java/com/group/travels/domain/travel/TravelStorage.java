package com.group.travels.domain.travel;

import com.group.travels.api.travel.TravelRequest;

import java.util.List;

public class TravelStorage {

    private final TravelRepository travelRepository;

    public TravelStorage(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public List<Travel> findAll(){
        return travelRepository.findAll();
    }

    public Travel findByID(Long id){
        return travelRepository.findById(id)
                .orElseThrow(() -> new TravelNotFoundException(id));
    }

    public Travel create(TravelRequest details) {
        Travel toSave = Travel.builder()
                .travelName(details.travelName())
                .travelDate(details.travelDate())
                .price(details.price())
                .maxNumberOfPlaces(details.maxNumberOfPlaces())
                .numberOfFreePlaces(details.maxNumberOfPlaces()).build();
        return travelRepository.save(toSave);
    }

    public Travel update(Long id, TravelRequest details) {
        Travel toUpdate = findByID(id);
        toUpdate.setTravelName(details.travelName());
        toUpdate.setTravelDate(details.travelDate());
        toUpdate.setPrice(details.price());
        //TODO handle case when nubmer of max places will be negative
        toUpdate.setMaxNumberOfPlaces(details.maxNumberOfPlaces());
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

    public void undoReservationOnTrip(Travel travel){
        travel.setNumberOfFreePlaces(travel.getNumberOfFreePlaces() + 1);
        travelRepository.save(travel);
    }
}
