package it.sportandreview.user_code_type;

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
@RequestMapping("/userCodeTypes")
public class UserCodeTypeController {

    private final UserCodeTypeService service;

    @Autowired
    public UserCodeTypeController(UserCodeTypeService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new user code type")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "userCodeTypeDTO") @Valid @RequestBody UserCodeTypeDTO userCodeTypeDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("UserCodeType creata con successo")
                .result(service.create(userCodeTypeDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/userCodeTypes")
    public ResponseEntity<ApiResponseDTO<List<UserCodeTypeDTO>>> findAll() {
        ApiResponseDTO<List<UserCodeTypeDTO>> response = ApiResponseDTO.<List<UserCodeTypeDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di UserCodeType")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{userCodeTypeId}")
    @Operation(summary = "Find user code type by id")
    public ResponseEntity<ApiResponseDTO<UserCodeTypeDTO>> findById(@PathVariable Long userCodeTypeId) {
        ApiResponseDTO<UserCodeTypeDTO> response = ApiResponseDTO.<UserCodeTypeDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("UserCodeType per l'id richiesto")
                .result(service.findById(userCodeTypeId))
                .build();
        return ResponseEntity.ok(response);
    }
}
