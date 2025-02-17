package app.aniMonster.business.resolver;

import app.aniMonster.business.common.annotation.SocialSession;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
public class SocialSessionResolver implements HandlerMethodArgumentResolver {

    // 특정 파라미터를 지원하는지 확인
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // @SocialSession 어노테이션이 있는지 확인
        var annotation = parameter.hasParameterAnnotation(SocialSession.class);
        // 파라미터 타입이 String인지 확인
        var parameterType = parameter.getParameterType().equals(String.class);

        return (annotation && parameterType);
//        return false;
    }

    // 파라미터 값을 해석
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        var requestContext = Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()
        );

        String socialId = requestContext.getAttribute("socialId", RequestAttributes.SCOPE_REQUEST).toString();
        return socialId;
    }
}
