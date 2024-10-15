package com.blubank.doctorappointment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("doctor-appointment API")
                        .description("This is doctor-appointment App Rest API Document")
                        .contact(new Contact()
                                .name("API support")
                                .email("milad.lahouti93@gmail.com")
                        )
                        .version("1.0.0")
                );
    }

}