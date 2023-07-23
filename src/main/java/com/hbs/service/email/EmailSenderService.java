package com.hbs.service.email;

import com.hbs.model.Mail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created By Soumojit Basak on 22 Jul, 2023
 */
@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(Mail mail) throws MessagingException, IOException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(mail.getProps());

        String emailTemplate = templateEngine.process("user-registration", context);

        messageHelper.setTo(mail.getMailTo());
        messageHelper.setText(emailTemplate, true);
        messageHelper.setSubject(mail.getSubject());

        mailSender.send(message);

    }
}
