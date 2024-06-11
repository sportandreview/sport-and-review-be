package it.sportandreview.match_state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/matchStates")
public class MatchStateController {

    private final MatchStateService service;

    @Autowired
    public MatchStateController(MatchStateService service) {
        this.service = service;
    }

}
