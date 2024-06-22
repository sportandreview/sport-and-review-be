package it.sportandreview.service.impl;

import it.sportandreview.dto.request.PlaygroundRequestDTO;
import it.sportandreview.dto.response.PlaygroundResponseDTO;
import it.sportandreview.entity.*;
import it.sportandreview.exception.SportFacilityNotFoundException;
import it.sportandreview.exception.SportNotFoundException;
import it.sportandreview.mapper.PlaygroundMapper;
import it.sportandreview.repository.PlaygroundRepository;
import it.sportandreview.repository.SportFacilityRepository;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlaygroundServiceImpl implements PlaygroundService {

    private final PlaygroundRepository playgroundRepository;
    private final SportFacilityRepository sportFacilityRepository;
    private final SportRepository sportRepository;
    private final PlaygroundMapper playgroundMapper;

    @Override
    @Transactional
    public PlaygroundResponseDTO createPlayground(Long facilityId, PlaygroundRequestDTO request) {
        SportFacility sportFacility = sportFacilityRepository.findById(facilityId)
                .orElseThrow(() -> new SportFacilityNotFoundException(facilityId));

        Sport sport = sportRepository.findById(request.getSportId())
                .orElseThrow(() -> new SportNotFoundException(request.getSportId()));

        Playground playground = playgroundMapper.toEntity(request);
        playground.setSportFacility(sportFacility);
        playground.setSport(sport);

        Set<TimeSlot> timeSlots = generateTimeSlots(sportFacility, playground, sport.getSlotDurationMinutes());
        playground.setTimeSlots(timeSlots);

        Playground savedPlayground = playgroundRepository.save(playground);
        return playgroundMapper.toDto(savedPlayground);
    }

    private Set<TimeSlot> generateTimeSlots(SportFacility sportFacility, Playground playground, int slotDurationMinutes) {
        Set<TimeSlot> timeSlots = new HashSet<>();
        LocalTime openingTime = sportFacility.getOpeningTime();
        LocalTime closingTime = sportFacility.getClosingTime();

        LocalTime currentTime = openingTime;
        while (currentTime.plusMinutes(slotDurationMinutes).isBefore(closingTime) || currentTime.plusMinutes(slotDurationMinutes).equals(closingTime)) {
            TimeSlot timeSlot = TimeSlot.builder()
                    .startTime(currentTime)
                    .endTime(currentTime.plusMinutes(slotDurationMinutes))
                    .available(true)
                    .playground(playground)
                    .build();
            timeSlots.add(timeSlot);
            currentTime = currentTime.plusMinutes(slotDurationMinutes);
        }

        return timeSlots;
    }
}
