package app.aniMonster.api.controller.social;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.common.annotation.SocialSession;
import app.aniMonster.business.domain.social.business.SocialBusiness;
import app.aniMonster.business.domain.social.model.SocialResponse;
import app.aniMonster.business.domain.social.model.SocialUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/social")
public class SocialApiController {

    private final SocialBusiness socialBusiness;

    private final Social social;


    @Operation(
            summary = "social 관련 값 수정",
            description = """
                    **하위 값은  아래의 값만 허용\n
                            - status = UNREGISTERED("해지"),REGISTERED("등록")
                            - is_adult = UNREGISTERED("해지"),REGISTERED("등록")
                    *** 주의 status UNREGISTERED 일경우 검색 안됨 아이디 비활성화 ***
                    """
    )
    @PostMapping("/update")
    public Api<SocialResponse> update(
            @Valid
            @RequestBody Api<SocialUpdateRequest> request
    ) {

        var response = socialBusiness.update(request.getBody(), social.getSocialId());
        return Api.OK(response);
    }

    @Operation(
            summary = "access-token으로 부터 윺저 정보 얻어옴",
            description = """
                        
                        """
    )
    @PostMapping("/showMe")
    public Api<SocialResponse> showMe() {

        var response = socialBusiness.showMe(social.getSocialId());

        return Api.OK(response);
    }

    public String getSocialId() {
        var requestContext = Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()
        );
        var socialId = requestContext.getAttribute("socialId", RequestAttributes.SCOPE_REQUEST);
        socialId = Objects.requireNonNull(socialId);
        return socialId.toString();
    }
}
