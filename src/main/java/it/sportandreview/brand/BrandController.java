package it.sportandreview.brand;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new brand")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "brandDTO") @RequestBody BrandDTO brandDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Brand creato con successo")
                .result(service.create(brandDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update brand")
    public ResponseEntity<ApiResponseDTO<BrandDTO>> update(@Parameter(name = "brandDTO") @RequestBody BrandDTO brandDTO){
        ApiResponseDTO<BrandDTO> response = ApiResponseDTO.<BrandDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Brand aggiornato con successo")
                .result(service.update(brandDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all brand")
    public ResponseEntity<ApiResponseDTO<List<BrandDTO>>> findAll() {
        ApiResponseDTO<List<BrandDTO>> response = ApiResponseDTO.<List<BrandDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di tutti i Brand")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{brandId}")
    @Operation(summary = "Find brand by id")
    public ResponseEntity<ApiResponseDTO<BrandDTO>> findById(@PathVariable Long brandId) {
        ApiResponseDTO<BrandDTO> response = ApiResponseDTO.<BrandDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Brand trovato per l'id specificato")
                .result(service.findById(brandId))
                .build();
        return ResponseEntity.ok(response);
    }
}
