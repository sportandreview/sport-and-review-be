package it.sportandreview.invitation;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.game_match.GameMatchDTO;
import it.sportandreview.invitation_state.InvitationStateDTO;
import it.sportandreview.player_user.PlayerUserDTO;
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
public class InvitationDTO extends BaseDTO {
    @NotNull(message = "la data dell'invito è obbligatoria")
    private ZonedDateTime date;
    @NotNull(message = "la partita è obbligatoria")
    private GameMatchDTO gameMatch;
    @NotNull(message = "lo stato dell'invito è obbligatorio")
    private InvitationStateDTO invitationState;
    @NotNull(message = "il player è obbligatorio")
    private PlayerUserDTO playerUser;


}
