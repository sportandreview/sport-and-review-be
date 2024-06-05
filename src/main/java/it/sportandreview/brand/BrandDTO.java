package it.sportandreview.brand;

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
public class BrandDTO extends BaseDTO {
    @NotBlank(message = "la descrizione è obbligatoria")
    private String description;
    @NotNull(message = "l'id dello sport è obbligatorio")
    private Long sportId;
}
