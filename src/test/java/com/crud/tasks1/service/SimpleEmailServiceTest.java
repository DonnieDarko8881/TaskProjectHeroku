package com.crud.tasks1.service;

import com.crud.tasks1.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    SimpleMailService simpleEmailService;

    @Mock
    JavaMailSender javaMailSender;

    @Mock
    Logger logger;

    @Test
    public void shoudSendMail() {
        //Given
        Mail mail = new Mail("test@test.com","testCC@test.com", "Test", "Test");
        simpleEmailService.createMimeMessage(mail);
        //when
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender,times(1)).send(any(MimeMessagePreparator.class));

    }
}