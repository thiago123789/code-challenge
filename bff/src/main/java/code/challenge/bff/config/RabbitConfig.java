package code.challenge.bff.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public final static String CREATE_SUBSCRIPTION_QUEUE = "createSubscriptionQueue";
    public final static String CANCEL_SUBSCRIPTION_QUEUE = "cancelSubscriptionQueue";

    @Value("${adidas.bff.queue}")
    private String queueSubscription;

    @Value("${adidas.bff.cancel.queue}")
    private String queueCancelSubscription;

    @Bean
    @Qualifier(CREATE_SUBSCRIPTION_QUEUE)
    public Queue queueConfig() {
        return new Queue(queueSubscription, true);
    }

    @Bean
    @Qualifier(CANCEL_SUBSCRIPTION_QUEUE)
    public Queue queueCancelConfig() {
        return new Queue(queueCancelSubscription, true);
    }
}
