package it.sportandreview.invitation_state;


import java.util.List;

public interface InvitationStateService {

    List<InvitationStateDTO> findAll();

    InvitationStateDTO findById(Long invitationStateId);

}
