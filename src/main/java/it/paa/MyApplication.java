package it.paa;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@ApplicationPath("/v1")
@OpenAPIDefinition(
        info = @Info(title = "Api Rest Mosem",
                description = "",
                version = "1.0",
                contact = @Contact(name = "P.A. Advice", email = "it@paa.it", url = "https://www.paa.it"))
        , security = {@SecurityRequirement(name = "bearerAuth")},
        components = @Components(securitySchemes = @SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "bearer", apiKeyName = "Keycloak", description = "keycloak bearer", securitySchemeName = "bearerAuth"))
)
public class MyApplication extends Application {
}
