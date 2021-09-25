package code.challenge.subscription.mq;

import code.challenge.subscription.models.Subscription;
import code.challenge.subscription.mq.dto.SubscriptionMqDto;
import code.challenge.subscription.services.SubscriptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMqConsumer {
    private SubscriptionService subscriptionService;

    @Autowired
    public RabbitMqConsumer(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @RabbitListener(queues = "${adidas.bff.queue}")
    public void handler(Subscription subscriptionPayload) {
        log.info(subscriptionPayload);
    }
}
