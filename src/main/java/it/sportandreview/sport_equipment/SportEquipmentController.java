package it.sportandreview.sport_equipment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sportEquipment")
public class SportEquipmentController {

    private final SportEquipmentService service;

    @Autowired
    public SportEquipmentController(SportEquipmentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new sport equipment")
    public ResponseEntity<Long> create(@Parameter(name = "ballsDTO") @RequestBody SportEquipmentDTO sportEquipmentDTO){
        return new ResponseEntity<>(service.create(sportEquipmentDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update sport equipment")
    public ResponseEntity<SportEquipmentDTO> update(@Parameter(name = "sportEquipmentDTO") @RequestBody SportEquipmentDTO sportEquipmentDTO){
        return new ResponseEntity<>(service.update(sportEquipmentDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all sport equipment")
    public ResponseEntity<List<SportEquipmentDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{sportEquipmentId}")
    @Operation(summary = "Find sport equipment by id")
    public ResponseEntity<SportEquipmentDTO> findById(@PathVariable Long sportEquipmentId) {
        return new ResponseEntity<>(service.findById(sportEquipmentId), HttpStatus.OK);
    }
}
