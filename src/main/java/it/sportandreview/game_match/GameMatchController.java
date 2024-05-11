package it.sportandreview.game_match;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/gameMatches")
public class GameMatchController {

    private final GameMatchService service;

    @Autowired
    public GameMatchController(GameMatchService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new game match")
    public ResponseEntity<Long> create(@Parameter(name = "gameMatchDTO") @RequestBody GameMatchDTO gameMatchDTO) {
        return new ResponseEntity<>(service.create(gameMatchDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update game match")
    public ResponseEntity<GameMatchDTO> update(@Parameter(name = "gameMatchDTO") @RequestBody GameMatchDTO gameMatchDTO) {
        return new ResponseEntity<>(service.update(gameMatchDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{gameMatchId}/{playerUserId}")
    @Operation(summary = "Delete game match by id")
    public ResponseEntity<Void> delete(@PathVariable Long gameMatchId, @PathVariable Long playerUserId) {
        service.deleteById(gameMatchId, playerUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byPlayerUserId/{playerUserId}")
    @Operation(summary = "Find player user by id")
    public ResponseEntity<List<GameMatchDTO>> findByPlayerUserId(@PathVariable Long playerUserId) {
        return new ResponseEntity<>(service.findByPlayerUserId(playerUserId), HttpStatus.OK);
    }

    @PostMapping("/newBooking")
    @Operation(summary = "Create new booking")
    public ResponseEntity<Long> createBooking(@Parameter(name = "bookingGameMatchDTO") @RequestBody BookingGameMatchDTO bookingGameMatchDTO) {
        return new ResponseEntity<>(service.createBooking(bookingGameMatchDTO), HttpStatus.OK);
    }
    @Operation(summary = "Find all game match")
    @GetMapping()
    public ResponseEntity<List<ReducedGameMatchDTO>> findAll(@Parameter(name = "clubCity") String clubCity, @Parameter(name = "clubName") String clubName,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "date") LocalDate date,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)@Parameter(name = "time") LocalTime time,
                                                             @Parameter(name = "sportId") Long sportId, @Parameter(name = "genderTeamId") Long genderTeamId,
                                                             @Parameter(name = "gameLevelId") Long gameLevelId, @Parameter(name = "playerUserId") Long playerUserId) {

        return new ResponseEntity<>(service.findAll(clubCity, clubName, date, time, sportId, genderTeamId, gameLevelId, playerUserId), HttpStatus.OK);
    }
}
