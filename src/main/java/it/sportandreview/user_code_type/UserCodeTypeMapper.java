package it.sportandreview.user_code_type;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={UserCodeType.class})
public interface UserCodeTypeMapper {

    UserCodeTypeDTO toDto(UserCodeType userCodeType);
    UserCodeType toEntity(UserCodeTypeDTO userCodeTypeDTO);

}
