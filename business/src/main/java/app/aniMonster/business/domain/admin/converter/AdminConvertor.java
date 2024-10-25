package app.aniMonster.business.domain.admin.converter;


import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.admin.model.AdminRequest;
import app.aniMonster.business.domain.admin.model.AdminResponse;
import app.aniMonster.postgresql.db.admin.entity.AdminEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Converter
public class AdminConvertor {

    public AdminEntity toEntity(AdminRequest adminRequest) {
        return Optional.ofNullable(adminRequest)
                .map(it -> {
                    return AdminEntity.builder()
                            .adminId(it.getAdmin_id())
                            .adminPassword(it.getAdmin_password())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Admin Request is null"));

    }


    public AdminResponse toResponse(AdminEntity adminEntity) {
        return Optional.ofNullable(adminEntity)
                .map(it -> {
                    return AdminResponse.builder()
                            .id(it.getId())
                            .admin_author(it.getAdminAuthor())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "AdminResponse is null"));
    }
}
