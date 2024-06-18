package it.sportandreview.service.impl;

import it.sportandreview.exception.SportNotFoundException;
import it.sportandreview.entity.Sport;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportServiceImpl implements SportService {
    private final SportRepository sportRepository;

    @Autowired
    public SportServiceImpl(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @Override
    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    @Override
    public Sport getSportById(Long id) {
        return sportRepository.findById(id).orElseThrow(() -> new SportNotFoundException(id));
    }

    @Override
    public Sport createSport(Sport sport) {
        return sportRepository.save(sport);
    }

    @Override
    public Sport updateSport(Long id, Sport sportDetails) {
        return sportRepository.findById(id)
                .map(existingSport -> {
                    existingSport.setMaxPlayers(sportDetails.getMaxPlayers());
                    existingSport.setSportType(sportDetails.getSportType());
                    return sportRepository.save(existingSport);
                })
                .orElseThrow(() -> new SportNotFoundException(id));
    }

    @Override
    public void deleteSport(Long id) {
        if (!sportRepository.existsById(id)) {
            throw new SportNotFoundException(id);
        }
        sportRepository.deleteById(id);
    }
}
