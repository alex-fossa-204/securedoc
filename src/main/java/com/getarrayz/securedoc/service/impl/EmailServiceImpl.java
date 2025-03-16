package com.getarrayz.securedoc.service.impl;

import com.getarrayz.securedoc.exception.ApiException;
import com.getarrayz.securedoc.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.getarrayz.securedoc.utils.EmailUtils.getEmailMessage;
import static com.getarrayz.securedoc.utils.EmailUtils.getResetPasswordMessage;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.verify.host}") //todo это пиздец, потом переделелать
    private String host;

    @Value("${spring.mail.username}") //todo это пиздец, потом переделелать
    private String fromEmail;

    public static final String NEW_USER_ACCOUNT_VERIFICATION_SUBJECT = "New User Account Verification";
    public static final String PASSWORD_RESET_REQUEST_SUBJECT = "New User Account Verification";

    @Override
    @Async
    public void sendNewAccountEmail(String name, String toEmail, String token) {
        try {
            final var message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION_SUBJECT);
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setText(getEmailMessage(name, host, token));
            javaMailSender.send(message);
        } catch (Exception exception) {
            log.error("Обработано исключение: ", exception);
            throw new ApiException("Не удалось отправить sms уведомление");
        }
    }

    @Override
    @Async
    public void sendPasswordResetEmail(String name, String toEmail, String token) {
        try {
            final var message = new SimpleMailMessage();
            message.setSubject(PASSWORD_RESET_REQUEST_SUBJECT);
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setText(getResetPasswordMessage(name, host, token));
            javaMailSender.send(message);
        } catch (Exception exception) {
            log.error("Обработано исключение: ", exception);
            throw new ApiException("Не удалось отправить sms уведомление");
        }
    }
}
