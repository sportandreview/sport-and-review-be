package it.sportandreview.field_size;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FieldSizeDTO extends BaseDTO {

    private String description;
    private String dimension;
    private Long sportId;
    private Long fieldId;
    private Long fieldSizeTypeId;
}
