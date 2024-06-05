package it.sportandreview.field_size;

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
@RequestMapping("/fieldSizes")
public class FieldSizeController {

    private final FieldSizeService service;

    @Autowired
    public FieldSizeController(FieldSizeService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new field size")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "fieldSizeDTO") @Valid @RequestBody FieldSizeDTO fieldSizeDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("FieldSize creata con successo")
                .result(service.create(fieldSizeDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update field size")
    public ResponseEntity<ApiResponseDTO<FieldSizeDTO>> update(@Parameter(name = "fieldSizeDTO") @Valid @RequestBody FieldSizeDTO fieldSizeDTO){
        ApiResponseDTO<FieldSizeDTO> response = ApiResponseDTO.<FieldSizeDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("FieldSize aggiornata con successo")
                .result(service.update(fieldSizeDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all field size")
    public ResponseEntity<ApiResponseDTO<List<FieldSizeDTO>>> findAll() {
        ApiResponseDTO<List<FieldSizeDTO>> response = ApiResponseDTO.<List<FieldSizeDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di FieldSize")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{fieldSizeId}")
    @Operation(summary = "Find field size by id")
    public ResponseEntity<ApiResponseDTO<FieldSizeDTO>> findById(@PathVariable Long fieldSizeId) {
        ApiResponseDTO<FieldSizeDTO> response = ApiResponseDTO.<FieldSizeDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("FieldSize trovata per l'id specificato")
                .result(service.findById(fieldSizeId))
                .build();
        return ResponseEntity.ok(response);
    }


}
