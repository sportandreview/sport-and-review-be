package it.sportandreview.field;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.services.ServicesDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FieldDTO extends BaseDTO {

    @NotBlank(message = "nome campo è obbligatorio")
    private String name;
    private String fieldImage;

    @NotNull(message = "orario di apertura obbligatorio!")
    private ZonedDateTime hourRangeStart;

    @NotNull(message = "orario di chiusura obbligatorio!")
    private ZonedDateTime hourRangeEnd;

    private Double price;
    private Boolean covered;
    private Double size;
    private Double rating;
    private String description;

    @NotNull(message = "il tipo di terreno è obbligatorio")
    private Long groundTypeId;

    @NotNull(message = "il club è obbligatorio")
    private Long clubId;

    @NotNull(message = "lo sport è obbligatorio")
    private Long sportId;

    private Boolean highlights;
    private Boolean markerPoint;

    @Builder.Default
    private Set<ServicesDTO> services = new HashSet<>();
}
