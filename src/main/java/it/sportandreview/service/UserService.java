package it.sportandreview.service;

import it.sportandreview.entity.Sport;
import it.sportandreview.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
}
