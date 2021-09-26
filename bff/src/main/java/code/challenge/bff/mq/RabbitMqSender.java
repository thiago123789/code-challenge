package code.challenge.bff.mq;

import code.challenge.bff.dtos.SubscriptionDto;

public interface RabbitMqSender {
    void sendSubscription(SubscriptionDto dto);
    void cancelSubscription(String id);
}
