package it.sportandreview.sport_equipment;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SportEquipmentDTO extends BaseDTO {

    private String description;
    private String dimension;
    private Long brandId;
    private Long sportEquipmentTypeId;
}
