package com.shivam.expensetracker.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI expenseTrackerOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Smart Expense Tracker API")
                                .description("REST API to manage personal expenses")
                                .version("1.0")
                );
    }
}
