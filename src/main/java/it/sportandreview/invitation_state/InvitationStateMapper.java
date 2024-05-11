package it.sportandreview.invitation_state;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={InvitationState.class})
public interface InvitationStateMapper {

    InvitationStateDTO toDto(InvitationState invitationState);
    InvitationState toEntity(InvitationStateDTO invitationState);

}
