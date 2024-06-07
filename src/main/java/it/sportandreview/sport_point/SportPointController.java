package it.sportandreview.sport_point;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.response.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "sportPointDTO") @Valid @RequestBody SportPointDTO sportPointDTO) {
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("SportPoint creato con successo")
                .result(service.create(sportPointDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update sport point")
    public ResponseEntity<ApiResponseDTO<SportPointDTO>> update(@Parameter(name = "sportPointDTO") @Valid @RequestBody SportPointDTO sportPointDTO) {
        ApiResponseDTO<SportPointDTO> response = ApiResponseDTO.<SportPointDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("SportPoint aggiornato con successo")
                .result(service.update(sportPointDTO))
                .build();
        return ResponseEntity.ok(response);
    }
}
