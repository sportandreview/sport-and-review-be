package it.sportandreview.field_size_type;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses={FieldSizeType.class})
public interface FieldSizeTypeMapper {



    FieldSizeTypeDTO toDto(FieldSizeType fieldSizeType);

    FieldSizeType toEntity(FieldSizeTypeDTO fieldSizeTypeDTO);

    List<FieldSizeTypeDTO> toDto(List<FieldSizeType> fieldSizeTypes);

    List<FieldSizeType> toEntity(List<FieldSizeTypeDTO> fieldSizeTypesDTO);


}
