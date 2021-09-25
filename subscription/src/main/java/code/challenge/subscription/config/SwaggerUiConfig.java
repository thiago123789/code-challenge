package code.challenge.subscription.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerUiConfig {
    @Bean
    public Docket subscriptionApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("subscription-api")
                .apiInfo(apiInfo())
                .select()
                .paths(regex(".*/api/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API")
                .description("Subscription API")
                .termsOfServiceUrl("")
                .contact(new Contact("Company", "", ""))
                .license("Apache License Version 2.0")
                .licenseUrl("")
                .version("1.0")
                .build();
    }
}
