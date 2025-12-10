package org.pacos.plugin.skeleton.config.openApi;

import java.util.List;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.pacos.plugin.skeleton.backend.api.ApiConst.API_VERSION;
import static org.pacos.plugin.skeleton.backend.api.ApiConst.PLUGIN_NAME;

/**
 * Base configuration for openApi documentation. Available only on dev mode
 * This configuration will not work inside a pacos when the plugin is managed by a pacos management tool - the
 * documentation will be not prepared automatically - must be prepared during build and stored as a physical file.
 */
@Profile(value = "dev")
@Configuration
public class OpenAPIConfig {

    @Bean
    @Primary
    public OpenAPI skeletonOpenAPI() {
        Server server = new Server();
        server.setUrl("/");
        server.setDescription("Current instance");
        return new OpenAPI()
                .info(new Info().title("Skeleton API")
                        .version(API_VERSION)
                        .description("Skeleton plugin API"))
                //Api token is required by Coupler in production mode
                .components(new Components()
                        .addSecuritySchemes("apiKeyAuth", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("Enter API token")
                        )
                )
                .servers(List.of(server))
                .addSecurityItem(new SecurityRequirement().addList("apiKeyAuth"));
    }

    @Bean
    public GroupedOpenApi skeletonGroupApi() {
        return GroupedOpenApi.builder()
                .packagesToScan("org.pacos.plugin.skeleton.backend.api")
                .group(PLUGIN_NAME)
                .build();
    }
}