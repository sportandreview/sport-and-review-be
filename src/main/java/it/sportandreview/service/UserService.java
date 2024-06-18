package it.sportandreview.service;

import it.sportandreview.entity.Sport;
import it.sportandreview.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    void addUserSport(Long userId, Long sportId);
    void removeUserSport(Long userId, Long sportId);
    List<Sport> getUserSports(Long userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmailOrMobilePhone(String email, String mobilePhone);
}
