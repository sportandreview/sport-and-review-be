package it.sportandreview.service_type;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ServicesTypeDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
