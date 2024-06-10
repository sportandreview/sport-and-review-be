package it.sportandreview.user;

import it.sportandreview.gender_type.GenderTypeMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={User.class, GenderTypeMapper.class})
public interface UserMapper {

     UserDTO toDto(User user);

     User toEntity(UserDTO userDto);

}
