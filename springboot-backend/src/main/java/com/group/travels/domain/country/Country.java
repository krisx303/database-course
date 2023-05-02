package com.group.travels.domain.country;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.travels.domain.travel.Travel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Builder
@Table(name = "COUNTRIES")
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long country_id;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @OneToMany(mappedBy = "country")
    @JsonManagedReference
    @Builder.Default
    private List<Travel> travels = new ArrayList<>();

}