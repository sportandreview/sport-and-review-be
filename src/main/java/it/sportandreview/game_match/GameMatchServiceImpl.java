package it.sportandreview.game_match;

import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevel;
import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelRepository;
import it.sportandreview.booked_slot.BookedSlot;
import it.sportandreview.booked_slot.BookedSlotDTO;
import it.sportandreview.booked_slot.BookedSlotService;
import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.field.FieldDTO;
import it.sportandreview.field.FieldMapper;
import it.sportandreview.field.FieldRepository;
import it.sportandreview.match_state.*;
import it.sportandreview.payment.*;
import it.sportandreview.payment_type.PaymentTypeDTO;
import it.sportandreview.payment_type.PaymentTypeMapper;
import it.sportandreview.payment_type.PaymentTypeRepository;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.player_user.PlayerUserMapper;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.services.ServicesMapper;
import it.sportandreview.services.ServicesRepository;
import it.sportandreview.slot_planning.SlotPlanningDTO;
import it.sportandreview.slot_planning.SlotPlanningMapper;
import it.sportandreview.slot_planning.SlotPlanningRepository;
import it.sportandreview.team.TeamRepository;
import it.sportandreview.user.User;
import it.sportandreview.user.UserRepository;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.*;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;



@Service
@Log4j2
public class GameMatchServiceImpl implements GameMatchService {

    private final GameMatchRepository gameMatchRepository;
    private final GameMatchMapper gameMatchMapper;
    private final MatchStateService matchStateService;
    private final UserRepository userRepository;
    private final PlayerUserMapper playerUserMapper;
    private final TeamRepository teamRepository;
    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;
    private final ServicesRepository servicesRepository;
    private final ServicesMapper servicesMapper;
    private final PaymentService paymentService;
    private final MatchStateRepository matchStateRepository;
    private final MatchStateMapper matchStateMapper;
    private final BookedSlotService bookedSlotService;
    private final SlotPlanningRepository slotPlanningRepository;
    private final SlotPlanningMapper slotPlanningMapper;
    private final AutoEvaluationSportLevelRepository autoEvaluationSportLevelRepository;

    @Autowired
    public GameMatchServiceImpl(GameMatchRepository gameMatchRepository, GameMatchMapper gameMatchMapper,
                                MatchStateService matchStateService, UserRepository userRepository,
                                TeamRepository teamRepository, PlayerUserMapper playerUserMapper, FieldRepository fieldRepository,
                                FieldMapper fieldMapper, PaymentTypeRepository paymentTypeRepository, PaymentTypeMapper paymentTypeMapper,
                                ServicesRepository servicesRepository, ServicesMapper servicesMapper,
                                PaymentService paymentService, MatchStateRepository matchStateRepository, MatchStateMapper matchStateMapper,
                                BookedSlotService bookedSlotService, SlotPlanningRepository slotPlanningRepository,
                                SlotPlanningMapper slotPlanningMapper, AutoEvaluationSportLevelRepository autoEvaluationSportLevelRepository) {

        this.gameMatchRepository = gameMatchRepository;
        this.gameMatchMapper = gameMatchMapper;
        this.matchStateService = matchStateService;
        this.userRepository = userRepository;
        this.playerUserMapper = playerUserMapper;
        this.teamRepository = teamRepository;
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeMapper = paymentTypeMapper;
        this.servicesRepository = servicesRepository;
        this.servicesMapper = servicesMapper;
        this.paymentService = paymentService;
        this.matchStateRepository = matchStateRepository;
        this.matchStateMapper = matchStateMapper;
        this.bookedSlotService = bookedSlotService;
        this.slotPlanningRepository = slotPlanningRepository;
        this.slotPlanningMapper = slotPlanningMapper;
        this.autoEvaluationSportLevelRepository = autoEvaluationSportLevelRepository;
    }

