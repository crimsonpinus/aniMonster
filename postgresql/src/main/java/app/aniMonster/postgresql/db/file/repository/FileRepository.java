package app.aniMonster.postgresql.db.file.repository;

import app.aniMonster.postgresql.db.file.entity.FileEntity;
import app.aniMonster.postgresql.db.file.enums.FileIsActivate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    Optional<FileEntity> findFirstByFileIdAndIsActivateOrderByIdDesc(String fileId, FileIsActivate isActivate);
    Optional<FileEntity> findFirstByFileIdOrderByIdDesc(String fileId);
}
