package it.sportandreview.configuration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.club.ClubDTO;
import it.sportandreview.club.ClubService;
import it.sportandreview.club.PagingClubDTO;
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
import it.sportandreview.user_otp.ResetPassword;
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
    public ResponseEntity<List<SportDTO>> findAllSports() {
        return new ResponseEntity<>(sportService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Find all sport points")
    @GetMapping("/sportPoints")
    public ResponseEntity<List<SportPointDTO>> findAllSportPoints() {
        return new ResponseEntity<>(sportPointService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Find all services by club")
    @GetMapping("/servicesByClub/{clubId}")
    public ResponseEntity<List<ServicesDTO>> findByClubId(@PathVariable List<Long> clubId) {
        return new ResponseEntity<>(servicesService.findByClubsId(clubId), HttpStatus.OK);
    }

    @Operation(summary = "Find all highlights")
    @GetMapping("/highlights")
    public ResponseEntity<List<HighlightDTO>> findAllHighlights() {
        return new ResponseEntity<>(highlightService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/highlights/{highlightId}")
    @Operation(summary = "Find highlight by id")
    public ResponseEntity<HighlightDTO> findHighlightById(@PathVariable Long highlightId) {
        return new ResponseEntity<>(highlightService.findById(highlightId), HttpStatus.OK);
    }

    @Operation(summary = "Find all game matches with page by state id")
    @GetMapping("/gameMatches/byMatchStateIdWithPage/{stateId}/{pageSize}/{pageNumber}")
    public ResponseEntity<PagingGameMatchDTO> findByMatchStateIdPaging(@PathVariable Long stateId, @PathVariable Integer pageSize, @PathVariable Integer pageNumber) {
        return new ResponseEntity<>(gameMatchservice.findByMatchStateIdPaging(stateId, pageSize, pageNumber), HttpStatus.OK);
    }

    @GetMapping("/gameMatches/countByMatchStateId/{stateId}")
    @Operation(summary = "Count game matches by state id")
    public ResponseEntity<Long> countByMatchStateId(@PathVariable Long stateId) {
        return new ResponseEntity<>(gameMatchservice.countByMatchStateId(stateId), HttpStatus.OK);
    }

    @GetMapping("/fields/{fieldId}")
    @Operation(summary = "Find field by id")
    public ResponseEntity<FieldDTO> findFieldById(@PathVariable Long fieldId) {
        return new ResponseEntity<>(fieldservice.findById(fieldId), HttpStatus.OK);
    }

    @Operation(summary = "Find all field by club")
    @GetMapping("/fields/byClubId/{clubId}")
    public ResponseEntity<List<FieldDTO>> findByClubId(@PathVariable Long clubId) {
        return new ResponseEntity<>(fieldservice.findByClubId(clubId), HttpStatus.OK);
    }

    @Operation(summary = "Find all clubs with page")
    @GetMapping("/clubs/{pageSize}/{pageNumber}")
    public ResponseEntity<PagingClubDTO> findAll(@PathVariable Integer pageSize, @PathVariable Integer pageNumber,
                                                 @Parameter(name = "city") String city, @Parameter(name = "sportId") Long sportId,
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(name = "date") LocalDate date,
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)@Parameter(name = "time") LocalTime time,
                                                 @Parameter(name = "preferred") Boolean preferred, @Parameter(name = "name") String name,
                                                 @Parameter(name = "selectedServices") String selectedServices, @Parameter(name = "brand") Long brand,
                                                 @Parameter(name = "dimensions") String dimensions, @Parameter(name = "playerUserId") Long playerUserId,
                                                 @Parameter(name = "footballGoals") String footballGoals, @Parameter(name = "fieldId") Long fieldId, @Parameter(name = "fieldSizeDimension") String fieldSizeDimension) {
        return new ResponseEntity<>(clubService.findAll(pageSize, pageNumber, city, sportId, date, time, preferred, name, selectedServices, brand, dimensions, playerUserId, footballGoals, fieldId, fieldSizeDimension), HttpStatus.OK);
    }

    @Operation(summary = "Find club by id")
    @GetMapping("/clubs/{clubId}")
    public ResponseEntity<ClubDTO> findClubById(@PathVariable Long clubId) {
        return new ResponseEntity<>(clubService.findById(clubId), HttpStatus.OK);
    }

    @Operation(summary = "Find all match states")
    @GetMapping("/matchStates")
    public ResponseEntity<List<MatchStateDTO>> findAllMatchStates() {
        return new ResponseEntity<>(matchStateService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/matchStates/{matchStateId}")
    @Operation(summary = "Find match state by id")
    public ResponseEntity<MatchState> findById(@PathVariable Long matchStateId) {
        return new ResponseEntity<>(matchStateService.findById(matchStateId), HttpStatus.OK);
    }

    @Operation(summary = "Find all game levels")
    @GetMapping("/gameLevels")
    public ResponseEntity<List<GameLevelDTO>> findAllGameLevels() {
        return new ResponseEntity<>(gameLevelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/gameLevels/{gameLevelId}")
    @Operation(summary = "Find game level by id")
    public ResponseEntity<GameLevelDTO> findGameLevelById(@PathVariable Long gameLevelId) {
        return new ResponseEntity<>(gameLevelService.findById(gameLevelId), HttpStatus.OK);
    }

    @Operation(summary = "Find all gender types")
    @GetMapping("/genderTypes")
    public ResponseEntity<List<GenderTypeDTO>> findAllGenderTypes() {
        return new ResponseEntity<>(genderTypeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/gameMatches/{gameMatchId}")
    @Operation(summary = "Find game match by id")
    public ResponseEntity<GameMatchDTO> findGameMatchById(@PathVariable Long gameMatchId) {
        return new ResponseEntity<>(gameMatchservice.findById(gameMatchId), HttpStatus.OK);
    }
    @GetMapping("/fields/{pageSize}/{pageNumber}")
    @Operation(summary = "Find all fields with page")
    public ResponseEntity<PagingFieldDTO> findAllFields(@PathVariable Integer pageSize, @PathVariable Integer pageNumber) {
        return new ResponseEntity<>(fieldService.findAll(pageSize, pageNumber), HttpStatus.OK);
    }

    @Operation(summary = "Find all services")
    @GetMapping("/services")
    public ResponseEntity<List<ServicesDTO>> findAllServices() {
        return new ResponseEntity<>(servicesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/slots")
    @Operation(summary = "")
    public ResponseEntity<FieldSlotsDTO> getAllBookableSlots(@Parameter(name = "date") LocalDate date, @Parameter(name = "fieldId") Long fieldId, @Parameter(name = "time") LocalTime time) {
        return new ResponseEntity<>(clubService.getAllBookableSlots(date, fieldId, time), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    @Operation(summary = "Change password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordRequest passwordRequest, @RequestParam String code) {
        resetPassword.changePassword(passwordRequest,code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
