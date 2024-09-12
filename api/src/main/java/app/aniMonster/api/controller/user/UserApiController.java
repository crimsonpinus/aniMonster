package app.aniMonster.api.controller.user;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.common.annotation.UserSession;
import app.aniMonster.business.domain.user.business.UserBusiness;
import app.aniMonster.business.domain.user.model.User;
import app.aniMonster.business.domain.user.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

//    private final UserBusiness userBusiness;
//
//    @GetMapping("/me")
//    public Api<UserResponse> me(
//            @UserSession User user
//    ){
//
//        var response = userBusiness.me(user.getId());
//
//        return Api.OK(response);
//    }
}
