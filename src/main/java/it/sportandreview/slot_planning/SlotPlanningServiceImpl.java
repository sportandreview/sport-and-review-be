package it.sportandreview.slot_planning;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.slot.*;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SlotPlanningServiceImpl implements SlotPlanningService {

    private final SlotPlanningRepository slotPlanningRepository;
    private final SlotPlanningMapper slotPlanningMapper;
    private final SlotService slotService;
    @Autowired
    public SlotPlanningServiceImpl(SlotPlanningRepository slotPlanningRepository, SlotPlanningMapper slotPlanningMapper,
                                   SlotService slotService) {

        this.slotPlanningRepository = slotPlanningRepository;
        this.slotPlanningMapper = slotPlanningMapper;
        this.slotService = slotService;
    }

    @Override
    public Long create(SlotPlanningDTO slotPlanningDTO) {
        // Setting SlotPlanning UUID
        String uuid = StringUtils.isBlank(slotPlanningDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : slotPlanningDTO.getUuid();
        slotPlanningDTO.setUuid(uuid);
        if (Objects.nonNull(slotPlanningDTO.getId()) && slotPlanningRepository.existsById(slotPlanningDTO.getId())) {
            throw new CreateEntityException("slotPlanning", "slotPlanning" + " exists into database");
        }
        if (Objects.isNull(slotPlanningDTO.getFieldId())) {
            throw new CreateEntityException("slotPlanning", "field cannot be null");
        }
        slotPlanningDTO.setDaysOfWeek(slotPlanningDTO.getDaysOfWeek().toLowerCase());
        SlotPlanning slotPlanning = slotPlanningRepository.save(slotPlanningMapper.toEntity(slotPlanningDTO));
        Set<SlotDTO> slots = calculateSlots(slotPlanning);
        slotService.createAll(slots);
        log.debug("slotPlanning created");
        return slotPlanning.getId();
    }

    @Override
    public SlotPlanningDTO update(SlotPlanningDTO slotPlanningDTO) {
        if (Objects.isNull(slotPlanningDTO.getId()) || !slotPlanningRepository.existsById(slotPlanningDTO.getId())) {
            throw new NotFoundException("SlotPlanning", "SlotPlanning" + "not found");
        }
        if (Objects.isNull(slotPlanningDTO.getFieldId())) {
            throw new CreateEntityException("slotPlanning", "field cannot be null");
        }
        slotPlanningDTO.setDaysOfWeek(slotPlanningDTO.getDaysOfWeek().toLowerCase());
        SlotPlanning saved = slotPlanningRepository.save(slotPlanningMapper.toEntity(slotPlanningDTO));
        log.debug("SlotPlanning updated");
        SlotPlanningDTO savedDTO = slotPlanningMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    public List<SlotPlanningDTO> findAll() {
        return slotPlanningRepository.findAll().stream().map(slotPlanningMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SlotPlanningDTO findById(Long slotPlanningId) {
        return slotPlanningRepository.findById(slotPlanningId).map(slotPlanningMapper::toDto).
                orElseThrow(() -> new NotFoundException("slot planning", "SlotPlanning" + "not exists into database"));
    }

    @Override
    public List<SlotDTO> findByDateAndField(LocalDate date, Long fieldId) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String daysOfWeek = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ITALIAN);
        List<SlotPlanningDTO> slotPlannings = slotPlanningRepository.findByDateAndDayOfWeek(date, daysOfWeek.toLowerCase(), fieldId).stream()
                .map(slotPlanningMapper::toDto).collect(Collectors.toList());
        List<SlotDTO> slotList = new ArrayList<>();
        slotPlannings.forEach(planning -> {
            Set<SlotDTO> slotSet = planning.getSlots();
            slotList.addAll(slotSet);
        });
        return slotList;
    }

    private Set<SlotDTO> calculateSlots(SlotPlanning slotPlanning) {
        Set<SlotDTO> slots = new HashSet<>();
        LocalTime openingTime = slotPlanning.getOpeningTime();
        LocalTime closingTime = slotPlanning.getClosingTime();
        LocalTime durationSlot = slotPlanning.getDurationSlot();
        LocalTime currentSlotTime = openingTime;
        while (currentSlotTime.isBefore(closingTime)) {
            SlotDTO slot = SlotDTO.builder().time(currentSlotTime).durationSlot(durationSlot).planningId(slotPlanning.getId()).build();
            slots.add(slot);
            currentSlotTime = currentSlotTime.plusMinutes(durationSlot.getMinute()).plusHours(durationSlot.getHour());
        }
        return slots;
    }
}
