package it.sportandreview.club;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "clubDTO") @Valid @RequestBody ClubDTO clubDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Club creato con successo")
                .result(service.create(clubDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update club")
    public ResponseEntity<ApiResponseDTO<ClubDTO>> update(@Parameter(name = "clubDTO") @Valid @RequestBody ClubDTO clubDTO){
        ApiResponseDTO<ClubDTO> response = ApiResponseDTO.<ClubDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Club aggiornato con successo")
                .result(service.update(clubDTO))
                .build();
        return ResponseEntity.ok(response);
    }
}
