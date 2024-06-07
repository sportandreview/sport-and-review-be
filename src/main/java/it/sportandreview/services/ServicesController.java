package it.sportandreview.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.response.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/services")
public class ServicesController {

    private final ServicesService service;

    @Autowired
    public ServicesController(ServicesService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new service")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "servicesDTO") @Valid @RequestBody ServicesDTO servicesDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Services creato con successo")
                .result(service.create(servicesDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update service")
    public ResponseEntity<ApiResponseDTO<ServicesDTO>> update(@Parameter(name = "servicesDTO") @Valid @RequestBody ServicesDTO servicesDTO){
        ApiResponseDTO<ServicesDTO> response = ApiResponseDTO.<ServicesDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Services aggiornato con successo")
                .result(service.update(servicesDTO))
                .build();
        return ResponseEntity.ok(response);
    }
}
