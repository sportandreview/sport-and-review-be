package it.sportandreview.highlight;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.response.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/highlights")
public class HighlightController {

    private final HighlightService service;

    @Autowired
    public HighlightController(HighlightService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new highlight")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "highlightDto") @Valid @RequestBody HighlightDTO highlightDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Highlight creata con successo")
                .result(service.create(highlightDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update highlight")
    public ResponseEntity<ApiResponseDTO<HighlightDTO>> update(@Parameter(name = "highlightDto") @Valid @RequestBody HighlightDTO highlightDTO){
        ApiResponseDTO<HighlightDTO> response = ApiResponseDTO.<HighlightDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Highlight aggiornata con successo")
                .result(service.update(highlightDTO))
                .build();
        return ResponseEntity.ok(response);
    }
}
