package app.aniMonster.business.domain.notification.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.notification.entity.NotificationEntity;
import app.aniMonster.postgresql.db.notification.enums.NotificationCategory;
import app.aniMonster.postgresql.db.notification.enums.NotificationIsActivate;
import app.aniMonster.postgresql.db.notification.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationEntity save(NotificationEntity entity) {
        return notificationRepository.save(entity);
    }

    @Transactional
    public NotificationEntity modifyFaq(NotificationEntity entity) {
        var entityCheck = notificationRepository.findFirstByIdOrderByIdDesc(entity.getId())
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Notification faq with id " + entity.getId() + " not found"));
        if (entity.getTitle() != null) {
            entityCheck.setTitle(entity.getTitle());
        }
        if (entity.getContents() != null) {
            entityCheck.setContents(entity.getContents());
        }
        if (entity.getExplanation() != null) {
            entityCheck.setExplanation(entity.getExplanation());
        }
        if (entity.getPriority() != null) {
            entityCheck.setPriority(entity.getPriority());
        }
        if (entity.getIsActivate() != null) {
            entityCheck.setIsActivate(entity.getIsActivate());
        }

        return entityCheck;
    }

    public List<NotificationEntity> getFaqs() {
        return notificationRepository.findAllByCategoryAndIsActivateOrderByPriorityDesc(NotificationCategory.FAQ, NotificationIsActivate.ACTIVATED);
    }

    public List<NotificationEntity> getFaqsAll() {
        return notificationRepository.findAllByCategoryOrderByPriorityDescIsActivateDesc(NotificationCategory.FAQ);
    }

    @Transactional
    public NotificationEntity modifyNoti(NotificationEntity entity) {
        var entityCheck = notificationRepository.findFirstByIdOrderByIdDesc(entity.getId())
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Notification Noti with id " + entity.getId() + " not found"));
        if (entity.getTitle() != null) {
            entityCheck.setTitle(entity.getTitle());
        }
        if (entity.getContents() != null) {
            entityCheck.setContents(entity.getContents());
        }
        if (entity.getIsActivate() != null) {
            entityCheck.setIsActivate(entity.getIsActivate());
        }
        if (entity.getLimitAt() != null) {
            entityCheck.setLimitAt(entity.getLimitAt());
            if (entityCheck.getLimitAt().isAfter(Instant.now())) {
                entityCheck.setIsActivate(NotificationIsActivate.ACTIVATED);
            }
        }

        return entityCheck;
    }

    @Transactional
    public List<NotificationEntity> getNotis() {
        var notiList = notificationRepository.findAllByCategoryAndIsActivateOrderByIdDesc(NotificationCategory.NOTIFICATION, NotificationIsActivate.ACTIVATED);
        notiList.forEach(it -> {
            if (it.getLimitAt() == null) {
                it.setLimitAt(Instant.now());
                it.setIsActivate(NotificationIsActivate.DEACTIVATED);
            } else if (it.getLimitAt().isBefore(Instant.now())) {
                it.setIsActivate(NotificationIsActivate.DEACTIVATED);
            }
        });
        return notificationRepository.findAllByCategoryAndIsActivateOrderByIdDesc(NotificationCategory.NOTIFICATION, NotificationIsActivate.ACTIVATED);
    }

    @Transactional
    public List<NotificationEntity> getNotisAll() {
        var notiList = notificationRepository.findAllByCategoryOrderByIdDescIsActivateDesc(NotificationCategory.NOTIFICATION);
        notiList.forEach(it -> {
            if (it.getLimitAt() == null) {
                it.setLimitAt(Instant.now());
                it.setIsActivate(NotificationIsActivate.DEACTIVATED);
            } else if (it.getLimitAt().isBefore(Instant.now())) {
                it.setIsActivate(NotificationIsActivate.DEACTIVATED);
            }
        });
        return notificationRepository.findAllByCategoryOrderByIdDescIsActivateDesc(NotificationCategory.NOTIFICATION);
    }
}
