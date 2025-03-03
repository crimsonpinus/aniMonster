package app.aniMonster.api.interceptor;

import app.aniMonster.api.common.error.ErrorCode;
import app.aniMonster.api.common.error.TokenErrorCode;
import app.aniMonster.api.common.exception.ApiException;
import app.aniMonster.business.domain.social.business.SocialBusiness;
import app.aniMonster.business.logic.jwt.business.JwtBusiness;
import app.aniMonster.business.logic.token.business.TokenBusiness;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

//public class AuthorizationInterceptor{
//}
@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final JwtBusiness jwtBusiness;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        log.info("AuthorizationInterceptor preHandle", request.getRequestURI()) ;


        //web, chrome 의경우 get, post options = pass
        //크롬 처리
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        //javascript, html, img 같은 resource 요청 처리 = pass
        if (handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        // TODO header 검증

        var accessToken = request.getHeader("authorization-token");
        if (accessToken == null || accessToken.isEmpty()) {
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        // 토큰 검증
        var idMap = jwtBusiness.validateToken(accessToken);
        if (idMap != null) {
            // 요청 컨텍스트에 사용자 정보 저장
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());

            // socialId와 adminId를 요청 스코프에 저장
            // 이 정보는 나중에 다음과 같이 접근할 수 있음:
            // var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            // var socialId = requestContext.getAttribute("socialId", RequestAttributes.SCOPE_REQUEST);
            requestContext.setAttribute("socialId" , idMap.get("id"), RequestAttributes.SCOPE_REQUEST);
            requestContext.setAttribute("adminId" , idMap.get("adminId"), RequestAttributes.SCOPE_REQUEST);
            return true;
        }

        throw new ApiException(ErrorCode.BAD_REQUEST, "토큰 인증 실패");
        /* return false; */
    }
}
