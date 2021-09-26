package code.challenge.bff.services;

import code.challenge.bff.dtos.SubscriptionDto;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRestClient {
    List<String> getAll();
    Optional<SubscriptionDto> getOne(String id);
}
