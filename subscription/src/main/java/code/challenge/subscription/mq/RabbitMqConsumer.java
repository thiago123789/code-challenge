package code.challenge.subscription.mq;

import code.challenge.subscription.models.Subscription;

import java.util.UUID;

public interface RabbitMqConsumer {
    void handlerSubscribe(Subscription subscriptionPayload);
    void handlerCancelSubscription(UUID subscriptionId);
}
