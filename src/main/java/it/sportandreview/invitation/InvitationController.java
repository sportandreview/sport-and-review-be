package it.sportandreview.invitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/invitations")
public class InvitationController {

    private final InvitationService service;

    @Autowired
    public InvitationController(InvitationService service) {
        this.service = service;
    }
}
