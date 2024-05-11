package it.sportandreview.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<Long> create(@Parameter(name = "servicesDTO") @RequestBody ServicesDTO servicesDTO){
        return new ResponseEntity<>(service.create(servicesDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update service")
    public ResponseEntity<ServicesDTO> update(@Parameter(name = "servicesDTO") @RequestBody ServicesDTO servicesDTO){
        return new ResponseEntity<>(service.update(servicesDTO), HttpStatus.OK);
    }
}
