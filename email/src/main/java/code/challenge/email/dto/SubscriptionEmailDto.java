package code.challenge.email.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionEmailDto {
    private String email;
    private String name;
}

