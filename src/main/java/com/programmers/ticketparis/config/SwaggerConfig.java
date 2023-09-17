package com.programmers.ticketparis.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
            .title("Springdoc 테스트")
            .description("Springdoc을 사용한 Swagger UI 테스트")
            .version("1.0.0");
    }

//    @Bean
//    public Docket apiDocket() {
//        return new Docket(DocumentationType.OAS_30)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.programmers.ticketparis.controller"))
//            .paths(PathSelectors.any())
//            .build();
//    }
}
