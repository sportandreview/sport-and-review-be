package it.sportandreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class OtpService {

    @Value("${mitto.api.key}")
    private String mittoApiKey;

    @Value("${mitto.api.url}")
    private String mittoApiUrl;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private final JavaMailSender mailSender;
    private final RestTemplate restTemplate;

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> otpExpiration = new ConcurrentHashMap<>();

    public String generateOtp(String key) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStorage.put(key, otp);
        otpExpiration.put(key, LocalDateTime.now().plusMinutes(10)); // OTP valido per 10 minuti
        return otp;
    }

    public boolean validateOtp(String key, String otp) {
        if (otpExpiration.containsKey(key) && LocalDateTime.now().isBefore(otpExpiration.get(key))) {
            return otp.equals(otpStorage.get(key));
        }
        return false;
    }

    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setSubject("Il tuo codice OTP");
        message.setText("Il tuo codice OTP è: " + otp);
        message.setTo(email);
        mailSender.send(message);
    }

    public void sendOtpSms(String phoneNumber, String otp) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Mitto-API-Key", mittoApiKey);

        Map<String, String> body = new HashMap<>();
        body.put("from", "SportReview");
        body.put("to", "+39" + phoneNumber);
        body.put("text", "Il tuo codice OTP è: " + otp);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        restTemplate.postForObject(mittoApiUrl, request, String.class);
    }
}
