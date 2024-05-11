package it.sportandreview.slot_planning;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.field.Field;
import it.sportandreview.field.FieldMapper;
import it.sportandreview.field.FieldRepository;
import it.sportandreview.slot.SlotMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", uses={SlotPlanning.class, FieldMapper.class, SlotMapper.class})
public abstract class SlotPlanningMapper {

    @Autowired
    private  FieldRepository fieldRepository;
    @Mapping(source = "field", target = "fieldId", qualifiedByName = "fieldToId")
    public abstract SlotPlanningDTO toDto(SlotPlanning slotPlanning);
    @Mapping(source = "fieldId", target = "field", qualifiedByName = "fieldIdToField")
    public abstract SlotPlanning toEntity(SlotPlanningDTO slotPlanningDTO);

    @Named("fieldToId")
    public Long fieldToId(Field f) {
        return f.getId();
    }

    @Named("fieldIdToField")
    public Field fieldIdToField(Long fieldId) {
        Field field = fieldRepository.findById(fieldId).
                orElseThrow(() -> new NotFoundException("field", "Field" + " not exists into database"));;
        return field;
    }
}
