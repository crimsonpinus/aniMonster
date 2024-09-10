package app.aniMonster.business.domain.user.business;


import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.logic.token.business.TokenBusiness;
import app.aniMonster.business.logic.token.controller.model.TokenResponse;
import app.aniMonster.business.domain.user.converter.UserConverter;
import app.aniMonster.business.domain.user.model.UserLoginRequest;
import app.aniMonster.business.domain.user.model.UserRegisterRequset;
import app.aniMonster.business.domain.user.model.UserResponse;
import app.aniMonster.business.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Business
@Slf4j
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    public UserResponse register(UserRegisterRequset request) {

        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
    }


//    public UserResponse logIn(UserLoginRequest request) {
    public TokenResponse logIn(UserLoginRequest request) {

        var entity = userConverter.toEntity(request);
        var newEntity = userService.logIn(entity);
        var response = userConverter.toResponse(newEntity);
//        return response;


        var tokenResponse = tokenBusiness.issueToken(response);
        return tokenResponse;

    }

    public UserResponse me(Long userId) {
        var userEntity = userService.getUserWithId(userId);
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
