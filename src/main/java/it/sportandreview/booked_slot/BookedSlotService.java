package it.sportandreview.booked_slot;

import java.time.LocalDate;
import java.util.List;

public interface BookedSlotService {

    Long create(BookedSlotDTO bookedSlotDTO);

    ReducedBookedSlotDTO findByDateAndField(LocalDate date, Long fieldId);

    List<BookedSlotDTO> saveAll(List<BookedSlotDTO> bookedSlots);
    List<BookedSlot> getBookedSlotsByGameId(Long gameId);
}
