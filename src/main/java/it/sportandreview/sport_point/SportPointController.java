package it.sportandreview.sport_point;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sportPoints")
public class SportPointController {

    private final SportPointService service;

    @Autowired
    public SportPointController(SportPointService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new sport point")
    public ResponseEntity<Long> create(@Parameter(name = "sportPointDTO") @RequestBody SportPointDTO sportPointDTO) {
        return new ResponseEntity<>(service.create(sportPointDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update sport point")
    public ResponseEntity<SportPointDTO> update(@Parameter(name = "sportPointDTO") @RequestBody SportPointDTO sportPointDTO) {
        return new ResponseEntity<>(service.update(sportPointDTO), HttpStatus.OK);
    }
}
