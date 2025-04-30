package com.example.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new info()
                        .titl(" APIs documentation ")
                        .description(" interactive documentation between APIs ")
                        .version(v1.0.4)
                );
    }
}
