package it.sportandreview.service.impl;

import it.sportandreview.exception.UserAlreadyExistException;
import it.sportandreview.exception.UserNotFoundException;
import it.sportandreview.entity.User;
import it.sportandreview.repository.UserRepository;
import it.sportandreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistException();
        }
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new UserAlreadyExistException();
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNickname(userDetails.getNickname());
                    existingUser.setName(userDetails.getName());
                    existingUser.setSurname(userDetails.getSurname());
                    existingUser.setBirthDate(userDetails.getBirthDate());
                    existingUser.setGenderType(userDetails.getGenderType());
                    existingUser.setEmail(userDetails.getEmail());
                    existingUser.setPassword(userDetails.getPassword());
                    existingUser.setMobilePhone(userDetails.getMobilePhone());
                    existingUser.setRoleType(userDetails.getRoleType());
                    existingUser.setSports(userDetails.getSports());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public Optional<User> findByEmailOrMobilePhone(String email, String mobilePhone) {
        return userRepository.findByEmailOrMobilePhone(email, mobilePhone);
    }
}
