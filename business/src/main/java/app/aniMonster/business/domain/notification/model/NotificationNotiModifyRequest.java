package app.aniMonster.business.domain.notification.model;

import app.aniMonster.postgresql.db.notification.enums.NotificationIsActivate;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationNotiModifyRequest {

    @NotBlank
    private Long id;
    private String title;
    private String contents;
    private NotificationIsActivate is_activated;
    private Instant limit_at;
}
