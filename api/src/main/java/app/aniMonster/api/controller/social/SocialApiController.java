package app.aniMonster.api.controller.social;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.common.annotation.SocialSession;
import app.aniMonster.business.domain.social.business.SocialBusiness;
import app.aniMonster.business.domain.social.model.SocialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/social")
public class SocialApiController {

    private final SocialBusiness socialBusiness;

    @PostMapping("/findMe")
    public Api<SocialResponse> findMe(
            @SocialSession String socialId
    ) {

        var response = socialBusiness.findMe(socialId);

        return Api.OK(response);
    }
}
