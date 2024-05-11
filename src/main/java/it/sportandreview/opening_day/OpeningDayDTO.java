package it.sportandreview.opening_day;

import it.sportandreview.base.BaseDTO;
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
    private ZonedDateTime openingTime;
    private ZonedDateTime closingTime;


}
