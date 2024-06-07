package it.sportandreview.field_size;

import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FieldSizeDTO extends BaseDTO {

    private String description;
    @NotBlank(message = "la dimensione è obbligatoria!")
    private String dimension;
    @NotNull(message = "lo sport è obbligatorio!")
    private Long sportId;
    @NotNull(message = "il campo è obbligatorio!")
    private Long fieldId;
    @NotNull(message = "il tipo di dimensione del campo è obbligatorio!")
    private Long fieldSizeTypeId;
}
