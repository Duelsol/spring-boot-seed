package me.duelsol.springbootseed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 冯奕骅
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public Docket docket(Environment env) {
        Profiles profile = Profiles.of("dev", "test");
        boolean flag = env.acceptsProfiles(profile);
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.duelsol.springbootseed.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Seed API")
                .description("This is a sample Spring Boot RESTful service using OpenAPI.")
                .version("1.0.0")
                .build();
    }

}
