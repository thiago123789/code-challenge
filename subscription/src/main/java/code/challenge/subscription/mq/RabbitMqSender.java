package code.challenge.subscription.mq;

import code.challenge.subscription.mq.dto.SubscriptionEmailDto;

public interface RabbitMqSender {
    void sendSubscriptionEmail(SubscriptionEmailDto dto);
    void sendSubscribeCancellation(SubscriptionEmailDto dto);
}
