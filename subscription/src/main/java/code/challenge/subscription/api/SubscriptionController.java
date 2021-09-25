package code.challenge.subscription.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {

    @GetMapping
    public String hello() {
        return " Hello World ";
    }

}
