package code.challenge.subscription.mq;

import code.challenge.subscription.models.Subscription;
import code.challenge.subscription.services.SubscriptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class RabbitMqConsumerImpl implements RabbitMqConsumer {
    private final SubscriptionService subscriptionService;

    @Autowired
    public RabbitMqConsumerImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @RabbitListener(queues = "${adidas.bff.queue}")
    public void handlerSubscribe(Subscription subscriptionPayload) {
        log.info(subscriptionPayload);
        this.subscriptionService.subscribe(subscriptionPayload);
    }

    @RabbitListener(queues = "${adidas.bff.cancel.queue}")
    public void handlerCancelSubscription(UUID subscriptionId) {
        log.info(subscriptionId);
        this.subscriptionService.cancelSubscription(subscriptionId);
    }
}
