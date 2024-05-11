package it.sportandreview.club_review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clubReviews")
public class ClubReviewController {

    private final ClubReviewService service;

    @Autowired
    public ClubReviewController(ClubReviewService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new club review")
    public ResponseEntity<Long> create(@Parameter(name = "clubReviewDTO") @RequestBody ClubReviewDTO clubReviewDTO){
        return new ResponseEntity<>(service.create(clubReviewDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update club review")
    public ResponseEntity<ClubReviewDTO> update(@Parameter(name = "clubReviewDTO") @RequestBody ClubReviewDTO clubReviewDTO){
        return new ResponseEntity<>(service.update(clubReviewDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all club review")
    public ResponseEntity<List<ClubReviewDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{clubReviewId}")
    @Operation(summary = "Find club review by id")
    public ResponseEntity<ClubReviewDTO> findById(@PathVariable Long clubReviewId) {
        return new ResponseEntity<>(service.findById(clubReviewId), HttpStatus.OK);
    }

}
