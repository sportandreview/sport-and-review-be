package it.sportandreview.invitation;

import it.sportandreview.game_match.GameMatchMapper;
import it.sportandreview.invitation_state.InvitationStateMapper;
import it.sportandreview.player_user.PlayerUserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Invitation.class, GameMatchMapper.class, InvitationStateMapper.class, PlayerUserMapper.class})
public interface InvitationMapper {

    InvitationDTO toDto(Invitation invitation);
    Invitation toEntity(InvitationDTO invitationDto);

}
