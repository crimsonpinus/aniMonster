package app.aniMonster.api.config.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String API_NAME = "AniMonster";
    private static final String API_VERSION = "0.01";
    private static final String API_DESCRIPTION = """
            /open/** -> token 불필요\n
            /api/** -> token 필수\n
            social_id 값 요구하는 api들은 token에서 받아옴 swagger가 오인식 하여 표출되는 경우(아무 문자나 적고 테스트)\n
            ***개발시에는 바로 호출하여 사용 가능***
            """;
    @Bean
    public OpenAPI OpenAPIConfig() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title(API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION);
    }
}
