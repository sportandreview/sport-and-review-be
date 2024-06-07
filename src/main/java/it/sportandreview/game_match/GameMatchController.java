package it.sportandreview.game_match;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.response.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "gameMatchDTO") @Valid @RequestBody GameMatchDTO gameMatchDTO) {
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("GameMatch creato con successo")
                .result(service.create(gameMatchDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update game match")
    public ResponseEntity<ApiResponseDTO<GameMatchDTO>> update(@Parameter(name = "gameMatchDTO") @Valid @RequestBody GameMatchDTO gameMatchDTO) {
        ApiResponseDTO<GameMatchDTO> response = ApiResponseDTO.<GameMatchDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("GameMatch aggiornato con successo")
                .result(service.update(gameMatchDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{gameMatchId}/{playerUserId}")
    @Operation(summary = "Delete game match by id")
    public ResponseEntity<ApiResponseDTO<Void>> delete(@PathVariable Long gameMatchId, @PathVariable Long playerUserId) {
        service.deleteById(gameMatchId, playerUserId);
        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
                .status(HttpServletResponse.SC_OK)
                .message("GameMatch eliminato con successo")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byPlayerUserId/{playerUserId}")
    @Operation(summary = "Find player user by id")
    public ResponseEntity<ApiResponseDTO<List<GameMatchDTO>>> findByPlayerUserId(@PathVariable Long playerUserId) {
        ApiResponseDTO<List<GameMatchDTO>> response = ApiResponseDTO.<List<GameMatchDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di GameMatch per il PlayerUser selezionato")
                .result(service.findByPlayerUserId(playerUserId))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/newBooking")
    @Operation(summary = "Create new booking")
    public ResponseEntity<ApiResponseDTO<Long>> createBooking(@Parameter(name = "bookingGameMatchDTO") @RequestBody BookingGameMatchDTO bookingGameMatchDTO) {
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di GameMatch per il PlayerUser selezionato")
                .result(service.createBooking(bookingGameMatchDTO))
                .build();
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Find all game match")
    @GetMapping()
    public ResponseEntity<ApiResponseDTO<List<ReducedGameMatchDTO>>> findAll(@Parameter(name = "clubCity") String clubCity, @Parameter(name = "clubName") String clubName,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "date") LocalDate date,
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)@Parameter(name = "time") LocalTime time,
                                                             @Parameter(name = "sportId") Long sportId, @Parameter(name = "genderTeamId") Long genderTeamId,
                                                             @Parameter(name = "gameLevelId") Long gameLevelId, @Parameter(name = "playerUserId") Long playerUserId) {
        ApiResponseDTO<List<ReducedGameMatchDTO>> response = ApiResponseDTO.<List<ReducedGameMatchDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di tutti i GameMatch")
                .result(service.findAll(clubCity, clubName, date, time, sportId, genderTeamId, gameLevelId, playerUserId))
                .build();
        return ResponseEntity.ok(response);
    }
}
