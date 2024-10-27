package app.aniMonster.business.domain.notification.model;

import app.aniMonster.postgresql.db.notification.enums.NotificationIsActivate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationFaqModifyRequest {

    @NotNull
    private Long id;

    private String title;
    private String contents;
    private String explanation;
    private Integer priority;
    private NotificationIsActivate is_activated;
}
