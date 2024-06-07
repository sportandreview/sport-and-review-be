package it.sportandreview.club_review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.response.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clubReviews")
public class ClubReviewController {

    private final ClubReviewService service;

    @Autowired
    public ClubReviewController(ClubReviewService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new club review")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "clubReviewDTO") @Valid @RequestBody ClubReviewDTO clubReviewDTO){
            ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                    .status(HttpServletResponse.SC_OK)
                    .message("ClubReview creata con successo")
                    .result(service.create(clubReviewDTO))
                    .build();
            return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update club review")
    public ResponseEntity<ApiResponseDTO<ClubReviewDTO>> update(@Parameter(name = "clubReviewDTO") @Valid @RequestBody ClubReviewDTO clubReviewDTO){
        ApiResponseDTO<ClubReviewDTO> response = ApiResponseDTO.<ClubReviewDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("ClubReview aggiornata con successo")
                .result(service.update(clubReviewDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Find all club review")
    public ResponseEntity<ApiResponseDTO<List<ClubReviewDTO>>> findAll() {
        ApiResponseDTO<List<ClubReviewDTO>> response = ApiResponseDTO.<List<ClubReviewDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di tutte le recensioni")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{clubReviewId}")
    @Operation(summary = "Find club review by id")
    public ResponseEntity<ApiResponseDTO<ClubReviewDTO>> findById(@PathVariable Long clubReviewId) {
        ApiResponseDTO<ClubReviewDTO> response = ApiResponseDTO.<ClubReviewDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("ClubReview trovato per l'id specificato")
                .result(service.findById(clubReviewId))
                .build();
        return ResponseEntity.ok(response);
    }

}
