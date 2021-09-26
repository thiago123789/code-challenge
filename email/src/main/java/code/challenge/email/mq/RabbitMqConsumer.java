package code.challenge.email.mq;

import code.challenge.email.dto.SubscriptionEmailDto;
import code.challenge.email.enums.EmailTypeEnum;
import code.challenge.email.services.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMqConsumer {
    private final EmailService emailService;

    @Autowired
    public RabbitMqConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${adidas.email.queue.subscribe}")
    public void handlerEmailSubscribe(SubscriptionEmailDto dto) {
        log.info("subscription received");
        this.emailService.sendEmail(dto, EmailTypeEnum.SUBSCRIPTION);
    }

    @RabbitListener(queues = "${adidas.email.queue.cancel}")
    public void handlerCancelSubscribe(SubscriptionEmailDto dto) {
        log.info("cancellation received");
        this.emailService.sendEmail(dto, EmailTypeEnum.SUBSCRIBE_CANCELLATION);
    }

}
