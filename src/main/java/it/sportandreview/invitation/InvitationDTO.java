package it.sportandreview.invitation;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.invitation_state.InvitationStateDTO;
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
public class InvitationDTO extends BaseDTO {

    private ZonedDateTime date;
    private GameMatch gameMatch;
    private InvitationStateDTO invitationState;
    private PlayerUserDTO playerUser;


}
