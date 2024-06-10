package it.sportandreview.invitation_state;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.dto.response.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/invitationStates")
public class InvitationStateController {

    private final InvitationStateService service;

    @Autowired
    public InvitationStateController(InvitationStateService service) {
        this.service = service;
    }

    @Operation(summary = "Find all invitation states")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<InvitationStateDTO>>> findAll() {
        ApiResponseDTO<List<InvitationStateDTO>> response = ApiResponseDTO.<List<InvitationStateDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di InvitationState")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{invitationStateId}")
    @Operation(summary = "Find invitation state by id")
    public ResponseEntity<ApiResponseDTO<InvitationStateDTO>> findById(@PathVariable Long invitationStateId) {
        ApiResponseDTO<InvitationStateDTO> response = ApiResponseDTO.<InvitationStateDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("InvitationState per l'id richiesto")
                .result(service.findById(invitationStateId))
                .build();
        return ResponseEntity.ok(response);
    }

}
