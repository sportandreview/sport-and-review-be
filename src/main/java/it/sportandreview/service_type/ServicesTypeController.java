package it.sportandreview.service_type;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/servicesTypes")
public class ServicesTypeController {

    private final ServicesTypeService service;

    @Autowired
    public ServicesTypeController(ServicesTypeService service) {
        this.service = service;
    }

    @Operation(summary = "Find all services types")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<ServicesTypeDTO>>> findAll() {
        ApiResponseDTO<List<ServicesTypeDTO>> response = ApiResponseDTO.<List<ServicesTypeDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di ServicesType")
                .result(service.findAll())
                .build();
        return ResponseEntity.ok(response);
    }
}
