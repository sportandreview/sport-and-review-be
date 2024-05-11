package it.sportandreview.sport_equipment_type;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SportEquipmentTypeDTO extends BaseDTO {

    private String description;
}
