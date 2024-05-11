package it.sportandreview.join_request;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/joinRequests")
public class JoinRequestController {

    private final JoinRequestService service;

    @Autowired
    public JoinRequestController(JoinRequestService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new join request")
    public ResponseEntity<Long> create(@Parameter(name = "joinRequestDTO") @RequestBody JoinRequestDTO joinRequestDTO){
        return new ResponseEntity<>(service.create(joinRequestDTO), HttpStatus.OK);
    }
    @Operation(summary = "Find all join request")
    public ResponseEntity<List<JoinRequestDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{joinRequestId}")
    @Operation(summary = "Find join request by id")
    public ResponseEntity<JoinRequestDTO> findById(@PathVariable Long joinRequestId) {
        return new ResponseEntity<>(service.findById(joinRequestId), HttpStatus.OK);
    }
}
