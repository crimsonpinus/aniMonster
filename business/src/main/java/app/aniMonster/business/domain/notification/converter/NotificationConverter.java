package app.aniMonster.business.domain.notification.converter;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.notification.model.*;
import app.aniMonster.postgresql.db.notification.entity.NotificationEntity;
import app.aniMonster.postgresql.db.notification.enums.NotificationCategory;
import app.aniMonster.postgresql.db.notification.enums.NotificationIsActivate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotificationConverter {

    public NotificationEntity toEntity(NotificationFaqRequest faqRequest) {
        return Optional.ofNullable(faqRequest)
                .map(it -> {
                    return NotificationEntity.builder()
                            .title(it.getTitle())
                            .contents(it.getContents())
                            .explanation(it.getExplanation())
                            .priority(it.getPriority())
                            .category(NotificationCategory.FAQ)
                            .isActivate(NotificationIsActivate.ACTIVATED)
                            .registeredAt(Instant.now())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Failed to convert faq request"));
    }

    public NotificationEntity toEntity(NotificationFaqModifyRequest faqModifyRequest) {
        return Optional.ofNullable(faqModifyRequest)
                .map(it -> {
                    return NotificationEntity.builder()
                            .id(it.getId())
                            .title(it.getTitle())
                            .contents(it.getContents())
                            .explanation(it.getExplanation())
                            .priority(it.getPriority())
                            .isActivate(it.getIs_activated())
                            .category(NotificationCategory.FAQ)
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Failed to convert faq modify request"));
    }

    public NotificationEntity toEntity(NotificationNotiRequest notiRequest) {
        return Optional.ofNullable(notiRequest)
                .map(it -> {
                    return NotificationEntity.builder()
                            .title(it.getTitle())
                            .contents(it.getContents())
                            .limitAt(it.getLimit_at())
                            .category(NotificationCategory.NOTIFICATION)
                            .isActivate(NotificationIsActivate.ACTIVATED)
                            .registeredAt(Instant.now())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Failed to convert noti request"));
    }

    public NotificationEntity toEntity(NotificationNotiModifyRequest notiModifyRequest) {
        return Optional.ofNullable(notiModifyRequest)
                .map(it -> {
                    return NotificationEntity.builder()
                            .id(it.getId())
                            .title(it.getTitle())
                            .contents(it.getContents())
                            .isActivate(it.getIs_activated())
                            .limitAt(it.getLimit_at())
                            .category(NotificationCategory.NOTIFICATION)
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Failed to convert notificate request"));
    }

    public NotificationFaqResponse toFaqResponse(NotificationEntity notificationEntity) {
        return Optional.ofNullable(notificationEntity)
                .map(it -> {
                    return NotificationFaqResponse.builder()
                            .id(it.getId())
                            .title(it.getTitle())
                            .contents(it.getContents())
                            .explanation(it.getExplanation())
                            .priority(it.getPriority())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Failed to convert notification faq entity"));

    }

    public NotificationNotiResponse toNotiResponse(NotificationEntity notificationEntity) {
        return Optional.ofNullable(notificationEntity)
                .map(it -> {
                    return NotificationNotiResponse.builder()
                            .id(it.getId())
                            .title(it.getTitle())
                            .contents(it.getContents())
                            .limit_at(it.getLimitAt())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Failed to convert notification noti entity"));
    }
}
