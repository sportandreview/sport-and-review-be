package it.sportandreview.field;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/fields")
public class FieldController {

    private final FieldService service;

    @Autowired
    public FieldController(FieldService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new field")
    public ResponseEntity<Long> create(@Parameter(name = "fieldDto") @RequestBody FieldDTO fieldDto){
        return new ResponseEntity<>(service.create(fieldDto), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update field")
    public ResponseEntity<FieldDTO> update(@Parameter(name = "fieldDto") @RequestBody FieldDTO fieldDto){
        return new ResponseEntity<>(service.update(fieldDto), HttpStatus.OK);
    }
}
