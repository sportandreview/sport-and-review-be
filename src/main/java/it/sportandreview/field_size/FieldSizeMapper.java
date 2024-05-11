package it.sportandreview.field_size;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.fieal_size_type.FieldSizeType;
import it.sportandreview.fieal_size_type.FieldSizeTypeMapper;
import it.sportandreview.fieal_size_type.FieldSizeTypeRepository;
import it.sportandreview.field.Field;
import it.sportandreview.field.FieldMapper;
import it.sportandreview.field.FieldRepository;
import it.sportandreview.sport.Sport;
import it.sportandreview.sport.SportMapper;
import it.sportandreview.sport.SportRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


@Mapper(componentModel = "spring", uses={FieldSize.class, SportMapper.class, FieldMapper.class, FieldSizeTypeMapper.class})
public abstract class FieldSizeMapper {

     @Autowired
     private FieldRepository fieldRepository;
     @Autowired
     private SportRepository sportRepository;
     @Autowired
     private FieldSizeTypeRepository fieldSizeTypeRepository;

     @Mapping(source = "sport.id", target = "sportId")
     @Mapping(source = "field.id", target = "fieldId")
     @Mapping(source = "fieldSizeType.id", target = "fieldSizeTypeId")
     public abstract FieldSizeDTO toDto(FieldSize fieldSize);
     @Mapping(source = "sportId", target = "sport", qualifiedByName = "sportIdToSport")
     @Mapping(source = "fieldId", target = "field", qualifiedByName = "fieldIdToField")
     @Mapping(source = "fieldSizeTypeId", target = "fieldSizeType", qualifiedByName = "fieldSizeTypeIdToFieldSizeType")
    public abstract FieldSize toEntity(FieldSizeDTO fieldSizeDTO);
     @Mapping(source = "sport.id", target = "sportId")
     @Mapping(source = "field.id", target = "fieldId")
     @Mapping(source = "fieldSizeType.id", target = "fieldSizeTypeId")
     public abstract List<FieldSizeDTO> toDto(List<FieldSize> fieldSizes);
     @Mapping(source = "sportId", target = "sport", qualifiedByName = "sportIdToSport")
     @Mapping(source = "fieldId", target = "field", qualifiedByName = "fieldIdToField")
     @Mapping(source = "fieldSizeTypeId", target = "fieldSizeType", qualifiedByName = "fieldSizeTypeIdToFieldSizeType")
     public abstract List<FieldSize> toEntity(List<FieldSizeDTO> fieldSizesDTO);

     @Named("sportIdToSport")
     public Sport sportIdToSport(Long sportId) {
          Sport sport = sportRepository.findById(sportId).
                  orElseThrow(() -> new NotFoundException("sport", "Sport" + " not exists into database"));
          return sport;
     }

     @Named("fieldIdToField")
     public Field fieldIdToField(Long fieldId) {
          Field field = fieldRepository.findById(fieldId).
                  orElseThrow(() -> new NotFoundException("field", "Field" + " not exists into database"));
          return field;
     }
    @Named("fieldSizeTypeIdToFieldSizeType")
    public FieldSizeType fieldSizeTypeIdToFieldSizeType(Long fieldSizeTypeId) {
        FieldSizeType fieldSizeType = fieldSizeTypeRepository.findById(fieldSizeTypeId).
                orElseThrow(() -> new NotFoundException("fieldSizeType", "FieldSizeType" + " not exists into database"));
        return fieldSizeType;
    }
}
