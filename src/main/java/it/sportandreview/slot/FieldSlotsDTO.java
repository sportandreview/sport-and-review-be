package it.sportandreview.slot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FieldSlotsDTO {

    private List<SlotDTO> bookedSlotList = new ArrayList<>();
    private List<SlotDTO> bookableSlotList = new ArrayList<>();
}
