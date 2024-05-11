package it.sportandreview.slot_planning;



import it.sportandreview.slot.SlotDTO;

import java.time.LocalDate;
import java.util.List;

public interface SlotPlanningService {

    Long create(SlotPlanningDTO slotPlanningDTO);

    SlotPlanningDTO update(SlotPlanningDTO slotPlanningDTO);

    List<SlotPlanningDTO> findAll();

    SlotPlanningDTO findById(Long slotPlanningId);

    List<SlotDTO> findByDateAndField(LocalDate date, Long fieldId);
}
