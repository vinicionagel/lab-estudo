package br.com.labestudo.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${application.openapi.security.tokenUrl}")
    private String tokenUrl;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("lab-estudo")
                        .version("0.0.1-SNAPSHOT"))
                .externalDocs(new ExternalDocumentation()
                        .description("Projeto do Laboratório de Estudo")
                        .url("https://github.com/emersontorresamorim/lab-estudo"))
                .components(new Components().addSecuritySchemes("labestudo-schecma",
                            new SecurityScheme()
                                    .type(SecurityScheme.Type.OAUTH2)
                                    .flows(new OAuthFlows()
                                            .password(new OAuthFlow()
                                                    .tokenUrl(tokenUrl)
                                                    .scopes(new Scopes()
                                                            .addString("READ", "Escopo de leitura")
                                                            .addString("WRITE", "Escopo de escrita"))))));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("lab-estudo-public")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("labestudo-schecma"));
                    return operation;
                })
                .pathsToMatch("/**")
                .build();
    }

}
