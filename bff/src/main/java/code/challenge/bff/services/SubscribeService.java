package code.challenge.bff.services;

import code.challenge.bff.dtos.SubscriptionDto;
import code.challenge.bff.dtos.SubscriptionOutputDto;
import code.challenge.bff.dtos.SubscriptionInputDto;

import java.util.List;
import java.util.Optional;

public interface SubscribeService {
    SubscriptionOutputDto subscribe(SubscriptionInputDto dto);
    void unsubscribe(String id);
    List<SubscriptionOutputDto> allSubscriptions();
    Optional<SubscriptionDto> subscriptionDetail(String id);
}
