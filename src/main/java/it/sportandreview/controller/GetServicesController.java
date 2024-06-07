package it.sportandreview.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.club.ClubDTO;
import it.sportandreview.club.ClubService;
import it.sportandreview.club.PagingClubDTO;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.field.FieldDTO;
import it.sportandreview.field.FieldService;
import it.sportandreview.field.PagingFieldDTO;
import it.sportandreview.game_level.GameLevelDTO;
import it.sportandreview.game_level.GameLevelService;
import it.sportandreview.game_match.GameMatchDTO;
import it.sportandreview.game_match.GameMatchService;
import it.sportandreview.game_match.PagingGameMatchDTO;
import it.sportandreview.gender_type.GenderTypeDTO;
import it.sportandreview.gender_type.GenderTypeService;
import it.sportandreview.highlight.HighlightDTO;
import it.sportandreview.highlight.HighlightService;
import it.sportandreview.match_state.MatchState;
import it.sportandreview.match_state.MatchStateDTO;
import it.sportandreview.match_state.MatchStateService;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.services.ServicesService;
import it.sportandreview.slot.FieldSlotsDTO;
import it.sportandreview.sport.SportDTO;
import it.sportandreview.sport.SportService;
import it.sportandreview.sport_point.SportPointDTO;
import it.sportandreview.sport_point.SportPointService;
import it.sportandreview.user.PasswordRequest;
import it.sportandreview.user.Role;
import it.sportandreview.user_otp.ResetPassword;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/getServices")
public class GetServicesController {
    private final SportService sportService;
    private final SportPointService sportPointService;
    private final ServicesService servicesService;
    private final HighlightService highlightService;
    private final GameMatchService gameMatchservice;
    private final FieldService fieldservice;
    private final ClubService clubService;
    private final MatchStateService matchStateService;
    private final GameLevelService gameLevelService;
    private final GenderTypeService genderTypeService;
    private final FieldService fieldService;
    private final ResetPassword resetPassword;

    public GetServicesController(SportService sportService, SportPointService sportPointService, ServicesService servicesService,
                                 HighlightService highlightService, GameMatchService gameMatchservice, FieldService fieldservice,
                                 ClubService clubService, MatchStateService matchStateService, GameLevelService gameLevelService,
                                 GenderTypeService genderTypeService, FieldService fieldService, ResetPassword resetPassword) {
        this.sportService = sportService;
        this.sportPointService = sportPointService;
        this.servicesService = servicesService;
        this.highlightService = highlightService;
        this.gameMatchservice = gameMatchservice;
        this.fieldservice = fieldservice;
        this.clubService = clubService;
        this.matchStateService = matchStateService;
        this.gameLevelService = gameLevelService;
        this.genderTypeService = genderTypeService;
        this.fieldService = fieldService;
        this.resetPassword = resetPassword;
    }

