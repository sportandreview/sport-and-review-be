package it.sportandreview.sport_equipment;

import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "il brand è obbligatorio!")
    private Long brandId;
    @NotNull(message = "il tipo di attrezzatura è obbligatorio!")
    private Long sportEquipmentTypeId;
}
