package it.sportandreview.player_review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/playerReviews")
public class PlayerReviewController {

    private final PlayerReviewService service;

    @Autowired
    public PlayerReviewController(PlayerReviewService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new player review")
    public ResponseEntity<Long> create(@Parameter(name = "playerReviewDTO") @RequestBody PlayerReviewDTO playerReviewDTO){
        return new ResponseEntity<>(service.create(playerReviewDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all player review")
    public ResponseEntity<List<PlayerReviewDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{playerReviewId}")
    @Operation(summary = "Find player review by id")
    public ResponseEntity<PlayerReviewDTO> findById(@PathVariable Long playerReviewId) {
        return new ResponseEntity<>(service.findById(playerReviewId), HttpStatus.OK);
    }
}
