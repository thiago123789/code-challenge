package code.challenge.subscription.services;

import code.challenge.subscription.models.Subscription;
import code.challenge.subscription.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionRepository repository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository){
        this.repository = repository;
    }

    @Override
    public void subscribe(Subscription model) {
        this.repository.save(model);
    }

    @Override
    public void cancelSubscription(UUID subscriptionId) {
        Optional<Subscription> subscriptionToDelete = this.repository.findById(subscriptionId);
        subscriptionToDelete.ifPresent(e -> this.repository.delete(e));
    }

    @Override
    public List<UUID> getAllSubscriptions() {
        return this.repository.findAll().stream().map(Subscription::getId).collect(Collectors.toList());
    }

    @Override
    public Optional<Subscription> getSubscriptionDetail(UUID uuid) {
        return this.repository.findById(uuid);
    }


}
