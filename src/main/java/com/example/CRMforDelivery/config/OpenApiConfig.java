package com.example.CRMforDelivery.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "CRM API",
                description = "API CRM для доставки",
                version = "1.0.0"
        )
)
public class OpenApiConfig {
}
