package it.sportandreview.sport_point;


import java.util.List;

public interface SportPointService {

    Long create(SportPointDTO sportPointDto);

    SportPointDTO update(SportPointDTO sportPointDto);

    List<SportPointDTO> findAll();

}
