package app.aniMonster.api.config.swagger;


 import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class OpenApiConfig {

        private static final String API_NAME = "AniMonster";
    private static final String API_VERSION = "1.0";
    private static final String API_DESCRIPTION = "AniMonster API";
    @Bean
    public OpenAPI OpenAPIConfig() {
        return new OpenAPI();
    }

}
