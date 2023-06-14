package com.group.travels.domain.travel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Long> {

    List<Travel> findByTravelNameContainingIgnoreCase(String query);

    @Query(value = "SELECT t.* FROM travels t " +
            "JOIN countries c ON t.country = c.id " +
            "WHERE c.id IN :countryNames " +
            "AND t.travel_name LIKE %:travelName% " +
            "AND t.number_of_free_places >= :minFreePlaces",
            nativeQuery = true)
    List<Travel> filterTravels(
            @Param("countryNames") List<Long> countryNames,
            @Param("travelName") String travelName,
            @Param("minFreePlaces") int minFreePlaces
    );

}
