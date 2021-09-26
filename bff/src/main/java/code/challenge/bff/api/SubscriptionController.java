package code.challenge.bff.api;

import code.challenge.bff.dtos.SubscriptionDto;
import code.challenge.bff.dtos.SubscriptionOutputDto;
import code.challenge.bff.dtos.SubscriptionInputDto;
import code.challenge.bff.services.SubscribeService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(oneOf = SubscriptionOutputDto.class))
            })
    })
    public ResponseEntity<SubscriptionOutputDto> subscribe(@Valid @RequestBody SubscriptionInputDto dto) {
        return ResponseEntity.ok(this.subscribeService.subscribe(dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204")
    })
    public ResponseEntity<Void> cancelSubscription(@PathVariable String id) {
        this.subscribeService.unsubscribe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(allOf = SubscriptionOutputDto.class))
            })
    })
    public ResponseEntity<List<SubscriptionOutputDto>> allSubscription() {
        return ResponseEntity.ok(this.subscribeService.allSubscriptions());
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {
                @Content(schema = @Schema(oneOf = SubscriptionDto.class))
        }),
        @ApiResponse(responseCode = "404")
    })
    public ResponseEntity<SubscriptionDto> getDetail(@PathVariable String id){
        Optional<SubscriptionDto> dto = this.subscribeService.subscriptionDetail(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
