package it.sportandreview.service.impl;

import it.sportandreview.dto.request.PlaygroundRequestDTO;
import it.sportandreview.dto.response.PlaygroundResponseDTO;
import it.sportandreview.entity.Playground;
import it.sportandreview.entity.Sport;
import it.sportandreview.entity.SportFacility;
import it.sportandreview.entity.TimeSlot;
import it.sportandreview.exception.EntityNotFoundException;
import it.sportandreview.mapper.PlaygroundMapper;
import it.sportandreview.repository.PlaygroundRepository;
import it.sportandreview.repository.SportFacilityRepository;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaygroundServiceImpl implements PlaygroundService {

    private final PlaygroundRepository playgroundRepository;
    private final SportFacilityRepository sportFacilityRepository;
    private final SportRepository sportRepository;
    private final PlaygroundMapper playgroundMapper;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public PlaygroundResponseDTO createPlayground(Long facilityId, PlaygroundRequestDTO request) {
        log.info("Creating new playground for facility with ID: {}", facilityId);

        SportFacility sportFacility = getSportFacility(facilityId);
        Sport sport = getSport(request.getSportId());

        Playground playground = createPlaygroundEntity(request, sportFacility, sport);
        Set<TimeSlot> timeSlots = generateTimeSlots(playground, sport.getSlotDurationMinutes());
        playground.setTimeSlots(timeSlots);

        Playground savedPlayground = playgroundRepository.save(playground);
        log.info("Playground created successfully with ID: {}", savedPlayground.getId());

        return playgroundMapper.toDto(savedPlayground);
    }

    private SportFacility getSportFacility(Long facilityId) {
        return sportFacilityRepository.findById(facilityId)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("sportfacility.not.found", new Object[]{facilityId}, LocaleContextHolder.getLocale())));
    }

    private Sport getSport(Long sportId) {
        return sportRepository.findById(sportId)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("sport.not.found", new Object[]{sportId}, LocaleContextHolder.getLocale())));
    }

    private Playground createPlaygroundEntity(PlaygroundRequestDTO request, SportFacility sportFacility, Sport sport) {
        Playground playground = playgroundMapper.toEntity(request);
        playground.setSportFacility(sportFacility);
        playground.setSport(sport);
        return playground;
    }

    private Set<TimeSlot> generateTimeSlots(Playground playground, int slotDurationMinutes) {
        Set<TimeSlot> timeSlots = new HashSet<>();
        LocalTime currentTime = playground.getOpeningTime();
        LocalTime closingTime = playground.getClosingTime();

        while (isWithinOperatingHours(currentTime, slotDurationMinutes, closingTime)) {
            timeSlots.add(createTimeSlot(currentTime, slotDurationMinutes, playground));
            currentTime = currentTime.plusMinutes(30); // Sovrapposizione di 30 minuti
        }

        return timeSlots;
    }

    private boolean isWithinOperatingHours(LocalTime currentTime, int slotDurationMinutes, LocalTime closingTime) {
        return currentTime.plusMinutes(slotDurationMinutes).isBefore(closingTime)
                || currentTime.plusMinutes(slotDurationMinutes).equals(closingTime);
    }

    private TimeSlot createTimeSlot(LocalTime startTime, int slotDurationMinutes, Playground playground) {
        return TimeSlot.builder()
                .startTime(startTime)
                .endTime(startTime.plusMinutes(slotDurationMinutes))
                .available(true)
                .playground(playground)
                .build();
    }
}
