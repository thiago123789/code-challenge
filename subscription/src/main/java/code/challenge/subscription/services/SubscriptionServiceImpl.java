package code.challenge.subscription.services;

import code.challenge.subscription.mq.dto.SubscriptionMqDto;
import code.challenge.subscription.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionRepository repository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository){
        this.repository = repository;
    }

    @Override
    public void processMqAddRequest(SubscriptionMqDto dto) {

    }
}
