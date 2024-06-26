package it.sportandreview.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class OtpUtil {

    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRATION_MINUTES = 10;

    @Value("${mitto.api.key}")
    private String mittoApiKey;

    @Value("${mitto.api.url}")
    private String mittoApiUrl;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private final JavaMailSender mailSender;
    private final RestTemplate restTemplate;
    private final MessageSource messageSource;

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> otpExpiration = new ConcurrentHashMap<>();

    public String generateOtp(String key) {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        String otpValue = otp.toString();
        otpStorage.put(key, otpValue);
        otpExpiration.put(key, LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES));
        return otpValue;
    }

    public boolean validateOtp(String key, String otp) {
        LocalDateTime expirationTime = otpExpiration.get(key);
        if (expirationTime != null && LocalDateTime.now().isBefore(expirationTime)) {
            return otp.equals(otpStorage.get(key));
        }
        return false;
    }

    public void sendOtpEmail(String email, String otp) {
        String message = messageSource.getMessage("otp.email.body", new Object[]{otp}, LocaleContextHolder.getLocale());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo(email);
        mailMessage.setSubject(messageSource.getMessage("otp.email.subject", null, LocaleContextHolder.getLocale()));
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    public void sendOtpSms(String phoneNumber, String otp) {
        String message = messageSource.getMessage("otp.sms.body", new Object[]{otp}, LocaleContextHolder.getLocale());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Mitto-API-Key", mittoApiKey);

        Map<String, String> body = Map.of(
                "from", "SportReview",
                "to", "+39" + phoneNumber,
                "text", message
        );

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        restTemplate.postForObject(mittoApiUrl, request, String.class);
    }
}