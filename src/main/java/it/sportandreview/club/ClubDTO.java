package it.sportandreview.club;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.field.FieldReducedDTO;
import it.sportandreview.opening_day.OpeningDayDTO;
import it.sportandreview.owner.OwnerDTO;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.sport.SportDTO;
import jakarta.validation.constraints.Email;
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
public class ClubDTO extends BaseDTO {
    @NotBlank(message = "il nome del club è obbligatorio")
    private String name;
    private String description;
    @NotBlank(message = "numero di telefono obbligatorio!")
    private String phone;
    @NotBlank(message = "la città è obbligatoria")
    private String city;
    @Email(message = "Invalid email format")
    @NotBlank(message = "l'email è obbligatoria")
    private String mail;
    @NotNull(message = "l'indirizzo è obbligatorio")
    private String address;
    private String clubLogo;
    private String bookingPolicy;
    private Double clubRating;
    private String location;
    private OwnerDTO owner;
    private Integer numberFields;
    private Double latitude;
    private Double longitude;
    private String stripeCode;

    private Set<FieldReducedDTO> fields = new HashSet<>();

    @Builder.Default
    private Set<ServicesDTO> services = new HashSet<>();
    @NotNull(message = "giorni di apertura obbligatori!")
    @Builder.Default
    private Set<OpeningDayDTO> openingDays = new HashSet<>();
    @Builder.Default
    private Set<SportDTO> sports = new HashSet<>();
}
