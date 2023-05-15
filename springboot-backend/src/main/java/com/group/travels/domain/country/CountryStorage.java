package com.group.travels.domain.country;

import com.group.travels.api.country.CountryRequest;

import java.util.List;

public class CountryStorage {

    private final CountryRepository countryRepository;

    public CountryStorage(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    public Country findByID(Long id){
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    public Country create(CountryRequest details) {
        Country toSave = Country.builder()
                .countryName(details.countryName()).build();
        return countryRepository.save(toSave);
    }

    public Country update(Long id, CountryRequest details) {
        Country toUpdate = findByID(id);
        toUpdate.setCountryName(details.countryName());
        return countryRepository.save(toUpdate);
    }

    public void delete(Long id) {
        Country country = findByID(id);
        countryRepository.delete(country);
    }

    public List<Country> searchByName(String query) {
        return countryRepository.findByCountryNameContainingIgnoreCase(query);
    }
}