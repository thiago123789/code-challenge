package code.challenge.bff.api;

import code.challenge.bff.dtos.SubscriptionDto;
import code.challenge.bff.dtos.SubscriptionOutputDto;
import code.challenge.bff.dtos.SubscriptionInputDto;
import code.challenge.bff.services.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    private final SubscribeService subscribeService;

    @Autowired
    public SubscriptionController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @PostMapping("/")
    public ResponseEntity<SubscriptionOutputDto> subscribe(@RequestBody SubscriptionInputDto dto) {
        return ResponseEntity.ok(this.subscribeService.subscribe(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable String id) {
        this.subscribeService.unsubscribe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<SubscriptionOutputDto>> allSubscription() {
        return ResponseEntity.ok(this.subscribeService.allSubscriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> getDetail(@PathVariable String id){
        Optional<SubscriptionDto> dto = this.subscribeService.subscriptionDetail(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
