package code.challenge.email.services;

import code.challenge.email.dto.SubscriptionEmailDto;
import code.challenge.email.enums.EmailTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {
    private final String SUBSCRIPTION_EMAIL_SUBJECT = "Subscription Confirmation";
    private final String SUBSCRIPTION_CANCELED = "Subscription Cancellation";

    private final TemplateEngine templateEngine;
    private final EmailClient emailClient;

    @Autowired
    public EmailServiceImpl(TemplateEngine templateEngine, EmailClient emailClient) {
        this.emailClient = emailClient;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(SubscriptionEmailDto dto, EmailTypeEnum emailType) {
        switch (emailType) {
            case SUBSCRIPTION:
                sendEmailSubscription(dto);
                break;
            case SUBSCRIBE_CANCELLATION:
                sendEmailCancellation(dto);
                break;
        }
    }

    private void sendEmailCancellation(SubscriptionEmailDto dto) {
        String emailContent = buildCancelSubscription(dto);
        this.emailClient.prepareEndSend(dto.getEmail(), emailContent, SUBSCRIPTION_CANCELED);
    }

    private void sendEmailSubscription(SubscriptionEmailDto dto){
        String emailContent = buildEmailSubscription(dto);
        this.emailClient.prepareEndSend(dto.getEmail(), emailContent, SUBSCRIPTION_EMAIL_SUBJECT);
    }

    private String buildCancelSubscription(SubscriptionEmailDto dto) {
        Context context = new Context();
        context.setVariable("name", dto.getName());
        return templateEngine.process("subscription_cancel", context);
    }

    private String buildEmailSubscription(SubscriptionEmailDto dto) {
        Context context = new Context();
        context.setVariable("name", dto.getName());
        return templateEngine.process("subscription", context);
    }
}
