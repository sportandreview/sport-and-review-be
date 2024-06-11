package it.sportandreview.user;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapperOld userMapperOld;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapperOld userMapperOld, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapperOld = userMapperOld;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(UserDTO userDto) {
        if (Objects.nonNull(userDto.getId()) && userRepository.existsById(userDto.getId())){
            throw new CreateEntityException(" user", "User" + " exists into database");
        }
        // Setting User UUID
        String uuid = StringUtils.isBlank(userDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : userDto.getUuid();
        userDto.setUuid(uuid);
        User user = userMapperOld.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        log.debug("User created");
        return saved;
    }

    @Override
    public User update(UserDTO userDto) {
        if (Objects.isNull(userDto.getId()) || !userRepository.existsById(userDto.getId())) {
            throw new NotFoundException("user", "PlayerUser" + " not found");
        }
        User saved = userRepository.save(userMapperOld.toEntity(userDto));
        log.debug("user updated");
        return saved;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("user", "User" + " not exists into database"));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO updatePassword(PasswordRequest request) {
        User user = userRepository.findById(request.getId()).
                orElseThrow(() -> new NotFoundException("user", "User" + "not exists into database"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        User user1 = userRepository.save(user);
        return userMapperOld.toDto(user1);
    }

    @Override
    public User updateProfileImage(Long playerUserId, String profileImage) {
        Optional<User> opt = userRepository.findById(playerUserId);
        if (opt.isEmpty()) {
            throw new NotFoundException("player user", "PlayerUser" + " not found");
        }
        User user = opt.get();
        user.setProfileImage(profileImage);
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findByEmailOrPhone(String key, String key1) {
        return Optional.empty();
    }

}
