package org.aidiary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // API 경로에 대해 CORS 허용
                        .allowedOrigins("http://localhost:5173", "http://localhost:4173")
                        .allowedMethods("*")  // 모든 메서드 허용 (GET, POST, PUT, DELETE 등)
                        .allowedHeaders("*")
                        .allowCredentials(true);  // 쿠키 허용
            }
        };
    }
}
