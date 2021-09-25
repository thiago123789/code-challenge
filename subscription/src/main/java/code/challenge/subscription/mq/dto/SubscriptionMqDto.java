package code.challenge.subscription.mq.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class SubscriptionMqDto implements Serializable {
    private UUID id;
    private String name;
    private String email;
}
