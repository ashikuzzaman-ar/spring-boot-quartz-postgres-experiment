package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("spring-boot-quartz-postgres-experiment")
                .version("0.0.1-SNAPSHOT")
                .description("Demo project for experimenting quartz in jdbc job store with postgresql in spring boot")
                .contact(new Contact()
                        .name("Md. Ashikuzzaman")
                        .email("ashikuzzaman.ar@gmail.com")
                        .url("https://ashikuzzaman.dev"))
                .license(new License()
                        .url("https://ashikuzzaman.dev")));
    }
}