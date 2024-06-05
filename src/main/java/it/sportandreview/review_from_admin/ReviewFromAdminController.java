package it.sportandreview.review_from_admin;

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
@RequestMapping("/reviewFromAdmins")
public class ReviewFromAdminController {

    private final ReviewFromAdminService service;

    @Autowired
    public ReviewFromAdminController(ReviewFromAdminService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Create new review from admin")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "reviewFromAdminDTO") @Valid @RequestBody ReviewFromAdminDTO reviewFromAdminDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("ReviewFromAdmin creata con successo")
                .result(service.create(reviewFromAdminDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all review from admin")
    public ResponseEntity<ApiResponseDTO<List<ReviewFromAdminDTO>>> findAll() {
        ApiResponseDTO<List<ReviewFromAdminDTO>> response = ApiResponseDTO.<List<ReviewFromAdminDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di ReviewFromAdmin")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reviewFromAdminId}")
    @Operation(summary = "Find review from admin by id")
    public ResponseEntity<ApiResponseDTO<ReviewFromAdminDTO>> findById(@PathVariable Long reviewFromAdminId) {
        ApiResponseDTO<ReviewFromAdminDTO> response = ApiResponseDTO.<ReviewFromAdminDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("ReviewFromAdmin per l'id richiesto")
                .result(service.findById(reviewFromAdminId))
                .build();
        return ResponseEntity.ok(response);
    }
}
