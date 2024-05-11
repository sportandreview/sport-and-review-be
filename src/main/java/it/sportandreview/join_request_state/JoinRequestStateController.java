package it.sportandreview.join_request_state;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.join_request.JoinRequestDTO;
import it.sportandreview.match_state.MatchState;
import it.sportandreview.match_state.MatchStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/joinRequestStates")
public class JoinRequestStateController {

    private final JoinRequestStateService service;

    @Autowired
    public JoinRequestStateController(JoinRequestStateService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new join request state")
    public ResponseEntity<Long> create(@Parameter(name = "joinRequestStateDTO") @RequestBody JoinRequestStateDTO joinRequestStateDTO){
        return new ResponseEntity<>(service.create(joinRequestStateDTO), HttpStatus.OK);
    }
    @Operation(summary = "Find all join request state")
    public ResponseEntity<List<JoinRequestStateDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{joinRequestStateId}")
    @Operation(summary = "Find join request state by id")
    public ResponseEntity<JoinRequestStateDTO> findById(@PathVariable Long joinRequestStateId) {
        return new ResponseEntity<>(service.findById(joinRequestStateId), HttpStatus.OK);
    }
}
