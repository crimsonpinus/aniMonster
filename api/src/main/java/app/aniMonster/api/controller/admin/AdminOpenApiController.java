package app.aniMonster.api.controller.admin;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.domain.admin.business.AdminBusiness;
import app.aniMonster.business.domain.admin.model.AdminRequest;
import app.aniMonster.business.logic.jwt.model.JwtAdminResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open/admin")
public class AdminOpenApiController {

    private final AdminBusiness adminBusiness;

    @Operation(
            summary = "***관리자*** 관리자 로그인"
    )
    @PostMapping("/login")
    public Api<JwtAdminResponse> login(
            @Valid
            @RequestBody Api<AdminRequest> request
    ) {
        var response = adminBusiness.login(request.getBody());
        return Api.OK(response);
    }
}
