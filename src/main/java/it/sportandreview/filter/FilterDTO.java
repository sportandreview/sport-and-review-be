package it.sportandreview.filter;

import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "il nome del filtro è obbligatorio!")
    private String filterName;
    @NotNull(message = "il club è obbligatorio!")
    private Long clubId;
    @NotNull(message = "il campo è obbligatorio!")
    private Long fieldId;
}
