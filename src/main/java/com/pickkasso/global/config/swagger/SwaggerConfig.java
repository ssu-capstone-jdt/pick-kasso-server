package com.pickkasso.global.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI SSUPlectorAPI() {
        Info info =
                new Info()
                        .title("Pick kasso API")
                        .description("Pick kasso API 명세서")
                        .version("1.0.0");

        String jwtSchemeName = "JWT TOKEN";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        Components components =
                new Components()
                        .addSecuritySchemes(
                                jwtSchemeName,
                                new SecurityScheme()
                                        .name(jwtSchemeName)
                                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                                        .scheme("bearer")
                                        .bearerFormat("JWT"));

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
