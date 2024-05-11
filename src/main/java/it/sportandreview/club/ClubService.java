package it.sportandreview.club;


import it.sportandreview.slot.FieldSlotsDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ClubService {
    Long create(ClubDTO clubDto);

    ClubDTO update(ClubDTO clubDto);

    PagingClubDTO findAll(Integer pageSize, Integer pageNumber, String city, Long sportId, LocalDate date, LocalTime time, Boolean preferred,
                          String name, String selectedService, Long brand, String dimension, Long playerUserId, String footballGoal, Long fieldId, String fieldSizeDimension);

    ClubDTO findById(Long id);

    FieldSlotsDTO getAllBookableSlots(LocalDate date, Long fieldId, LocalTime time);

}
