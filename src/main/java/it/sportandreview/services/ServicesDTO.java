package it.sportandreview.services;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.field_size.FieldSizeDTO;
import it.sportandreview.service_type.ServicesTypeDTO;
import it.sportandreview.sport_equipment.SportEquipmentDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ServicesDTO extends BaseDTO {

    private String name;
    private String icon;
    private Double price;

    @Builder.Default
    private Set<SportEquipmentDTO> sportEquipmentSet = new HashSet<>();
    @Builder.Default
    private Set<FieldSizeDTO> fieldSizeSet = new HashSet<>();
    private ServicesTypeDTO serviceType;

}
