package code.challenge.email.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailClientImpl implements EmailClient{

    @Value("${email.username}")
    private String emailUsername;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailClientImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void prepareEndSend(String recipient, String message, String subject) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(emailUsername);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(message, true);
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("email sended");
        }catch (MailException e){
            log.error(e.getMessage());
        }
    }
}
