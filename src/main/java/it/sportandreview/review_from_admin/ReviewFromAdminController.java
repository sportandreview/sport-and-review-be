package it.sportandreview.review_from_admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reviewFromAdmins")
public class ReviewFromAdminController {

    private final ReviewFromAdminService service;

    @Autowired
    public ReviewFromAdminController(ReviewFromAdminService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Create new review from admin")
    public ResponseEntity<Long> create(@Parameter(name = "reviewFromAdminDTO") @RequestBody ReviewFromAdminDTO reviewFromAdminDTO){
        return new ResponseEntity<>(service.create(reviewFromAdminDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all review from admin")
    public ResponseEntity<List<ReviewFromAdminDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{reviewFromAdminId}")
    @Operation(summary = "Find review from admin by id")
    public ResponseEntity<ReviewFromAdminDTO> findById(@PathVariable Long reviewFromAdminId) {
        return new ResponseEntity<>(service.findById(reviewFromAdminId), HttpStatus.OK);
    }
}
