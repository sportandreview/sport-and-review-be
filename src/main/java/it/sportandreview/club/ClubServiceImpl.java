package it.sportandreview.club;

import it.sportandreview.booked_slot.BookedSlotService;
import it.sportandreview.booked_slot.ReducedBookedSlotDTO;
import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.field.*;
import it.sportandreview.services.ServicesMapper;
import it.sportandreview.slot.FieldSlotsDTO;
import it.sportandreview.slot.SlotDTO;
import it.sportandreview.slot_planning.SlotPlanningService;
import it.sportandreview.sport.SportEnum;
import it.sportandreview.utils.Sha256Utils;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;



@Service
@Log4j2
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;
    private final FieldService fieldService;
    private final ServicesMapper servicesMapper;
    private final FieldMapper fieldMapper;
    private final SlotPlanningService slotPlanningService;
    private final BookedSlotService bookedSlotService;


    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, ClubMapper clubMapper, FieldService fieldService, ServicesMapper servicesMapper,
                           FieldMapper fieldMapper, SlotPlanningService slotPlanningService, BookedSlotService bookedslotService) {
        this.clubRepository = clubRepository;
        this.clubMapper = clubMapper;
        this.fieldService = fieldService;
        this.servicesMapper = servicesMapper;
        this.fieldMapper = fieldMapper;
        this.slotPlanningService = slotPlanningService;
        this.bookedSlotService = bookedslotService;
    }

    @Override
    public Long create(ClubDTO clubDto) {
        if (Objects.nonNull(clubDto.getId()) && clubRepository.existsById(clubDto.getId())){
            throw new CreateEntityException("Club", "Club" + " exists into database");
        }
        // Setting Club UUID
        String uuid = StringUtils.isBlank(clubDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : clubDto.getUuid();
        clubDto.setUuid(uuid);
        Club club = clubRepository.save(clubMapper.toEntity(clubDto));
        log.debug("Club created");
        return club.getId();
    }

    @Override
    public ClubDTO update(ClubDTO clubDto) {
        if (Objects.isNull(clubDto.getId()) || !clubRepository.existsById(clubDto.getId())) {
            throw new NotFoundException("Club", "Club" + " not found");
        }

        Club saved = clubRepository.save(clubMapper.toEntity(clubDto));
        log.debug("Club updated");

        ClubDTO savedDTO = clubMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    public PagingClubDTO findAll(Integer pageSize, Integer pageNumber, String city, Long sportId, LocalDate date, LocalTime time, Boolean preferred,
                                 String name, String selectedService, Long brandId, String dimension, Long playerUserId, String footballGoal, Long fieldId, String fieldSizeDimension) {
        Set<Long> selectedServices = Objects.nonNull(selectedService) ? Arrays.stream(selectedService.split(","))
                .map(selectedServiceId -> Long.getLong(selectedServiceId)).collect(Collectors.toSet()) : null;
        Set<String> dimensions = Objects.nonNull(dimension) ? Arrays.stream(dimension.split(",")).collect(Collectors.toSet()) : null;
        Set<String> footballGoals = Objects.nonNull(footballGoal) ? Arrays.stream(footballGoal.split(",")).collect(Collectors.toSet()) : null;
        Set<String> fieldSizeDimensions = Objects.nonNull(fieldSizeDimension) ? Arrays.stream(dimension.split(",")).collect(Collectors.toSet()) : null;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        List<ClubDTO> clubs =  getAllBookableSlotsForField(city, sportId, date, time, preferred, name, selectedServices, brandId, dimensions, playerUserId, footballGoals, fieldId, fieldSizeDimensions, pageable);
        return PagingClubDTO.builder().pageSize(pageSize).clubs(clubs).build();
    }
    @Override
    public ClubDTO findById(Long id) {
        return clubRepository.findById(id).map(clubMapper::toDto).
                orElseThrow(() -> new NotFoundException("Club", "Club" + "not exists into database"));
    }
    @Override
    public FieldSlotsDTO getAllBookableSlots(LocalDate date, Long fieldId, LocalTime time) {
        List<SlotDTO> slotPlanningList = slotPlanningService.findByDateAndField(date, fieldId);
        ReducedBookedSlotDTO reducedBookedSlotDTO = bookedSlotService.findByDateAndField(date, fieldId);
        if (Boolean.TRUE.equals(reducedBookedSlotDTO.getAllDay())) {
            List<SlotDTO> slots = reducedBookedSlotDTO.getSlots();
            slots.addAll(slotPlanningList);
            return FieldSlotsDTO.builder().bookedSlotList(slots).build();
        } else {
            List<SlotDTO> slots = new ArrayList<>(slotPlanningList);
            slots.removeAll(reducedBookedSlotDTO.getSlots());
            slots = getAllSlotsForTime(time, slots);
            return FieldSlotsDTO.builder().bookedSlotList(reducedBookedSlotDTO.getSlots()).bookableSlotList(slots).build();
        }
    }

    private Page<Club> findAllAndFilterByPredicates(@NotNull String city, @NotNull Long sportId, @NotNull Boolean preferred, @NotNull String name,
                                                    @NotNull Set<Long> selectedServices, @NotNull Long brandId, @NotNull Set<String> dimensions,
                                                    @NotNull Long playerUserId, @NotNull Set<String> footballGoals, @NotNull Long fieldId, @NotNull Set<String> fieldSizeDimensions, Pageable pageable) {

        Specification<Club> citySpecification = Objects.nonNull(city) ? ClubSpecifications.cityEquals(city) : ClubSpecifications.NULL_SPECIFICATION;
        Specification<Club> sportSpecification = Objects.nonNull(sportId) ? ClubSpecifications.sportEquals(sportId) : ClubSpecifications.NULL_SPECIFICATION;
        Specification<Club> preferredSpecification = Boolean.TRUE.equals(preferred) ? ClubSpecifications.filterByPreferred(playerUserId) : ClubSpecifications.NULL_SPECIFICATION;
        Specification<Club> nameSpecification = Objects.nonNull(name) ? ClubSpecifications.filterByName(name) : ClubSpecifications.NULL_SPECIFICATION;
        Specification<Club> servicesSpecification = Objects.nonNull(selectedServices) ? ClubSpecifications.filterByServices(selectedServices) : ClubSpecifications.NULL_SPECIFICATION;
        Specification<Club> specificationByServices = Objects.nonNull(sportId) && Objects.nonNull(brandId) && Objects.nonNull(dimensions) &&
                (sportId.equals(SportEnum.CALCIO.getId()) || sportId.equals(SportEnum.CALCETTO.getId()) ||
                        sportId.equals(SportEnum.CALCIOTTO.getId())) ? ClubSpecifications.filterByServicesBySportEquipment(brandId,dimensions) :
                ClubSpecifications.NULL_SPECIFICATION;
        Specification<Club> footballGoalsSpecification = Objects.nonNull(sportId) && sportId.equals(SportEnum.CALCETTO.getId()) ? ClubSpecifications.filterByFootballGoalSize(footballGoals).and(specificationByServices) :
                ClubSpecifications.NULL_SPECIFICATION;
        Specification<Club> specificationByFieldSize = Objects.nonNull(fieldId) ? ClubSpecifications.filterByFieldSize(fieldId, fieldSizeDimensions) : ClubSpecifications.NULL_SPECIFICATION;
        Specification<Club> finalSpec = Specification.where(citySpecification).and(sportSpecification)
                .and(preferredSpecification).and(nameSpecification).and(servicesSpecification).and(specificationByServices).and(footballGoalsSpecification).and(specificationByFieldSize) ;


        Page<Club> clubs = clubRepository.findAll(finalSpec, pageable);
        return clubs;
    }

    private List<ClubDTO> getAllBookableSlotsForField(String city, Long sportId,LocalDate date, LocalTime time, Boolean preferred,
                                                      String name, Set<Long> selectedServices, Long brandId, Set<String> dimensions, Long playerUserId,
                                                      Set<String> footballGoals, Long fieldId, Set<String> fieldSizeDimensions, Pageable pageable) {
        List<Club> clubs = findAllAndFilterByPredicates(city, sportId, preferred,
                name, selectedServices, brandId, dimensions, playerUserId, footballGoals, fieldId, fieldSizeDimensions, pageable).getContent();
        if (Objects.nonNull(date)) {
            List<ClubDTO> result = new ArrayList<>();
            for (Club club : clubs) {
                Set<FieldReducedDTO> fieldsReduced = new HashSet<>();

                for (Field field : club.getFields()) {
                    FieldSlotsDTO slots = getAllBookableSlots(date, field.getId(), time);
                    if (Objects.nonNull(slots.getBookableSlotList()) && !slots.getBookableSlotList().isEmpty()) {
                        FieldReducedDTO fieldReducedDTO = fieldMapper.toDtoReduced(field);
                        fieldReducedDTO.setBookableSlotList(slots.getBookableSlotList());
                        fieldReducedDTO.setBookedSlotList(slots.getBookedSlotList());
                        fieldsReduced.add(fieldReducedDTO);
                    }
                }
                if (!fieldsReduced.isEmpty()) {
                    ClubDTO clubDTO = clubMapper.toDto(club);
                    Comparator<FieldReducedDTO> fieldComparator = (field1, field2) -> Double.compare(field2.getRating(), field1.getRating());
                    clubDTO.setFields(fieldsReduced.stream().sorted(fieldComparator).collect(Collectors.toCollection(LinkedHashSet::new)));
                    result.add(clubDTO);
                }
            }
            return result;
        }
       return clubMapper.toDto(clubs) ;
    }

    private List<SlotDTO> getAllSlotsForTime(LocalTime time, List<SlotDTO> slotsList) {
        if (Objects.nonNull(time)) {
           List<SlotDTO> slots = slotsList.stream().filter(slot -> isTimeInRange(time, slot.getTime(), slot.getDurationSlot()))
                   .collect(Collectors.toList());
            return slots;
        }
        return slotsList;
    }

    private static boolean isTimeInRange(LocalTime targetTime, LocalTime startTime, LocalTime duration) {
        LocalTime endTime = startTime.plusHours(duration.getHour()).plusMinutes(duration.getMinute());
        return !targetTime.isBefore(startTime) && targetTime.isBefore(endTime);
    }




}
