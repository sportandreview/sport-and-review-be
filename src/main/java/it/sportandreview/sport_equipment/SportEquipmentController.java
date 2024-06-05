package it.sportandreview.sport_equipment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "ballsDTO") @Valid @RequestBody SportEquipmentDTO sportEquipmentDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("SportEquipment creata con successo")
                .result(service.create(sportEquipmentDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update sport equipment")
    public ResponseEntity<ApiResponseDTO<SportEquipmentDTO>> update(@Parameter(name = "sportEquipmentDTO") @Valid @RequestBody SportEquipmentDTO sportEquipmentDTO){
        ApiResponseDTO<SportEquipmentDTO> response = ApiResponseDTO.<SportEquipmentDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("SportEquipment aggiornata con successo")
                .result(service.update(sportEquipmentDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all sport equipment")
    public ResponseEntity<ApiResponseDTO<List<SportEquipmentDTO>>> findAll() {
        ApiResponseDTO<List<SportEquipmentDTO>> response = ApiResponseDTO.<List<SportEquipmentDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di SportEquipment")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{sportEquipmentId}")
    @Operation(summary = "Find sport equipment by id")
    public ResponseEntity<ApiResponseDTO<SportEquipmentDTO>> findById(@PathVariable Long sportEquipmentId) {
        ApiResponseDTO<SportEquipmentDTO> response = ApiResponseDTO.<SportEquipmentDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("SportEquipment per l'id richiesto")
                .result(service.findById(sportEquipmentId))
                .build();
        return ResponseEntity.ok(response);
    }
}
