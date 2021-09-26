package code.challenge.bff.services;

import code.challenge.bff.dtos.SubscriptionOutputDto;
import code.challenge.bff.dtos.SubscriptionDto;
import code.challenge.bff.dtos.SubscriptionInputDto;
import code.challenge.bff.mq.RabbitMqSender;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscribeServiceImpl implements SubscribeService {
    private final RabbitMqSender rabbitMqSender;
    private final SubscriptionRestClient subscriptionRestClient;
    private final ModelMapper modelMapper;

    @Autowired
    public SubscribeServiceImpl(RabbitMqSender rabbitMqSender,
                                SubscriptionRestClient subscriptionRestClient,
                                ModelMapper modelMapper) {
        this.rabbitMqSender = rabbitMqSender;
        this.subscriptionRestClient = subscriptionRestClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubscriptionOutputDto subscribe(SubscriptionInputDto dto) {
        SubscriptionDto rabbitDto = convertFromInputToRabbitDto(dto);
        this.rabbitMqSender.sendSubscription(rabbitDto);
        return SubscriptionOutputDto
                .builder()
                .id(rabbitDto.getId().toString())
                .build();
    }

    @Override
    public void unsubscribe(String id) {
        this.rabbitMqSender.cancelSubscription(id);
    }

    @Override
    public List<SubscriptionOutputDto> allSubscriptions() {
        return this.subscriptionRestClient.getAll().stream().map(e -> SubscriptionOutputDto.builder().id(e).build()).collect(Collectors.toList());
    }

    @Override
    public Optional<SubscriptionDto> subscriptionDetail(String id) {
        return this.subscriptionRestClient.getOne(id);
    }

    private SubscriptionDto convertFromInputToRabbitDto(SubscriptionInputDto dto) {
        return modelMapper.map(dto, SubscriptionDto.class);
    }
}
