package it.sportandreview.auto_evaluation;

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
@RequestMapping("/autoEvaluations")
public class AutoEvaluationController {

    private final AutoEvaluationService service;

    @Autowired
    public AutoEvaluationController(AutoEvaluationService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new auto evaluation")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "autoEvaluationDTO") @Valid @RequestBody AutoEvaluationDTO autoEvaluationDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Autovalutazione creata con successo")
                .result(service.create(autoEvaluationDTO))
                .build();
        return ResponseEntity.ok(response);


    }

    @PostMapping("/bulk")
    @Operation(summary = "Create new list of auto evaluations")
    public ResponseEntity<ApiResponseDTO<List<AutoEvaluationDTO>>> createAll(@Parameter(name = "autoEvaluationDTO") @Valid  @RequestBody List<AutoEvaluationDTO> autoEvaluationsDTO){
        ApiResponseDTO<List<AutoEvaluationDTO>> response = ApiResponseDTO.<List<AutoEvaluationDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di Autovalutazioni creata con successo")
                .result(service.createAll(autoEvaluationsDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update auto evaluation")
    public ResponseEntity<ApiResponseDTO<AutoEvaluationDTO>> update(@Parameter(name = "autoEvaluationDTO") @Valid  @RequestBody AutoEvaluationDTO autoEvaluationDTO){
        ApiResponseDTO<AutoEvaluationDTO> response = ApiResponseDTO.<AutoEvaluationDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Autovalutazione aggiornata con successo")
                .result(service.update(autoEvaluationDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all auto evaluations by player user")
    @GetMapping("/byPlayerUser/{playerUserId}")
    public ResponseEntity<ApiResponseDTO<List<AutoEvaluationDTO>>> findByPlayerUserId(@PathVariable Long playerUserId) {
        ApiResponseDTO<List<AutoEvaluationDTO>> response = ApiResponseDTO.<List<AutoEvaluationDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di Autovalutazioni per l'utente specificato")
                .result(service.findByPlayerUserId(playerUserId))
                .build();
        return ResponseEntity.ok(response);
    }

}
