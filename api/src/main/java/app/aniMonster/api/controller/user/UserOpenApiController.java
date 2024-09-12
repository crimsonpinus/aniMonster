package app.aniMonster.api.controller.user;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.logic.token.controller.model.TokenResponse;
import app.aniMonster.business.domain.user.business.UserBusiness;
import app.aniMonster.business.domain.user.model.UserLoginRequest;
import app.aniMonster.business.domain.user.model.UserRegisterRequset;
import app.aniMonster.business.domain.user.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open/user")
public class UserOpenApiController {

//    private final UserBusiness userBusiness;
//
//    @PostMapping("/register")
//    public Api<UserResponse> register(
//            @Valid
//            @RequestBody Api<UserRegisterRequset> request
//    ){
//        var response = userBusiness.register(request.getBody());
//        return Api.OK(response);
//    }
//
//
//    @PostMapping("/login")
////    public Api<UserResponse> login(
//    public Api<TokenResponse> login(
//            @Valid
//            @RequestBody Api<UserLoginRequest> request
//    ){
//        var response = userBusiness.logIn(request.getBody());
//        return Api.OK(response);
//    }
}
