package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.request.PlaygroundRequestDTO;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.PlaygroundResponseDTO;
import it.sportandreview.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playgrounds")
public class PlaygroundController {

    private final PlaygroundService playgroundService;

    @PostMapping("/facility/{facilityId}")
    @Operation(summary = "Crea un nuovo campo per una struttura sportiva esistente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campo creato con successo"),
            @ApiResponse(responseCode = "404", description = "Struttura sportiva o sport non trovata"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<ApiResponseDTO<PlaygroundResponseDTO>> createPlayground(
            @PathVariable Long facilityId,
            @Valid @RequestBody PlaygroundRequestDTO request) {
        PlaygroundResponseDTO response = playgroundService.createPlayground(facilityId, request);
        String message = "Campo creato con successo";
        return ResponseEntity.ok(new ApiResponseDTO<>(200, message, response));
    }
}
