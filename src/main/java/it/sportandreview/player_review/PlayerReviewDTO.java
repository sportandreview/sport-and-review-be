package it.sportandreview.player_review;

import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PlayerReviewDTO extends BaseDTO {

    @NotNull(message = "il player è obbligatorio!")
    private Long playerUserId;
    @NotNull(message = "il player che recensisce è obbligatorio!")
    private Long madeById;
    @NotNull(message = "la partita è obbligatoria!")
    private Long gameMatchId;
    @NotNull(message = "lo sport è obbligatorio!")
    private Long sportId;
    @NotNull(message = "l'abbilità fisica è obbligatoria!")
    private Double physicalAbility;
    @NotNull(message = "il comportamento è obbligatorio!")
    private Double behavior;
    @NotNull(message = "la capacità tattica è obbligatoria!")
    private Double tacticalAbility;
    @NotNull(message = "la capacità tecnica è obbligatoria!")
    private Double technicalAbility;
    private LocalDate votingDate;
    private LocalTime votingTime;

}
