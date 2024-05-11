package it.sportandreview.invitation_state;

import it.sportandreview.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class InvitationStateServiceImpl implements InvitationStateService {

    private final InvitationStateRepository InvitationStateRepository;
    private final InvitationStateMapper invitationStateMapper;

    @Autowired
    public InvitationStateServiceImpl(InvitationStateRepository InvitationStateRepository, InvitationStateMapper invitationStateMapper) {
        this.InvitationStateRepository = InvitationStateRepository;
        this.invitationStateMapper = invitationStateMapper;
    }

    @Override
    public List<InvitationStateDTO> findAll() {
        return InvitationStateRepository.findAll().stream().map(invitationStateMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public InvitationStateDTO findById(Long invitationStateId) {
        return InvitationStateRepository.findById(invitationStateId).map(invitationStateMapper::toDto).
                orElseThrow(() -> new NotFoundException("invitation state ", "InvitationState" + "not exists into database"));
    }

}
