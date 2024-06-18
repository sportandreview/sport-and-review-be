package it.sportandreview.service;

import it.sportandreview.entity.Sport;

import java.util.List;

public interface SportService {
    List<Sport> getAllSports();
    Sport getSportById(Long id);
    Sport createSport(Sport sport);
    Sport updateSport(Long id, Sport sportDetails);
    void deleteSport(Long id);
}
