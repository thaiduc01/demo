package com.example.demochauluc.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    // Tạo group dành cho resource (/api/...)
    @Bean
    GroupedOpenApi resourceApi() {
        return GroupedOpenApi.builder()
                .group("Resource endpoint")
                .pathsToMatch("/api/**")
                .packagesToScan("com.example.demochauluc.controller")
                .addOpenApiCustomiser(apiResourceCustomizer())
                .build();
    }

    // Swagger - ui / openapi authorization header with jwt
    @Bean
    OpenApiCustomiser apiResourceCustomizer() {
        return openApi -> {
            openApi.addSecurityItem(new SecurityRequirement().addList("token"));
            openApi.getComponents().addSecuritySchemes("token",
                    new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("Bearer")
                                        .bearerFormat("jwt")
                                        .name("Authorization"));
        };
    }

    // Group dành cho phần đăng ký, đăng nhập,...
    @Bean
    GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Auth endpoint")
                .pathsToMatch("/manage/**")
                .packagesToScan("com.example.demochauluc.controller.auth")
                .build();
    }
}
