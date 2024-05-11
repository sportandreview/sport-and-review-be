package it.sportandreview.invitation;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepository;
    private final InvitationMapper InvitationMapper;

    @Autowired
    public InvitationServiceImpl(InvitationRepository invitationRepository, InvitationMapper InvitationMapper) {
        this.invitationRepository = invitationRepository;
        this.InvitationMapper = InvitationMapper;
    }
}
