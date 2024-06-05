package it.sportandreview.field_size_type;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FieldSizeTypeDTO extends BaseDTO {

    private String description;
}
