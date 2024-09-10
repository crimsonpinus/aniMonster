package app.aniMonster.api.config.web;

import app.aniMonster.api.interceptor.AuthorizationInterceptor;
import app.aniMonster.business.resolver.UserSessionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;

    private final UserSessionResolver userSessionResolver;

    //인증하지 않을 url
    private List<String> OPEN_API = List.of(
            "/open/**"
    ) ;

    //기본 url
    private List<String> DEFAULT_EXCLUDES = List.of(
            "/",
            "favicon.ico",
            "/error"
    );

    //swagger
    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .excludePathPatterns(OPEN_API)
                .excludePathPatterns(DEFAULT_EXCLUDES)
                .excludePathPatterns(SWAGGER)
        ;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userSessionResolver);
    }
}
