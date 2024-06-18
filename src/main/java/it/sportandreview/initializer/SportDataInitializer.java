package it.sportandreview.initializer;

import it.sportandreview.enums.SportType;
import it.sportandreview.entity.Sport;
import it.sportandreview.repository.SportRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
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
                    new Sport(4, SportType.TENNIS),
                    new Sport(22, SportType.CALCIO),
                    new Sport(10, SportType.BASKET),
                    new Sport(10, SportType.CALCIOA5),
                    new Sport(14, SportType.CALCETTO),
                    new Sport(16, SportType.CALCIOTTO)
            );
            sportRepository.saveAll(sports);
        }
    }
}
