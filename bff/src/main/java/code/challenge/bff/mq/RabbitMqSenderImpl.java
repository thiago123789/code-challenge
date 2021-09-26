package code.challenge.bff.mq;

import code.challenge.bff.config.RabbitConfig;
import code.challenge.bff.dtos.SubscriptionDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSenderImpl implements RabbitMqSender {
    private final Queue cancelQueue;
    private final RabbitMessagingTemplate rabbitTemplate;
    private final Queue createQueue;
    private final MappingJackson2MessageConverter converter;

    @Autowired
    public RabbitMqSenderImpl(
            @Qualifier(RabbitConfig.CREATE_SUBSCRIPTION_QUEUE) Queue createQueue,
            @Qualifier(RabbitConfig.CANCEL_SUBSCRIPTION_QUEUE) Queue cancelQueue,
            RabbitMessagingTemplate rabbitTemplate,
            MappingJackson2MessageConverter converter) {
        this.createQueue = createQueue;
        this.cancelQueue = cancelQueue;
        this.rabbitTemplate = rabbitTemplate;
        this.converter = converter;
    }

    @Override
    public void sendSubscription(SubscriptionDto dto) {
        this.rabbitTemplate.setMessageConverter(converter);
        this.rabbitTemplate.convertAndSend(this.createQueue.getName(), dto);
    }

    @Override
    public void cancelSubscription(String id) {
        this.rabbitTemplate.setMessageConverter(converter);
        this.rabbitTemplate.convertAndSend(this.cancelQueue.getName(), id);
    }
}
