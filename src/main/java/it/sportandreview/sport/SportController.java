package it.sportandreview.sport;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sports")
public class SportController {

    private final SportService service;

    @Autowired
    public SportController(SportService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new sport")
    public ResponseEntity<Long> create(@Parameter(name = "sportDTO") @RequestBody SportDTO sportDTO) {
        return new ResponseEntity<>(service.create(sportDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update sport")
    public ResponseEntity<SportDTO> update(@Parameter(name = "sportDTO") @RequestBody SportDTO sportDTO) {
        return new ResponseEntity<>(service.update(sportDTO), HttpStatus.OK);
    }
}
