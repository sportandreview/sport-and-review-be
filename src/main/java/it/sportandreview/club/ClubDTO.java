package it.sportandreview.club;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.field.FieldReducedDTO;
import it.sportandreview.opening_day.OpeningDayDTO;
import it.sportandreview.owner.OwnerDTO;
import it.sportandreview.services.ServicesDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ClubDTO extends BaseDTO {

    private String name;
    private String description;
    private String phone;
    private String city;
    private String mail;
    private String address;
    private String clubLogo;
    private String bookingPolicy;
    private Double clubRating;
    private String location;
    private OwnerDTO owner;

    private Set<FieldReducedDTO> fields = new HashSet<>();

    @Builder.Default
    private Set<ServicesDTO> services = new HashSet<>();
    @Builder.Default
    private Set<OpeningDayDTO> openingDays = new HashSet<>();
}
