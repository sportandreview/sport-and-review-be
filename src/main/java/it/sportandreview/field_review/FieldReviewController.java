package it.sportandreview.field_review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fieldReviews")
public class FieldReviewController {

    private final FieldReviewService service;

    @Autowired
    public FieldReviewController(FieldReviewService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new field review")
    public ResponseEntity<Long> create(@Parameter(name = "fieldReviewDTO") @RequestBody FieldReviewDTO fieldReviewDTO){
        return new ResponseEntity<>(service.create(fieldReviewDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update field review")
    public ResponseEntity<FieldReviewDTO> update(@Parameter(name = "fieldReviewDTO") @RequestBody FieldReviewDTO fieldReviewDTO){
        return new ResponseEntity<>(service.update(fieldReviewDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all field review")
    public ResponseEntity<List<FieldReviewDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{fieldReviewId}")
    @Operation(summary = "Find field review by id")
    public ResponseEntity<FieldReviewDTO> findById(@PathVariable Long fieldReviewId) {
        return new ResponseEntity<>(service.findById(fieldReviewId), HttpStatus.OK);
    }


}
