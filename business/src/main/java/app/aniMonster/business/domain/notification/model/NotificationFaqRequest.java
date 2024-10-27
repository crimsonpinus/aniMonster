package app.aniMonster.business.domain.notification.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationFaqRequest {

    @NotNull
    private String title;

    @NotNull
    private String contents;

    private String explanation;

    @NotNull
    private Integer priority;
}
