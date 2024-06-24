package it.sportandreview.service.impl;

import it.sportandreview.dto.request.AuthenticationRequestDTO;
import it.sportandreview.dto.request.UserRequestDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.enums.RoleType;
import it.sportandreview.exception.*;
import it.sportandreview.mapper.UserMapper;
import it.sportandreview.repository.UserRepository;
import it.sportandreview.service.AuthenticationService;
import it.sportandreview.entity.User;
import it.sportandreview.service.UserService;
import it.sportandreview.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    // Constants
    private static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 70;
    private static final Integer STANDARD_RANKING = 50;
    private static final Integer STANDARD_RELIABILITY = 100;

    // Dependencies
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final MessageSource messageSource;

    @Override
    public void register(UserRequestDTO userRequestDTO, RoleType roleType) {
        log.info("Registering user with email: {} and role: {}", userRequestDTO.getEmail(), roleType.getDescription());
        validateUserRequest(userRequestDTO);
        User user = userMapper.toEntity(userRequestDTO);
        user.setRoleType(roleType);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        initializeUserRoleSpecificFields(user);
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticateUser(request.getEmail(), request.getPassword());
        User user = userService.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException(
                messageSource.getMessage("user.not.found", new Object[]{request.getEmail()}, LocaleContextHolder.getLocale())));
        return generateTokens(user);
    }

    @Override
    public AuthenticationResponseDTO refreshToken(AuthenticationRequestDTO request) {
        User user = userService.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException(
                messageSource.getMessage("user.not.found", new Object[]{request.getEmail()}, LocaleContextHolder.getLocale())));
        if (jwtTokenUtil.validateToken(request.getRefreshToken(), user)) {
            return generateTokens(user);
        }
        throw new TokenNotValidException("Token di refresh non valido");
    }

    public void updateVerificationStatus(String key, boolean verified) {
        Optional<User> userOpt = userRepository.findByEmailOrMobilePhone(key, key);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (key.contains("@")) {
                user.setEmailCheck(verified);
            } else {
                user.setMobilePhoneCheck(verified);
            }
            userRepository.save(user);
        }
    }

    public AuthenticationResponseDTO generateTokens(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwtToken = jwtTokenUtil.generateToken(userDetails, TOKEN_EXPIRATION_TIME);
        String refreshToken = jwtTokenUtil.generateToken(userDetails, REFRESH_TOKEN_EXPIRATION_TIME);
        return AuthenticationResponseDTO.builder()
                .userId(user.getId())
                .token(jwtToken)
                .refreshToken(refreshToken)
                .mobilePhoneCheck(user.isMobilePhoneCheck())
                .emailCheck(user.isEmailCheck())
                .build();
    }

    private void validateUserRequest(UserRequestDTO userRequestDTO) {
        if (userService.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            String errorMessage = String.format(
                    messageSource.getMessage("user.mail.already.exists", null, LocaleContextHolder.getLocale()),
                    userRequestDTO.getEmail()
            );
            throw new DuplicateEntityException(errorMessage);
        }
        if (userService.findByNickname(userRequestDTO.getNickname()).isPresent()) {
            String errorMessage = String.format(
                    messageSource.getMessage("user.nickname.already.exists", null, LocaleContextHolder.getLocale()),
                    userRequestDTO.getNickname()
            );
            throw new DuplicateEntityException(errorMessage);
        }
    }

    private void initializeUserRoleSpecificFields(User user) {
        if (user.getRoleType() == RoleType.ROLE_USER) {
            user.setRanking(STANDARD_RANKING);
            user.setReliability(STANDARD_RELIABILITY);
        }
    }

    private void authenticateUser(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            throw new BadCredentialsException(messageSource.getMessage("user.authenticate.failure", null, LocaleContextHolder.getLocale()));
        }
    }
}
