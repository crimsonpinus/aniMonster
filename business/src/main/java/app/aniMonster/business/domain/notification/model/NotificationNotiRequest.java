package app.aniMonster.business.domain.notification.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationNotiRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    private Instant limit_at;
}
