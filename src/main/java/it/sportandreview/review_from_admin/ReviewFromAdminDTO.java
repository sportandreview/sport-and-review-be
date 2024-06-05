package it.sportandreview.review_from_admin;

import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ReviewFromAdminDTO extends BaseDTO {

    @NotNull(message = "il player è obbligatorio!")
    private Long playerUserId;
    @NotNull(message = "il gestore è obbligatorio!")
    private Long adminUserId;
    @NotNull(message = "la partita è obbligatoria!")
    private Long gameMatchId;
    private LocalDateTime dateAndTimeReview;
    @NotNull(message = "voto prenotazione è obbligatorio!")
    private Double booking;
    @NotNull(message = "voto comportamento gara è obbligatorio!")
    private Double behaviorRace;
}
