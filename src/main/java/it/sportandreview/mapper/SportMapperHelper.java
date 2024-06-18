package it.sportandreview.mapper;

import it.sportandreview.entity.Sport;
import it.sportandreview.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SportMapperHelper {

    @Autowired
    private SportRepository sportRepository;

    public Sport map(Long sportId) {
        return sportRepository.findById(sportId)
                .orElseThrow(() -> new RuntimeException("Sport not found"));
    }
}
