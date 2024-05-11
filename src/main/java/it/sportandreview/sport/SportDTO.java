package it.sportandreview.sport;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.sport_point.SportPointDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SportDTO extends BaseDTO {

    @ToString.Exclude
    private ServicesDTO service;

    private Integer maxPlayer;
    private String name;
    private Set<SportPointDTO> sportPoints = new HashSet<>();

}