    @Operation(summary = "Find all sports")
    @GetMapping("/sports")
    public ResponseEntity<ApiResponseDTO<List<SportDTO>>> findAllSports() {
        ApiResponseDTO<List<SportDTO>> response = ApiResponseDTO.<List<SportDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di sport")
                .result(sportService.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all sport points")
    @GetMapping("/sportPoints")
    public ResponseEntity<ApiResponseDTO<List<SportPointDTO>>> findAllSportPoints() {
        ApiResponseDTO<List<SportPointDTO>> response = ApiResponseDTO.<List<SportPointDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di sport point")
                .result(sportPointService.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all services by club")
    @GetMapping("/servicesByClub/{clubId}")
    public ResponseEntity<ApiResponseDTO<List<ServicesDTO>>> findByClubId(@PathVariable List<Long> clubId) {
        ApiResponseDTO<List<ServicesDTO>> response = ApiResponseDTO.<List<ServicesDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di services for club")
                .result(servicesService.findByClubsId(clubId))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all highlights")
    @GetMapping("/highlights")
    public ResponseEntity<ApiResponseDTO<List<HighlightDTO>>> findAllHighlights() {
        ApiResponseDTO<List<HighlightDTO>> response = ApiResponseDTO.<List<HighlightDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di Highlight")
                .result(highlightService.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/highlights/{highlightId}")
    @Operation(summary = "Find highlight by id")
    public ResponseEntity<ApiResponseDTO<HighlightDTO>> findHighlightById(@PathVariable Long highlightId) {
        ApiResponseDTO<HighlightDTO> response = ApiResponseDTO.<HighlightDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Highlight per l'id richiesto")
                .result(highlightService.findById(highlightId))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all game matches with page by state id")
    @GetMapping("/gameMatches/byMatchStateIdWithPage/{stateId}/{pageSize}/{pageNumber}")
    public ResponseEntity<ApiResponseDTO<PagingGameMatchDTO>> findByMatchStateIdPaging(@PathVariable Long stateId, @PathVariable Integer pageSize, @PathVariable Integer pageNumber) {
        ApiResponseDTO<PagingGameMatchDTO> response = ApiResponseDTO.<PagingGameMatchDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Game match per l'id richiesto")
                .result(gameMatchservice.findByMatchStateIdPaging(stateId, pageSize, pageNumber))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/gameMatches/countByMatchStateId/{stateId}")
    @Operation(summary = "Count game matches by state id")
    public ResponseEntity<ApiResponseDTO<Long>> countByMatchStateId(@PathVariable Long stateId) {
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Game match per l'id richiesto")
                .result(gameMatchservice.countByMatchStateId(stateId))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fields/{fieldId}")
    @Operation(summary = "Find field by id")
    public ResponseEntity<ApiResponseDTO<FieldDTO>> findFieldById(@PathVariable Long fieldId) {
        ApiResponseDTO<FieldDTO> response = ApiResponseDTO.<FieldDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Field per l'id richiesto")
                .result(fieldService.findById(fieldId))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all field by club")
    @GetMapping("/fields/byClubId/{clubId}")
    public ResponseEntity<ApiResponseDTO<List<FieldDTO>>> findByClubId(@PathVariable Long clubId) {
        ApiResponseDTO<List<FieldDTO>> response = ApiResponseDTO.<List<FieldDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di field per l'id richiesto")
                .result(fieldService.findByClubId(clubId))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all clubs with page")
    @GetMapping("/clubs/{pageSize}/{pageNumber}")
    public ResponseEntity<ApiResponseDTO<PagingClubDTO>> findAll(@PathVariable Integer pageSize, @PathVariable Integer pageNumber,
                                                 @Parameter(name = "city") String city, @Parameter(name = "sportId") Long sportId,
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "date") LocalDate date,
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)@Parameter(name = "time") LocalTime time,
                                                 @Parameter(name = "preferred") Boolean preferred, @Parameter(name = "name") String name,
                                                 @Parameter(name = "selectedServices") String selectedServices, @Parameter(name = "brand") Long brand,
                                                 @Parameter(name = "dimensions") String dimensions, @Parameter(name = "playerUserId") Long playerUserId,
                                                 @Parameter(name = "footballGoals") String footballGoals, @Parameter(name = "fieldId") Long fieldId, @Parameter(name = "fieldSizeDimension") String fieldSizeDimension) {
        ApiResponseDTO<PagingClubDTO> response = ApiResponseDTO.<PagingClubDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di club in base al filtro selszionato")
                .result(clubService.findAll(pageSize, pageNumber, city, sportId, date, time, preferred, name, selectedServices, brand, dimensions, playerUserId, footballGoals, fieldId, fieldSizeDimension))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find club by id")
    @GetMapping("/clubs/{clubId}")
    public ResponseEntity<ApiResponseDTO<ClubDTO>> findClubById(@PathVariable Long clubId) {
        ApiResponseDTO<ClubDTO> response = ApiResponseDTO.<ClubDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di field per l'id richiesto")
                .result(clubService.findById(clubId))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all match states")
    @GetMapping("/matchStates")
    public ResponseEntity<ApiResponseDTO<List<MatchStateDTO>>> findAllMatchStates() {
        ApiResponseDTO<List<MatchStateDTO>> response = ApiResponseDTO.<List<MatchStateDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di match state")
                .result(matchStateService.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/matchStates/{matchStateId}")
    @Operation(summary = "Find match state by id")
    public ResponseEntity<ApiResponseDTO<MatchState>> findById(@PathVariable Long matchStateId) {
        ApiResponseDTO<MatchState> response = ApiResponseDTO.<MatchState>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di match state")
                .result(matchStateService.findById(matchStateId))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all game levels")
    @GetMapping("/gameLevels")
    public ResponseEntity<ApiResponseDTO<List<GameLevelDTO>>> findAllGameLevels() {
        ApiResponseDTO<List<GameLevelDTO>> response = ApiResponseDTO.<List<GameLevelDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di game level")
                .result(gameLevelService.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/gameLevels/{gameLevelId}")
    @Operation(summary = "Find game level by id")
    public ResponseEntity<ApiResponseDTO<GameLevelDTO>> findGameLevelById(@PathVariable Long gameLevelId) {
        ApiResponseDTO<GameLevelDTO> response = ApiResponseDTO.<GameLevelDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("game level per l'id richeisto ")
                .result(gameLevelService.findById(gameLevelId))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all gender types")
    @GetMapping("/genderTypes")
    public ResponseEntity<ApiResponseDTO<List<GenderTypeDTO>>> findAllGenderTypes() {
        ApiResponseDTO<List<GenderTypeDTO>> response = ApiResponseDTO.<List<GenderTypeDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di gendet type")
                .result(genderTypeService.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/gameMatches/{gameMatchId}")
    @Operation(summary = "Find game match by id")
    public ResponseEntity<ApiResponseDTO<GameMatchDTO>> findGameMatchById(@PathVariable Long gameMatchId) {
        ApiResponseDTO<GameMatchDTO> response = ApiResponseDTO.<GameMatchDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("game match per l'id richiesto")
                .result(gameMatchservice.findById(gameMatchId))
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/fields/{pageSize}/{pageNumber}")
    @Operation(summary = "Find all fields with page")
    public ResponseEntity<ApiResponseDTO<PagingFieldDTO>> findAllFields(@PathVariable Integer pageSize, @PathVariable Integer pageNumber) {
        ApiResponseDTO<PagingFieldDTO> response = ApiResponseDTO.<PagingFieldDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di campi")
                .result(fieldService.findAll(pageSize, pageNumber))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all services")
    @GetMapping("/services")
    public ResponseEntity<ApiResponseDTO<List<ServicesDTO>>> findAllServices() {
        ApiResponseDTO<List<ServicesDTO>> response = ApiResponseDTO.<List<ServicesDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Lista di servizi")
                .result(servicesService.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/slots")
    @Operation(summary = "")
    public ResponseEntity<ApiResponseDTO<FieldSlotsDTO>> getAllBookableSlots(@Parameter(name = "date") LocalDate date, @Parameter(name = "fieldId") Long fieldId, @Parameter(name = "time") LocalTime time) {
        ApiResponseDTO<FieldSlotsDTO> response = ApiResponseDTO.<FieldSlotsDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Slot prenotati")
                .result(clubService.getAllBookableSlots(date, fieldId, time))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/changePassword")
    @Operation(summary = "Change password")
    public ResponseEntity<ApiResponseDTO<Void>> changePassword(@RequestBody PasswordRequest passwordRequest, @RequestParam String code) {
        resetPassword.changePassword(passwordRequest,code);
        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
                .status(HttpServletResponse.SC_OK)
                .message("password cambiata con successo")
                .result(null)
                .build();
        return ResponseEntity.ok(response);
    }
}

