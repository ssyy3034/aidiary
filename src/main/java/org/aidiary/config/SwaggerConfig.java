package org.aidiary.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aidiary API")
                        .description("Aidiary 프로젝트의 API 명세서")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Aidiary Support")
                                .email("support@aidiary.com")
                        )
                );
    }
}
