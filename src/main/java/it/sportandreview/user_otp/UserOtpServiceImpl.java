package it.sportandreview.user_otp;

import it.sportandreview.exception.NotGenerateCodeException;
import it.sportandreview.exception.NotVerifiedCodeException;
import it.sportandreview.user.*;
import it.sportandreview.user_code_type.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@Service
@Component
public class UserOtpServiceImpl extends EmailSending implements UserOtpService {

    private final UserRepository userRepository;
    private final UserOtpRepository userOtpRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserCodeTypeRepository userCodeTypeRepository;

    @Autowired
    public UserOtpServiceImpl(UserRepository userRepository, UserOtpRepository userOtpRepository,
                              UserService userService, UserMapper userMapper,
                              UserCodeTypeRepository userCodeTypeRepository) {
        super(userRepository, userCodeTypeRepository, userOtpRepository);
        this.userRepository = userRepository;
        this.userOtpRepository = userOtpRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.userCodeTypeRepository = userCodeTypeRepository;
    }

    public void sendMessage(Long userId) {
        String mittoApiKey = "iFyz0nTRLE3jQmEA6rNWJmO9gIpV4TLg";
        String mittoOtpUrl = "https://rest.mittoapi.com/sms";
        String sportReview = "SportReview";

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && user.get().checkMobilePhone()) {
            String mobilePhone = user.get().getMobilePhone();
            String otpCode = OTP();
            String otpMessage = otpCode + " è il codice di sicurezza (OTP) di SportAndReview. Non condividerlo e non rispondere a questo messaggio.";
            String payload = "{\"from\":\"" + sportReview + "\",\"to\":\"" + mobilePhone + "\",\"text\":\"" + otpMessage + "\"}";

            try {
                URL url = new URL(mittoOtpUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("X-Mitto-API-Key", mittoApiKey);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);

                int statusCode = conn.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    log.info("Correct generation and sending of OTP message.");
                    create(userId, otpCode, UserCodeTypeEnum.PHONE.getId());
                } else {
                    log.error("Error in generation and sending of OTP message");
                    throw new NotGenerateCodeException("Errore", "Errore MITTO nella generazione del codice OTP");
                }
                conn.disconnect();
            } catch (Exception e) {
                log.error("Error in generation and sending of OTP message");
                throw new NotGenerateCodeException("Errore", "Errore MITTO nella generazione del codice OTP");
            }

        } else {
            throw new NotGenerateCodeException("Errore", "Errore legato all'utente nella generazione del codice OTP. " +
                    "Verificare che l'utente con quell'id esista e che il numero di telefono sia nel formato +39");
        }
    }

    @Transactional
    public void verifyOtp(Long userId, String otp, UserCodeTypeEnum userCodeType) {
        UserOtp userOtp = userOtpRepository.findLatestCodeByUserIdAndType(userId, userCodeType.getId());
        deleteByUserIdAndUserCodeTypeId(userId, userCodeType.getId());
        if (Objects.nonNull(userOtp) && otp.equals(userOtp.getCode()) && LocalDateTime.now().isBefore(userOtp.getExpirationDate())) {
            User user = userService.findById(userId);
            UserDTO userDTO = userMapper.toDto(user);
            if (userCodeType.equals(UserCodeTypeEnum.PHONE)) {
                userDTO.setMobilePhoneCheck(true);
            } else if (userCodeType.equals(UserCodeTypeEnum.EMAIL)) {
                userDTO.setEmailCheck(true);
            }
            userService.update(userDTO);
        } else {
            throw new NotVerifiedCodeException("Errore", "Errore OTP errato o scaduto");
        }
    }

    @Override
   public void sendEmail(Long userId) {
        String code = OTP();
        String subject = "Verifica dell'email";
        String body = "Il tuo codice di verifica è:" + code;
        sendEmail(userId, subject, body);
        create(userId, code, UserCodeTypeEnum.EMAIL.getId());

    }

    public String OTP() {
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000);
        return String.valueOf(otpNumber);
    }


    private void deleteByUserIdAndUserCodeTypeId(Long userId, Long userCodeTypeId) {
        userOtpRepository.deleteAllByUserIdAndUserCodeTypeId(userId, userCodeTypeId);
    }
}
