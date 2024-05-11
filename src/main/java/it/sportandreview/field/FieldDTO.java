package it.sportandreview.field;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.club.ClubDTO;
import it.sportandreview.ground_type.GroundTypeDTO;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.sport.SportDTO;
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

    private String name;
    private String fieldImage;
    private ZonedDateTime hourRangeStart;
    private ZonedDateTime hourRangeEnd;
    private Double price;
    private Boolean covered;
    private Double size;
    private Double rating;
    private String description;
    private GroundTypeDTO groundType;
    private ClubDTO club;
    private SportDTO sport;
    private Boolean highlights;
    private Boolean markerPoint;

    @Builder.Default
    private Set<ServicesDTO> services = new HashSet<>();
}
