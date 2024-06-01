package it.sportandreview.user_otp;

import it.sportandreview.exception.NotGenerateCodeException;
import it.sportandreview.user.*;
import it.sportandreview.user_code_type.UserCodeType;
import it.sportandreview.user_code_type.UserCodeTypeRepository;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Properties;

@Log4j2
@Service
@Component
public abstract class EmailSending {

    private UserRepository userRepository;
    private UserCodeTypeRepository userCodeTypeRepository;
    private UserOtpRepository userOtpRepository;

    @Value("${docker.smtp.server}")
    private String server;

    @Value("${docker.smtp.username}")
    private String email;

    @Value("${docker.smtp.pw}")
    private String pw;

    protected EmailSending(UserRepository userRepository, UserCodeTypeRepository userCodeTypeRepository, UserOtpRepository userOtpRepository) {

        this.userRepository = userRepository;
        this.userCodeTypeRepository = userCodeTypeRepository;
        this.userOtpRepository = userOtpRepository;
    }


    public void sendEmail(Long userId,  String subject, String body) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && Objects.nonNull(user.get().getEmail())) {
            String to = user.get().getEmail();

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", server);
            props.put("mail.smtp.port", "587");

            // sessione di autenticazione
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(email, pw);
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject);

                String emailContent = body;

                message.setText(emailContent);

                Transport.send(message);
                log.info("Correct generation and sending of message.");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new NotGenerateCodeException("Errore", " Errore nell'invio dell'email. Verificare che l'utente con quell'id esista e che l'email sia corretta");

        }
    }
    public UserOtp create(Long userId, String otpCode, Long userCodeTypeId) {
        Optional<UserCodeType> userCodeType = userCodeTypeRepository.findById(userCodeTypeId);
        if (userCodeType.isPresent()) {
            String uuid = Sha256Utils.getEncodedRandomUUID();
            UserOtp userOtp = UserOtp.builder().uuid(uuid).userId(userId).code(otpCode).
                    expirationDate(calculateExpirationDate()).userCodeType(userCodeType.get()).build();
            userOtpRepository.save(userOtp);
            return userOtp;
        }
        return null;
    }
    private LocalDateTime calculateExpirationDate() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime expirationDate = currentDate.plusMinutes(5);
        return expirationDate;
    }

}
