package app.aniMonster.business.domain.notification.model;

import app.aniMonster.postgresql.db.notification.enums.NotificationCategory;
import app.aniMonster.postgresql.db.notification.enums.NotificationIsActivate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class NotificationNotiResponse {

    private Long id;
    private String title;
    private String contents;
    private Instant limit_at;
}
