package me.duelsol.springbootseed.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 冯奕骅
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info();
        info.setTitle("Spring Boot Seed API");
        info.setDescription("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.");
        info.setVersion("1.0.0");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
