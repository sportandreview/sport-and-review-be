package it.sportandreview.field_size;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fieldSizes")
public class FieldSizeController {

    private final FieldSizeService service;

    @Autowired
    public FieldSizeController(FieldSizeService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new field size")
    public ResponseEntity<Long> create(@Parameter(name = "fieldSizeDTO") @RequestBody FieldSizeDTO fieldSizeDTO){
        return new ResponseEntity<>(service.create(fieldSizeDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update field size")
    public ResponseEntity<FieldSizeDTO> update(@Parameter(name = "fieldSizeDTO") @RequestBody FieldSizeDTO fieldSizeDTO){
        return new ResponseEntity<>(service.update(fieldSizeDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all field size")
    public ResponseEntity<List<FieldSizeDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{fieldSizeId}")
    @Operation(summary = "Find field size by id")
    public ResponseEntity<FieldSizeDTO> findById(@PathVariable Long fieldSizeId) {
        return new ResponseEntity<>(service.findById(fieldSizeId), HttpStatus.OK);
    }


}
