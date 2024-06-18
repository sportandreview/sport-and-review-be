package it.sportandreview.service.impl;

import it.sportandreview.entity.Sport;
import it.sportandreview.entity.User;
import it.sportandreview.exception.SportNotFoundException;
import it.sportandreview.exception.UserAlreadyExistException;
import it.sportandreview.exception.UserNotFoundException;
import it.sportandreview.repository.SportRepository;
import it.sportandreview.repository.UserRepository;
import it.sportandreview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SportRepository sportRepository;


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
                    existingUser.setSportSet(userDetails.getSportSet());
                    existingUser.setHeight(userDetails.getHeight());
                    existingUser.setWeight(userDetails.getWeight());
                    existingUser.setPhysicalStructure(userDetails.getPhysicalStructure());
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

    @Override
    public void addUserSport(Long userId, Long sportId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Sport sport = sportRepository.findById(sportId).orElseThrow(() -> new SportNotFoundException(sportId));
        user.getSportSet().add(sport);
        userRepository.save(user);
    }

    @Override
    public void removeUserSport(Long userId, Long sportId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Sport sport = sportRepository.findById(sportId).orElseThrow(() -> new SportNotFoundException(sportId));
        user.getSportSet().remove(sport);
        userRepository.save(user);
    }

    @Override
    public List<Sport> getUserSports(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return List.copyOf(user.getSportSet());
    }
}
