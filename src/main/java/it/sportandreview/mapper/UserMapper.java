package it.sportandreview.mapper;

import it.sportandreview.dto.request.UserRegistrationRequestDTO;
import it.sportandreview.enums.GenderType;
import it.sportandreview.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true) // Encoded separately
    @Mapping(source = "genderType", target = "genderType", qualifiedByName = "stringToGenderType")
    User toEntity(UserRegistrationRequestDTO dto);

    @Named("stringToGenderType")
    default GenderType stringToGenderType(String genderType) {
        return GenderType.valueOf(genderType.toUpperCase());
    }
}
