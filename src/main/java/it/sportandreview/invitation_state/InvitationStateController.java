package it.sportandreview.invitation_state;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/invitationStates")
public class InvitationStateController {

    private final InvitationStateService service;

    @Autowired
    public InvitationStateController(InvitationStateService service) {
        this.service = service;
    }

    @Operation(summary = "Find all invitation states")
    @GetMapping
    public ResponseEntity<List<InvitationStateDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{invitationStateId}")
    @Operation(summary = "Find invitation state by id")
    public ResponseEntity<InvitationStateDTO> findById(@PathVariable Long invitationStateId) {
        return new ResponseEntity<>(service.findById(invitationStateId), HttpStatus.OK);
    }

}
