package it.sportandreview.opening_day;

import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class OpeningDayDTO extends BaseDTO {

    private String description;
    @NotNull(message = "la data e l'orario di apertura sono obbligatoria")
    private ZonedDateTime openingTime;
    @NotNull(message = "la data e l'orario di chiusura è obbligatoria")
    private ZonedDateTime closingTime;


}
