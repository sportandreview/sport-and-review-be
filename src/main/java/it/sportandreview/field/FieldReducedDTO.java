package it.sportandreview.field;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.ground_type.GroundTypeDTO;
import it.sportandreview.slot.SlotDTO;
import it.sportandreview.sport.SportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FieldReducedDTO extends BaseDTO {

    private String name;
    private String fieldImage;
    private ZonedDateTime hourRangeStart;
    private ZonedDateTime hourRangeEnd;
    private Double price;
    private Boolean covered;
    private Double size;
    private Double rating;
    private String description;
    private GroundTypeDTO groundType;
    private SportDTO sport;
    private Boolean highlights;
    private Boolean markerPoint;
    private List<SlotDTO> bookedSlotList = new ArrayList<>();
    private List<SlotDTO> bookableSlotList = new ArrayList<>();
}
