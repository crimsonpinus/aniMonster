package app.aniMonster.api.controller.user;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.domain.user.business.UsersPreRegBusiness;
import app.aniMonster.business.domain.user.model.UsersPreRegRequest;
import app.aniMonster.business.domain.user.model.UsersPreRegResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open/preuser")
public class UsersPreRegOpenApiController {

    private final UsersPreRegBusiness usersPreRegBusiness;

//    @PostMapping("/register")
//    public Api<UsersPreRegResponse> register(
//            @Valid
//            @RequestBody Api<UsersPreRegRequest> request
//    ){
//        var response = usersPreRegBusiness.register(request.getBody());
//        return Api.OK(response);
//    }
//
//    @PostMapping("/mobile")
//    public Api<UsersPreRegResponse> mobile(
//            @Valid
//            @RequestBody Api<String> request
//    ){
//        var response = usersPreRegBusiness.findByMobile(request.getBody());
//        return Api.OK(response);
//    }
}
