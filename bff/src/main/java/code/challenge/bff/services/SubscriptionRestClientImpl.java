package code.challenge.bff.services;

import code.challenge.bff.dtos.SubscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionRestClientImpl implements SubscriptionRestClient {
    private final RestTemplate restTemplate;

    @Value("${subscription.service.baseurl}")
    private String subscriptionServiceBaseUrl;

    @Autowired
    public SubscriptionRestClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getAll() {
        return this.restTemplate.getForObject(this.subscriptionServiceBaseUrl, List.class);
    }

    public Optional<SubscriptionDto> getOne(String id) {
        try {
            return Optional.ofNullable(this.restTemplate.getForObject(this.subscriptionServiceBaseUrl + "/" + id, SubscriptionDto.class));
        } catch (Exception e) {
            return Optional.empty();
        }

    }

}
