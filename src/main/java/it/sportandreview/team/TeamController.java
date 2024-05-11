package it.sportandreview.team;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new team")
    public ResponseEntity<Long> create(@Parameter(name = "teamDTO") @RequestBody TeamDTO teamDTO){
        return new ResponseEntity<>(service.create(teamDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update team")
    public ResponseEntity<TeamDTO> update(@Parameter(name = "clubDTO") @RequestBody TeamDTO TeamDTO){
        return new ResponseEntity<>(service.update(TeamDTO), HttpStatus.OK);
    }
}
