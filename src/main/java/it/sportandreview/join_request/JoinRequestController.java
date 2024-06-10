package it.sportandreview.join_request;

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
@RequestMapping("/joinRequests")
public class JoinRequestController {

    private final JoinRequestService service;

    @Autowired
    public JoinRequestController(JoinRequestService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new join request")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "joinRequestDTO") @Valid @RequestBody JoinRequestDTO joinRequestDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("JoinRequest creata con successo")
                .result(service.create(joinRequestDTO))
                .build();
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Find all join request")
    public ResponseEntity<ApiResponseDTO<List<JoinRequestDTO>>> findAll() {
        ApiResponseDTO<List<JoinRequestDTO>> response = ApiResponseDTO.<List<JoinRequestDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di JoinRequest")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{joinRequestId}")
    @Operation(summary = "Find join request by id")
    public ResponseEntity<ApiResponseDTO<JoinRequestDTO>>findById(@PathVariable Long joinRequestId) {
        ApiResponseDTO<JoinRequestDTO> response = ApiResponseDTO.<JoinRequestDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("JoinRequest per l'id richiesto")
                .result(service.findById(joinRequestId))
                .build();
        return ResponseEntity.ok(response);
    }
}
