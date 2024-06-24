package it.sportandreview.service.impl;

import it.sportandreview.entity.Sport;
import it.sportandreview.exception.EntityNotFoundException;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportServiceImpl implements SportService {

    private final SportRepository sportRepository;
    private final MessageSource messageSource;

    @Override
    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    @Override
    public Sport getSportById(Long id) {
        return sportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("sport.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));
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
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("sport.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteSport(Long id) {
        if (!sportRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    messageSource.getMessage("sport.not.found", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
        sportRepository.deleteById(id);
    }
}
