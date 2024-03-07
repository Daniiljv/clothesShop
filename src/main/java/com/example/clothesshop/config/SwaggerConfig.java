package com.example.clothesshop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configuration(){
        return new OpenAPI()
                .info(new Info().title("Clothes Shop")
                        .description("One of the Pet Projects")
                        .version("1.0.0")
                        .contact(new Contact().name("Daniil")));


    }
}
