package it.sportandreview.highlight;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.game_match.GameMatchDTO;
import it.sportandreview.player_user.PlayerUserDTO;
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

    private String videoUrl;
    private ZonedDateTime creationDate;
    private String thumbnail;
    private GameMatchDTO gameMatch;
    private PlayerUserDTO createdBy;


}
