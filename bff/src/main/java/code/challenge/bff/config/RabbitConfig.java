package code.challenge.bff.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${adidas.bff.queue}")
    private String queueSubscription;

    @Bean
    public Queue queueConfig() {
        return new Queue(queueSubscription, true);
    }

}
