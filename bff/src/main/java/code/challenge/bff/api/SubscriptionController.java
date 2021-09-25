package code.challenge.bff.api;

import code.challenge.bff.dtos.SubscriptionDto;
import code.challenge.bff.mq.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {
    private RabbitMqSender rabbitMqSender;

    @Autowired
    public SubscriptionController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @GetMapping("/api/test")
    public String testSubscription() {
        SubscriptionDto dto = new SubscriptionDto();
        rabbitMqSender.sendSubscription(dto);
        return dto.getId().toString();
    }

}
