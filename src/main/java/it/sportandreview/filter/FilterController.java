package it.sportandreview.filter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.player_user.PlayerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/filters")
public class FilterController {

    private final FilterService service;

    @Autowired
    public FilterController(FilterService service) {
        this.service = service;
    }
}
