package it.sportandreview.filter;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.club.Club;
import it.sportandreview.field.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FilterDTO extends BaseDTO {

    private String filterName;
    private Club club;
    private Field field;
}
