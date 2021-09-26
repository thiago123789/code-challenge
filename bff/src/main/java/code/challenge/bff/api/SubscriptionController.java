package code.challenge.bff.api;

import code.challenge.bff.dtos.SubscriptionDto;
import code.challenge.bff.dtos.SubscriptionOutputDto;
import code.challenge.bff.dtos.SubscriptionInputDto;
import code.challenge.bff.services.SubscribeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success creation", response = SubscriptionOutputDto.class)
    })
    public ResponseEntity<SubscriptionOutputDto> subscribe( @RequestBody SubscriptionInputDto dto) {
        SubscriptionOutputDto createdDto = this.subscribeService.subscribe(dto);
        return ResponseEntity.created(URI.create("/" + createdDto.getId())).build();
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "")
    })
    public ResponseEntity<Void> cancelSubscription(@PathVariable String id) {
        this.subscribeService.unsubscribe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success retrieval", response = SubscriptionOutputDto.class, responseContainer = "List")
    })
    public ResponseEntity<List<SubscriptionOutputDto>> allSubscription() {
        return ResponseEntity.ok(this.subscribeService.allSubscriptions());
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success retrieval", response = SubscriptionDto.class),
        @ApiResponse(code = 404, message = "Subscription not found")
    })
    public ResponseEntity<SubscriptionDto> getDetail(@PathVariable String id){
        Optional<SubscriptionDto> dto = this.subscribeService.subscriptionDetail(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
