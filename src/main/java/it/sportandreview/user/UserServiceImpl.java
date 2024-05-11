package it.sportandreview.user;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User create(UserDTO userDto) {
        if (Objects.nonNull(userDto.getId()) && userRepository.existsById(userDto.getId())){
            throw new CreateEntityException(" user", "User" + " exists into database");
        }
        // Setting User UUID
        String uuid = StringUtils.isBlank(userDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : userDto.getUuid();
        userDto.setUuid(uuid);
        User user = userMapper.toEntity(userDto);
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
        User saved = userRepository.save(userMapper.toEntity(userDto));
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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), request.getOldPassword()));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        User user1 = userRepository.save(user);
        return userMapper.toDto(user1);
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

}