    @Override
    public Long create(GameMatchDTO gameMatchDto) {
        if (Objects.nonNull(gameMatchDto.getId()) && gameMatchRepository.existsById(gameMatchDto.getId())) {
            throw new CreateEntityException("game match", "GameMatch" + " exists into database");
        }
        // Setting GameMatch UUID
        String uuid = StringUtils.isBlank(gameMatchDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : gameMatchDto.getUuid();
        gameMatchDto.setUuid(uuid);
        bookingToAvailableSlot(gameMatchDto.getDate(), gameMatchDto.getField().getId(), gameMatchDto.getSlots());
        GameMatch gameMatch = gameMatchRepository.save(gameMatchMapper.toEntity(gameMatchDto));
        List<BookedSlotDTO> slots = gameMatchDto.getSlots().stream().map(slotId -> BookedSlotDTO.builder().gameMatchId(gameMatch.getId()).
                slotId(slotId).fieldId(gameMatchDto.getField().getId()).startDate(gameMatchDto.getDate().atStartOfDay()).endDate(gameMatchDto.getDate().atStartOfDay())
                .allDay(Boolean.FALSE).uuid(Sha256Utils.getEncodedRandomUUID()).build()).collect(Collectors.toList());
        bookedSlotService.saveAll(slots);
        log.debug("Game Match created");
        return gameMatch.getId();
    }

    @Override
    public GameMatchDTO update(GameMatchDTO gameMatchDto) {
        if (Objects.isNull(gameMatchDto.getId()) || !gameMatchRepository.existsById(gameMatchDto.getId())) {
            throw new NotFoundException("game match", "GameMatch" + " not found");
        }
        GameMatch saved = gameMatchRepository.save(gameMatchMapper.toEntity(gameMatchDto));
        log.debug("Game Match updated");
        GameMatchDTO savedDTO = gameMatchMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    public void deleteById(Long gameMatchId, Long playerUserId) {
        GameMatch gameMatch = gameMatchRepository.findById(gameMatchId).
                orElseThrow(() -> new NotFoundException("game match", "GameMatch" + "not exists into database"));
        if ((playerUserId.equals(gameMatch.getOrganizer().getId()))) {
            gameMatch.setState(matchStateService.findById(MatchStateEnum.DENIED.getId()));
            gameMatchRepository.save(gameMatch);
        } else {
            gameMatch.getTeams().forEach(team -> {
                Set<User> players = team.getPlayers();
                User playerUser = userRepository.findById(playerUserId).
                        orElseThrow(() -> new NotFoundException("player user", "PlayerUser" + "not exists into database"));
                if (players.contains(playerUser)) {
                    players.remove(playerUser);
                    team.setPlayers(players);
                    teamRepository.save(team);
                }
            });
        }
    }

    @Override
    public PagingGameMatchDTO findByMatchStateIdPaging(Long stateId, Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("dateGame"));
        Page<GameMatch> page = gameMatchRepository.findByStateId(stateId, pageable);
        List<GameMatch> matches = page.getContent();
        return PagingGameMatchDTO.builder().pageSize(pageSize).
                gameMatches(matches.stream().map(gameMatchMapper::toDto).collect(Collectors.toList())).build();
    }

    @Override
    public Long countByMatchStateId(Long stateId) {
        return gameMatchRepository.countByStateId(stateId);
    }

    @Override
    public List<GameMatchDTO> findByPlayerUserId(Long playerUserId) {
        List<GameMatch> matches = gameMatchRepository.findByPlayerUserIdOrganizerOrPlayer(playerUserId);
        return matches.stream().map(gameMatchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GameMatchDTO findById(Long gameMatchId) {
        return gameMatchRepository.findById(gameMatchId).map(gameMatchMapper::toDto).
                orElseThrow(() -> new NotFoundException("game match", "GameMatch" + "not exists into database"));
    }

    @Override
    public Long createBooking(BookingGameMatchDTO bookingGameMatchDTO) {
        FieldDTO field = fieldRepository.findById(bookingGameMatchDTO.getFieldId()).map(fieldMapper::toDto).
                orElseThrow(() -> new NotFoundException("field", "Field" + "not exists into database"));
        PaymentTypeDTO paymentTypeDTO = paymentTypeRepository.findById(bookingGameMatchDTO.getPaymentTypeId()).map(paymentTypeMapper::toDto).
                orElseThrow(() -> new NotFoundException("paymentType", "PaymentType" + "not exists into database"));
        Set<ServicesDTO> servicesDTO = servicesRepository.findAllById(bookingGameMatchDTO.getServices()).stream().map(servicesMapper::toDto)
                .collect(Collectors.toSet());
        PlayerUserDTO playerUserDTO = userRepository.findById(bookingGameMatchDTO.getPlayerUserId()).map(playerUserMapper::toDto).
                orElseThrow(() -> new NotFoundException("playerUser", "PlayerUser" + "not exists into database"));
        PaymentDTO paymentDTO = PaymentDTO.builder().amount(bookingGameMatchDTO.getAmount()).paymentType(paymentTypeDTO).build();
        PaymentDTO payment = paymentService.create(paymentDTO);
        MatchStateDTO matchState = matchStateRepository.findById(MatchStateEnum.BOOKED.getId()).map(matchStateMapper::toDto).
                orElseThrow(() -> new NotFoundException("matchState", "MatchState" + "not exists into database"));
        GameMatchDTO gameMatchDTO = GameMatchDTO.builder().field(field).date(bookingGameMatchDTO.getDate()).payment(payment).services(servicesDTO)
                .organizer(playerUserDTO).state(matchState).slots(bookingGameMatchDTO.getSlots())
                .build();
        return create(gameMatchDTO);
    }

    @Override
    public List<ReducedGameMatchDTO> findAll(String clubCity, String clubName, LocalDate date, LocalTime time, Long sportId, Long genderTeamId,
                                             Long gameLevelId, Long playerUserId) {
        List<ReducedGameMatchDTO> gameMatchesList = sortingGameMatches(clubCity, clubName, date, time, sportId, genderTeamId,
                gameLevelId, playerUserId);
        return gameMatchesList;
    }

    private void bookingToAvailableSlot(LocalDate date, Long fieldId, Set<Long> slots) {
        if (slots.isEmpty()) {
            throw new CreateEntityException("game match", "GameMatch" + " impossibile effettuare una prenotazione senza slot");
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String daysOfWeek = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ITALIAN);
        List<SlotPlanningDTO> slotPlannings = slotPlanningRepository.findByDateAndDayOfWeek(date, daysOfWeek, fieldId).stream()
                .map(slotPlanningMapper::toDto).collect(Collectors.toList());
        if (slotPlannings.isEmpty()) {
            throw new CreateEntityException("game match", "GameMatch" + " Impossibile effettuare la prenotazione perchè la pianificazione non esiste" +
                    " in quella data");
        }
        slotPlannings.forEach(slotPlanning -> {
            if (!slotPlanning.getSlots().contains(slots)) {
                throw new CreateEntityException("game match", "GameMatch" + " Gli slot inseriti non sono validi");
            }
        });
    }

    private List<GameMatch> findAllAndFilterByPredicates(String clubCity, String clubName, LocalDate date,
                                                         LocalTime time, Long sportId, Long genderTeamId,
                                                         Long gameLevelId) {
        Specification<GameMatch> clubCitySpecification = Objects.nonNull(clubCity) ? GameMatchSpecifications.filterByCity(clubCity) : GameMatchSpecifications.NULL_SPECIFICATION;
        Specification<GameMatch> clubNameSpecification = Objects.nonNull(clubName) ? GameMatchSpecifications.filterByClubName(clubName) : GameMatchSpecifications.NULL_SPECIFICATION;
        Specification<GameMatch> filterByDateSpecification = Objects.nonNull(date) ? GameMatchSpecifications.filterByDate(date) : GameMatchSpecifications.NULL_SPECIFICATION;
        Specification<GameMatch> filterByTimeSpecification = Objects.nonNull(time) ? GameMatchSpecifications.filterByTime(time) : GameMatchSpecifications.NULL_SPECIFICATION;
        Specification<GameMatch> filterBySportIdSpecification = Objects.nonNull(sportId) ? GameMatchSpecifications.filterBySportId(sportId) : GameMatchSpecifications.NULL_SPECIFICATION;
        Specification<GameMatch> filterByGenderTypeId = Objects.nonNull(genderTeamId) ? GameMatchSpecifications.filterByGenderTypeId(genderTeamId) : GameMatchSpecifications.NULL_SPECIFICATION;
        Specification<GameMatch> filterByGameLevelId = Objects.nonNull(gameLevelId) ? GameMatchSpecifications.filterByGameLevelId(gameLevelId) : GameMatchSpecifications.NULL_SPECIFICATION;
        Specification<GameMatch> withMissingPlayers = GameMatchSpecifications.withMissingPlayers();
        Specification<GameMatch> withStateId = GameMatchSpecifications.withStateId(MatchStateEnum.CREATED.getId());
        Specification<GameMatch> finalSpec = Specification.where(clubCitySpecification).and(clubNameSpecification)
                .and(filterByDateSpecification).and(filterByTimeSpecification).and(filterBySportIdSpecification).and(filterByGenderTypeId)
                .and(withMissingPlayers).and(withStateId).and(filterByGameLevelId);
        List<GameMatch> gameMatches = gameMatchRepository.findAll(finalSpec);
        return gameMatches;
    }

    private List<ReducedGameMatchDTO> sortingGameMatches(String clubCity, String clubName, LocalDate date,
                                                         LocalTime time, Long sportId, Long genderTeamId,
                                                         Long gameLevelId, Long playerUserId) {
        List<AutoEvaluationSportLevel> autoEvaluationSportLevels = autoEvaluationSportLevelRepository.findByAutoEvaluationPlayerUserIdOrderByLevelDesc(playerUserId);
        Map<Long, Double> sportLevelMap = autoEvaluationSportLevels.stream()
                .collect(Collectors.toMap(
                        autoEvaluationSportLevel -> autoEvaluationSportLevel.getSport().getId(),
                        AutoEvaluationSportLevel::getLevel
                ));
        List<GameMatch> gameMatches = findAllAndFilterByPredicates(clubCity, clubName, date, time, sportId, genderTeamId, gameLevelId);
        List<ReducedGameMatchDTO> list = gameMatches.stream()
                .sorted(Comparator.comparingInt(
                                gameMatch -> ((GameMatch) gameMatch).getTotalPlayers() - ((GameMatch) gameMatch).getTeams().stream().mapToInt(team -> team.getPlayers().size()).sum())
                        .thenComparingDouble(gameMatch -> sportLevelMap.getOrDefault(((GameMatch) gameMatch).getField().getSport().getId(), 0.0))
                        .reversed())
                .map(gameMatchMapper::toDtoReduced)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public void checkTimeForGameMatch(Long gameMatchId) {
        GameMatchDTO gameMatch = findById(gameMatchId);
        if (Objects.nonNull(gameMatch)) {
            LocalDateTime currentTime = LocalDateTime.now();
            List<BookedSlot> bookedSlots = bookedSlotService.getBookedSlotsByGameId(gameMatchId);
            if (!bookedSlots.isEmpty()) {
                LocalDateTime startDateTime = bookedSlots.get(0).getStartDate();
                long hoursSinceBooking = Duration.between(startDateTime, currentTime).toHours();
                if (hoursSinceBooking <= 25) {
                    log.info("correct generation review");
                } else {
                    throw new CreateEntityException("Errore", "Non è più possibile votare per quella partita");
                }
            }
        }
    }
}
