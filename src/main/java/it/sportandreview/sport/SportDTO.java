package it.sportandreview.sport;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.sport_point.SportPointDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "il servizio è obbligatorio!")
    @ToString.Exclude
    private ServicesDTO service;
    @NotNull(message = "numero massimo di giocatori è obbligatorio!")
    private Integer maxPlayer;
    @NotBlank(message = "il nome è obbligatorio!")
    private String name;
    private Set<SportPointDTO> sportPoints = new HashSet<>();

}
