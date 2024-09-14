package app.aniMonster.api.controller.social;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.common.annotation.SocialSession;
import app.aniMonster.business.domain.social.business.SocialBusiness;
import app.aniMonster.business.domain.social.model.SocialRefreshRequest;
import app.aniMonster.business.domain.social.model.SocialResponse;
import app.aniMonster.business.logic.jwt.model.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/social")
public class SocialApiController {

    private final SocialBusiness socialBusiness;

    @Operation(
            summary = "access-token으로 부터 윺저 정보 얻어옴",
            description = "social_id는 swagger특징으로 값을 요청하나 아무 값이나 넣으면 됨 (세션에서 주는값을 받아야하는 값으로 swagger에서 착각하여 발생) "
    )
    @PostMapping("/showMe")
    public Api<SocialResponse> showMe(

            @SocialSession String social_id
    ) {

        var response = socialBusiness.showMe(social_id);

        return Api.OK(response);
    }

    @Operation(
            summary = "refresh-token 갱신",
            description = "access-token으로 갱신 불가 로직 추가 refresh로만 발급 가능"
    )
    @PostMapping("/refresh")
    public Api<JwtResponse> refresh(
            @Valid
            @RequestBody
            Api<SocialRefreshRequest> request
    ) {
        var response = socialBusiness.refreshToken(request.getBody());

        return Api.OK(response);
    }
}
