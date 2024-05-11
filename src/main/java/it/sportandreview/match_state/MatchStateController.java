package it.sportandreview.match_state;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.gender_type.GenderTypeDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/matchStates")
public class MatchStateController {

    private final MatchStateService service;

    @Autowired
    public MatchStateController(MatchStateService service) {
        this.service = service;
    }

}
