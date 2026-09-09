package pe.edu.upeu.bomerp;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI bomErpOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Portal Estudiantil API")
                .version("v1")
                .description("API del Portal Estudiantil - Backend LP2"));
    }
}