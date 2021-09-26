package code.challenge.bff;

import code.challenge.bff.api.SubscriptionController;
import code.challenge.bff.dtos.SubscriptionInputDto;
import code.challenge.bff.dtos.SubscriptionOutputDto;
import code.challenge.bff.services.SubscribeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriptionControllerTest {

    @Autowired
    private SubscriptionController controller;

    @MockBean
    private SubscribeService service;

    @Test
    public void contextLoadTest() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testSubscriptionInputValidation() {
        SubscriptionOutputDto outputDto = SubscriptionOutputDto.builder().id("idTest").build();

        SubscriptionInputDto dto = new SubscriptionInputDto();

        when(service.subscribe(any())).thenReturn(outputDto);
        ResponseEntity<SubscriptionOutputDto> responseEntity = controller.subscribe(dto);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(SubscriptionOutputDto.class);
        assertThat(responseEntity.getBody().getId()).isEqualTo(outputDto.getId());
    }

}
