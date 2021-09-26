package code.challenge.subscription.mq.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionEmailDto {
    private String name;
    private String email;
}
