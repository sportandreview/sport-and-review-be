package it.sportandreview.user;

import java.util.Optional;

public interface UserService {

    User create(UserDTO userDto);

    User update(UserDTO userDto);

    User findById(Long id);

    Optional<User> findByEmail(String email);

    UserDTO updatePassword(PasswordRequest request);

    User updateProfileImage(Long userId, String profileImage);

}
