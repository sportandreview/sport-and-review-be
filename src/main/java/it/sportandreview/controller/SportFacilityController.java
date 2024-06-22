package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.request.SportFacilityRequestDTO;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.SportFacilityResponseDTO;
import it.sportandreview.service.SportFacilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sport-facilities")
@RequiredArgsConstructor
public class SportFacilityController {

    private final SportFacilityService sportFacilityService;

    @PostMapping
    @Operation(summary = "Crea una nuova struttura sportiva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Struttura creata con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<ApiResponseDTO<SportFacilityResponseDTO>> createSportFacility(@Valid @RequestBody SportFacilityRequestDTO request) {
        SportFacilityResponseDTO response = sportFacilityService.createSportFacility(request);
        return ResponseEntity.status(201).body(new ApiResponseDTO<>(201, "Struttura creata con successo", response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiorna una struttura sportiva esistente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Struttura aggiornata con successo"),
            @ApiResponse(responseCode = "404", description = "Struttura non trovata")
    })
    public ResponseEntity<ApiResponseDTO<SportFacilityResponseDTO>> updateSportFacility(@PathVariable Long id, @Valid @RequestBody SportFacilityRequestDTO request) {
        SportFacilityResponseDTO response = sportFacilityService.updateSportFacility(id, request);
        return ResponseEntity.ok(new ApiResponseDTO<>(200, "Struttura aggiornata con successo", response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una struttura sportiva esistente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Struttura eliminata con successo"),
            @ApiResponse(responseCode = "404", description = "Struttura non trovata")
    })
    public ResponseEntity<ApiResponseDTO<Void>> deleteSportFacility(@PathVariable Long id) {
        sportFacilityService.deleteSportFacility(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera una struttura sportiva esistente per ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Struttura trovata con successo"),
            @ApiResponse(responseCode = "404", description = "Struttura non trovata")
    })
    public ResponseEntity<ApiResponseDTO<SportFacilityResponseDTO>> getSportFacilityById(@PathVariable Long id) {
        SportFacilityResponseDTO response = sportFacilityService.getSportFacilityById(id);
        return ResponseEntity.ok(new ApiResponseDTO<>(200, "Struttura trovata con successo", response));
    }

    @GetMapping
    @Operation(summary = "Recupera tutte le strutture sportive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Strutture recuperate con successo")
    })
    public ResponseEntity<ApiResponseDTO<List<SportFacilityResponseDTO>>> getAllSportFacilities() {
        List<SportFacilityResponseDTO> response = sportFacilityService.getAllSportFacilities();
        return ResponseEntity.ok(new ApiResponseDTO<>(200, "Strutture recuperate con successo", response));
    }
}
