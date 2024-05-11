package it.sportandreview.user_otp;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.exception.NotVerifiedCodeException;
import it.sportandreview.user.*;
import it.sportandreview.user_code_type.UserCodeTypeEnum;
import it.sportandreview.user_code_type.UserCodeTypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Log4j2
@Service
@Component
public class ResetPassword extends EmailSending {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserService userService;
    private final UserCodeTypeRepository userCodeTypeRepository;
    private final UserOtpRepository userOtpRepository;

    @Value("${SportAndReview.password-url}")
    private String link;


    public ResetPassword(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper,
                         UserService userService, UserCodeTypeRepository userCodeTypeRepository,
                         UserOtpRepository userOtpRepository) {
        super(userRepository, userCodeTypeRepository, userOtpRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userService = userService;
        this.userCodeTypeRepository = userCodeTypeRepository;
        this.userOtpRepository = userOtpRepository;
    }

    public String sendEmailChangePassword(Long userId) {
        String code = code();
        String subject = "Cambia la password";
        String body = "Il link per cambiare la password è il seguente: " + link;
        sendEmail(userId, subject, body);
        create(userId, code, UserCodeTypeEnum.PASSWORD.getId());
        return code;
    }

    public void changePassword(PasswordRequest passwordRequest, String code) {
        Optional<UserOtp> userOtp = userOtpRepository.findByCode(code);
        if (userOtp.isPresent() && LocalDateTime.now().isBefore(userOtp.get().getExpirationDate())) {
            User user = userRepository.findById(passwordRequest.getId()).
                    orElseThrow(() -> new NotFoundException("user", "User" + "not exists into database"));
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            UserDTO userDTO = userMapper.toDto(user);
            userService.update(userDTO);
        } else {
            throw new NotVerifiedCodeException("Errore", "Errore codice errato o scaduto");
        }
    }
    public String code() {
        return UUID.randomUUID().toString();
    }

}
