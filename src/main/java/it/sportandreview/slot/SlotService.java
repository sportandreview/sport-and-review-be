package it.sportandreview.slot;

import java.util.List;
import java.util.Set;

public interface SlotService {
    List<SlotDTO> createAll(Set<SlotDTO> slotDTOs);

}
