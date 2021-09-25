package code.challenge.subscription.services;


import code.challenge.subscription.mq.dto.SubscriptionMqDto;

public interface SubscriptionService {

    public void processMqAddRequest(SubscriptionMqDto dto);

}
