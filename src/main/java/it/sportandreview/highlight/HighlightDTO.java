package it.sportandreview.highlight;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.game_match.GameMatchDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class HighlightDTO extends BaseDTO {
    @NotBlank(message = "l'url del video è obbligatorio!")
    private String videoUrl;
    @NotNull(message = "data della creazione obbligatoria!")
    private ZonedDateTime creationDate;
    private String thumbnail;
    @NotNull(message = "la partita è obbligatoria!")
    private GameMatchDTO gameMatch;
    @NotNull(message = "il player che la crea obbligatorio!")
    private PlayerUserDTO createdBy;


}
