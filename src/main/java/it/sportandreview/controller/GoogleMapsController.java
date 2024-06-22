package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.GooglePlaceAutocompleteResponse;
import it.sportandreview.dto.response.GooglePlaceDetailsResponse;
import it.sportandreview.service.GoogleMapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/google-maps")
public class GoogleMapsController {

    private final GoogleMapsService googleMapsService;

    @GetMapping("/autocomplete")
    @Operation(summary = "Ottieni suggerimenti di indirizzi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suggerimenti ottenuti con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<ApiResponseDTO<GooglePlaceAutocompleteResponse>> getAutocompleteSuggestions(@RequestParam String input) {
        GooglePlaceAutocompleteResponse response = googleMapsService.getAutocompleteSuggestions(input);
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), null, response));
    }

    @GetMapping("/place-details")
    @Operation(summary = "Ottieni dettagli dell'indirizzo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dettagli ottenuti con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<ApiResponseDTO<GooglePlaceDetailsResponse>> getPlaceDetails(@RequestParam String placeId) {
        GooglePlaceDetailsResponse response = googleMapsService.getPlaceDetails(placeId);
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), null, response));
    }
}
