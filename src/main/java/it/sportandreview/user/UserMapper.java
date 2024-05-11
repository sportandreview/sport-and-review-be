package it.sportandreview.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={User.class})
public interface UserMapper {

     UserDTO toDto(User user);

     User toEntity(UserDTO userDto);

}
