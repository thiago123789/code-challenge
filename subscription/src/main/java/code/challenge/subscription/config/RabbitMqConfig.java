package code.challenge.subscription.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public final static String CANCEL_SUBSCRIPTION_EMAIL = "cancelSubscriptionEmail";
    public final static String SUBSCRIBE_EMAIL = "subscribeEmail";

    @Value("${adidas.email.queue.subscribe}")
    private String queueSubscribeEmail;

    @Value("${adidas.email.queue.cancel}")
    private String queueCancelEmail;

    @Bean
    @Qualifier(CANCEL_SUBSCRIPTION_EMAIL)
    public Queue queueCancel() {
        return new Queue(queueCancelEmail, true);
    }

    @Bean
    @Qualifier(SUBSCRIBE_EMAIL)
    public Queue queueSubscribeEmail() {
        return new Queue(queueSubscribeEmail, true);
    }
}
