package code.challenge.subscription.services;

import code.challenge.subscription.models.Subscription;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionService {
    void subscribe(Subscription dto);
    void cancelSubscription(UUID subscriptionId);
    List<UUID> getAllSubscriptions();
    Optional<Subscription> getSubscriptionDetail(UUID uuid);
}
