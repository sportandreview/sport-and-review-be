package it.sportandreview.sport;

import java.util.List;

public interface SportService {

    Long create(SportDTO sportDto);

    SportDTO update(SportDTO sportDto);

    List<SportDTO> findAll();
    
}
