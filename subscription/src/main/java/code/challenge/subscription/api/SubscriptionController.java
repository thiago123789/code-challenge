package code.challenge.subscription.api;

import code.challenge.subscription.SubscriptionApplication;
import code.challenge.subscription.models.Subscription;
import code.challenge.subscription.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionDetail(@PathVariable String id) {
        try {
            Optional<Subscription> subscription = this.subscriptionService.getSubscriptionDetail(UUID.fromString(id));
            return subscription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<UUID>> getSubscriptionDetail() {
        List<UUID> subscriptions = this.subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

}
