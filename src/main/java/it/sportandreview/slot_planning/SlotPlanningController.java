package it.sportandreview.slot_planning;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.slot.SlotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/slotPlanning")
public class SlotPlanningController {

    private final SlotPlanningService service;

    @Autowired
    public SlotPlanningController(SlotPlanningService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new slot planning")
    public ResponseEntity<Long> create(@Parameter(name = "slotPlanningDTO") @RequestBody SlotPlanningDTO slotPlanningDTO) {
        return new ResponseEntity<>(service.create(slotPlanningDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update slot planning")
    public ResponseEntity<SlotPlanningDTO> update(@Parameter(name = "slotPlanningDTO") @RequestBody SlotPlanningDTO slotPlanningDTO) {
        return new ResponseEntity<>(service.update(slotPlanningDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all slot planning")
    @GetMapping
    public ResponseEntity<List<SlotPlanningDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{slotPlanningId}")
    @Operation(summary = "Find slot planning by id")
    public ResponseEntity<SlotPlanningDTO> findById(@PathVariable Long slotPlanningId) {
        return new ResponseEntity<>(service.findById(slotPlanningId), HttpStatus.OK);
    }

    @GetMapping("/byDateAndField/{date}/{fieldId}")
    @Operation(summary = "Find slot  by date")
    public ResponseEntity<List<SlotDTO>> findByDateAndField(@PathVariable LocalDate date, @PathVariable Long fieldId) {
        return new ResponseEntity<>(service.findByDateAndField(date, fieldId), HttpStatus.OK);
    }
}
