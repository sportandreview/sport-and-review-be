package it.sportandreview.player_review;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.game_match.GameMatchDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.sport.SportDTO;
import it.sportandreview.user.UserDTO;
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

    private Long playerUserId;
    private Long madeById;
    private Long gameMatchId;
    private Long sportId;
    private Double physicalAbility;
    private Double behavior;
    private Double tacticalAbility;
    private Double technicalAbility;
    private LocalDate votingDate;
    private LocalTime votingTime;

}
