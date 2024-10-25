package app.aniMonster.business.domain.admin.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.admin.entity.AdminEntity;
import app.aniMonster.postgresql.db.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminEntity login(AdminEntity adminEntity) {
        return adminRepository.findFirstByAdminIdAndAdminPassword(adminEntity.getAdminId(), adminEntity.getAdminPassword())
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Admin Login Failed"));
    }
}
