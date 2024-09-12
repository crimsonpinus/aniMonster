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

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        var annotation = parameter.hasParameterAnnotation(SocialSession.class);
        var parameterType = parameter.getParameterType().equals(String.class);

        return (annotation && parameterType);
//        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        var requestContext = Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()
        );
        String socialId = requestContext.getAttribute("socialId", RequestAttributes.SCOPE_REQUEST).toString();
        return socialId;
    }
}
