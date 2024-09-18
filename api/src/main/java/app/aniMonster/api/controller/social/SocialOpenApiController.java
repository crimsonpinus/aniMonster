package app.aniMonster.api.controller.social;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.domain.social.business.SocialBusiness;
import app.aniMonster.business.domain.social.model.SocialRefreshRequest;
import app.aniMonster.business.domain.social.model.SocialSignRequest;
import app.aniMonster.business.logic.jwt.model.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open/social")
public class SocialOpenApiController {

    private final SocialBusiness socialBusiness;

    @Operation(summary = "유저 생성 및 로그인", description = "이메일과 프로바이더로 검색하여 업으면 생성 및 로그인 있으면 로그인")
    @PostMapping("/sign")
    public Api<JwtResponse> sign(
            @Valid
            @RequestBody Api<SocialSignRequest> request
    ){
        var response = socialBusiness.sign(request.getBody());
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
