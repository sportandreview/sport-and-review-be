package it.sportandreview.player_review;

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
@RequestMapping("/playerReviews")
public class PlayerReviewController {

    private final PlayerReviewService service;

    @Autowired
    public PlayerReviewController(PlayerReviewService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new player review")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "playerReviewDTO") @Valid @RequestBody PlayerReviewDTO playerReviewDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("PlayerReview creata con successo")
                .result(service.create(playerReviewDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all player review")
    public ResponseEntity<ApiResponseDTO<List<PlayerReviewDTO>>> findAll() {
        ApiResponseDTO<List<PlayerReviewDTO>> response = ApiResponseDTO.<List<PlayerReviewDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di PlayerReview")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{playerReviewId}")
    @Operation(summary = "Find player review by id")
    public ResponseEntity<ApiResponseDTO<PlayerReviewDTO>> findById(@PathVariable Long playerReviewId) {
        ApiResponseDTO<PlayerReviewDTO> response = ApiResponseDTO.<PlayerReviewDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("PlayerReview per l'id richiesto")
                .result(service.findById(playerReviewId))
                .build();
        return ResponseEntity.ok(response);
    }
}
