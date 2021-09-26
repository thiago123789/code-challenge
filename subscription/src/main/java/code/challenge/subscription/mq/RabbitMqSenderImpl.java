package code.challenge.subscription.mq;

import code.challenge.subscription.config.RabbitMqConfig;
import code.challenge.subscription.mq.dto.SubscriptionEmailDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSenderImpl implements RabbitMqSender {
    private final RabbitMessagingTemplate rabbitTemplate;
    private final Queue queueSubscribeCancellation;
    private final Queue queueSubscription;
    private final MappingJackson2MessageConverter converter;

    @Autowired
    public RabbitMqSenderImpl(RabbitMessagingTemplate rabbitTemplate,
                              @Qualifier(RabbitMqConfig.CANCEL_SUBSCRIPTION_EMAIL) Queue queueSubscribeCancellation,
                              @Qualifier(RabbitMqConfig.SUBSCRIBE_EMAIL) Queue queueSubscription,
                            MappingJackson2MessageConverter mappingJackson2MessageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueSubscribeCancellation = queueSubscribeCancellation;
        this.queueSubscription = queueSubscription;
        this.converter = mappingJackson2MessageConverter;
    }

    @Override
    public void sendSubscriptionEmail(SubscriptionEmailDto dto) {
        this.rabbitTemplate.setMessageConverter(converter);
        this.rabbitTemplate.convertAndSend(this.queueSubscription.getName(), dto);
    }

    @Override
    public void sendSubscribeCancellation(SubscriptionEmailDto dto) {
        this.rabbitTemplate.setMessageConverter(converter);
        this.rabbitTemplate.convertAndSend(this.queueSubscribeCancellation.getName(), dto);
    }
}
