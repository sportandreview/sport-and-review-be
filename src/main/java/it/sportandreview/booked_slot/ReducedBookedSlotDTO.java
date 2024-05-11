package it.sportandreview.booked_slot;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.slot.SlotDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ReducedBookedSlotDTO extends BaseDTO {

    private Boolean allDay;
    private List<SlotDTO> slots;

}
