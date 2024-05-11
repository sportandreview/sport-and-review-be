package it.sportandreview.admin_user;


import it.sportandreview.user.UserDTO;

public interface AdminUserService {

    AdminUserDTO findById(Long userId);

    AdminUserDTO update(AdminUserDTO adminUserDTO);
    AdminUserDTO updateProfileImage(Long userId, String profileImage);

}
