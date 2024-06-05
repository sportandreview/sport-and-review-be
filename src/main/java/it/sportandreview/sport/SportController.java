package it.sportandreview.sport;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "sportDTO") @Valid @RequestBody SportDTO sportDTO) {
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Sport creato con successo")
                .result(service.create(sportDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update sport")
    public ResponseEntity<ApiResponseDTO<SportDTO>> update(@Parameter(name = "sportDTO") @Valid @RequestBody SportDTO sportDTO) {
        ApiResponseDTO<SportDTO> response = ApiResponseDTO.<SportDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Sport aggiornato con successo")
                .result(service.update(sportDTO))
                .build();
        return ResponseEntity.ok(response);
    }
}
