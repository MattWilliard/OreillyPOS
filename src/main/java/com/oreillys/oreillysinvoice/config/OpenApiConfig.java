package com.oreillys.oreillysinvoice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class OpenApiConfig {

        @Bean
        public OpenAPI myOpenAPI() {

            Contact contact = new Contact();
            contact.setEmail("williard.matt@gmail.com");
            contact.setName("Matt Williard");

            Info info = new Info()
                    .title("OReilly Auto Parts Invoice API")
                    .version("1.0")
                    .contact(contact)
                    .description("This API retrieves customer invoices by customer id.");

            return new OpenAPI().info(info);
        }
}
