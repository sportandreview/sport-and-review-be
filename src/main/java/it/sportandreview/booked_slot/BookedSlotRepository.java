package it.sportandreview.booked_slot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookedSlotRepository extends JpaRepository<BookedSlot, Long> {
    @Query("SELECT bs FROM BookedSlot bs " +
            "WHERE :date BETWEEN bs.startDate AND bs.endDate " +
            "AND bs.field.id = :fieldId")
    List<BookedSlot> findBookedSlotsForDateAndFieldId(@Param("date") LocalDate date,  @Param("fieldId") Long fieldId);

    @Query("SELECT bs FROM BookedSlot bs WHERE bs.gameMatch.id = :gameId ORDER BY bs.slot.time ASC")
    List<BookedSlot> findFirstByGameMatchIdOrderBySlotAsc(Long gameId);

}
