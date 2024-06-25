package it.sportandreview.initializer;

import it.sportandreview.enums.SportType;
import it.sportandreview.entity.Sport;
import it.sportandreview.repository.SportRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SportDataInitializer {

    private final SportRepository sportRepository;

    @Autowired
    public SportDataInitializer(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @PostConstruct
    public void init() {
        if (sportRepository.count() == 0) {
            List<Sport> sports = Arrays.asList(
                    new Sport(4, SportType.PADEL, 90),
                    new Sport(4, SportType.TENNIS, 60),
                    new Sport(22, SportType.CALCIO, 90)
            );

            List<Sport> existingSports = sportRepository.findAll();
            List<Sport> newSports = sports.stream()
                    .filter(sport -> existingSports.stream()
                            .noneMatch(existingSport -> existingSport.getSportType().equals(sport.getSportType())))
                    .collect(Collectors.toList());

            if (!newSports.isEmpty()) {
                sportRepository.saveAll(newSports);
                log.info("Sports data initialized: {}", newSports);
            } else {
                log.info("All sports data already initialized.");
            }
        }
    }
}
