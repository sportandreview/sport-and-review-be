package it.sportandreview.brand;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new brand")
    public ResponseEntity<Long> create(@Parameter(name = "brandDTO") @RequestBody BrandDTO brandDTO){
        return new ResponseEntity<>(service.create(brandDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update brand")
    public ResponseEntity<BrandDTO> update(@Parameter(name = "brandDTO") @RequestBody BrandDTO brandDTO){
        return new ResponseEntity<>(service.update(brandDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all brand")
    public ResponseEntity<List<BrandDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{brandId}")
    @Operation(summary = "Find brand by id")
    public ResponseEntity<BrandDTO> findById(@PathVariable Long brandId) {
        return new ResponseEntity<>(service.findById(brandId), HttpStatus.OK);
    }
}
