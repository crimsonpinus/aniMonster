package app.aniMonster.api.controller.social;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.domain.social.business.SocialBusiness;
import app.aniMonster.business.domain.social.model.SocialResponse;
import app.aniMonster.business.domain.social.model.SocialSignRequest;
import app.aniMonster.business.logic.jwt.model.JwtResponse;
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

    @PostMapping("/sign")
    public Api<JwtResponse> sign(
            @Valid
            @RequestBody Api<SocialSignRequest> request
    ){
        var response = socialBusiness.sign(request.getBody());
        return Api.OK(response);
    }
}
