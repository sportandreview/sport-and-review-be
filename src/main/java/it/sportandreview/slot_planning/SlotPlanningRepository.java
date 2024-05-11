package it.sportandreview.slot_planning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SlotPlanningRepository extends JpaRepository<SlotPlanning, Long> {
    @Query("SELECT sp FROM SlotPlanning sp " +
            "WHERE :date BETWEEN sp.startDateValidity AND sp.endDateValidity " +
            "AND LOWER(sp.daysOfWeek) LIKE %:dayOfWeek% " +
            "AND sp.field.id = :fieldId")
    List<SlotPlanning> findByDateAndDayOfWeek(@Param("date") LocalDate date, @Param("dayOfWeek") String dayOfWeek,
                                              @Param("fieldId") Long fieldId);
}
