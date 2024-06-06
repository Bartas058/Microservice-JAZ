package pl.pjatk.kprusak.swagger;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Movie Service API",
                version = "1.0.0",
                description = "This Spring Boot Application serves as a client interface for the Rental Service API.",
                contact = @Contact(
                        name = "Kacper Prusak",
                        email = "kacper.prusak@pjwstk.edu.pl"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Movie Service API Documentation",
                url = "https://github.com/Bartas058/RentalService"
        )
)

public class OpenApiConfiguration {
    // Configuration Class
}
