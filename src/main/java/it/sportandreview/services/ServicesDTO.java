package it.sportandreview.services;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.field_size.FieldSizeDTO;
import it.sportandreview.service_type.ServicesTypeDTO;
import it.sportandreview.sport_equipment.SportEquipmentDTO;
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
public class ServicesDTO extends BaseDTO {
    @NotBlank(message = "il nome del servizio è obbligatorio!")
    private String name;
    private String icon;
    @NotNull(message = "il prezzo è obbligatorio!")
    private Double price;

    @Builder.Default
    private Set<SportEquipmentDTO> sportEquipmentSet = new HashSet<>();
    @Builder.Default
    private Set<FieldSizeDTO> fieldSizeSet = new HashSet<>();
    @NotNull(message = "il tipo di servizio è obbligatorio!")
    private ServicesTypeDTO serviceType;

}
