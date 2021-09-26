package code.challenge.subscription.services;

import code.challenge.subscription.models.Subscription;
import code.challenge.subscription.mq.RabbitMqSender;
import code.challenge.subscription.mq.dto.SubscriptionEmailDto;
import code.challenge.subscription.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository repository;
    private final RabbitMqSender rabbitMqSender;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository,
                                   RabbitMqSender rabbitMqSender){
        this.repository = repository;
        this.rabbitMqSender = rabbitMqSender;
    }

    @Override
    public void subscribe(Subscription model) {
        this.repository.save(model);
        this.rabbitMqSender.sendSubscriptionEmail(SubscriptionEmailDto.builder()
                .email(model.getEmail())
                .name(model.getFirstName())
                .build());
    }

    @Override
    public void cancelSubscription(UUID subscriptionId) {
        Optional<Subscription> subscriptionToDelete = this.repository.findById(subscriptionId);
        subscriptionToDelete.ifPresent(this.repository::delete);
        subscriptionToDelete
                .map(e -> SubscriptionEmailDto.builder()
                                        .email(e.getEmail())
                                        .name(e.getFirstName())
                                        .build())
                .ifPresent(this.rabbitMqSender::sendSubscribeCancellation);
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
