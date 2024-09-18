package app.aniMonster.api.controller.social.profile;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.common.annotation.SocialSession;
import app.aniMonster.business.domain.social.profile.business.SocialProfileBusiness;
import app.aniMonster.business.domain.social.profile.model.SocialProfileRequest;
import app.aniMonster.business.domain.social.profile.model.SocialProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/social/socialprofile")
public class SocialProfileApiController {

    private final SocialProfileBusiness socialProfileBusiness;

    @Operation(
            summary = "유저 세부정보 업데이트",
            description = """
                아래 정보충 업데이트 할 값만 넘겨주면 관련 값 업데이트\n
                **하위 값은  아래의 값만 허용
                - gender = MALE("남성"), FEMALE("여성"), NOT_SET("미설정")
                - service_terms = PERMIT("승인"), REFUSE("거절")
                - privacy_term = PERMIT("승인"), REFUSE("거절")
                - marketing_opt_in_term = PERMIT("승인"), REFUSE("거절")
                - third_party_term = PERMIT("승인"), REFUSE("거절")
                """
    )
    @PostMapping("/update")
    public Api<SocialProfileResponse> update(
            @Valid
            @RequestBody Api<SocialProfileRequest> request
    ) {
        var response = socialProfileBusiness.update(request.getBody());
        return Api.OK(response);
    }

    @Operation(
            summary = "access-token을 이용하아 유저 세부 정보 획득",
            description = "social_id는 swagger특징으로 값을 요청하나 아무 값이나 넣으면 됨 (세션에서 주는값을 받아야하는 값으로 swagger에서 착각하여 발생) "
    )
    @PostMapping("/showMe")
    public Api<SocialProfileResponse> showMe(
            @SocialSession String social_id
    ){
        var response = socialProfileBusiness.showMe(social_id);
        return Api.OK(response);
    }

}
