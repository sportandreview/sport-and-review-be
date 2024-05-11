package it.sportandreview.club;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.slot.FieldSlotsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;


@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService service;

    @Autowired
    public ClubController(ClubService service) {
        this.service = service;
    }
    @PostMapping
    @Operation(summary = "Create new club")
    public ResponseEntity<Long> create(@Parameter(name = "clubDTO") @RequestBody ClubDTO clubDTO){
        return new ResponseEntity<>(service.create(clubDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update club")
    public ResponseEntity<ClubDTO> update(@Parameter(name = "clubDTO") @RequestBody ClubDTO clubDTO){
        return new ResponseEntity<>(service.update(clubDTO), HttpStatus.OK);
    }
}
