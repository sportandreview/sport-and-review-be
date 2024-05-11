package it.sportandreview.slot;

import it.sportandreview.services.Services;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.slot_planning.SlotPlanning;
import it.sportandreview.slot_planning.SlotPlanningRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring", uses={Slot.class})
public abstract class SlotMapper {
    @Autowired
    private SlotPlanningRepository slotPlanningRepository;

    @Mapping(source = "planning", target = "planningId", qualifiedByName = "planningToId")
    public abstract SlotDTO toDto(Slot slot);

    @Mapping(source = "planningId", target = "planning", qualifiedByName = "planningIdToPlanning")
    public abstract Slot toEntity(SlotDTO slotDTO);

    @Mapping(source = "planningId", target = "planning", qualifiedByName = "planningIdToPlanning")
    public abstract Set<Slot> toEntity(Set<SlotDTO> slotDTOs);

    @Mapping(source = "planning", target = "planningId", qualifiedByName = "planningToId")
    public abstract Set<SlotDTO> toDto(Set<Slot> slots);

    @Mapping(source = "planningId", target = "planning", qualifiedByName = "planningIdToPlanning")
    public abstract List<Slot> toEntity(List<SlotDTO> slotDTOs);

    @Mapping(source = "planning", target = "planningId", qualifiedByName = "planningToId")
    public abstract List<SlotDTO> toDto(List<Slot> slots);

    @Named("planningToId")
    public Long planningToId(SlotPlanning b) {
        return b.getId();
    }

    @Named("planningIdToPlanning")
    public SlotPlanning planningIdToPlanning(Long planningId) {
        SlotPlanning slotPlanning = slotPlanningRepository.findById(planningId).orElse(null);
        return slotPlanning;
    }
}
