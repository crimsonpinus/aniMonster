package app.aniMonster.api.controller.social;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.api.common.api.Result;
import app.aniMonster.business.domain.social.business.SocialBusiness;
import app.aniMonster.business.domain.social.model.SocialRefreshRequest;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;


@RequiredArgsConstructor
@Component
public class Social {

    private final SocialBusiness socialBusiness;

    public String getSocialId() {
        var requestContext = Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()
        );
        var socialId = requestContext.getAttribute("socialId", RequestAttributes.SCOPE_REQUEST);
        socialId = Objects.requireNonNull(socialId);
        return socialId.toString();
    }

    public <T> Api<T> withToken(T data, Result result) {
        var api = Api.OK(data);

        if (result.getResult_code() == 299 && result.getResult_message().equals("REFRESH_TOKEN")) {
            var token = socialBusiness.refreshToken(SocialRefreshRequest.builder()
                    .refresh_token(result.getResult_description())
                    .build()
            );

            var tokenResult = Result.builder()
                    .result_code(201)
                    .result_message(token.getAccess_token())
                    .result_description(token.getRefresh_token())
                    .build();
            api.setResult(tokenResult);
        }

        return api;
    }
}