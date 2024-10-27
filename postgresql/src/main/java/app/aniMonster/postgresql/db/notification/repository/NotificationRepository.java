package app.aniMonster.postgresql.db.notification.repository;

import app.aniMonster.postgresql.db.notification.entity.NotificationEntity;
import app.aniMonster.postgresql.db.notification.enums.NotificationCategory;
import app.aniMonster.postgresql.db.notification.enums.NotificationIsActivate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByCategoryAndIsActivateOrderByPriorityDesc(NotificationCategory category, NotificationIsActivate isActivate);
    List<NotificationEntity> findAllByCategoryOrderByPriorityDescIsActivateDesc(NotificationCategory category);
    List<NotificationEntity> findAllByCategoryAndIsActivateOrderByIdDesc(NotificationCategory category, NotificationIsActivate isActivate);
    List<NotificationEntity> findAllByCategoryOrderByIdDescIsActivateDesc(NotificationCategory category);
    Optional<NotificationEntity> findFirstByIdOrderByIdDesc(Long id);
}
