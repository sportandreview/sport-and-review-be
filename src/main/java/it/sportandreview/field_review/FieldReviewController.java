package it.sportandreview.field_review;

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
@RequestMapping("/fieldReviews")
public class FieldReviewController {

    private final FieldReviewService service;

    @Autowired
    public FieldReviewController(FieldReviewService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new field review")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "fieldReviewDTO") @Valid @RequestBody FieldReviewDTO fieldReviewDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("FieldReview creata con successo")
                .result(service.create(fieldReviewDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update field review")
    public ResponseEntity<ApiResponseDTO<FieldReviewDTO>> update(@Parameter(name = "fieldReviewDTO") @Valid @RequestBody FieldReviewDTO fieldReviewDTO){
        ApiResponseDTO<FieldReviewDTO> response = ApiResponseDTO.<FieldReviewDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("FieldReview aggiornata con successo")
                .result(service.update(fieldReviewDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all field review")
    public ResponseEntity<ApiResponseDTO<List<FieldReviewDTO>>> findAll() {
        ApiResponseDTO<List<FieldReviewDTO>> response = ApiResponseDTO.<List<FieldReviewDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di tutte le recensioni")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{fieldReviewId}")
    @Operation(summary = "Find field review by id")
    public ResponseEntity<ApiResponseDTO<FieldReviewDTO>> findById(@PathVariable Long fieldReviewId) {
        ApiResponseDTO<FieldReviewDTO> response = ApiResponseDTO.<FieldReviewDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("FieldReview trovato per l'id specificato")
                .result(service.findById(fieldReviewId))
                .build();
        return ResponseEntity.ok(response);
    }


}
