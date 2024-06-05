package it.sportandreview.slot_planning;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.slot.SlotDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "slotPlanningDTO") @Valid @RequestBody SlotPlanningDTO slotPlanningDTO) {
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("SlotPlanning creata con successo")
                .result(service.create(slotPlanningDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update slot planning")
    public ResponseEntity<ApiResponseDTO<SlotPlanningDTO>> update(@Parameter(name = "slotPlanningDTO") @Valid @RequestBody SlotPlanningDTO slotPlanningDTO) {
        ApiResponseDTO<SlotPlanningDTO> response = ApiResponseDTO.<SlotPlanningDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("SlotPlanning aggiornata con successo")
                .result(service.update(slotPlanningDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all slot planning")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<SlotPlanningDTO>>> findAll() {
        ApiResponseDTO<List<SlotPlanningDTO>> response = ApiResponseDTO.<List<SlotPlanningDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di SlotPlanning")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{slotPlanningId}")
    @Operation(summary = "Find slot planning by id")
    public ResponseEntity<ApiResponseDTO<SlotPlanningDTO>> findById(@PathVariable Long slotPlanningId) {
        ApiResponseDTO<SlotPlanningDTO> response = ApiResponseDTO.<SlotPlanningDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("SlotPlanning per l'id richiesto")
                .result(service.findById(slotPlanningId))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byDateAndField/{date}/{fieldId}")
    @Operation(summary = "Find slot  by date")
    public ResponseEntity<ApiResponseDTO<List<SlotDTO>>> findByDateAndField(@PathVariable LocalDate date, @PathVariable Long fieldId) {
        ApiResponseDTO<List<SlotDTO>> response = ApiResponseDTO.<List<SlotDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di slot per la data e il campo selezionati")
                .result(service.findByDateAndField(date, fieldId))
                .build();
        return ResponseEntity.ok(response);
    }
}
