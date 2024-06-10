package it.sportandreview.field_review;

import it.sportandreview.base.BaseDTO;
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
public class FieldReviewDTO extends BaseDTO {

    @NotNull(message = "la partita è obbligatoria!")
    private Long gameMatchId;
    @NotNull(message = "il campo è obbligatorio!")
    private Long fieldId;
    @NotNull(message = "il player è obbligatorio!")
    private Long playerUserId;
    @NotNull(message = "il valore è obbligatorio!")
    private Double value;
    private String note;
}
