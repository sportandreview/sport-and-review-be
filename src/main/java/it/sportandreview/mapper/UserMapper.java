package it.sportandreview.mapper;

import it.sportandreview.dto.request.UserRequestDTO;
import it.sportandreview.dto.response.UserResponseDTO;
import it.sportandreview.entity.User;
import it.sportandreview.enums.GenderType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {SportMapper.class})
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roleType", ignore = true)
    @Mapping(target = "password", ignore = true) // Encoded separately
    @Mapping(source = "genderType", target = "genderType", qualifiedByName = "stringToGenderType")
    User toEntity(UserRequestDTO dto);

    //@Mapping(target = "sports", source = "sportSet")
    UserResponseDTO toDto(User user);

    @Named("stringToGenderType")
    default GenderType stringToGenderType(String genderType) {
        return GenderType.valueOf(genderType.toUpperCase());
    }
}