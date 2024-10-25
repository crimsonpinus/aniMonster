package app.aniMonster.postgresql.db.admin.repository;

import app.aniMonster.postgresql.db.admin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
    Optional<AdminEntity> findFirstByAdminIdAndAdminPassword(String adminId, String password);
}
