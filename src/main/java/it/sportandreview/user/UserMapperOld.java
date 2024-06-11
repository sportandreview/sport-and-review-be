package it.sportandreview.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={User.class})
public interface UserMapperOld {

     UserDTO toDto(User user);

     User toEntity(UserDTO userDto);

}
