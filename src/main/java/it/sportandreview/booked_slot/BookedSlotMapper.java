package it.sportandreview.booked_slot;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.field.Field;
import it.sportandreview.field.FieldMapper;
import it.sportandreview.field.FieldRepository;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.game_match.GameMatchMapper;
import it.sportandreview.game_match.GameMatchRepository;
import it.sportandreview.slot.Slot;
import it.sportandreview.slot.SlotMapper;
import it.sportandreview.slot.SlotRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses={BookedSlot.class, GameMatchMapper.class, FieldMapper.class, SlotMapper.class})
public abstract class BookedSlotMapper {
        @Autowired
        private GameMatchRepository gameMatchRepository;
        @Autowired
        private SlotRepository slotRepository;
        @Autowired
        private FieldRepository fieldRepository;
    @Mapping(source = "gameMatch", target = "gameMatchId", qualifiedByName = "gameMatchToId")
    @Mapping(source = "slot", target = "slotId", qualifiedByName = "slotToId")
    @Mapping(source = "field", target = "fieldId", qualifiedByName = "fieldToId")
    public abstract BookedSlotDTO toDto(BookedSlot bookedSlot);
    @Mapping(source = "gameMatchId", target = "gameMatch", qualifiedByName = "gameMatchIdToGameMatch")
    @Mapping(source = "slotId", target = "slot", qualifiedByName = "slotIdToSlot")
    @Mapping(source = "fieldId", target = "field", qualifiedByName = "fieldIdToField")
    public abstract BookedSlot toEntity(BookedSlotDTO bookedSlotDTO);
    @Mapping(source = "gameMatch", target = "gameMatchId", qualifiedByName = "gameMatchToId")
    @Mapping(source = "slot", target = "slotId", qualifiedByName = "slotToId")
    @Mapping(source = "field", target = "fieldId", qualifiedByName = "fieldToId")
    public abstract List<BookedSlotDTO> toDto(List<BookedSlot> bookedSlots);
    @Mapping(source = "gameMatchId", target = "gameMatch", qualifiedByName = "gameMatchIdToGameMatch")
    @Mapping(source = "slotId", target = "slot", qualifiedByName = "slotIdToSlot")
    @Mapping(source = "fieldId", target = "field", qualifiedByName = "fieldIdToField")
    public abstract List<BookedSlot> toEntity(List<BookedSlotDTO> bookedSlotDTOs);

    @Named("gameMatchToId")
    public Long gameMatchToId(GameMatch g) {
        return g.getId();
    }

    @Named("gameMatchIdToGameMatch")
    public GameMatch gameMatchIdToGameMatch(Long gameMatchId) {
        GameMatch gameMatch = gameMatchRepository.findById(gameMatchId).
                orElseThrow(() -> new NotFoundException("gameMatch", "GameMatch" + " not exists into database"));
        return gameMatch;
    }

    @Named("slotToId")
    public Long slotToId(Slot s) {
        return s.getId();
    }



    @Named("slotIdToSlot")
    public Slot slotIdToSlot(Long slotId) {
        Slot slot = slotRepository.findById(slotId).
                orElseThrow(() -> new NotFoundException("slot", "Slot" + " not exists into database"));
        return slot;
    }

    @Named("fieldToId")
    public Long fieldToId(Field f) {
        return f.getId();
    }

    @Named("fieldIdToField")
    public Field fieldIdToField(Long fieldId) {
        Field field = fieldRepository.findById(fieldId).
                orElseThrow(() -> new NotFoundException("field", "Field" + " not exists into database"));
        return field;
    }
}
