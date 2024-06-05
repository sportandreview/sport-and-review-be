package it.sportandreview.join_request_state;

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
@RequestMapping("/joinRequestStates")
public class JoinRequestStateController {

    private final JoinRequestStateService service;

    @Autowired
    public JoinRequestStateController(JoinRequestStateService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new join request state")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "joinRequestStateDTO") @Valid @RequestBody JoinRequestStateDTO joinRequestStateDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("JoinRequestState creata con successo")
                .result(service.create(joinRequestStateDTO))
                .build();
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Find all join request state")
    public ResponseEntity<ApiResponseDTO<List<JoinRequestStateDTO>>> findAll() {
        ApiResponseDTO<List<JoinRequestStateDTO>> response = ApiResponseDTO.<List<JoinRequestStateDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di JoinRequestState")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{joinRequestStateId}")
    @Operation(summary = "Find join request state by id")
    public ResponseEntity<ApiResponseDTO<JoinRequestStateDTO>> findById(@PathVariable Long joinRequestStateId) {
        ApiResponseDTO<JoinRequestStateDTO> response = ApiResponseDTO.<JoinRequestStateDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("JoinRequestState per l'id richiesto")
                .result(service.findById(joinRequestStateId))
                .build();
        return ResponseEntity.ok(response);
    }
}
