package com.project.shopapp.confiuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${openapi.title}") String title,
            @Value("${openapi.version}") String version,
            @Value("${openapi.description}") String description,
            @Value("${openapi.serverUrl}") String serverUrl,
            @Value("${openapi.serverName}") String serverName
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description)
                        .license(new License().name(version)))
                .servers(List.of(new Server().url(serverUrl).description(serverName)));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("product-service")
                .packagesToScan("com.project.shopapp")
                .build();
    }
}