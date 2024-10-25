package app.aniMonster.business.domain.admin.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.admin.converter.AdminConvertor;
import app.aniMonster.business.domain.admin.model.AdminRequest;
import app.aniMonster.business.domain.admin.service.AdminService;
import app.aniMonster.business.logic.jwt.business.JwtBusiness;
import app.aniMonster.business.logic.jwt.model.JwtAdminResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class AdminBusiness {

    private final AdminConvertor adminConvertor;
    private final AdminService adminService;
    private final JwtBusiness jwtBusiness;

    public JwtAdminResponse login(AdminRequest adminRequest) {
        var entity = adminConvertor.toEntity(adminRequest);
        var usedEntity = adminService.login(entity);
        var response = adminConvertor.toResponse(usedEntity);

        return jwtBusiness.issueToken(response);
    }
}
