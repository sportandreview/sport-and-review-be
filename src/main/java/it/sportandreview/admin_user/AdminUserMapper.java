package it.sportandreview.admin_user;

import it.sportandreview.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={User.class})
public interface AdminUserMapper {

    AdminUserDTO toDto(User userAdmin);
    User toEntity(AdminUserDTO userAdminDto);

}
