package code.challenge.bff.mq;

import code.challenge.bff.dtos.SubscriptionDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSender {
    private RabbitMessagingTemplate rabbitTemplate;
    private Queue queue;

    @Autowired
    private MappingJackson2MessageConverter converter;

    @Autowired
    public RabbitMqSender(Queue queue, RabbitMessagingTemplate rabbitTemplate) {
        this.queue = queue;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendSubscription(SubscriptionDto dto) {
        this.rabbitTemplate.setMessageConverter(converter);
        this.rabbitTemplate.convertAndSend(this.queue.getName(), dto);
    }
}
