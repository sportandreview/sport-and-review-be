package it.sportandreview.field;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/fields")
public class FieldController {

    private final FieldService service;

    @Autowired
    public FieldController(FieldService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new field")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "fieldDto") @Valid @RequestBody FieldDTO fieldDto){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Field creato con successo")
                .result(service.create(fieldDto))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update field")
    public ResponseEntity<ApiResponseDTO<FieldDTO>> update(@Parameter(name = "fieldDto") @Valid @RequestBody FieldDTO fieldDto){
        ApiResponseDTO<FieldDTO> response = ApiResponseDTO.<FieldDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Field aggiornata con successo")
                .result(service.update(fieldDto))
                .build();
        return ResponseEntity.ok(response);
    }
}
