package code.challenge.email.mq;

import code.challenge.email.dto.SubscriptionEmailDto;
import code.challenge.email.enums.EmailTypeEnum;
import code.challenge.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {
    private final EmailService emailService;

    @Autowired
    public RabbitMqConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${adidas.email.queue.subscribe}")
    public void handlerEmailSubscribe(SubscriptionEmailDto dto) {
        this.emailService.sendEmail(dto, EmailTypeEnum.SUBSCRIPTION);
    }

    @RabbitListener(queues = "${adidas.email.queue.cancel}")
    public void handlerCancelSubscribe(SubscriptionEmailDto dto) {
        this.emailService.sendEmail(dto, EmailTypeEnum.SUBSCRIBE_CANCELLATION);
    }

}
