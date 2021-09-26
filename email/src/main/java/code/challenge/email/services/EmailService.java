package code.challenge.email.services;

import code.challenge.email.dto.SubscriptionEmailDto;
import code.challenge.email.enums.EmailTypeEnum;

public interface EmailService {
    void sendEmail(SubscriptionEmailDto dto, EmailTypeEnum emailType);
}
