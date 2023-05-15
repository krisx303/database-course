package com.group.travels.domain.travel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Long> {

    List<Travel> findByTravelNameContainingIgnoreCase(String query);

}
